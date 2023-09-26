package persistance;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.SharedCacheMode;
import jakarta.persistence.ValidationMode;
import jakarta.persistence.spi.ClassTransformer;
import jakarta.persistence.spi.PersistenceUnitInfo;
import jakarta.persistence.spi.PersistenceUnitTransactionType;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import javax.sql.DataSource;

public class CustomPersistanceUnitInfo implements PersistenceUnitInfo {
  @Override
  public String getPersistenceUnitName() {
    return "my-persistence-unit";
  }

  @Override
  public String getPersistenceProviderClassName() {
    return "org.hibernate.jpa.HibernatePersistenceProvider";
  }

  @Override
  public PersistenceUnitTransactionType getTransactionType() {
    return PersistenceUnitTransactionType.RESOURCE_LOCAL;
  }

  @Override
  public DataSource getJtaDataSource() {
    var dataSource = new HikariDataSource();
    dataSource.setUsername("postgres");
    dataSource.setPassword("123456");
    dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/jdbc");
    return dataSource;
  }

  @Override
  public DataSource getNonJtaDataSource() {
    return null;
  }

  @Override
  public List<String> getMappingFileNames() {
    return null;
  }

  @Override
  public List<URL> getJarFileUrls() {
    return null;
  }

  @Override
  public URL getPersistenceUnitRootUrl() {
    return null;
  }

  @Override
  public List<String> getManagedClassNames() {
    return List.of("entities.Product", "entities.Player");
  }

  @Override
  public boolean excludeUnlistedClasses() {
    return false;
  }

  @Override
  public SharedCacheMode getSharedCacheMode() {
    return null;
  }

  @Override
  public ValidationMode getValidationMode() {
    return null;
  }

  @Override
  public Properties getProperties() {
    return null;
  }

  @Override
  public String getPersistenceXMLSchemaVersion() {
    return null;
  }

  @Override
  public ClassLoader getClassLoader() {
    return null;
  }

  @Override
  public void addTransformer(ClassTransformer transformer) {}

  @Override
  public ClassLoader getNewTempClassLoader() {
    return null;
  }
}
