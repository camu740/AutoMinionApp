package autominion.database.persistence.dao.interfaces;

import java.math.BigDecimal;
import java.util.List;

import autominion.database.persistence.entities.Repairs;
import autominion.database.persistence.entities.composed_id.RepairsId;

public interface RepairDaoI extends CommonDaoI<Repairs> {
	
	/**
	 * Obtener reparaciones por su id
	 * @param id (id repair formada por id de mecanico, de vehiculo a arreglar y fecha)
	 * @return Repair buscada
	 */
	public Repairs searchById(final RepairsId id);

	/**
	 * Obtener reparaciones que no han finalizado
	 * @return lista de reparaciones no finalizadas
	 */
	public List<Repairs> searchAllNotFinalize();

	public Long getRepairs();

	public BigDecimal getCollected();
	
}
