package generators;

import java.util.UUID;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class UUIDGenerator implements IdentifierGenerator {
  @Override
  public Object generate(SharedSessionContractImplementor session, Object object) {
    return "--" + UUID.randomUUID() + "***";
  }
}
