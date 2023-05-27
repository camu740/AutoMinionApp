package autominion.database.services.interfaces;

import java.util.List;

import autominion.database.persistence.entities.Customers;

public interface CustomerManagementServiceI {
	/**
	 * Inserta un nuevo cliente.
	 * 
	 * @param newClient
	 * @return 
	 */
	public Customers insertNewCustomer(final Customers newCustomer);

	/**
	 * Actualiza un cliente existente.
	 * 
	 * @param updatedClient
	 */
	public void updateCustomer(final Customers updatedCustomer);

	/**
	 * Elimina un cliente existente.
	 * 
	 * @param deletedClient
	 */
	public void deleteCustomer(final Customers deletedCustomer);

	/**
	 * Obtiene un cliente mediante su ID.
	 * 
	 * @param clientId
	 */
	public Customers searchById(final Long customerId);

	/**
	 * Obtiene todos los clientes existentes.
	 * 
	 * @return List<Customers>
	 */
	public List<Customers> searchAll();

	/**
	 * Obtiene clientes por nombre y salario.
	 * 
	 * @param namePattern
	 * @param surnamePattern
	 * @return List<Customers>
	 */
	public List<Customers> searchByNameAndSurname(final String namePattern, final String surnamePattern);
}