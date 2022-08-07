package it.projects.catalogue;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import it.projects.catalogue.util.JpaUtil;

public class BorrowImpDAO {

	private static final Logger logger = LoggerFactory.getLogger(BorrowImpDAO.class);	
	private ArrayList<BorrowImp> Borrowed = new ArrayList<>();
		
    public void searchByLibId(int libId) {
        getBorrowed().stream()
        // filtro se il numero di tessera combacia e gli ogg. non ancora noleggiati di quel singolo utente
        .filter(ele -> ele.getUser().getLibraryId() == libId)
        .filter(ele -> ele.getActualReturn() == null)
        .forEach(ele -> System.out.println("@@@ Lista Prestiti utente " + ele.getUser().getName() + " "
                + ele.getUser().getSurname() + ": " + ele.getBorrowedItem()));
    }
    
    public void searchByExpired(int libId) {   	
    	getBorrowed().stream()
    		// filtro se il numero di tessera combacia, se non l'ha ancora consegnato e se la data di scadenza e' superata
    		.filter(ele -> ele.getUser().getLibraryId() == libId)
    		.filter(ele -> ele.getActualReturn() != null)
    		.filter(ele -> ele.getActualReturn().isAfter(ele.getExpectedReturn()) == true)
    		.forEach(ele -> 
    			System.out.println("###Utente: " + ele.getUser().getName() + " " +
				ele.getUser().getSurname() + " - Tessera n.: " + ele.getUser().getLibraryId() + 
				" ha passato la data di scadenza per l'oggetto: " + ele.getBorrowedItem().getTitle()));
    }
	
	public void save(BorrowImp object) {
		
		getBorrowed().add(object);
				
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.persist(object);
			transaction.commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			logger.error("Error saving object: " + object.getClass().getSimpleName(), ex);
			throw ex;
		} finally {
			em.close();
		}
	}

	public void refresh(BorrowImp object) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.refresh(object);
		} finally {
			em.close();
		}
	}

	public BorrowImp getById(long id) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			return em.find(BorrowImp.class, id);
		} finally {
			em.close();
		}
	}
	
	public void delete(BorrowImp object) {
		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.remove(em.contains(object) ? object : em.merge(object));
			transaction.commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			logger.error("Error deleting object: " + object.getClass().getSimpleName(), ex);
			throw ex;
		} finally {
			em.close();
		}
	}	

	public ArrayList<BorrowImp> getBorrowed() {	return Borrowed; }
	public void setBorrowed(ArrayList<BorrowImp> borrowed) { Borrowed = borrowed; }

	@Override
	public String toString() {
		return "BorrowImpDAO [Borrowed=" + Borrowed + "]";
	}
	
}
