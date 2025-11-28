package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import entity.AnimalDomestico;
import entity.Cachorro;
import entity.Gato;

public class AnimalDomesticoDAO {

    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration().configure();
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void close() {
        sessionFactory.close();
    }

    public void salvarAnimalDomestico(AnimalDomestico animal) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(animal);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public AnimalDomestico getAnimalDomesticoById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(AnimalDomestico.class, id);
        }
    }

    public void atualizarAnimalDomestico(AnimalDomestico animal) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(animal);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deletarAnimalDomestico(AnimalDomestico animal) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(animal);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public ArrayList<AnimalDomestico> getTodosAnimaisDomesticos() {
        try (Session session = sessionFactory.openSession()) {
            Query<AnimalDomestico> query = session.createQuery("FROM AnimalDomestico", AnimalDomestico.class);
            List<AnimalDomestico> animaisList = query.list();
            return new ArrayList<>(animaisList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Métodos específicos para Cachorro
    public ArrayList<Cachorro> getTodosCachorros() {
        try (Session session = sessionFactory.openSession()) {
            Query<Cachorro> query = session.createQuery("FROM Cachorro", Cachorro.class);
            List<Cachorro> cachorrosList = query.list();
            return new ArrayList<>(cachorrosList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Métodos específicos para Gato
    public ArrayList<Gato> getTodosGatos() {
        try (Session session = sessionFactory.openSession()) {
            Query<Gato> query = session.createQuery("FROM Gato", Gato.class);
            List<Gato> gatosList = query.list();
            return new ArrayList<>(gatosList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}