package autominion.database.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import autominion.database.persistence.dao.implementations.DirectorDaoImpl;
import autominion.database.persistence.dao.interfaces.DirectorDaoI;
import autominion.database.persistence.entities.Director;
import autominion.database.services.interfaces.DirectorManagementServiceI;

public class DirectorManagementServiceImpl implements DirectorManagementServiceI {

	private DirectorDaoI directorDao;

	/**
	 * Método constructor.
	 */
	public DirectorManagementServiceImpl(final Session session) {
		this.directorDao = new DirectorDaoImpl(session);
	}

	@Override
	public Director insertNewDirector(Director newDirector) {
		// Verificación de nulidad e inexistencia.
		if (newDirector != null) {
			// Insercción del nuevo cliente.
			directorDao.insert(newDirector);
		}

		return newDirector;
	}

	@Override
	public void updateDirector(Director updatedDirector) {
		// Verificación de nulidad y existencia.
		if (updatedDirector != null) {
			// Actualización del cliente.
			directorDao.update(updatedDirector);
		}
	}

	@Override
	public void deleteDirector(Director deletedDirector) {
		// Verificación de nulidad y existencia.
		if (deletedDirector != null && (Long) deletedDirector.getId() != null) {

			// Eliminación del cliente.
			directorDao.delete(deletedDirector);
		}
	}

	@Override
	public List<Director> searchAll() {
		// Resultado.
		List<Director> empleadosList = new ArrayList<>();

		empleadosList = directorDao.searchAll();

		return empleadosList;
	}

}
