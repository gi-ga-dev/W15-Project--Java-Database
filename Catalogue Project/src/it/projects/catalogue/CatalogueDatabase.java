package it.projects.catalogue;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.projects.catalogue.book.BookImp;

public class CatalogueDatabase implements Catalogue {
	// contenitore metodi catalogo
	
	private static final Logger log = LoggerFactory.getLogger(Main.class);
	// tipizzare collection del tipo classe astratta per racchiudere diversi tipi di sottoclassi
	private HashMap<Long, AbstractCatalogue> Database = new HashMap<>();	
	
	public void size() {
		log.info("N. of books/magazines in Catalogue: " + Database.size()); 
	}
	
	@Override
	public void addElement(long codIsbn, AbstractCatalogue obj) {
		Database.put(codIsbn, obj);
		log.info("Object Successfully added!");
	}

	@Override
	public void removeByIsbn(long codIsbn) {
		Database.remove(codIsbn);
		log.info("Object Successfully removed!");
	}

	@Override
	public void searchByIsbn(long codIsbn) {
		// restituisce chiave inerente all'oggetto
		log.info("Search By ISBN --> " + Database.get(codIsbn));	
	}

	@Override
	public void searchByTitle(String title) {
		Database.values().stream()
		.filter(ele -> ele.getTitle() == title)
		.forEach(ele -> log.info("Search By Title --> " + ele.toString()));		
	}
	
	@Override
	public void searchByYear(int year) {	
		// prendo valori del database, .stream (= a .subscribe())
		Database.values().stream()
			// filtro ogni elemento nel database in base all'anno
			.filter(ele -> ele.getYear() == year)
			// stampa le info in base alla ricerca per anno
			.forEach(ele -> log.info("Search By Year --> " + ele.toString()));
	}

	@Override
	public void searchByAuthor(String author) {
		Database.values().stream()
			.filter(ele -> ele instanceof BookImp)
				// cast degli elementi in tipo BookImp
				.map(ele -> (BookImp)ele)
				// per filtrare l'autore di una sottoclasse
				.filter(ele -> author.equals(ele.getAuthor()))
				.forEach(ele -> log.info("Search By Author --> " + ele.toString()));
	}

	@Override
	public void searchByGenre(String genre) {
		Database.values().stream()
		.filter(ele -> ele instanceof BookImp)
			.map(ele -> (BookImp)ele)
			.filter(ele -> genre.equals(ele.getGenre()))
			.forEach(ele -> log.info("Search By Genre --> " + ele.toString()));
	}

	@Override
	public void saveOnDisk() {				
		File myObj = new File("fileText/database.txt");
		log.info("****File Save successfull!****");
		String text = ""; 
		// Database.values() mi restituisce tutti i valori del db
		for (AbstractCatalogue ele : Database.values()) {
			text += ele.toString();		
		}		
		// prova a creare un file altrimenti genera un errore nel log
		try { FileUtils.writeStringToFile(myObj, text, "UTF-8"); } 
		catch (IOException e) { e.printStackTrace(); }			
	}

	@Override
	public void loadFromDisk() {
		// prendere cont. file testo e riportarlo nella console		
		try { 
			String fileCont = FileUtils.readFileToString(new File("fileText/database.txt")); 
			log.info("****File Load successfull!****");
			log.info(fileCont);
		} 
		catch (IOException e) { e.printStackTrace(); }		
	}

}
