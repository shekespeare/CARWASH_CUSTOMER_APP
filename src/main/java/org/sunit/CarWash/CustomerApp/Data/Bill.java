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

@Entity(name = "Bill")
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BILL_SEQ" )
    @SequenceGenerator(sequenceName = "DEBITBILL_ID_SEQ", allocationSize = 1, name = "BILL_SEQ")
	@Column(name = "BILLID")
	private Integer billId;
	@Column(name = "CUSTID")
	private Integer custId;
	@Column(name = "CUSTFNAME")
	private String custFName;
	@Column(name = "CUSTLNAME")
	private String custLName;
	@Column(name = "BILLAMOUNT")
	private Integer billAmount;
	@Column(name = "BILLDATE")
	private Date billDate;
	@Column(name = "CLEARDATE")
	private Date billClearDate;
	@Column(name = "CLEARFLAG")
	private String clearFlag;
	@Column(name = "DATECOUNT")
	private Integer dateCount;
	@Column(name = "DUE")
	private Integer due;

	public Bill() {
	}

	public Bill(Integer custId, String custFName, String custLName, Integer billAmount, Date billdate) {
		this.custId = custId;
		this.custFName = custFName;
		this.custLName = custLName;
		this.billAmount = billAmount;
		this.billDate = billdate;
	}

	public Bill(Integer billId, Integer custId, String custFName, String custLName, Integer billAmount, Date billdate,
			Integer due, String clearFlag, Date billClearDate, Integer dateCount) {
		this.billId = billId;
		this.custId = custId;
		this.custFName = custFName;
		this.custLName = custLName;
		this.billAmount = billAmount;
		this.billDate = billdate;
		this.due = due;
		this.billClearDate = billClearDate;
		this.clearFlag = clearFlag;
		this.dateCount = dateCount;

	}

	public void setBillDate(String billDate) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = formatter.parse(billDate);
		this.billDate = date;
	}

	public Date getBillClearDate() {
		return billClearDate;
	}

	public void setBillClearDate(String billClearDate) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = formatter.parse(billClearDate);
		this.billClearDate = date;
	}

	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
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

	public Integer getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(Integer billAmount) {
		this.billAmount = billAmount;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public String getClearFlag() {
		return clearFlag;
	}

	public void setClearFlag(String clearFlag) {
		this.clearFlag = clearFlag;
	}

	public Integer getDateCount() {
		return dateCount;
	}

	public void setDateCount(Integer dateCount) {
		this.dateCount = dateCount;
	}

	public Integer getDue() {
		return due;
	}

	public void setDue(Integer due) {
		this.due = due;
	}

	public void setBillClearDate(Date billClearDate) {
		this.billClearDate = billClearDate;
	}

}
