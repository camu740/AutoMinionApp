package autominion.database.persistence.entities;
// default package
// Generated 2 feb 2023 9:48:16 by Hibernate Tools 4.3.6.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Concessionaires generated by hbm2java
 */
@Entity
@Table(name = "concessionaires", catalog = "autominionbbdd")
public class Concessionaires extends AbstractEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;
	private String name;
	private String address;
	private Integer cp;
	private String city;
	private Set<Employees> employeeses = new HashSet<Employees>(0);
	private Set<Vehicles> vehicleses = new HashSet<Vehicles>(0);

	public Concessionaires() {
	}

	public Concessionaires(long id) {
		this.id = id;
	}

	public Concessionaires(String name, String address, Integer cp, String city, Set<Employees> employeeses,
			Set<Vehicles> vehicleses) {
		this.name = name;
		this.address = address;
		this.cp = cp;
		this.city = city;
		this.employeeses = employeeses;
		this.vehicleses = vehicleses;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "name", length = 30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "address", length = 50)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "CP")
	public Integer getCp() {
		return this.cp;
	}

	public void setCp(Integer cp) {
		this.cp = cp;
	}

	@Column(name = "City", length = 50)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "concessionaires")
	public Set<Employees> getEmployeeses() {
		return this.employeeses;
	}

	public void setEmployeeses(Set<Employees> employeeses) {
		this.employeeses = employeeses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "concessionaires")
	public Set<Vehicles> getVehicleses() {
		return this.vehicleses;
	}

	public void setVehicleses(Set<Vehicles> vehicleses) {
		this.vehicleses = vehicleses;
	}

}
