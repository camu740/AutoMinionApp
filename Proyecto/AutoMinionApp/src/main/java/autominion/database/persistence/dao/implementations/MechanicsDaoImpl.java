package autominion.database.persistence.dao.implementations;

import org.hibernate.Session;

import autominion.database.persistence.dao.interfaces.MechanicsDaoI;
import autominion.database.persistence.entities.Mechanics;

public class MechanicsDaoImpl extends CommonDaoImpl<Mechanics> implements MechanicsDaoI {

	public MechanicsDaoImpl(Session session) {
		super(session);
	}
	
	public Mechanics searchById(long id) {
		verifySession();

		// Búsqueda por PK.
		return (Mechanics) session.createQuery("FROM Mechanics WHERE id = " + id).list().get(0);
	}
	
	public Mechanics searchBoss() {
		verifySession();

		// Búsqueda por PK.
		return (Mechanics) session.createQuery("FROM Mechanics WHERE id = bossId").list().get(0);
	}
}
