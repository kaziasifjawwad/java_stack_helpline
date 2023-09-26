package entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import keys.StudentKey;

@Entity
@Table(name = "student")
public class StudentEntity {
  @EmbeddedId private StudentKey id;

  public void setId(StudentKey id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public StudentKey getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  private String name;
}
