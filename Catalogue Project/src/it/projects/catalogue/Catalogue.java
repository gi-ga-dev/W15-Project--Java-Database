package it.projects.catalogue;

public interface Catalogue {

	// === Servizi Database (key: codIsbn, value: object) ===
	void addElement(long codIsbn, AbstractCatalogue obj);

	void removeByIsbn(long codIsbn);

	void searchByIsbn(long codIsbn);
	
	void searchByTitle(String title);

	void searchByYear(int year);
	
	void searchByAuthor(String author);
	
	void searchByGenre(String genre);

	void saveOnDisk();

	void loadFromDisk();

}