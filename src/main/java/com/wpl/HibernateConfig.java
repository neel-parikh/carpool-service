package com.wpl;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class HibernateConfig {
	
	@Autowired
	private Environment env;
  
	@Bean
	public DataSource dataSource() {
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
	    dataSource.setUrl(env.getProperty("spring.datasource.url"));
	    dataSource.setUsername(env.getProperty("spring.datasource.username"));
	    dataSource.setPassword(env.getProperty("spring.datasource.password"));
	    return dataSource;
	}

	  @Bean
	  public LocalSessionFactoryBean sessionFactory() throws IOException {
	    LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
	    sessionFactoryBean.setDataSource(dataSource());
	    sessionFactoryBean.setPackagesToScan("com.wpl");
	    sessionFactoryBean.afterPropertiesSet();
	    Properties hibernateProperties = new Properties();
	    hibernateProperties.put("hibernate.dialect", env.getProperty("spring.jpa.hibernate.dialect"));
	    hibernateProperties.put("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
	    hibernateProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
	    sessionFactoryBean.setHibernateProperties(hibernateProperties);
	    //sessionFactoryBean.setConfigLocation("classpath:hibernate.cfg.xml");
	    return sessionFactoryBean;
	  }
  
	  @Bean
	  public HibernateTemplate getHibernateTemplate()
	  {
		  try {
			  SessionFactory factory = sessionFactory().getObject();
			  HibernateTemplate template = new HibernateTemplate(factory);
			  template.setCheckWriteOperations(false);
			return template;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	  }
}
