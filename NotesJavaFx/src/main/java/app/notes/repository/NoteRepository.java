package app.notes.repository;

import app.notes.model.Note;
import app.notes.util.EntityManagerUtil;

import javax.persistence.EntityManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NoteRepository {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public void persist(Note note) {
        try {
            EntityManager entityManager = EntityManagerUtil.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(note);
            entityManager.getTransaction().commit();
            //entityManager.close();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        }

    }
}
