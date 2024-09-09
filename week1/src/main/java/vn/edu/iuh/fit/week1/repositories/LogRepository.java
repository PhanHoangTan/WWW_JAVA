package vn.edu.iuh.fit.week1.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import vn.edu.iuh.fit.week1.entities.Log;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogRepository {
    private final Logger logger = Logger.getLogger(LogRepository.class.getName());
    private EntityManager em;
    private EntityTransaction trans;

    public LogRepository() {
        em = Persistence.createEntityManagerFactory("mariaDB").createEntityManager();
        trans = em.getTransaction();
    }

    // Insert a new log entry
    public void insert(Log log) {
        try {
            trans.begin();
            em.persist(log);  // Add Log entry to the database
            trans.commit();
        } catch (Exception exception) {
            if (trans.isActive()) {
                trans.rollback();  // Rollback if an error occurs
            }
            logger.log(Level.SEVERE, "Failed to insert log: " + exception.getMessage(), exception);
        }
    }

    // Get all log entries
    public List<Log> getAllLogs() {
        try {
            return em.createQuery("SELECT l FROM Log l", Log.class).getResultList();
        } catch (Exception exception) {
            logger.log(Level.SEVERE, "Failed to fetch logs: " + exception.getMessage(), exception);
            return null;
        }
    }
    // findLogbyUsers
    public List<Log> findLogbyUsers() {
        try {
            return em.createQuery("SELECT l FROM Log l WHERE l.accountId = :accountId", Log.class).getResultList();
        } catch (Exception exception) {
            logger.log(Level.SEVERE, "Failed to fetch logs: " + exception.getMessage(), exception);
            return null;
        }
    }
    // findLogbyTime
    public List<Log> findLogInTime() {
        try {
            return em.createQuery("SELECT l FROM Log l WHERE l.loginTime = :loginTime", Log.class).getResultList();
        } catch (Exception exception) {
            logger.log(Level.SEVERE, "Failed to fetch logs: " + exception.getMessage(), exception);
            return null;
        }
    }
    //updateLog
    public void updateLog(Log log) {
        log.setLogoutTime(new Date(System.currentTimeMillis()));
        em.merge(log);

    }





}
