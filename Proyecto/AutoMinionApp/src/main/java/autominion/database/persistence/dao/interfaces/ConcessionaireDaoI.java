package autominion.database.persistence.dao.interfaces;

import autominion.database.persistence.entities.Concessionaires;

public interface ConcessionaireDaoI extends CommonDaoI<Concessionaires>{
	/**
	 * Localiza un concessionaire por ID en BBDD.
	 * 
	 * @param paramT
	 */
	public Concessionaires searchById(final Long id);
}
