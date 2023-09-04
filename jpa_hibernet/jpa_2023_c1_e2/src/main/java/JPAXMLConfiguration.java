import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.HashMap;
import org.hibernate.jpa.HibernatePersistenceProvider;
import persistance.CustomPersistanceUnitInfo;

public class JPAXMLConfiguration {
  public static void main(String[] args) {

    EntityManagerFactory emf =
        new HibernatePersistenceProvider()
            .createContainerEntityManagerFactory(new CustomPersistanceUnitInfo(), new HashMap<>());

    //                Persistence
    //                .createEntityManagerFactory("my-persistence-unit");
    EntityManager em = emf.createEntityManager(); // represents the context

    try {
      em.getTransaction().begin();

      Product product = em.find(Product.class, 34L);
      product.setName("Jawwad");
      System.out.println(product);
      em.getTransaction().commit();
    } finally {
      em.clear();
    }
  }
}
