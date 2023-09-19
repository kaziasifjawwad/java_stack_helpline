import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Product {

  @Id private Long id;
  private String name;

  public Product setId(Long id) {
    this.id = id;
    return this;
  }

  public Product setName(String name) {
    this.name = name;
    return this;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "Product{" + "id=" + id + ", name='" + name + '\'' + '}';
  }
}
