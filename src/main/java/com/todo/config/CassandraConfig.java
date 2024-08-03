import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories(basePackages = "com.todo.repository.cassandra")
public class CassandraConfig extends AbstractCassandraConfiguration {

  @Override
  protected String getKeyspaceName() {
    return "research";
  }

  @Override
  protected String getContactPoints() {
    return "127.0.0.1";
  }

  @Override
  protected int getPort() {
    return 9042;
  }

  @Override
  protected String getLocalDataCenter() {
    return "datacenter1"; // ここを修正
  }

  @Bean
  public CqlSessionFactoryBean cassandraSession() {
    CqlSessionFactoryBean session = new CqlSessionFactoryBean();
    session.setContactPoints(getContactPoints());
    session.setPort(getPort());
    session.setKeyspaceName(getKeyspaceName());
    session.setLocalDatacenter(getLocalDataCenter());
    return session;
  }
}