package try1.server.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.dialect.MySQL8Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import try1.server.model.Bill;
import try1.server.model.BillDao;
import try1.server.model.Logs;
import try1.server.model.LogsDao;
import try1.server.model.User;
import try1.server.model.UserDao;
import try1.server.services.AutService;
import try1.server.services.AutServiceImp;


@Configuration
@EnableTransactionManagement
@ComponentScan("try1.server")
public class UserConfiguration {
	
	@Bean
	@Transactional
	public LogsDao logsDao(SessionFactory sessionFactory) {
		return new LogsDao(sessionFactory);
	}
	
	@Bean
	@Transactional
	public UserDao userDao(SessionFactory sessionFactory) {
		return new UserDao(sessionFactory);
	}
	@Bean
	@Transactional
	public BillDao billDao(SessionFactory sessionFactory) {
		return new BillDao(sessionFactory);
	}
	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource);
		sessionFactoryBean.setHibernateProperties(hibernateProperties());
		sessionFactoryBean.setAnnotatedClasses(User.class,Bill.class,Logs.class);
		return sessionFactoryBean;
	}

	@Bean
	public HibernateTransactionManager trancationalManager(SessionFactory sessionFactory){
		return new HibernateTransactionManager(sessionFactory);
	}
	
	@Bean(name="dataSource")
	public static DataSource getDataSource() {
	    BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setDriverClassName(MySQL8Dialect.class.getName());
	    dataSource.setUrl("jdbc:mysql://localhost:3306/db2");
	    dataSource.setUsername("root");
	    dataSource.setPassword("SqlAlex");
	    return dataSource;
	}
	
	private Properties hibernateProperties() {
		Properties properties = new Properties();

		properties.setProperty(AvailableSettings.DIALECT, MySQL8Dialect.class.getName());
		properties.setProperty(AvailableSettings.SHOW_SQL, String.valueOf(true));
		properties.setProperty(AvailableSettings.HBM2DDL_AUTO, "update");
		properties.setProperty(AvailableSettings.DRIVER, "com.mysql.cj.jdbc.Driver");
		return properties;
	}
}
