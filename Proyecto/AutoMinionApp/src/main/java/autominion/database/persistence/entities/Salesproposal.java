package autominion.database.persistence.entities;
// default package
// Generated 2 feb 2023 9:48:16 by Hibernate Tools 4.3.6.Final

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import autominion.database.persistence.entities.composed_id.SalesproposalId;

/**
 * Salesproposal generated by hbm2java
 */
@Entity
@Table(name = "salesproposal", catalog = "autominionbbdd")
public class Salesproposal extends AbstractEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SalesproposalId id;
	private Customers customers;
	private Salesemployees salesemployees;
	private Vehicles vehicles;
	private BigDecimal proposalPrice;
	private Boolean sold;
	private Date soldDate;

	public Salesproposal() {
	}

	/**
	 * 	Constructor para ventas
	 * @param id
	 * @param customers
	 * @param salesemployees
	 * @param vehicles
	 * @param proposalPrice
	 * @param sold
	 * @param soldDate
	 */
	public Salesproposal(SalesproposalId id, Customers customers, Salesemployees salesemployees, Vehicles vehicles,
			BigDecimal proposalPrice, Boolean sold, Date soldDate) {
		this.id = id;
		this.customers = customers;
		this.salesemployees = salesemployees;
		this.vehicles = vehicles;
		this.proposalPrice = proposalPrice;
		this.sold = sold;
		this.soldDate = soldDate;
	}

	/**
	 * Constructor para propuestas no vendidas
	 * @param id
	 * @param customers
	 * @param salesemployees
	 * @param vehicles
	 * @param proposalPrice
	 */
	public Salesproposal(SalesproposalId id, Customers customers, Salesemployees salesemployees, Vehicles vehicles,
			BigDecimal proposalPrice) {
		this.id = id;
		this.customers = customers;
		this.salesemployees = salesemployees;
		this.vehicles = vehicles;
		this.proposalPrice = proposalPrice;
		this.sold = false;
		this.soldDate = null;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "salesEmployeeId", column = @Column(name = "salesEmployeeId", nullable = false)),
			@AttributeOverride(name = "customerId", column = @Column(name = "customerId", nullable = false)),
			@AttributeOverride(name = "vehicleId", column = @Column(name = "vehicleId", nullable = false)),
			@AttributeOverride(name = "proposalDate", column = @Column(name = "proposal_date", nullable = false, length = 10)) })
	public SalesproposalId getId() {
		return this.id;
	}

	public void setId(SalesproposalId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerId", nullable = false, insertable = false, updatable = false)
	public Customers getCustomers() {
		return this.customers;
	}

	public void setCustomers(Customers customers) {
		this.customers = customers;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "salesEmployeeId", nullable = false, insertable = false, updatable = false)
	public Salesemployees getSalesemployees() {
		return this.salesemployees;
	}

	public void setSalesemployees(Salesemployees salesemployees) {
		this.salesemployees = salesemployees;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vehicleId", nullable = false, insertable = false, updatable = false)
	public Vehicles getVehicles() {
		return this.vehicles;
	}

	public void setVehicles(Vehicles vehicles) {
		this.vehicles = vehicles;
	}

	@Column(name = "proposalPrice", precision = 16)
	public BigDecimal getProposalPrice() {
		return this.proposalPrice;
	}

	public void setProposalPrice(BigDecimal proposalPrice) {
		this.proposalPrice = proposalPrice;
	}

	@Column(name = "sold")
	public Boolean getSold() {
		return this.sold;
	}

	public void setSold(Boolean sold) {
		this.sold = sold;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "sold_date", length = 10)
	public Date getSoldDate() {
		return this.soldDate;
	}

	public void setSoldDate(Date soldDate) {
		this.soldDate = soldDate;
	}

}
