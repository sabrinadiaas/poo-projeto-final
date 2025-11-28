package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import entity.AnimalSilvestre;
import entity.Serpente;
import entity.TartarugaMarinha;

public class AnimalSilvestreDAO {

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

    public void salvarAnimalSilvestre(AnimalSilvestre animal) {
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

    public AnimalSilvestre getAnimalSilvestreById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(AnimalSilvestre.class, id);
        }
    }

    public void atualizarAnimalSilvestre(AnimalSilvestre animal) {
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

    public void deletarAnimalSilvestre(AnimalSilvestre animal) {
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

    public ArrayList<AnimalSilvestre> getTodosAnimaisSilvestres() {
        try (Session session = sessionFactory.openSession()) {
            Query<AnimalSilvestre> query = session.createQuery("FROM AnimalSilvestre", AnimalSilvestre.class);
            List<AnimalSilvestre> animaisList = query.list();
            return new ArrayList<>(animaisList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Métodos específicos para Serpente
    public ArrayList<Serpente> getTodasSerpentes() {
        try (Session session = sessionFactory.openSession()) {
            Query<Serpente> query = session.createQuery("FROM Serpente", Serpente.class);
            List<Serpente> serpentesList = query.list();
            return new ArrayList<>(serpentesList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Métodos específicos para TartarugaMarinha
    public ArrayList<TartarugaMarinha> getTodasTartarugas() {
        try (Session session = sessionFactory.openSession()) {
            Query<TartarugaMarinha> query = session.createQuery("FROM TartarugaMarinha", TartarugaMarinha.class);
            List<TartarugaMarinha> tartarugasList = query.list();
            return new ArrayList<>(tartarugasList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}