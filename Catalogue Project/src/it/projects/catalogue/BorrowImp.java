package it.projects.catalogue;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import it.projects.users.UsersImp;

import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BorrowImp {

	@ManyToOne
	UsersImp user;
	
	@ManyToOne
	AbstractCatalogue borrowedItem;
	LocalDate startDate;
	LocalDate expectedReturn;
	LocalDate actualReturn;
		
	@Id
	@SequenceGenerator(name = "borrow_seq", sequenceName = "borrow_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "borrow_seq")
	private Long id;
	
	public BorrowImp(UsersImp user, AbstractCatalogue borrowedItem, LocalDate startDate, LocalDate expectedReturn,
			LocalDate actualReturn) {
		super();
		this.user = user;
		this.borrowedItem = borrowedItem;
		this.startDate = startDate;
		this.expectedReturn = expectedReturn.plusDays(30);
		this.actualReturn = actualReturn;
	}

	public BorrowImp() { super(); }
	
	// ===== Getters =====
	public UsersImp getUser() { return user; }
	public AbstractCatalogue getBorrowedItem() { return borrowedItem; }
	public LocalDate getStartDate() { return startDate;	}
	public LocalDate getExpectedReturn() { return expectedReturn; }
	public LocalDate getActualReturn() { return actualReturn; }	
	public Long getId() { return id; }	

	// ===== Setters =====
	public void setUser(UsersImp user) { this.user = user; }
	public void setBorrowedItem(AbstractCatalogue borrowedItem) { this.borrowedItem = borrowedItem;	}
	public void setStartDate(LocalDate startDate) {	this.startDate = startDate; }
	public void setExpectedReturn(LocalDate expectedReturn) { this.expectedReturn = expectedReturn;	}
	public void setActualReturn(LocalDate actualReturn) { this.actualReturn = actualReturn;	}
	public void setId(Long id) { this.id = id; }

	@Override
	public String toString() {
		return "BorrowImp [user=" + user + ", borrowedItem=" + borrowedItem + ", startDate=" + startDate
				+ ", expectedReturn=" + expectedReturn + ", actualReturn=" + actualReturn + ", id=" + id + "]";
	}
	
}
