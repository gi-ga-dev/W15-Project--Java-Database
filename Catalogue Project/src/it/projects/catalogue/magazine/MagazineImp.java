package it.projects.catalogue.magazine;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;

import it.projects.catalogue.AbstractCatalogue;
import it.projects.catalogue.Periodicity;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class MagazineImp extends AbstractCatalogue {
	
	private Periodicity periodicity;
	
	public MagazineImp(long isbn, String title, int year, int pages, Periodicity periodicity) {
		super(isbn, title, year, pages);
		this.periodicity = periodicity;
	}	
	
	public MagazineImp() { super();	}

	// === Getters ===
	public Periodicity getPeriodicity() { return periodicity; }
	
	// === Setters ===
	public void setPeriodicity(Periodicity periodicity) { this.periodicity = periodicity; }
	
	@Override
	public String toString() {
		// dati da riportare nel logger tramite il main
		return periodicity + " Magazine, " + super.toString() + ". ";
	}

	
	
}
