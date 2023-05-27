package autominion.database.services.interfaces;

import autominion.database.persistence.entities.Concessionaires;

public interface ConcessionaireManagementServiceI {
	/**
	 * Obtiene un concesionario mediante su ID.
	 * 
	 * @param concessionaireId
	 */
	public Concessionaires searchById(final Long concessionaireId);
}
