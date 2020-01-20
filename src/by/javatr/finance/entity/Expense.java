package by.javatr.finance.entity;


import java.io.Serializable;
import java.util.Date;
import by.javatr.finance.entity.validator.ExpenseValidator;


public class Expense implements Serializable, Comparable<Expense> {
	private static final long serialVersionUID = -3550517173856626886L;
	private static int IDcounter = 0;
	protected Integer id;
	private double amount;
	private String note;
	private ExpenseCategory category;
	private Date expenseDate;

	private Expense(ExpenseBuilder builder) {
		id = builder.id;
		amount = builder.amount;
		note = builder.note;
		category = builder.category;
		expenseDate = builder.expenseDate;
	}

	private Expense() {

	}
	
	public static class ExpenseBuilder {
		protected Integer id;
		private double amount;
		private String note;
		private ExpenseCategory category;
		private Date expenseDate;
		
		
		public ExpenseBuilder buildAmount(double amount) {
			if (!ExpenseValidator.isNumberValid(amount)) {
				throw new IllegalArgumentException("Wrong amount. Amount must be positive!");
			}
			
			this.amount = amount;
			
			return this;
		}
		
		public ExpenseBuilder buildNote(String note) {
			if (!ExpenseValidator.isNotNullObject(note)) {
				note = "";
			}
			
			this.note = note;
			
			return this;
		}
		
		public ExpenseBuilder buildCategory(ExpenseCategory category) {
			if (!ExpenseValidator.isNotNullObject(category)) {
				category = ExpenseCategory.DEFAULT;
			}

			this.category = category;
			
			return this;
		}
		
		public ExpenseBuilder buildDate(Date date) {
			if (!ExpenseValidator.isNotNullObject(date)) {
				date = new Date();
			}
			
			expenseDate = date;
			
			return this;
		}
		
		public Expense build() {
			id = IDcounter++;
			
			return new Expense(this);
		}
	}
	

	public int getId() {
		return id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		if (!ExpenseValidator.isNumberValid(id)) {
			throw new IllegalArgumentException("Wrong amount. Amount must be positive ");
		}

		this.amount = amount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		if (!ExpenseValidator.isNotNullObject(note)) {
			note = new String();
		}

		this.note = note;
	}

	public ExpenseCategory getCategory() {
		return category;
	}

	public void setCategory(ExpenseCategory category) {
		if (!ExpenseValidator.isNotNullObject(note)) {
			category = ExpenseCategory.DEFAULT;
		}

		this.category = category;
	}

	public Date getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(Date expenseDate) {
		if (!ExpenseValidator.isNotNullObject(expenseDate)) {
			expenseDate = new Date();
		}

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
		return id + "/ " + amount + "/ " + note + " /" + category + " /" + expenseDate;
	}

	@Override
	public int compareTo(Expense o) {
		return this.id.compareTo(o.id);
	}
}
