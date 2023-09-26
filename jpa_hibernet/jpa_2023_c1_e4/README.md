## Primary key

In hibernate we can assign primary key of an entity
under different generation type. In our entity class,
we must define a primary key. For creating primary
key hibernate provides us different strategy that we
can use to automatically assign primary key to an
attribute.

```java
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
```

For this we need to use @GeneratedValue annotation
and we can select different strategy type.
for example:
* GenerationType.AUTO
* GenerationType.IDENTITY
* GenerationType.SEQUENCE
* GenerationType.TABLE
* GenerationType.UUID

Sometimes we have some requirement where we need to
define our own generation type logic. For this, we
can create our own generator for generation key.


Besides this above option, we can implement our own 
generation logic. For this we need to create a class
that will implement `IdentifierGenerator` class.

```java
import java.util.UUID;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class UUIDGenerator implements IdentifierGenerator {
  @Override
  public Object generate(SharedSessionContractImplementor session, Object object) {
    return "--" + UUID.randomUUID() + "***";
  }
}
```

```java
  @Id
  @GenericGenerator(name = "UUIDGenerator", type = UUIDGenerator.class)
  @GeneratedValue(generator = "UUIDGenerator")
  private String id;
```
First we created a custom generator using UUIDGenerator
class. And then we created a generic generator in our
entity class and gave it a name 'UUIDGenerator'.
Then instead of using GenerationType, we used `generator`
and provided the generator that we created with
`@GenericGenerator(name = "UUIDGenerator", type = UUIDGenerator.class)`

