package autominion.database.services.implementations;

import java.util.List;

import org.hibernate.Session;

import autominion.database.persistence.dao.implementations.MechanicsDaoImpl;
import autominion.database.persistence.dao.interfaces.MechanicsDaoI;
import autominion.database.persistence.entities.Mechanics;
import autominion.database.services.interfaces.MechanicsManagementServiceI;

public class MechanicsManagementServiceImpl implements MechanicsManagementServiceI {
	private MechanicsDaoI mechanicsDao;

	/**
	 * Método constructor.
	 */
	public MechanicsManagementServiceImpl(final Session session) {
		this.mechanicsDao = new MechanicsDaoImpl(session);
	}

	@Override
	public void updateMechanics(Mechanics updatedEmployee) {
		// Verificación de nulidad y existencia.
		if (updatedEmployee != null) {
			// Actualización del cliente.
			mechanicsDao.update(updatedEmployee);
		}
	}

	@Override
	public Mechanics searchById(Long clienteId) {
		// Resultado.
		Mechanics mecanico = null;

		// Verificación de nulidad.
		if (clienteId != null) {

			// Obtención del cliente por ID.
			mecanico = mechanicsDao.searchById(clienteId);
		}

		return mecanico;
	}

	@Override
	public List<Mechanics> searchAll() {
		return mechanicsDao.searchAll();
	}

	@Override
	public Mechanics insertNewMechanics(Mechanics newMechanics) {
		// Verificación de nulidad e inexistencia.
		if (newMechanics != null) {
			// Insercción del nuevo cliente.
			mechanicsDao.insert(newMechanics);
		}

		return newMechanics;
	}

	@Override
	public void deleteMechanics(Mechanics deletedMechanics) {
		// Verificación de nulidad y existencia.
		if (deletedMechanics != null && (Long) deletedMechanics.getId() != null) {

			// Eliminación del cliente.
			mechanicsDao.delete(deletedMechanics);
		}
	}

	public Mechanics searchBoss() {
		return mechanicsDao.searchBoss();
	}
}
