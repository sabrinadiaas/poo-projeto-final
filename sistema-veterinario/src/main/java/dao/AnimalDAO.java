package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import entity.Animal;
import entity.AnimalDomestico;
import entity.AnimalSilvestre;

public class AnimalDAO {

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

    public ArrayList<Animal> getTodosAnimais() {
        try (Session session = sessionFactory.openSession()) {
            // Busca todos os animais domésticos
            Query<AnimalDomestico> queryDomesticos = session.createQuery("FROM AnimalDomestico", AnimalDomestico.class);
            List<AnimalDomestico> animaisDomesticos = queryDomesticos.list();
            
            // Busca todos os animais silvestres
            Query<AnimalSilvestre> querySilvestres = session.createQuery("FROM AnimalSilvestre", AnimalSilvestre.class);
            List<AnimalSilvestre> animaisSilvestres = querySilvestres.list();
            
            // Combina as listas
            ArrayList<Animal> todosAnimais = new ArrayList<>();
            todosAnimais.addAll(animaisDomesticos);
            todosAnimais.addAll(animaisSilvestres);
            
            return todosAnimais;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public int getTotalAnimais() {
        try (Session session = sessionFactory.openSession()) {
            Query<AnimalDomestico> queryDomesticos = session.createQuery("FROM AnimalDomestico", AnimalDomestico.class);
            List<AnimalDomestico> animaisDomesticos = queryDomesticos.list();
            
            Query<AnimalSilvestre> querySilvestres = session.createQuery("FROM AnimalSilvestre", AnimalSilvestre.class);
            List<AnimalSilvestre> animaisSilvestres = querySilvestres.list();
            
            return animaisDomesticos.size() + animaisSilvestres.size();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}