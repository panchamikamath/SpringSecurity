package kamath.panchami.springsecurity.demo.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="kamath.panchami.springsecurity.demo")
@PropertySource("classpath:persistence-mysql.properties")
public class DemoAppConfig {
	
	//set up variable to hold properties
	
	@Autowired
	Environment env;
	
	//set up logger for daignostics
	
	private Logger logger = Logger.getLogger(getClass().getName());

	// define a bean for ViewResolver

	@Bean
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	
	//define a bean for our security datasource
	
	@Bean
	public DataSource securityDataSource() {
		
		//create connection pool
		ComboPooledDataSource securityDatSource
			= new ComboPooledDataSource();
		
		//set jdbc driver class
		
		try {
			securityDatSource.setDriverClass(env.getProperty("jdbc.driver"));
		}catch(PropertyVetoException ex) {
			throw new RuntimeException(ex);
		}
		
		logger.info(">>>jdbc.url= " + env.getProperty("jdbc.url"));
		logger.info(">>>jdbc.user= " + env.getProperty("jdbc.user"));
		
		//set database connection props
		securityDatSource.setJdbcUrl(env.getProperty("jdbc.url"));
		securityDatSource.setUser(env.getProperty("jdbc.user"));
		securityDatSource.setPassword(env.getProperty("jdbc.password"));
		
		//set connection pool props
		
		securityDatSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		securityDatSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		securityDatSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		securityDatSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
		
		return securityDatSource;
	}
	
	private int getIntProperty(String propName) {
		String propVal = env.getProperty(propName);
		int intPropVal = Integer.parseInt(propVal);
		return intPropVal;
	}
	
}









