package autominion.database.services.interfaces;

import java.util.List;

import autominion.database.persistence.entities.Employees;
import autominion.database.persistence.entities.Mechanics;
import autominion.database.persistence.entities.Salesemployees;

public interface EmployeesManagementServiceI {
	public Employees insertNewEmployee(final Employees newEmployee);

	/**
	 * Actualiza un cliente existente.
	 * 
	 * @param updatedClient
	 */
	public void updateEmployee(final Employees updatedEmployee);

	/**
	 * Elimina un cliente existente.
	 * 
	 * @param deletedClient
	 */
	public void deleteEmployee(final Employees deletedEmployee);

	/**
	 * Obtiene un cliente mediante su ID.
	 * 
	 * @param clientId
	 */
	public Employees searchById(final Long employeeId);

	/**
	 * Obtiene todos los clientes existentes.
	 * 
	 * @return List<Customers>
	 */
	public List<Employees> searchAll();

	/**
	 * Obtiene clientes por nombre y salario.
	 * 
	 * @param namePattern
	 * @param surnamePattern
	 * @return List<Customers>
	 */
	public List<Employees> searchByNameAndSurname(final String namePattern, final String surnamePattern);
	
	public Employees searchByEmail(String email);
	
	public List<Salesemployees> getSalesemployees();
	
	public List<Mechanics> getMechanics();
}
