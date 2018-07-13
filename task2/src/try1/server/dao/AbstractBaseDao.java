package try1.server.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**
 * Абстрактный класс для базовых torrent операций
 * 
 * @param <T>
 */
@Transactional
public abstract class AbstractBaseDao<T> {

	private Class<? extends T> entityClass;

	private SessionFactory sessionFactory;

	/**
	 * Конструктор. Уснавливает дао класс
	 * 
	 * @param targetClass
	 */
	public AbstractBaseDao(Class<? extends T> targetClass) {
		entityClass = targetClass;
	}

	/**
	 * Конструктор. Уснавливает дао класс и sessionFactory
	 * 
	 * @param targetClass
	 * @param sessionFactory
	 */
	public AbstractBaseDao(Class<? extends T> targetClass, SessionFactory sessionFactory) {
		entityClass = targetClass;
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Создает новый екземпляр класса
	 * 
	 * @return созданный екземпляр
	 */
	public T newBeanInstance() {
		try {
			return entityClass.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * создать запись в базе
	 * 
	 * @param pObj
	 */
	public void create(T pObj) {
		final Session session = getSession();
		session.save(pObj);
	}

	/**
	 * мержить запись в базе
	 * 
	 * @param pObj
	 */
	@SuppressWarnings("unchecked")
	public T merge(T pObj) {
		final Session session = getSession();
		return (T) session.merge(pObj);
	}

	/**
	 * Удалить запить из базы
	 * 
	 * @param pObj
	 */
	public void delete(T pObj) {
		getSession().delete(pObj);
	}

	/**
	 * Удалить запить из базы по индефикатору
	 * 
	 * @param id
	 */
	public void delete(Serializable id) {
		T findByPK = findByPK(id);
		if (findByPK != null) {
			getSession().delete(findByPK);
		}
	}

	/**
	 * Удалить все
	 */
	public void deleteAll() {
		throw new RuntimeException("Not implemented");
		// List<T> findAll = findAll();
		// for (T t : findAll) {
		// getSession().delete(t);
		// }
	}

	/**
	 * Найти запись по индефикатору
	 * 
	 * @param id
	 * @return выбранная запись
	 */
	public T findByPK(Serializable id) {
		return (T) getSession().get(entityClass, id);
	}

	/**
	 * Найти запись по индефикатору с залочкой на запись
	 * 
	 * @param id
	 * @return выбранная запись
	 */
	public T findByPKforUpgrade(Serializable id) {
		return (T) getSession().get(entityClass, id, LockOptions.UPGRADE);
	}

	/**
	 * Обновить запись
	 * 
	 * @param pObj
	 */
	public void update(T pObj) {
		getSession().saveOrUpdate(pObj);
	}

	/**
	 * Обновить список записей
	 * 
	 * @param list
	 */
	public void updateAll(List<T> list) {
		for (T t : list) {
			update(t);
		}
	}

	/**
	 * Выбрать всю таблицу
	 * 
	 * @return все записи
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<T> findAll() {
		try {
			Criteria crit = getSession().createCriteria(entityClass);
			return crit.list();
		} catch (DataAccessException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * посчитать кол-во записей в таблице
	 * 
	 * @return кол-во записей
	 */
	@SuppressWarnings("deprecation")
	public Long countAll() {
		try {
			Criteria crit = getSession().createCriteria(entityClass);
			crit.setProjection(Projections.rowCount());
			return (Long) crit.uniqueResult();
		} catch (DataAccessException e) {
			throw new RuntimeException(e);
		}
	}
	

	/**
	 * Выбрать записи в таблице с пагинацией
	 * 
	 * @param offset
	 * @param limit
	 * @return выбранные записи
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<T> findAll(int offset, int limit) {
		try {
			Criteria crit = getSession().createCriteria(entityClass);
			crit.setFirstResult(offset);
			crit.setMaxResults(limit);
			return crit.list();
		} catch (DataAccessException e) {
			throw new RuntimeException(e);
		}
	}

	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<T> findAllOrdered(String fieldName, int offset, int limit) {
		try {
			Criteria crit = getSession().createCriteria(entityClass);
			crit.setFirstResult(offset);
			crit.setMaxResults(limit);
			crit.addOrder(Order.asc(fieldName));
			return crit.list();
		} catch (DataAccessException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Взять текущию сессию Hibernate
	 * 
	 * @return session
	 */
	public Session getSession() {
		Session currentSession;
		try {
			currentSession = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			e.printStackTrace();
			currentSession = sessionFactory.openSession();
		}
		return currentSession;
	}

	public void evict(Object entity) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.evict(entity);
	}

	public void clearSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.clear();
	}

	public void flush() {
		final Session session = getSession();
		session.flush();
	}
}
