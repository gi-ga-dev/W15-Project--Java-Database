package it.projects.catalogue;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;

// Far mappare anche prop. ereditate alle sottoclassi
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractCatalogue {
	// Attributi da condiv. tra sottoclassi
	private long isbn;
	private String title;
	private int year;
	private int pages;
	
	@Id
	@SequenceGenerator(name = "cat_seq", sequenceName = "cat_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cat_seq")
	private Long id;
	
	// Generate constructor using fields
	public AbstractCatalogue(long isbn, String title, int year, int pages) {
		this.isbn = isbn;
		this.title = title;
		this.year = year;
		this.pages = pages;
	}	

	public AbstractCatalogue() { super(); }

	// === Getters ===
	public long getIsbn() { return isbn; }
	public String getTitle() { return title; }	
	public int getYear() { return year; }	
	public int getPages() { return pages; }
	
	// === Setters ===
	public void setIsbn(long isbn) { this.isbn = isbn; }
	public void setTitle(String title) { this.title = title; }
	public void setYear(int year) { this.year = year; }
	public void setPages(int pages) { this.pages = pages; }

	// Auto generare toString. Source --> Generate toString()
	@Override
	public String toString() {
		return "ISBN: " + isbn + ", title: " + title + ", year: " + year + ", pages: " + pages;
	}
	
	
}
