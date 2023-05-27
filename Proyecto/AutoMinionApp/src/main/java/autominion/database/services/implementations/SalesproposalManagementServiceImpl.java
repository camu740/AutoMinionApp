package autominion.database.services.implementations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import autominion.database.persistence.dao.implementations.SalesproposalDaoImpl;
import autominion.database.persistence.dao.interfaces.SalesproposalDaoI;
import autominion.database.persistence.entities.Salesemployees;
import autominion.database.persistence.entities.Salesproposal;
import autominion.database.services.interfaces.SalesproposalManagementServiceI;

public class SalesproposalManagementServiceImpl implements SalesproposalManagementServiceI {
	private SalesproposalDaoI salesproposalDao;

	/**
	 * Método constructor.
	 */
	public SalesproposalManagementServiceImpl(final Session session) {
		this.salesproposalDao = new SalesproposalDaoImpl(session);
	}

	@Override
	public Salesproposal insertNewSalesproposal(Salesproposal newSales) {
		// Verificación de nulidad e inexistencia.
		if (newSales != null) {
			// Insercción del nuevo cliente.
			salesproposalDao.insert(newSales);
		}
		return newSales;
	}

	@Override
	public void updateSalesproposal(Salesproposal updatedSales) {
		// Verificación de nulidad y existencia.
		if (updatedSales != null) {

			// Actualización del cliente.
			salesproposalDao.update(updatedSales);
		}
	}

	@Override
	public void deleteSalesproposal(Salesproposal deletedSales) {
		// Verificación de nulidad y existencia.
		if (deletedSales != null && deletedSales.getId() != null) {

			// Eliminación del cliente.
			salesproposalDao.delete(deletedSales);
		}

	}

	@Override
	public List<Salesproposal> searchAll() {
		// Resultado.
		List<Salesproposal> salesList = new ArrayList<>();

		salesList = salesproposalDao.searchAll();

		return salesList;
	}

	@Override
	public Salesemployees searchById(Long employeeId) {
		// Resultado.
		Salesemployees sales = null;

		// Verificación de nulidad.
		if (employeeId != null) {

			// Obtención del cliente por ID.
			sales = salesproposalDao.searchById(employeeId);
		}

		return sales;
	}

	@Override
	public List<Salesproposal> searchProposal() {
		// Resultado.
		List<Salesproposal> salesList = new ArrayList<>();

		salesList = salesproposalDao.searchProposal();

		return salesList;
	}

	@Override
	public Long getSales() {
		return salesproposalDao.getSales();
		
	}

	@Override
	public BigDecimal getCollected() {
		return salesproposalDao.getCollected();
		
	}

}
