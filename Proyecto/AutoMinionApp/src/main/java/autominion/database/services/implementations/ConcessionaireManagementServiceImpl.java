package autominion.database.services.implementations;

import org.hibernate.Session;

import autominion.database.persistence.dao.implementations.ConcessionaireDaoImpl;
import autominion.database.persistence.dao.interfaces.ConcessionaireDaoI;
import autominion.database.persistence.entities.Concessionaires;
import autominion.database.services.interfaces.ConcessionaireManagementServiceI;

public class ConcessionaireManagementServiceImpl implements ConcessionaireManagementServiceI {
	
	private ConcessionaireDaoI concessionaireDao;

	/**
	 * Método constructor.
	 */
	public ConcessionaireManagementServiceImpl(final Session session) {
		this.concessionaireDao = new ConcessionaireDaoImpl(session);
	}
	
	@Override
	public Concessionaires searchById(Long concessionaireId) {
		// Resultado.
		Concessionaires concessionaire = null;

		// Verificación de nulidad.
		if (concessionaireId != null) {

			// Obtención del cliente por ID.
			concessionaire = concessionaireDao.searchById(concessionaireId);
		}

		return concessionaire;
	}

}
