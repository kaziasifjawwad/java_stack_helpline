package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import keys.PlayerKey;

@Entity
@IdClass(PlayerKey.class)
public class Player {
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
