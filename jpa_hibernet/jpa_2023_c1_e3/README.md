# Context

In the last chapter we discussed how context work.
In this chapter, we will discuss some important 
topic of context.

#### persist
In entity manager,  the persis method is used to
add any instance in the context. For example,
I want to add a product 'apple' with ID '1' to
the context. I will do it using :

```java
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
    EntityManagerFactory emf =
        new HibernatePersistenceProvider()
            .createContainerEntityManagerFactory(new CustomPersistanceUnitInfo(), props);
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();

      var e1 = new Product();
      e1.setId(3L);
      e1.setName("Banana");
      em.persist(e1);

      e1 = em.find(Product.class, 3L);
      em.getTransaction().commit();
    } finally {
      em.clear();
    }
  }
}
```

In the above scenario, the Banana object was
persisted by entity manager. So, now this will be
part of the context. Now when I call the find method
with the same id `3L` It will not call the database
for the data. As the object with primary key `3L`
is already exists in the context, it will no longer
query for it in the database.

* The persist method will not call insert operation
as like JDBC.

When we call the commit it will insert this value in
the database.

#### Find vs get reference
the find operation will first check in the context
if the instance exists or not. If the instance exists
in the context, it will not call for it in the DB.
It will only call for it in the database as long the
instance does not exist in the context.
On the other hand, the getReference method will
create a shell over the instance. It will call for
the instance only when the code do something with
that instance. Below is the example of getReference.

```java
      em.getTransaction().begin();
      var e1 = em.getReference(Product.class, 1L);
      em.getTransaction().commit();
```

This will not call the database.

```java
      em.getTransaction().begin();
      var e1 = em.getReference(Product.class, 1L);
      System.out.pringln(e1);
      em.getTransaction().commit();
```

This will call the database.
We did something with it 
(printed it in the console).


#### Refresh
There is nothing called update in context based framework. When we
make some change in the entity that exists in the context, it will
automatically reflect on the database as soon we call the commit
method on the entity.

```java
em.getTransaction.commit();
```
By calling the commit method, we will automatically update
all the entity that are present in the current context. 
Now assume that we have a very complex algorithm where there might
be a high change that some of our entity may be changed by mistak.
And we want to ensure that a particular entity should not be 
modified before commit. For this we can refresh that entity by
this method.

```java
      em.getTransaction().begin();
      var e1 = em.getReference(Product.class, 1L);
      e1.setName("Abc");
      em.refresh(e1);
      em.getTransaction().commit();
```

By calling refresh over e1 object, we will make it sure that the
object e1 should not have any change that will reflect on the database.
In summary, it will not update the name to `Abc` on the database.

