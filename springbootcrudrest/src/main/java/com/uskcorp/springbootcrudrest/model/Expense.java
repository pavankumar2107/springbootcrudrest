package com.uskcorp.springbootcrudrest.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "ExpenseTest")
@EntityListeners(AuditingEntityListener.class)
public class Expense {
	private int expenseId;
	private Date date=new Date();
	private String purposeOfExpense;
	private long balance;
//	private int amount;
	private int credit=0;
	private int debit=0;
	private Date createdDate=new Date();
	private Date updatedDate;
	private String description;
	private int activeFlag;
	private boolean typeOfExpense;
	private long totalBalance;
	
	private Date fromDate;
	private Date toDate;
	
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	@Column(name = "totalBalance", nullable = true)
	public long getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(long totalBalance) {
		this.totalBalance = totalBalance;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getExpenseId() {
		return expenseId;
	}

	@Column(name = "date", nullable = true)
	public Date getDate() {
		return date;
	}

	@Column(name = "purposeOfExpense", nullable = false)
	public String getPurposeOfExpense() {
		return purposeOfExpense;
	}
	@Column(name = "balance", nullable = false)
	public long getBalance() {
		return balance;
	}

	/*public int getAmount() {
		return amount;
	}*/

	@Column(name = "credit", nullable = false)
	public int getCredit() {
		return credit;
	}

	@Column(name = "debit", nullable = false)
	public int getDebit() {
		return debit;
	}

	@Column(name = "createddate", nullable = false)
	public Date getCreatedDate() {
		return createdDate;
	}

	@Column(name = "updateddate", nullable = true)
	public Date getUpdatedDate() {
		return updatedDate;
	}

	@Column(name = "description", nullable = false)
	public String getDescription() {
		return description;
	}

	@Column(name = "activeflag", nullable = true)
	public int getActiveFlag() {
		return activeFlag;
	}

	@Column(name = "typeofexpense", nullable = false)
	public boolean isTypeOfExpense() {
		return typeOfExpense;
	}

	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setPurposeOfExpense(String purposeOfExpense) {
		this.purposeOfExpense = purposeOfExpense;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	/*public void setAmount(int amount) {
		this.amount = amount;
	}*/

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public void setDebit(int debit) {
		this.debit = debit;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setActiveFlag(int activeFlag) {
		this.activeFlag = activeFlag;
	}

	public void setTypeOfExpense(boolean typeOfExpense) {
		this.typeOfExpense = typeOfExpense;
	}

	public Expense( Date date, String purposeOfExpense, long balance, int credit, int debit,
			Date createdDate, Date updatedDate, String description,long totalBalance,boolean typeOfExpense) {
		super();
		this.date = date;
		this.purposeOfExpense = purposeOfExpense;
		this.balance = balance;
		this.credit = credit;
		this.debit = debit;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.description = description;
		this.totalBalance = totalBalance;
		this.typeOfExpense = typeOfExpense;
	}

	public Expense() {
		super();
	}
	

}
