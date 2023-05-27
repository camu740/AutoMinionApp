package autominion.database.persistence.dao.interfaces;

import java.math.BigDecimal;
import java.util.List;

import autominion.database.persistence.entities.Salesemployees;
import autominion.database.persistence.entities.Salesproposal;

public interface SalesproposalDaoI extends CommonDaoI<Salesproposal> {

	Salesemployees searchById(Long employeeId);
	List<Salesproposal> searchProposal();
	Long getSales();
	BigDecimal getCollected();
}
