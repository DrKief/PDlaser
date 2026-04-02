package pdl.backend.gallery.core;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("images")
public class ImageRecordLayer {

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

  @Column("user_id")
  private Long userId;

  @Column("is_private")
  private boolean isPrivate;

  @Transient
  private byte[] data;

  public ImageRecordLayer() {}

  public ImageRecordLayer(final String name, final byte[] data) {
    this.name = name;
    this.data = data;
  }

  public void setId(Long id) { this.id = id; }
  public long getId() { return (id != null) ? id : 0; }
  public String getName() { return name; }
  public void setName(final String name) { this.name = name; }
  public byte[] getData() { return data; }
  public void setData(byte[] data) { this.data = data; }
  public String getHash() { return hash; }
  public void setHash(String hash) { this.hash = hash; }
  public String getFormat() { return format; }
  public void setFormat(String format) { this.format = format; }
  public int getWidth() { return width; }
  public void setWidth(int width) { this.width = width; }
  public int getHeight() { return height; }
  public void setHeight(int height) { this.height = height; }
  public String getExtractionStatus() { return extractionStatus; }
  public void setExtractionStatus(String extractionStatus) { this.extractionStatus = extractionStatus; }
  public Long getUserId() { return userId; }
  public void setUserId(Long userId) { this.userId = userId; }
  public boolean isPrivate() { return isPrivate; }
  public void setPrivate(boolean isPrivate) { this.isPrivate = isPrivate; }
}