package org.sunit.CarWash.CustomerApp.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The Class CustomerTO.
 * @author SOMESH
 */
@Entity(name = "Customer")
public class CustomerTO{
	
	/** The cust id. */
	@Id 
	@Column (name = "CUSTID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUST_SEQ" )
    @SequenceGenerator(sequenceName = "Customer_Id_Seq", allocationSize = 1, name = "CUST_SEQ")
	private int custId;
	
	/** The cust F name. */
	@Column (name = "CUSTFNAME")
	@NotNull
    @Size(min=2, max=30)
	private String custFName;
	
	/** The cust L name. */
	@Column (name = "CUSTLNAME")
	private String custLName;
	
	/** The city. */
	@Column (name = "CITY")
	private String city;
	
	/** The state. */
	@Column (name = "STATE")
	private String state;
	
	/** The contact. */
	@Column (name = "CONTACT")
	private String contact;
	
	/** The total due. */
	@Column (name = "TOTALDUE")
	@NotNull
	private Long totalDue;
	
	public CustomerTO() {
	}
	
	/**
	 * Instantiates a new customer TO.
	 *
	 * @param custFName the cust F name
	 * @param custLName the cust L name
	 * @param city the city
	 * @param state the state
	 * @param contact the contact
	 */
	public CustomerTO(String custFName, String custLName, String city, String state, String contact) {
		this.custFName = custFName;
		this.custLName = custLName;
		this.city = city;
		this.state = state;
		this.contact = contact;
	}


	/**
	 * Gets the cust id.
	 *
	 * @return the cust id
	 */
	public int getCustId() {
		return custId;
	}


	/**
	 * Sets the cust id.
	 *
	 * @param custId the new cust id
	 */
	public void setCustId(int custId) {
		this.custId = custId;
	}


	/**
	 * Gets the cust fname.
	 *
	 * @return the cust fname
	 */
	public String getCustFName() {
		return custFName;
	}


	/**
	 * Sets the cust fname.
	 *
	 * @param custFName the new cust fname
	 */
	public void setCustFName(String custFName) {
		this.custFName = custFName;
	}


	/**
	 * Gets the cust L name.
	 *
	 * @return the cust L name
	 */
	public String getCustLName() {
		return custLName;
	}


	/**
	 * Sets the cust L name.
	 *
	 * @param custLName the new cust L name
	 */
	public void setCustLName(String custLName) {
		this.custLName = custLName;
	}


	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}


	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}


	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
	}


	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(String state) {
		this.state = state;
	}


	/**
	 * Gets the contact.
	 *
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}


	/**
	 * Sets the contact.
	 *
	 * @param contact the new contact
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}


	/**
	 * Gets the total due.
	 *
	 * @return the total due
	 */
	public Long getTotalDue() {
		return totalDue;
	}


	/**
	 * Sets the total due.
	 *
	 * @param totalDue the new total due
	 */
	public void setTotalDue(Long totalDue) {
		this.totalDue = totalDue;
	}
	
	

}
