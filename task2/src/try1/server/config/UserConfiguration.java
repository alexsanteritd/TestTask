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

import try1.server.dao.AccountHistoryDao;
import try1.server.dao.UserDao;
import try1.server.model.AccountHistory;
import try1.server.model.User;


@Configuration
@EnableTransactionManagement
@ComponentScan("try1.server")
public class UserConfiguration {
	
	@Bean
	@Transactional
	public AccountHistoryDao accountHistoryDao(SessionFactory sessionFactory) {
		return new AccountHistoryDao(sessionFactory);
	}
	
	@Bean
	@Transactional
	public UserDao userDao(SessionFactory sessionFactory) {
		return new UserDao(sessionFactory);
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource);
		sessionFactoryBean.setHibernateProperties(hibernateProperties());
		sessionFactoryBean.setAnnotatedClasses(User.class,AccountHistory.class);
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
