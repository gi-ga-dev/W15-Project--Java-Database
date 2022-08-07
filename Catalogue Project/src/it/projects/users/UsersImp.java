package it.projects.users;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class UsersImp {
	private String name;
	private String surname;
	private String dateOfBirth;	
	
	// 1 utente puo' avere molti prestiti
	@OneToMany
	private Set<UsersImp> user;
	private int libraryId;	
	
	@Id
	@SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	private Long id;
	
	public UsersImp(String name, String surname, String dateOfBirth, int libraryId) {
		super();
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.libraryId = libraryId;
	}
	
	public UsersImp() { super(); }
	
	// ===== Getters =====
	public String getName() { return name; }
	public String getSurname() { return surname; }
	public String getDateOfBirth() { return dateOfBirth; }
	public int getLibraryId() { return libraryId; }
	public Long getId() { return id; }	
	public Set<UsersImp> getUser() { return user; }	

	// ===== Setters =====	
	public void setName(String name) { this.name = name; }	
	public void setSurname(String surname) { this.surname = surname; }	
	public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }
	public void setLibraryId(int libraryId) { this.libraryId = libraryId; }
	public void setId(Long id) { this.id = id; }
	public void setUser(Set<UsersImp> user) { this.user = user; }

	@Override
	public String toString() {
		return "UsersImp [name=" + name + ", surname=" + surname + ", dateOfBirth=" + dateOfBirth + ", user=" + user
				+ ", libraryId=" + libraryId + ", id=" + id + "]";
	}	

}
