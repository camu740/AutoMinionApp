package autominion.database.persistence.dao.interfaces;

import autominion.database.persistence.entities.Mechanics;

public interface MechanicsDaoI extends CommonDaoI<Mechanics>{
	public Mechanics searchById(long id);
	public Mechanics searchBoss();
}
