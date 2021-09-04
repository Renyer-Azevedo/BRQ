package com.brq.santander.cadeiasocietaria.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.SessionFactory;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.config.SessionFactoryFactoryBean;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.convert.CassandraConverter;
import org.springframework.data.cassandra.core.convert.CassandraCustomConversions;
import org.springframework.data.cassandra.core.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.mapping.SimpleUserTypeResolver;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import com.brq.santander.cadeiasocietaria.converter.InstantToStringConverter;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.shaded.guava.common.collect.Lists;

import lombok.Data;

@Data
@Configuration
@EnableCassandraRepositories(basePackages = { "com.brq.santander.cadeiasocietaria.repository" })
@ConfigurationProperties(prefix = "spring.data.cassandra")
public class DatabaseConfiguration2 {
	
	  private String contactPoints;
	  private String username;
	  private String password;
	  private String keyspace;
	  private int port;
	  private String schemaAction;
	  private String localDatacenter;
	
	  @Bean
	  public CqlSessionFactoryBean session() {

	    CqlSessionFactoryBean session = new CqlSessionFactoryBean();
	    session.setContactPoints(contactPoints);
	    session.setPort(port);
	    session.setLocalDatacenter(localDatacenter);
	    session.setKeyspaceName(keyspace);
	    session.setUsername(username);
	    session.setPassword(password);

	    return session;
	  }

	  @Bean
	  public SessionFactoryFactoryBean sessionFactory(CqlSession session, CassandraConverter converter) {

	    SessionFactoryFactoryBean sessionFactory = new SessionFactoryFactoryBean();
	    sessionFactory.setSession(session);
	    sessionFactory.setConverter(converter);
	    sessionFactory.setSchemaAction(SchemaAction.valueOf(schemaAction));

	    return sessionFactory;
	  }

	  @Bean
	  public CassandraConverter converter(CqlSession cqlSession) {
		  MappingCassandraConverter mappingCassandraConverter = new MappingCassandraConverter();
		  mappingCassandraConverter.setUserTypeResolver(new SimpleUserTypeResolver(cqlSession));
		  mappingCassandraConverter.setCustomConversions(customConversions());
	    return mappingCassandraConverter;
	  }

	  @Bean
	  public CassandraOperations cassandraTemplate(SessionFactory sessionFactory, CassandraConverter converter) {
	    return new CassandraTemplate(sessionFactory, converter);
	  }

	  public CassandraCustomConversions customConversions() {
		    return new CassandraCustomConversions(
		      Lists.newArrayList(new InstantToStringConverter())
		    );
	  }
	  
	  
}
