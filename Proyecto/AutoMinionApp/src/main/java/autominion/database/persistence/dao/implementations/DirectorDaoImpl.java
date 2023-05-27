package autominion.database.persistence.dao.implementations;

import org.hibernate.Session;

import autominion.database.persistence.dao.interfaces.DirectorDaoI;
import autominion.database.persistence.entities.Director;

public class DirectorDaoImpl extends CommonDaoImpl<Director> implements DirectorDaoI{

	public DirectorDaoImpl(Session session) {
		super(session);
	}

}
