package keys;

import java.io.Serializable;

public class PlayerKey implements Serializable {
  private String code;
  private long number;

  public void setCode(String code) {
    this.code = code;
  }

  public void setNumber(long number) {
    this.number = number;
  }

  public String getCode() {
    return code;
  }

  public long getNumber() {
    return number;
  }
}
