package autominion.database.services.interfaces;

import java.util.List;

import autominion.database.persistence.entities.Mechanics;

public interface MechanicsManagementServiceI {
	public Mechanics insertNewMechanics(final Mechanics newMechanics);

	public void updateMechanics(final Mechanics updatedMechanics);

	public void deleteMechanics(final Mechanics deletedMechanics);

	public Mechanics searchById(final Long mechanicsId);

	public List<Mechanics> searchAll();
	
	public Mechanics searchBoss();
}
