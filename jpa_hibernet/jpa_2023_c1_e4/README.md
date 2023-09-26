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

### Composed primary key

In hibernate we also can create composed primary key.
There are two ways to create composed primary key
* using Id class
* Using embadable class (EmbadableId)

#### Id class
For this we need to create a class that will contain 
the same attributes (variable) with same name and type
of the entity class thous which we want to use as composed
primary key.
For example we have an entity called Player. In our
player class we have 
```java
  private String code;
  private long number;
```

We want this two attribute as composed primary key.
For this we need to craete a seperate class with this
two attributes.

```java
package keys;

import java.io.Serializable;

public class PlayerKey implements Serializable {
  private String code;
  private long number;
}
```

Then we can use this class to crete our composed primary
key.

```java
package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import keys.PlayerKey;

@Entity
@IdClass(PlayerKey.class)
public class Player {
  @Id private String code;
  @Id private long number;
  private String name;
}
```
We need to tell hibernate that we will use a class
called PlayerKey.
We also have to use @Id annotation in each of the composite
key's attribute. In this case they are `code, name`.


#### Using Embeddable class (EmbeddedId)
In this second approach, we can create a composite primary key using
a seperate composite class. The class that contains all the attribute
of composite keys (all the prime attribute) must be annoated with
`@Embeddable`

```java
package keys;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class StudentKey implements Serializable {
  private long number;
  private String code;
}
```

Now we have a class that is ready to use as composite primary key.
Now we can use it in our entity class.

```java
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import keys.StudentKey;

@Entity
public class StudentEntity {
  @EmbeddedId private StudentKey id;

  private String name;
}
```

Keep in mind that we are no longer using `@Id` annotation. Rather
we are using `@EmbeddedId` annotation in our entity class.
