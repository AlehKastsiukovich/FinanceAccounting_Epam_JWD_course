package by.javatr.finance.bean;

import java.io.Serializable;
import java.util.*;

public class Expense implements Serializable, Comparable<Expense> {
	private static final long serialVersionUID = -3550517173856626886L;
	private Integer id;
	private double amount;
	private String note;
	private ExpenseCategory category;
	private Date expenseDate;

	// need to do builder

	public Expense(int id, double amount, String note, ExpenseCategory category) {
		this.id = id;
		this.amount = amount;
		this.note = note;
		this.category = category;
		expenseDate = new Date();
	}

	public Expense() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public ExpenseCategory getCategory() {
		return category;
	}

	public void setCategory(ExpenseCategory category) {
		this.category = category;
	}

	public Date getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null)
			return false;
		if (object.getClass() != this.getClass())
			return false;
		Expense other = (Expense) object;
		if (this.id != other.id && Double.doubleToLongBits(this.amount) != Double.doubleToLongBits(other.amount)
				&& this.note.equals(other.note) && this.category != other.category)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 31 * hash + (int) id;
		hash = 31 * hash + (note == null ? 0 : note.hashCode());
		return hash;
	}

	@Override
	public String toString() {
		return id + "/ " + amount + "/ " + note + " /" + category;
	}

	@Override
	public int compareTo(Expense o) {
		return this.id.compareTo(o.id);
	}
}
