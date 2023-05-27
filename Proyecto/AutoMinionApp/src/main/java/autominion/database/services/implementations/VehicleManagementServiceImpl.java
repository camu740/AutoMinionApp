package autominion.database.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;

import autominion.database.persistence.dao.implementations.VehicleDaoImpl;
import autominion.database.persistence.dao.interfaces.VehicleDaoI;
import autominion.database.persistence.entities.Vehicles;
import autominion.database.services.interfaces.VehicleManagementServiceI;

public class VehicleManagementServiceImpl implements VehicleManagementServiceI {

	private VehicleDaoI vehicleDao;

	/**
	 * Método constructor.
	 */
	public VehicleManagementServiceImpl(final Session session) {
		this.vehicleDao = new VehicleDaoImpl(session);
	}

	@Override
	public Vehicles insertNewVehicle(Vehicles newVehicle) {
		// Verificación de nulidad.
		if (newVehicle != null) {
			// Insercción del nuevo cliente.
			vehicleDao.insert(newVehicle);
		}

		return newVehicle;
	}

	@Override
	public void updateVehicle(Vehicles updatedVehicle) {
		// Verificación de nulidad y existencia.
		if (updatedVehicle != null && (Long) updatedVehicle.getId() != null) {

			// Actualización del vehiculo.
			vehicleDao.update(updatedVehicle);
		}
	}

	@Override
	public void deleteVehicle(Vehicles deletedVehicle) {
		// Verificación de nulidad y existencia.
		if (deletedVehicle != null && (Long) deletedVehicle.getId() != null) {

			// Eliminación del cliente.
			vehicleDao.delete(deletedVehicle);
		}
	}

	@Override
	public Vehicles searchById(Long vehicleId) {
		// Resultado.
		Vehicles vehicle = null;

		// Verificación de vehiculo.
		if (vehicleId != null) {

			// Obtención del vehiculo por ID.
			vehicle = vehicleDao.searchById(vehicleId);
		}

		return vehicle;
	}

	@Override
	public List<Vehicles> searchAll() {
		// Resultado.
		List<Vehicles> vehicleList = new ArrayList<>();

		// Obtención de vehiculos.
		vehicleList = vehicleDao.searchAll();

		return vehicleList;
	}

	@Override
	public List<Vehicles> searchByType(String vehicleType) {
		// Resultado.
		List<Vehicles> vehicleList = new ArrayList<>();

		// Verificación de nulidad.
		if (StringUtils.isNotBlank(vehicleType)) {

			// Obtención del vehiculo por tipo
			vehicleList = vehicleDao.searchByType(vehicleType);
		}

		return vehicleList;
	}

	@Override
	public List<Vehicles> searchByDrivingType(String drivingType) {
		// Resultado.
		List<Vehicles> vehicleList = new ArrayList<>();

		// Verificación de nulidad.
		if (StringUtils.isNotBlank(drivingType)) {

			// Obtención del vehiculo por tipo de conduccion.
			vehicleList = vehicleDao.searchByDrivingType(drivingType);
		}

		return vehicleList;
	}

	@Override
	public List<Vehicles> searchByCombustion(String combustion) {
		// Resultado.
		List<Vehicles> vehicleList = new ArrayList<>();

		// Verificación de nulidad.
		if (StringUtils.isNotBlank(combustion)) {

			// Obtención del vehiculo por tipo de combustion
			vehicleList = vehicleDao.searchByCombustion(combustion);
		}

		return vehicleList;
	}

	@Override
	public Vehicles searchByRegistration(String registration) {
		// Resultado.
		Vehicles vehicle = null;

		// Verificación de vehiculo.
		if (registration != null) {

			// Obtención del vehiculo por ID.
			vehicle = vehicleDao.searchByRegistration(registration);
		}

		return vehicle;
	}

	@Override
	public List<Vehicles> getVehiclesOldDate() {
		return vehicleDao.getVehiclesOldDate();
	}

}
