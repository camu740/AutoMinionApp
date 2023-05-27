package autominion.database.persistence.dao.implementations;

import org.hibernate.Session;

import autominion.database.persistence.dao.interfaces.ConcessionaireDaoI;
import autominion.database.persistence.entities.Concessionaires;

public class ConcessionaireDaoImpl extends CommonDaoImpl<Concessionaires> implements ConcessionaireDaoI {

	public ConcessionaireDaoImpl(Session session) {
		super(session);
	}

	@Override
	public Concessionaires searchById(Long id) {
		verifySession();

		// Búsqueda por PK.
		return (Concessionaires) session.createQuery("FROM Concessionaires WHERE id = " + id).uniqueResult();
	}

}
