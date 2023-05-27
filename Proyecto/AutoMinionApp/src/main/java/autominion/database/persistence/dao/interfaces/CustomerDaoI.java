package autominion.database.persistence.dao.interfaces;

import java.util.List;

import autominion.database.persistence.entities.Customers;

public interface CustomerDaoI extends CommonDaoI<Customers>{
	/**
	 * Obtiene clientes por nombre y apellido.
	 * 
	 * @param namePattern
	 * @param surnamePattern
	 * @return List<Client>
	 */
	public List<Customers> searchByNameAndSurname(final String namePattern, final String surnamePattern);
	
	
	public List<Customers> getAll();
	
	/**
	 * Localiza un customer por ID en BBDD.
	 * 
	 * @param paramT
	 */
	public Customers searchById(final Long id);
	
}