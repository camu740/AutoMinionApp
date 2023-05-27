package autominion.database.persistence.dao.implementations;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Session;

import autominion.database.persistence.dao.interfaces.SalesproposalDaoI;
import autominion.database.persistence.entities.Salesemployees;
import autominion.database.persistence.entities.Salesproposal;

public class SalesproposalDaoImpl extends CommonDaoImpl<Salesproposal> implements SalesproposalDaoI {

	public SalesproposalDaoImpl(Session session) {
		super(session);
	}

	@Override
	public Salesemployees searchById(Long employeeId) {
		verifySession();

		// Búsqueda por PK.
		return (Salesemployees) session.createQuery("FROM Salesemployees WHERE id = " + employeeId).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Salesproposal> searchProposal() {
		verifySession();

		// Búsqueda por PK.
		return session.createQuery("FROM Salesproposal WHERE sold != 1").list();
	}

	@Override
	public Long getSales() {
		verifySession();
		
		return (Long) session.createQuery("SELECT COUNT(s.salesemployees) FROM Salesproposal s WHERE s.sold = 1").uniqueResult();
	}

	@Override
	public BigDecimal getCollected() {
		verifySession();
		
		return (BigDecimal) session.createQuery("SELECT SUM(s.proposalPrice) FROM Salesproposal s WHERE s.sold = 1").uniqueResult();
		
	}
}
