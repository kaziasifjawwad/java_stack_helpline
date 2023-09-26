package entities;

import generators.UUIDGenerator;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "product")
public class ProductEntity {

  @Id
  @GenericGenerator(name = "UUIDGenerator", type = UUIDGenerator.class)
  @GeneratedValue(generator = "UUIDGenerator")
  private String id;

  @Column(unique = true)
  private String name;

  public ProductEntity setName(String name) {
    this.name = name;
    return this;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "entities.Product{" + "id=" + id + ", name='" + name + '\'' + '}';
  }
}
