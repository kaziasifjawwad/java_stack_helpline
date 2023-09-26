package keys;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class StudentKey implements Serializable {
  private long number;
  private String code;

  public void setNumber(long number) {
    this.number = number;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public long getNumber() {
    return number;
  }

  public String getCode() {
    return code;
  }
}
