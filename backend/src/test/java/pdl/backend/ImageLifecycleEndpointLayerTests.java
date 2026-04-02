package pdl.backend;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import pdl.backend.gallery.core.ImageLifecycleEndpointLayer;
import pdl.backend.gallery.core.ImageStorageLayer;
import pdl.backend.gallery.tags.ImageQueryRepoLayer;
import pdl.backend.gallery.processing.UploadStatusTrackerLayer;
import pdl.backend.auth.UserAccountRepoLayer;

@WebMvcTest(ImageLifecycleEndpointLayer.class)
public class ImageLifecycleEndpointLayerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ImageStorageLayer storageService;

    @MockitoBean
    private ImageQueryRepoLayer queryRepo;

    @MockitoBean
    private UploadStatusTrackerLayer statusNotifier;

    // These two mocks are required to satisfy Application.java (dataSeeder) 
    // and SecurityLayer.java (userDetailsService) during the sliced test.
    @MockitoBean
    private UserAccountRepoLayer userRepository;

    @MockitoBean
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("Validates the 415 response when payload violates content constraints")
    public void shouldReturnUnsupportedMediaType() throws Exception {
        mockMvc.perform(post("/images")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"filename\": \"test.jpg\", \"data\": \"base64string\"}"))
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    @DisplayName("Validates the 202 Accepted response for valid multipart uploads")
    public void shouldReturnAcceptedWhenUploadingImage() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
            "file", 
            "test-image.jpg", 
            "image/jpeg", 
            "dummy image content".getBytes()
        );

        mockMvc.perform(multipart("/images")
                .file(file))
                .andExpect(status().isAccepted());

        verify(storageService).processAndSaveImage(any(), eq(true));
    }
}