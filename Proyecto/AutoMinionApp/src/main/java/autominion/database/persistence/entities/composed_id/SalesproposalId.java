package autominion.database.persistence.entities.composed_id;
// default package
// Generated 2 feb 2023 9:48:16 by Hibernate Tools 4.3.6.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SalesproposalId generated by hbm2java
 */
@Embeddable
public class SalesproposalId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long salesEmployeeId;
	private long customerId;
	private long vehicleId;
	private Date proposalDate;

	public SalesproposalId() {
	}

	public SalesproposalId(long salesEmployeeId, long customerId, long vehicleId, Date proposalDate) {
		this.salesEmployeeId = salesEmployeeId;
		this.customerId = customerId;
		this.vehicleId = vehicleId;
		this.proposalDate = proposalDate;
	}

	@Column(name = "salesEmployeeId", nullable = false)
	public long getSalesEmployeeId() {
		return this.salesEmployeeId;
	}

	public void setSalesEmployeeId(long salesEmployeeId) {
		this.salesEmployeeId = salesEmployeeId;
	}

	@Column(name = "customerId", nullable = false)
	public long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	@Column(name = "vehicleId", nullable = false)
	public long getVehicleId() {
		return this.vehicleId;
	}

	public void setVehicleId(long vehicleId) {
		this.vehicleId = vehicleId;
	}

	@Column(name = "proposal_date", nullable = false, length = 10)
	public Date getProposalDate() {
		return this.proposalDate;
	}

	public void setProposalDate(Date proposalDate) {
		this.proposalDate = proposalDate;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SalesproposalId))
			return false;
		SalesproposalId castOther = (SalesproposalId) other;

		return (this.getSalesEmployeeId() == castOther.getSalesEmployeeId())
				&& (this.getCustomerId() == castOther.getCustomerId())
				&& (this.getVehicleId() == castOther.getVehicleId())
				&& ((this.getProposalDate() == castOther.getProposalDate())
						|| (this.getProposalDate() != null && castOther.getProposalDate() != null
								&& this.getProposalDate().equals(castOther.getProposalDate())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getSalesEmployeeId();
		result = 37 * result + (int) this.getCustomerId();
		result = 37 * result + (int) this.getVehicleId();
		result = 37 * result + (getProposalDate() == null ? 0 : this.getProposalDate().hashCode());
		return result;
	}

}
