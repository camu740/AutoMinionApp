package autominion.database.persistence.dao.interfaces;

import java.util.List;

import autominion.database.persistence.entities.Vehicles;

public interface VehicleDaoI extends CommonDaoI<Vehicles> {
	
	/**
	 * Obtener vehiculos dependiendo del tipo
	 * @param vehicleType tipo de vehiculo (coche, moto, ciclomotor)
	 * @return lista de vehiculos de ese tipo
	 */
	public List<Vehicles> searchByType(final String vehicleType);
	
	/**
	 * Obtener vehiculos dependiendo del tipo de conduccion
	 * @param drivingType Tipo de conduccion (manual, automatico)
	 * @return lista de vehiculos de este tipo
	 */
	public List<Vehicles> searchByDrivingType(final String drivingType);
	
	/**
	 * Obtener vehiculos dependiendo del tipo de combustion
	 * @param combustion Tipo de combustion (Diesel, gasolina, electrico, hibrido)
	 * @return lista de vehiculos de este tipo
	 */
	public List<Vehicles> searchByCombustion(final String combustion);
	
	/**
	 * Localiza un vehiculo por ID en BBDD.
	 * 
	 * @param paramT
	 */
	public Vehicles searchById(final Long id);
	
	/**
	 * Obtiene vehiculo por su matricula
	 * 
	 * @return Vehicle
	 */
	public Vehicles searchByRegistration(final String registration);

	public List<Vehicles> getVehiclesOldDate();
}
