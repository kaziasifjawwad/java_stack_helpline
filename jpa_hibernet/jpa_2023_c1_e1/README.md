## The context

Before jump to the context, let us know some
concept for hybernate table annotation.

> **@Table**
><br>If we want to change the table to anything
> else rather then the class name of the entity,
> we will use @Table annotation. It takes
> the name of the table as parameter and will
> create a table in the database according to
> the name we provide to it.
>

Example:
```java
package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="product_custom_table")
public class Product {
    @Id
    private Long id;

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

    @Id


    private String name;
}
```
In this example, the table will be saved according 
to the parameter we pass through the table annotation.


# The context
We will query the dtabase using the entity manager.
```java
EntityManager em = emf.createEntityManager(); // represents the context

        try{
            em.getTransaction().begin();

            Product product = new Product().setName("eraser").setId(34L);
            em.find(product.class , 1); // add this object to the context -> this is not an insert query.
            em.getTransaction().commit();
        }finally {
            em.clear();
        }
```
