package autominion.database.persistence.dao.implementations;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Session;

import autominion.database.persistence.dao.interfaces.RepairDaoI;
import autominion.database.persistence.entities.Repairs;
import autominion.database.persistence.entities.composed_id.RepairsId;

public class RepairDaoImpl extends CommonDaoImpl<Repairs> implements RepairDaoI {

	/** Sesión de conexión a BD */

	public RepairDaoImpl(Session session) {
		super(session);
	}

	@Override
	public Repairs searchById(RepairsId id) {
		verifySession();

		// Localiza reparacion buscada
		return (Repairs) session
				.createQuery("FROM Repairs WHERE mechanicId = " + id.getMechanicId() + " AND vehicleToRepair = "
						+ id.getVehicleToRepair() + " AND requestDate = '" + id.getRequestDate() + "'");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Repairs> searchAllNotFinalize() {
		verifySession();

		// Búsqueda de todos los registros.
		return session.createQuery("FROM Repairs WHERE finalize = 0").list();
	}

	@Override
	public Long getRepairs() {
		verifySession();

		return (Long) session.createQuery("SELECT COUNT(s.mechanics) FROM Repairs s WHERE s.finalize = 1")
				.uniqueResult();
	}

	@Override
	public BigDecimal getCollected() {
		verifySession();

		return (BigDecimal) session.createQuery("SELECT SUM(s.estimatedBudget) FROM Repairs s WHERE s.finalize = 1")
				.uniqueResult();
	}

}
