package autominion.database.persistence.dao.implementations;

import java.util.List;

import org.hibernate.Session;

import autominion.database.persistence.dao.interfaces.CustomerDaoI;
import autominion.database.persistence.entities.Customers;

public class CustomerDaoImpl extends CommonDaoImpl<Customers> implements CustomerDaoI {

	/**
	 * Método constructor
	 */
	public CustomerDaoImpl(Session session) {
		super(session);
	}

	@SuppressWarnings("unchecked")
	public List<Customers> getAll() {
		verifySession();

		return session.createQuery("FROM Customers").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customers> searchByNameAndSurname(final String name, final String surname1) {
		verifySession();

		// Localiza clientes en función del nombre y apellido.
		return session.createQuery("FROM Customers WHERE name like '" + name + "' and surname1 like '" + surname1 + "'")
				.list();

	}

	public Customers searchById(final Long id) {
		verifySession();

		// Búsqueda por PK.
		return (Customers) session.createQuery("FROM Customers WHERE id = " + id).uniqueResult();
	}

}