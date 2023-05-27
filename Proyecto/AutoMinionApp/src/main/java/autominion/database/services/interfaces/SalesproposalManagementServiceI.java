package autominion.database.services.interfaces;

import java.math.BigDecimal;
import java.util.List;

import autominion.database.persistence.entities.Salesemployees;
import autominion.database.persistence.entities.Salesproposal;

public interface SalesproposalManagementServiceI {
	public Salesproposal insertNewSalesproposal(final Salesproposal newEmployee);

	public void updateSalesproposal(final Salesproposal updatedEmployee);

	public void deleteSalesproposal(final Salesproposal deletedEmployee);

	public List<Salesproposal> searchAll();

	public Salesemployees searchById(Long employeeId);
	
	public List<Salesproposal> searchProposal();

	public Long getSales();

	public BigDecimal getCollected();
}
