import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
import persistance.CustomPersistanceUnitInfo;

import java.util.HashMap;

public class JPAXMLConfiguration {
    public static void main(String[] args) {

        EntityManagerFactory emf =
                new HibernatePersistenceProvider()
                        .createContainerEntityManagerFactory(
                                new CustomPersistanceUnitInfo(),
                                new HashMap<>()
                        );

//                Persistence
//                .createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager(); // represents the context

        try{
            em.getTransaction().begin();

            Product product = new Product().setName("Pen").setId(303L);
            em.persist(product); // add this object to the context -> this is not an insert query.
            em.getTransaction().commit();
        }finally {
            em.clear();
        }
    }
}