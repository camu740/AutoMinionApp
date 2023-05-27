package autominion.database.persistence.entities;
// default package
// Generated 2 feb 2023 9:48:16 by Hibernate Tools 4.3.6.Final

import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;


import autominion.database.persistence.entities.composed_id.RepairsId;

/**
 * Repairs generated by hbm2java
 */
@Entity
@Table(name = "repairs", catalog = "autominionbbdd")
public class Repairs extends AbstractEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private RepairsId id;
	private Customers customers;
	private Mechanics mechanics;
	private Vehicles vehicles;
	private BigDecimal estimatedBudget;
	private BigDecimal estimatedTime;
	private String details;
	private String priority;
	private String partsList;
	private Date finalizeDate;
	private Boolean finalize;

	public Repairs() {
	}

	public Repairs(RepairsId id, Mechanics mechanics, Vehicles vehicles) {
		this.id = id;
		this.mechanics = mechanics;
		this.vehicles = vehicles;
	}

	public Repairs(RepairsId id, Customers customers, BigDecimal estimatedBudget, 
			BigDecimal estimatedTime, String details, String priority, String partsList,
			Date finalizeDate, Boolean finalize) {
		this.id = id;
		this.customers = customers;
		this.estimatedBudget = estimatedBudget;
		this.estimatedTime = estimatedTime;
		this.details = details;
		this.priority = priority;
		this.partsList = partsList;
		this.finalizeDate = finalizeDate;
		this.finalize = finalize;
	}
	
	public Repairs(RepairsId id, Customers customers, BigDecimal estimatedBudget, 
			BigDecimal estimatedTime, String details, String priority, String partsList) {
		this.id = id;
		this.customers = customers;
		this.estimatedBudget = estimatedBudget;
		this.estimatedTime = estimatedTime;
		this.details = details;
		this.priority = priority;
		this.partsList = partsList;
		this.finalizeDate = null;
		this.finalize = false;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "mechanicId", column = @Column(name = "mechanicId", nullable = false)),
			@AttributeOverride(name = "vehicleToRepair", column = @Column(name = "vehicleToRepair", nullable = false)),
			@AttributeOverride(name = "requestDate", column = @Column(name = "request_date", nullable = false, length = 10)) })
	public RepairsId getId() {
		return this.id;
	}

	public void setId(RepairsId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerId")
	public Customers getCustomers() {
		return this.customers;
	}

	public void setCustomers(Customers customers) {
		this.customers = customers;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mechanicId", nullable = false, insertable = false, updatable = false)
	public Mechanics getMechanics() {
		return this.mechanics;
	}

	public void setMechanics(Mechanics mechanics) {
		this.mechanics = mechanics;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vehicleToRepair", nullable = false, insertable = false, updatable = false)
	public Vehicles getVehicles() {
		return this.vehicles;
	}

	public void setVehicles(Vehicles vehicles) {
		this.vehicles = vehicles;
	}

	@Column(name = "estimatedBudget", precision = 16)
	public BigDecimal getEstimatedBudget() {
		return this.estimatedBudget;
	}

	public void setEstimatedBudget(BigDecimal estimatedBudget) {
		this.estimatedBudget = estimatedBudget;
	}

	@Column(name = "estimatedTime", precision = 16)
	public BigDecimal getEstimatedTime() {
		return this.estimatedTime;
	}

	public void setEstimatedTime(BigDecimal estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	@Column(name = "Details", length = 65535)
	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Column(name = "Priority", length = 20)
	public String getPriority() {
		return this.priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	@Column(name = "partsList", length = 65535)
	public String getPartsList() {
		return this.partsList;
	}

	public void setPartsList(String partsList) {
		this.partsList = partsList;
	}

	@Column(name = "finalize_date", length = 10)
	public Date getFinalizeDate() {
		return this.finalizeDate;
	}

	public void setFinalizeDate(Date finalizeDate) {
		this.finalizeDate = finalizeDate;
	}

	@Column(name = "finalize")
	public Boolean getFinalize() {
		return this.finalize;
	}

	public void setFinalize(Boolean finalize) {
		this.finalize = finalize;
	}

}