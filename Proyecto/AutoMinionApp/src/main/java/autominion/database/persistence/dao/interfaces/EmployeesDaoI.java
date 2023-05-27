package autominion.database.persistence.dao.interfaces;

import java.util.List;

import autominion.database.persistence.entities.Employees;
import autominion.database.persistence.entities.Mechanics;
import autominion.database.persistence.entities.Salesemployees;

public interface EmployeesDaoI extends CommonDaoI<Employees> {

	public List<Employees> searchByNameAndSurname(final String namePattern, final String surnamePattern);

	public Employees searchById(final Long id);

	public List<Employees> getAll();
	
	public Employees searchByEmail(String email);
	
	public List<Salesemployees> getSalesEmployees();
	
	public List<Mechanics> getMechanics();
}
