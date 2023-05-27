package autominion.database.persistence.dao.implementations;

import java.util.List;

import org.hibernate.Session;

import autominion.database.persistence.dao.interfaces.EmployeesDaoI;
import autominion.database.persistence.entities.Employees;
import autominion.database.persistence.entities.Mechanics;
import autominion.database.persistence.entities.Salesemployees;

public class EmployeesDaoImpl extends CommonDaoImpl<Employees> implements EmployeesDaoI {
	/**
	 * Método constructor
	 */
	public EmployeesDaoImpl(Session session) {
		super(session);
	}

	@SuppressWarnings("unchecked")
	public List<Employees> getAll() {
		verifySession();

		return session.createQuery("SELECT id, name, surname1, surname2, phone, email, password FROM Employees").list();
	}

	@SuppressWarnings("unchecked")
	public List<Employees> searchByNameAndSurname(final String name, final String surname1) {
		verifySession();

		// Localiza clientes en función del nombre y apellido.
		return session.createQuery("FROM Employees WHERE name like '" + name + "' and surname1 like '" + surname1 + "'")
				.list();
	}

	public Employees searchByEmail(String email) {
		verifySession();

		return (Employees) session.createQuery("FROM Employees WHERE email = '" + email + "'").list().get(0);
	}

	public Employees searchById(final Long id) {
		verifySession();

		// Búsqueda por PK.
		return (Employees) session.createQuery("FROM Employees WHERE id = " + id);
	}

	@SuppressWarnings("unchecked")
	public List<Salesemployees> getSalesEmployees() {
		// Localiza clientes en función del nombre y apellido.
		return session.createQuery("FROM Salesemployees").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Mechanics> getMechanics() {
		// Localiza clientes en función del nombre y apellido.
		return session.createQuery("FROM Mechanics").list();
	}
}
