//package com.brq.santander.cadeiasocietaria.configuration;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
//import org.springframework.data.cassandra.config.CqlSessionFactoryBean;
//import org.springframework.data.cassandra.config.SchemaAction;
//import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
//
//@Configuration
//@EnableCassandraRepositories(basePackages = { "com.brq.santander.cadeiasocietaria.repository" })
//@PropertySource("classpath:application.yml")
//public class DatabaseConfiguration extends AbstractCassandraConfiguration{
//
//	@Value("${spring.data.cassandra.contact-points:placeholder}")
//	private String contactPoints;
//
//	@Value("${spring.data.cassandra.port:0000}")
//	private int port;
//
//	@Value("${spring.data.cassandra.keyspace:placeholder}")
//	private String keySpace;
//
//	@Value("${spring.data.cassandra.username:placeholder}")
//	private String username;
//	
//	@Value("${spring.data.cassandra.password:placeholder}")
//	private String password;
//
//	@Value("${spring.data.cassandra.schema-action:placeholder}")
//	private String schemaAction;
//
//	@Override
//	protected String getKeyspaceName() {
//		return keySpace;
//	}
//
//	@Override
//	protected String getContactPoints() {
//		return contactPoints;
//	}
//
//	@Override
//	protected int getPort() {
//		return port;
//	}
//
//	@Override
//	public SchemaAction getSchemaAction() {
//		return SchemaAction.valueOf(schemaAction);
//	}
//
//	@Bean
//	@Override
//	public CqlSessionFactoryBean cassandraSession() {
//		CqlSessionFactoryBean cassandraSession = super.cassandraSession();
//		cassandraSession.setUsername(username);
//		cassandraSession.setPassword(password);
//		return cassandraSession;
//	}
//
//
//}
