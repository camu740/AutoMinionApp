package autominion.database.persistence.dao.implementations;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;

import autominion.database.persistence.dao.interfaces.CommonDaoI;
import autominion.database.persistence.entities.AbstractEntity;

public abstract class CommonDaoImpl<T extends AbstractEntity> implements CommonDaoI<T> {

	/** Tipo de clase */
	private Class<T> entityClass;

	/** Sesión de conexión a BD */
	protected Session session;

	/**
	 * Método constructor.
	 * 
	 * @param session
	 */
	@SuppressWarnings("unchecked")
	protected CommonDaoImpl(Session session) {
		setEntityClass(
				(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
		this.session = session;
	}

	@Override
	public void insert(T paramT) {
		verifySession();

		// Insercción.
		session.save(paramT);
		session.flush();

		// Commit.
		session.getTransaction().commit();
	}

	@Override
	public void update(final T paramT) {
		verifySession();

		// Insercción.
		session.saveOrUpdate(paramT);

		// Commit.
		session.getTransaction().commit();
	}

	@Override
	public void delete(final T paramT) {
		verifySession();

		// Insercción.
		session.delete(paramT);

		// Commit.
		session.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> searchAll() {
		verifySession();

		// Búsqueda de todos los registros.
		return session.createQuery("FROM " + this.entityClass.getName()).list();

	}

	/**
	 * @return the entityClass
	 */
	public Class<T> getEntityClass() {
		return entityClass;
	}

	/**
	 * @param entityClass the entityClass to set
	 */
	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * Comprueba si la session está activa y si no es el caso la inicia
	 */
	protected void verifySession() {
		// Verificación de transacción activa.
		if (!session.getTransaction().isActive()) {
			session.getTransaction().begin();
		}
	}

}