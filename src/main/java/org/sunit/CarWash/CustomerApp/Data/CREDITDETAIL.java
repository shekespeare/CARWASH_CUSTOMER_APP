package org.sunit.CarWash.CustomerApp.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "CREDITDETAIL")
public class CREDITDETAIL {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CBILL_SEQ" )
    @SequenceGenerator(sequenceName = "CREDITBILL_ID_SEQ", allocationSize = 1, name = "CBILL_SEQ")
	@Column(name = "BILLID")
	private Integer cBillId; 
	@Column(name = "CUSTID")
	private Integer custId;
	@Column(name = "CUSTFNAME")
	private String custFName;
	@Column(name = "CUSTLNAME")
	private String custLName;
	@Column(name = "CREDITAMOUNT")
	private Integer creditAmount;
	@Column(name = "CREDITEDDATE")
	private Date creditDate;
	
	public CREDITDETAIL() {
	}
	
	public CREDITDETAIL(Integer custId, String custFName, String custLName, Integer creditAmount, Date creditdate) {
		this.custId = custId;
		this.custFName = custFName;
		this.custLName = custLName;
		this.creditAmount = creditAmount;
		this.creditDate = creditdate;
	}
	
	public CREDITDETAIL(Integer cBillId, Integer custId, String custFName, String custLName, Integer creditAmount, Date creditdate) {
		this.cBillId = cBillId;
		this.custId = custId;
		this.custFName = custFName;
		this.custLName = custLName;
		this.creditAmount = creditAmount;
		this.creditDate = creditdate;
	}

	public Integer getcBillId() {
		return cBillId;
	}

	public void setcBillId(Integer cBillId) {
		this.cBillId = cBillId;
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public String getCustFName() {
		return custFName;
	}

	public void setCustFName(String custFName) {
		this.custFName = custFName;
	}

	public String getCustLName() {
		return custLName;
	}

	public void setCustLName(String custLName) {
		this.custLName = custLName;
	}

	public Integer getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(Integer creditAmount) {
		this.creditAmount = creditAmount;
	}

	public Date getCreditDate() {
		return creditDate;
	}

	public void setCreditDate(String creditDate) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = formatter.parse(creditDate);
		this.creditDate = date;
	}
	
}
