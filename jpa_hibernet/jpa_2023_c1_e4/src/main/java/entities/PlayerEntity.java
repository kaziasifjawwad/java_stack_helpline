package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import keys.PlayerKey;

@Entity
@IdClass(PlayerKey.class)
@Table(name = "player")
public class PlayerEntity {
  @Id private long number;
  @Id private String code;
  private String name;

  public void setCode(String code) {
    this.code = code;
  }

  public void setNumber(long number) {
    this.number = number;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public long getNumber() {
    return number;
  }

  public String getName() {
    return name;
  }
}
