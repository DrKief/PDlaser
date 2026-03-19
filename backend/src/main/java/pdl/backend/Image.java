package pdl.backend;

public class Image {
  private Long id;
  private String name;
  private byte[] data;
  private String hash;

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

  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }
}
