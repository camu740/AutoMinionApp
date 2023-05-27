package autominion.database.persistence.dao.implementations;

import java.util.List;

import org.hibernate.Session;

import autominion.database.persistence.dao.interfaces.VehicleDaoI;
import autominion.database.persistence.entities.Vehicles;

public class VehicleDaoImpl extends CommonDaoImpl<Vehicles> implements VehicleDaoI {

	/** Sesión de conexión a BD */

	public VehicleDaoImpl(Session session) {
		super(session);
	}
	
	@Override
	public Vehicles searchById(final Long id) {
		verifySession();

		// Búsqueda por PK.
		return (Vehicles) session.createQuery("FROM Vehicles WHERE id = " + id).uniqueResult();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Vehicles> searchByType(String vehicleType) {
		verifySession();

		// Localiza vehiculos en función del tipo
		return session.createQuery("FROM Vehicles WHERE vehicleType = '" + vehicleType + "'").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vehicles> searchByDrivingType(String drivingType) {
		verifySession();

		// Localiza vehiculos en función del tipo de conduccion
		return session.createQuery("FROM Vehicles WHERE drivingType = '" + drivingType + "'").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vehicles> searchByCombustion(String combustion) {
		verifySession();

		// Localiza vehiculos en función del tipo de combustible
		return session.createQuery("FROM Vehicles WHERE combustion = '" + combustion + "'").list();
	}

	@Override
	public Vehicles searchByRegistration(String registration) {
		verifySession();

		// Búsqueda por PK.
		return (Vehicles) session.createQuery("FROM Vehicles WHERE registration = '" + registration + "'").uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Vehicles> getVehiclesOldDate() {
		verifySession();

		// Búsqueda por PK.
		return session.createQuery("FROM Vehicles ORDER BY entry_date").list();
	}
}
