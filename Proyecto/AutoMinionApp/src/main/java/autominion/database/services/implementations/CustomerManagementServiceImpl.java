package autominion.database.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import autominion.database.persistence.dao.implementations.CustomerDaoImpl;
import autominion.database.persistence.dao.interfaces.CustomerDaoI;
import autominion.database.persistence.entities.Customers;
import autominion.database.services.interfaces.CustomerManagementServiceI;
import org.apache.commons.lang3.StringUtils;


public class CustomerManagementServiceImpl implements CustomerManagementServiceI {

	private CustomerDaoI customerDao;

	/**
	 * Método constructor.
	 */
	public CustomerManagementServiceImpl(final Session session) {
		this.customerDao = new CustomerDaoImpl(session);
	}

	@Override
	public void updateCustomer(Customers updatedCustomer) {
		// Verificación de nulidad y existencia.
		if (updatedCustomer != null) {

			// Actualización del cliente.
			customerDao.update(updatedCustomer);
		}

	}

	@Override
	public Customers searchById(Long clienteId) {
		// Resultado.
		Customers cliente = null;

		// Verificación de nulidad.
		if (clienteId != null) {

			// Obtención del cliente por ID.
			cliente = customerDao.searchById(clienteId);
		}

		return cliente;
	}

	@Override
	public List<Customers> searchAll() {
		// Resultado.
		List<Customers> clienteList = new ArrayList<>();

		// Obtención de clientes.
		clienteList = customerDao.searchAll();

		return clienteList;
	}

	@Override
	public List<Customers> searchByNameAndSurname(String namePattern, String surnamePattern) {
		// Resultado.
		List<Customers> clienteList = new ArrayList<>();

		// Verificación de nulidad.
		if (StringUtils.isNotBlank(namePattern) && StringUtils.isNotBlank(surnamePattern)) {

			// Obtención del cliente por nombre y apellido.
			clienteList = customerDao.searchByNameAndSurname(namePattern, surnamePattern);
		}

		return clienteList;
	}

	@Override
	public Customers insertNewCustomer(Customers newCustomer) {
		// Verificación de nulidad e inexistencia.
		if (newCustomer != null) {
			// Insercción del nuevo cliente.
			customerDao.insert(newCustomer);
		}

		return newCustomer;
	}

	@Override
	public void deleteCustomer(Customers deletedCustomer) {
		// Verificación de nulidad y existencia.
		if (deletedCustomer != null && (Long) deletedCustomer.getId() != null) {

			// Eliminación del cliente.
			customerDao.delete(deletedCustomer);
		}
	}

}