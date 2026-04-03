package pdl.backend.gallery.core;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("images")
public class MediaRecord {

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

  // --- NEW ROBUST METADATA FIELDS ---
  private String provider = "LOCAL";

  @Column("provider_id")
  private String providerId;

  private String description;

  @Column("photographer_name")
  private String photographerName;

  @Column("photographer_username")
  private String photographerUsername;

  @Column("camera_make")
  private String cameraMake;

  @Column("camera_model")
  private String cameraModel;

  private Integer iso;

  @Column("location_country")
  private String locationCountry;

  @Column("location_city")
  private String locationCity;

  @Column("stats_downloads")
  private Long statsDownloads;

  @Column("remote_url")
  private String remoteUrl;

  @Transient
  private byte[] data;

  public MediaRecord() {}

  public MediaRecord(final String name, final byte[] data) {
    this.name = name;
    this.data = data;
  }

  // --- STANDARD GETTERS & SETTERS ---
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

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public boolean isPrivate() {
    return isPrivate;
  }

  public void setPrivate(boolean isPrivate) {
    this.isPrivate = isPrivate;
  }

  // --- ROBUST GETTERS & SETTERS ---
  public String getProvider() {
    return provider;
  }

  public void setProvider(String provider) {
    this.provider = provider;
  }

  public String getProviderId() {
    return providerId;
  }

  public void setProviderId(String providerId) {
    this.providerId = providerId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPhotographerName() {
    return photographerName;
  }

  public void setPhotographerName(String photographerName) {
    this.photographerName = photographerName;
  }

  public String getPhotographerUsername() {
    return photographerUsername;
  }

  public void setPhotographerUsername(String photographerUsername) {
    this.photographerUsername = photographerUsername;
  }

  public String getCameraMake() {
    return cameraMake;
  }

  public void setCameraMake(String cameraMake) {
    this.cameraMake = cameraMake;
  }

  public String getCameraModel() {
    return cameraModel;
  }

  public void setCameraModel(String cameraModel) {
    this.cameraModel = cameraModel;
  }

  public Integer getIso() {
    return iso;
  }

  public void setIso(Integer iso) {
    this.iso = iso;
  }

  public String getLocationCountry() {
    return locationCountry;
  }

  public void setLocationCountry(String locationCountry) {
    this.locationCountry = locationCountry;
  }

  public String getLocationCity() {
    return locationCity;
  }

  public void setLocationCity(String locationCity) {
    this.locationCity = locationCity;
  }

  public Long getStatsDownloads() {
    return statsDownloads;
  }

  public void setStatsDownloads(Long statsDownloads) {
    this.statsDownloads = statsDownloads;
  }

  public String getRemoteUrl() {
    return remoteUrl;
  }

  public void setRemoteUrl(String remoteUrl) {
    this.remoteUrl = remoteUrl;
  }
}
