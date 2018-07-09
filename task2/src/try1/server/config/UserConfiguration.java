package try1.server.config;

import java.util.Properties;

import try1.server.model.Bill;
import try1.server.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.dialect.MySQL8Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import com.mysql.jdbc.Driver;

import try1.server.hibernate.HibernateBillDao;
import try1.server.hibernate.HibernateUserDao;
import try1.server.model.UserDao;

@SuppressWarnings("unused")
@Configuration
@ComponentScan("try1.server")
public class UserConfiguration {
	@Bean
	public UserDao userDao(SessionFactory sessionFactory) {
		return new HibernateUserDao(sessionFactory);
	}
	@Bean
	public HibernateBillDao billDao(SessionFactory sessionFactory) {
		return new HibernateBillDao(sessionFactory);
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setHibernateProperties(hibernateProperties());
		sessionFactoryBean.setAnnotatedClasses(User.class,Bill.class);
		return sessionFactoryBean;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.setProperty(AvailableSettings.URL, "jdbc:mysql://localhost:3306/db2");
		properties.setProperty(AvailableSettings.USER, "root");
		properties.setProperty(AvailableSettings.PASS, "SqlAlex");
		properties.setProperty(AvailableSettings.DIALECT, MySQL8Dialect.class.getName());
		properties.setProperty(AvailableSettings.SHOW_SQL, String.valueOf(false));
		properties.setProperty(AvailableSettings.HBM2DDL_AUTO, "update");
		properties.setProperty(AvailableSettings.DRIVER, "com.mysql.jdbc.Driver");
		return properties;
	}
}
