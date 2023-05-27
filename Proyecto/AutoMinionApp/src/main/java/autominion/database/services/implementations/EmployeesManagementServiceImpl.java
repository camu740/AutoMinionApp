package autominion.database.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;

import autominion.database.persistence.dao.implementations.EmployeesDaoImpl;
import autominion.database.persistence.dao.interfaces.EmployeesDaoI;
import autominion.database.persistence.entities.Employees;
import autominion.database.persistence.entities.Mechanics;
import autominion.database.persistence.entities.Salesemployees;
import autominion.database.services.interfaces.EmployeesManagementServiceI;

public class EmployeesManagementServiceImpl implements EmployeesManagementServiceI {
	private EmployeesDaoI employeesDao;

	/**
	 * Método constructor.
	 */
	public EmployeesManagementServiceImpl(final Session session) {
		this.employeesDao = new EmployeesDaoImpl(session);
	}

	@Override
	public void updateEmployee(Employees updatedEmployee) {
		// Verificación de nulidad y existencia.
		if (updatedEmployee != null) {

			// Actualización del cliente.
			employeesDao.update(updatedEmployee);
		}

	}

	@Override
	public Employees searchById(Long clienteId) {
		// Resultado.
		Employees cliente = null;

		// Verificación de nulidad.
		if (clienteId != null) {

			// Obtención del cliente por ID.
			cliente = employeesDao.searchById(clienteId);
		}

		return cliente;
	}

	@Override
	public List<Employees> searchAll() {
		// Resultado.
		List<Employees> empleadosList = new ArrayList<>();

		empleadosList = employeesDao.searchAll();

		return empleadosList;
	}

	@Override
	public List<Employees> searchByNameAndSurname(String namePattern, String surnamePattern) {
		// Resultado.
		List<Employees> clienteList = new ArrayList<>();

		// Verificación de nulidad.
		if (StringUtils.isNotBlank(namePattern) && StringUtils.isNotBlank(surnamePattern)) {

			// Obtención del cliente por nombre y apellido.
			clienteList = employeesDao.searchByNameAndSurname(namePattern, surnamePattern);
		}

		return clienteList;
	}

	@Override
	public Employees searchByEmail(String email) {
		// Resultado.
		Employees empleadoList = new Employees();

		// Verificación de nulidad.
		if (StringUtils.isNotBlank(email)) {

			// Obtención del cliente por nombre y apellido.
			empleadoList = employeesDao.searchByEmail(email);
		}

		return empleadoList;
	}

	@Override
	public Employees insertNewEmployee(Employees newEmployee) {
		// Verificación de nulidad e inexistencia.
		if (newEmployee != null) {
			// Insercción del nuevo cliente.
			employeesDao.insert(newEmployee);
		}

		return newEmployee;
	}

	@Override
	public void deleteEmployee(Employees deletedEmployee) {
		// Verificación de nulidad y existencia.
		if (deletedEmployee != null && (Long) deletedEmployee.getId() != null) {

			// Eliminación del cliente.
			employeesDao.delete(deletedEmployee);
		}
	}

	@Override
	public List<Salesemployees> getSalesemployees() {
		// Resultado.
		List<Salesemployees> empleadosList = new ArrayList<>();

		empleadosList = employeesDao.getSalesEmployees();

		return empleadosList;
	}

	@Override
	public List<Mechanics> getMechanics() {
		// Resultado.
		List<Mechanics> empleadosList = new ArrayList<>();

		empleadosList = employeesDao.getMechanics();

		return empleadosList;
	}

}
