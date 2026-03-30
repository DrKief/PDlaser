package pdl.backend;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Entity class representing an Image metadata record in the database.
 * Maps directly to the "images" table in PostgreSQL.
 */
@Table("images")
public class Metadata {

  @Id
  private Long id;

  @Column("filename")
  private String name;

  private String format;
  private int width;
  private int height;
  private String hash; // SHA-256 hash to prevent duplicate uploads

  @Column("extraction_status")
  private String extractionStatus; // e.g., PENDING, PROCESSING, COMPLETED, FAILED

  // @Transient ensures Spring Data doesn't try to save the raw byte array into the DB.
  // We store the bytes physically on the disk instead.
  @Transient
  private byte[] data;

  /**
   * Default constructor required by Spring Data.
   */
  public Metadata() {}

  /**
   * Constructor for creating a new metadata record from an upload.
   *
   * @param name The original filename.
   * @param data The raw byte data of the image.
   */
  public Metadata(final String name, final byte[] data) {
    this.name = name;
    this.data = data;
  }

  // --- Getters and Setters ---

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
