package main.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@ComponentScan("main")
@PropertySource("/WEB-INF/resources/database.properties")
@EnableTransactionManagement
public class DatabaseConfig {

	@Autowired
	private Environment environment;

	public DatabaseConfig() {
		System.out.println("INVOKING DatabaseConfig v 1.0");
	}

	@Bean
	public DataSource getDataSource() {
		System.out.println("Creating datasource object");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getProperty("jdbc.driver"));
		dataSource.setUrl(environment.getProperty("jdbc.url"));
		dataSource.setUsername(environment.getProperty("jdbc.username"));
		dataSource.setPassword(environment.getProperty("jdbc.password"));
		return dataSource;
	}

	@Bean("transactionManager")
	public PlatformTransactionManager transactionManager() {
		System.out.println("Creating DataSourceTransactionManager bean");
		return new DataSourceTransactionManager(getDataSource());
	}

	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
		System.out.println("Creating NamedParameterJdbcTemplate bean");
		NamedParameterJdbcTemplate namedJdbcTemplate =
				new NamedParameterJdbcTemplate(getDataSource());
		return namedJdbcTemplate;
	}

}
