package pdl.backend;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImageController {

  @Autowired
  private ObjectMapper mapper;

  private final ImageDao imageDao;

  public ImageController(ImageDao imageDao) {
    this.imageDao = imageDao;
  }

  @RequestMapping(value = "/images/{id}", method = RequestMethod.GET)
  public ResponseEntity<?> getImage(@PathVariable long id) {
    Optional<Image> image = imageDao.retrieve(id);
    if (image.isPresent()) {
      org.springframework.http.MediaType mediaType = org.springframework.http.MediaTypeFactory.getMediaType(image.get().getName())
          .orElse(org.springframework.http.MediaType.IMAGE_JPEG);
      return ResponseEntity.ok()
          .contentType(mediaType)
          .body(image.get().getData());
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @RequestMapping(value = "/images/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteImage(@PathVariable long id) {
    Optional<Image> image = imageDao.retrieve(id);
    if (image.isPresent()) {
      imageDao.delete(image.get());
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @RequestMapping(value = "/images", method = RequestMethod.POST)
  public ResponseEntity<?> addImage(@RequestParam("file") MultipartFile file) {
    if (file.getContentType() == null || !file.getContentType().startsWith("image/")) {
      return new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
    try {
      Image img = new Image(file.getOriginalFilename(), file.getBytes());
      imageDao.create(img);
      return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (IOException e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @RequestMapping(value = "/images", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
  @ResponseBody
  public ArrayNode getImageList() {
    ArrayNode nodes = mapper.createArrayNode();
    List<Image> images = imageDao.retrieveAll();
    for (Image img : images) {
      ObjectNode n = mapper.createObjectNode();
      n.put("id", img.getId());
      n.put("name", img.getName());
      nodes.add(n);
    }
    return nodes;
  }
}