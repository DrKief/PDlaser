package pdl.backend;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("images")
public class Image {

  @Id
  private Long id;

  @Column("filename")
  private String name;

  private String format;
  private int width;
  private int height;
  private String hash;

  @Column("extraction_status")
  private String extractionStatus;

  // @Transient ensures Spring Data doesn't try to save the byte array to the DB
  @Transient
  private byte[] data;

  public Image() {
    // Required by Spring Data
  }

  public Image(final String name, final byte[] data) {
    this.name = name;
    this.data = data;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public long getId() {
    return (id != null) ? id : 0;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public byte[] getData() {
    return data;
  }

  public void setData(byte[] data) {
    this.data = data;
  }

  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }

  public String getFormat() {
    return format;
  }

  public void setFormat(String format) {
    this.format = format;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public String getExtractionStatus() {
    return extractionStatus;
  }

  public void setExtractionStatus(String extractionStatus) {
    this.extractionStatus = extractionStatus;
  }
}
