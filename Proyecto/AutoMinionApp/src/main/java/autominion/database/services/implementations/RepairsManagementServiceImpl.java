package autominion.database.services.implementations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import autominion.database.persistence.dao.implementations.RepairDaoImpl;
import autominion.database.persistence.dao.interfaces.RepairDaoI;
import autominion.database.persistence.entities.Repairs;
import autominion.database.persistence.entities.composed_id.RepairsId;
import autominion.database.services.interfaces.RepairsManagementServiceI;

public class RepairsManagementServiceImpl implements RepairsManagementServiceI {

	private RepairDaoI repairDao;

	/**
	 * Método constructor.
	 */
	public RepairsManagementServiceImpl(final Session session) {
		this.repairDao = new RepairDaoImpl(session);
	}

	@Override
	public Repairs insertNewRepair(Repairs newRepair) {
		// Verificación de nulidad.
		if (newRepair != null) {
			// Insercción del nuevo cliente.
			repairDao.insert(newRepair);
		}

		return newRepair;
	}

	@Override
	public void updateRepair(Repairs updatedRepair) {
		// Verificación de nulidad y existencia.
		if (updatedRepair != null) {

			// Actualización del vehiculo.
			repairDao.update(updatedRepair);
		}

	}

	@Override
	public void deleteRepair(Repairs deletedRepair) {
		// Verificación de nulidad y existencia.
		if (deletedRepair != null) {

			// Eliminación del cliente.
			repairDao.delete(deletedRepair);
		}

	}

	@Override
	public Repairs searchById(RepairsId repairId) {
		// Resultado.
		Repairs repair = null;

		// Verificación de vehiculo.
		if (repairId != null) {

			// Obtención del vehiculo por ID.
			repair = repairDao.searchById(repairId);
		}

		return repair;
	}

	@Override
	public List<Repairs> searchAll() {
		// Resultado.
		List<Repairs> repairList = new ArrayList<>();

		// Obtención de reparaciones.
		repairList = repairDao.searchAll();

		return repairList;
	}

	public List<Repairs> searchAllNotFinalize() {
		// Resultado.
		List<Repairs> repairList = new ArrayList<>();

		// Obtención de reparaciones.
		repairList = repairDao.searchAllNotFinalize();

		// Búsqueda de todos los registros.
		return repairList;
	}

	@Override
	public Long getRepairs() {
		return repairDao.getRepairs();
	}

	@Override
	public BigDecimal getCollected() {
		return repairDao.getCollected();

	}

}
