package autominion.database.services.interfaces;

import java.math.BigDecimal;
import java.util.List;

import autominion.database.persistence.entities.Repairs;
import autominion.database.persistence.entities.composed_id.RepairsId;


public interface RepairsManagementServiceI {
	/**
	 * Inserta una nueva reparacion.
	 * 
	 * @param newRepair
	 * @return 
	 */
	public Repairs insertNewRepair(final Repairs newRepair);

	/**
	 * Actualiza una reparacion existente.
	 * 
	 * @param updatedRepair
	 */
	public void updateRepair(final Repairs updatedRepair);

	/**
	 * Elimina una reparacion existente.
	 * 
	 * @param deletedRepair
	 */
	public void deleteRepair(final Repairs deletedRepair);

	/**
	 * Obtiene una reparacion mediante su ID.
	 * 
	 * @param repairId
	 */
	public Repairs searchById(final RepairsId repairId);
	
	/**
	 * Obtiene todas las reparaciones existentes.
	 * 
	 * @return List<Repairs>
	 */
	public List<Repairs> searchAll();
	
	/**
	 * Obtiene todas las reparaciones existentes no finalizadas.
	 * 
	 * @return List<Repairs>
	 */
	public List<Repairs> searchAllNotFinalize();

	public Long getRepairs();

	public BigDecimal getCollected();

}
