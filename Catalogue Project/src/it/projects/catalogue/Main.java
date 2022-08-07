package it.projects.catalogue;

import java.time.LocalDate;
import it.projects.catalogue.book.BookImp;
import it.projects.catalogue.book.BookImpDAO;
import it.projects.catalogue.magazine.MagazineImp;
import it.projects.catalogue.magazine.MagazineImpDAO;
import it.projects.users.UsersImp;
import it.projects.users.UsersImpDAO;

public class Main {

	public static void main(String[] args) {
		
		// ======== Users ========
		UsersImp user1 = new UsersImp("Edoardo", "Pellegrini", "28/10/1999", 125);
		UsersImp user2 = new UsersImp("Claudio", "Licheri", "14/12/1995", 60);
		
		// ======== Books ========
		BookImp book1 = new BookImp(88045121, "Poeta al comando", 2003, 256, "Alessandro Barbero", "Storico-Medievale");
		BookImp book2 = new BookImp(74216261, "Harry Potter and the Philosopher's Stone", 1997, 223, "J. K. Rowling", "Fantasy");
		BookImp book3 = new BookImp(84919191, "In Search of Lost Time", 1913, 4215, "Marcel Proust", "Modernist");
		BookImp book4 = new BookImp(58491211, "Ulysses", 1922, 730, "James Joyce", "Modernist");
		BookImp book5 = new BookImp(91262911, "Don Quixote", 1605, 1077, "Miguel de Cervantes", "Novel");
		BookImp book6 = new BookImp(32841481, "Hamlet", 1599, 192, "William Shakespeare", "	Shakespearean tragedy");
		
		// ======== Magazines ========
		MagazineImp mag1 = new MagazineImp(357375533, "Edge", 1999, 80, Periodicity.MONTHLY);		
		MagazineImp mag2 = new MagazineImp(783553377, "Nintendo Power", 2002, 120, Periodicity.SEMESTRAL);		
		MagazineImp mag3 = new MagazineImp(537535782, "Game Informer", 2005, 110, Periodicity.SEMESTRAL);		
		MagazineImp mag4 = new MagazineImp(978084783, "Time", 2012, 90, Periodicity.WEEKLY);		
		
		// ======== Borrowed Items ========
		BorrowImp borrow1 = new BorrowImp(user1, book1, LocalDate.now(), LocalDate.now(), null);
		BorrowImp borrow2 = new BorrowImp(user1, book2, LocalDate.now(), LocalDate.now(), null);
		BorrowImp borrow3 = new BorrowImp(user1, book3, LocalDate.now(), LocalDate.now(), null);		
		BorrowImp borrow4 = new BorrowImp(user1, mag1, LocalDate.now(), LocalDate.now(), null);
		BorrowImp borrow5 = new BorrowImp(user1, mag2, LocalDate.now(), LocalDate.now(), null);
		
		BorrowImp borrow6 = new BorrowImp(user2, book4, LocalDate.now(), LocalDate.now(), null);
		BorrowImp borrow7 = new BorrowImp(user2, book5, LocalDate.now(), LocalDate.now(), null);
		BorrowImp borrow8 = new BorrowImp(user2, book6, LocalDate.now(), LocalDate.now(), null);
		BorrowImp borrow9 = new BorrowImp(user2, mag3, LocalDate.now(), LocalDate.now(), null);
		BorrowImp borrow10 = new BorrowImp(user2, mag4, LocalDate.now(), LocalDate.now(), null);
		
		// Referenza classi DAO per accedere ai metodi
		BookImpDAO bookDAO = new BookImpDAO();	
		MagazineImpDAO magDAO = new MagazineImpDAO();
		UsersImpDAO userDAO = new UsersImpDAO();		
		BorrowImpDAO borrowDAO = new BorrowImpDAO();		
				
		// Immagazinare oggetti nel db tramite classi surrogate DAO
		bookDAO.save(book1);  	
		bookDAO.save(book2); 
		bookDAO.save(book3); 
		bookDAO.save(book4); 
		bookDAO.save(book5); 
		bookDAO.save(book6); 
		magDAO.save(mag1);     
		magDAO.save(mag2);     
		magDAO.save(mag3);     
		magDAO.save(mag4);     
		userDAO.save(user1); // Edoardo	
		userDAO.save(user2); // Claudio	 
				
		// Salvare nel db id utente ed elemento preso in prestito
		borrowDAO.save(borrow1);
		borrowDAO.save(borrow2);
		borrowDAO.save(borrow3);
		borrowDAO.save(borrow4);
		borrowDAO.save(borrow5);
		borrowDAO.save(borrow6);
		borrowDAO.save(borrow7);
		borrowDAO.save(borrow8);
		borrowDAO.save(borrow9);
		borrowDAO.save(borrow10);
		
		// prendo codice tessera da un utente e mi restituisce gli ogg. presi in prestito
		borrowDAO.searchByLibId(60); // restituisce ogg. Claudio
		borrowDAO.searchByLibId(125);// restituisce ogg. Edoardo			
		
		// ===== Settare data Fine Prestito (da attr. ad un button) =====
		// Setto la data di restituzione manualmente e cerco ogg. scaduti tramite id		
		borrow1.setActualReturn(LocalDate.now().plusDays(40));
		borrowDAO.searchByExpired(125);
		
		// ===== Cancellare elementi dal db =====
		//bookDAO.delete(book1);
		//bookDAO.delete(book2);	
		
		// --------------------------------------------------------
			
//		UsersImp userAccess = new UsersImp();
//		userAccess.getUser().add(user1);
	
		
//		// referenza catalogo per accedere ai metodi
//		CatalogueDatabase database = new CatalogueDatabase();
//		
//		database.addElement(book1.getIsbn(), book1);
//		database.addElement(book2.getIsbn(), book2);
//		database.addElement(mag1.getIsbn(), mag1);
//		database.size();
//				
//		database.searchByIsbn(88045121);
//		database.searchByTitle("Harry Potter and the Philosopher's Stone");
//		database.searchByYear(2003);
//		database.searchByAuthor("J. K. Rowling");
//		database.searchByGenre("Storico-Medievale");
//		
//		database.saveOnDisk();
//		database.loadFromDisk();
//				
//		// ===============================================
//		
//		database.removeByIsbn(88045121);
//		database.size();		
	}
			
}
