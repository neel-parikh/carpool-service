package com.wpl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DBConnection 
{
/*	private HibernateTemplate hibernateTemplate;
	
	public DBConnection() 
	{
		Configuration config = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
		applySettings(config.getProperties());
		SessionFactory factory = config.buildSessionFactory(builder.build());
		this.hibernateTemplate = new HibernateTemplate(factory);
	}
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}*/
	
	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplateObject;
	
	public void setDataSource(DataSource dataSource) {
	      this.dataSource = dataSource;
	      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	   }

	public JdbcTemplate getJdbcTemplateObject() {
		return jdbcTemplateObject;
	}
	
	/*final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	final static String DB_URL = "jdbc:mysql://localhost:3306/carpool";
	public static Connection connect()
	{
		Connection conn = null;
		try {
		Class.forName("com.mysql.jdbc.Driver");
		conn = (Connection) DriverManager.getConnection(DB_URL,"root","tiger");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return conn;
	}*/
}