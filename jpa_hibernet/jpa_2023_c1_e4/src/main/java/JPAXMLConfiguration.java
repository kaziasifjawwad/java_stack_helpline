import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;
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
      Product product = new Product();
      product.setName("Good product");
      em.persist(product);
      em.getTransaction().commit();

    } finally {
      em.clear();
    }
  }
}
