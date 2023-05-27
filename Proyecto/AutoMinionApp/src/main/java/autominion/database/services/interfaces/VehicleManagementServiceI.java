package autominion.database.services.interfaces;

import java.util.List;

import autominion.database.persistence.entities.Vehicles;


public interface VehicleManagementServiceI {
	/**
	 * Inserta un nuevo vehiculo.
	 * 
	 * @param newVehicle
	 * @return 
	 */
	public Vehicles insertNewVehicle(final Vehicles newVehicle);

	/**
	 * Actualiza un vehiculo existente.
	 * 
	 * @param updatedVehicle
	 */
	public void updateVehicle(final Vehicles updatedVehicle);

	/**
	 * Elimina un vehiculo existente.
	 * 
	 * @param deletedVehicle
	 */
	public void deleteVehicle(final Vehicles deletedVehicle);

	/**
	 * Obtiene un vehiculo mediante su ID.
	 * 
	 * @param vehicleId
	 */
	public Vehicles searchById(final Long vehicleId);

	/**
	 * Obtiene todos los vehiculos existentes.
	 * 
	 * @return List<Vehicles>
	 */
	public List<Vehicles> searchAll();
	
	/**
	 * Obtiene vehiculo por su matricula
	 * 
	 * @return Vehicle
	 */
	public Vehicles searchByRegistration(final String registration);
	
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
	
	public List<Vehicles> getVehiclesOldDate();
}
