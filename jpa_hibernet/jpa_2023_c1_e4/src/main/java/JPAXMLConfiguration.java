import entities.PlayerEntity;
import entities.ProductEntity;
import entities.StudentEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;
import keys.StudentKey;
import org.hibernate.jpa.HibernatePersistenceProvider;
import persistance.CustomPersistanceUnitInfo;

public class JPAXMLConfiguration {
  public static void main(String[] args) {

    Map<String, String> props = new HashMap<>();
    props.put("hibernate.show_sql", "true");
    props.put("hibernate.hbm2ddl.auto", "update");

    EntityManagerFactory emf =
        new HibernatePersistenceProvider()
            .createContainerEntityManagerFactory(new CustomPersistanceUnitInfo(), props);

    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();

      var product = new ProductEntity();
      product.setName("Good product");
      em.persist(product);

      var player = new PlayerEntity();
      player.setCode("444");
      player.setName("7878");
      player.setName("HASIM AMLA");
      em.persist(player);

      var studentKey = new StudentKey();
      studentKey.setCode("7845");
      studentKey.setNumber(234);

      var student = new StudentEntity();
      student.setId(studentKey);
      student.setName("Arif");
      em.persist(student);

      em.getTransaction().commit();
    } finally {
      em.clear();
    }
  }
}
