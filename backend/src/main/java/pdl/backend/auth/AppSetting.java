package pdl.backend.auth;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

@Table("app_settings")
public class AppSetting {

  @Id
  @Column("setting_key")
  private String settingKey;

  @Column("setting_value")
  private String settingValue;

  public AppSetting() {}

  public AppSetting(String settingKey, String settingValue) {
    this.settingKey = settingKey;
    this.settingValue = settingValue;
  }

  public String getSettingKey() {
    return settingKey;
  }

  public void setSettingKey(String settingKey) {
    this.settingKey = settingKey;
  }

  public String getSettingValue() {
    return settingValue;
  }

  public void setSettingValue(String settingValue) {
    this.settingValue = settingValue;
  }
}
