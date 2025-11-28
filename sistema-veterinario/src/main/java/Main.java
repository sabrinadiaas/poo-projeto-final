import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;
import java.util.Scanner;
import org.hibernate.HibernateException;
// ^^ importações pro banco de dados funcionar, import entity * é "seletor universal"

public class Main {

    private static SessionFactory sessionFactory;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
            boolean executando = true;

            while (executando) {
                System.out.println("\n=== SISTEMA VETERINÁRIO ===");
                System.out.println("1 - Cadastrar Animal");
                System.out.println("2 - Listar Animais");
                System.out.println("3 - Excluir Animal");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");

                int opc = scanner.nextInt();
                scanner.nextLine();

                switch (opc) {
                    case 1:
                        cadastrarAnimal();
                        break;
                    case 2:
                        listarAnimais();
                        break;
                    case 3:
                        excluirAnimal();
                        break;
                    case 0:
                        executando = false;
                        System.out.println("Saindo do sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            }

        } catch (HibernateException e) {
            System.err.println("Erro ao inicializar o sistema: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (sessionFactory != null) {
                sessionFactory.close();
            }
            scanner.close();
        }
    }

    private static void cadastrarAnimal() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            System.out.println("\n=== CADASTRAR ANIMAL ===");
            System.out.println("Selecione o tipo de animal:");
            System.out.println("1 - Animal Doméstico");
            System.out.println("2 - Animal Silvestre");
            System.out.print("Opção: ");

            int tipo = scanner.nextInt();
            scanner.nextLine();

            transaction = session.beginTransaction();

            switch (tipo) {
                case 1:
                    cadastrarAnimalDomestico(session);
                    break;
                case 2:
                    cadastrarAnimalSilvestre(session);
                    break;
                default:
                    System.out.println("Tipo inválido!");
                    return;
            }

            transaction.commit();
            System.out.println("Animal cadastrado com sucesso!");

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Erro ao cadastrar animal: " + e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void cadastrarAnimalDomestico(Session session) {
        // Ele nn vai pedir mais ID, tá gerando automatico (UUID em banco de dados, evita dados inconsistentes)
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        System.out.print("Peso: ");
        double peso = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("\n=== ANIMAL DOMÉSTICO ===");
        System.out.println("Selecione o tipo:");
        System.out.println("1 - Cachorro");
        System.out.println("2 - Gato");
        System.out.print("Opção: ");

        int tipo = scanner.nextInt();
        scanner.nextLine();

        AnimalDomestico animal = null;

        switch (tipo) {
            case 1:
                System.out.print("Raça: ");
                String raca = scanner.nextLine();
                System.out.print("Porte (PEQUENO/MEDIO/GRANDE): ");
                String porte = scanner.nextLine();
                // Id começa null pq vai gerar automatico
                animal = new Cachorro(null, nome, idade, peso, raca, porte);
                break;
            case 2:
                System.out.print("Pelagem: ");
                String pelagem = scanner.nextLine();
                // Id começa null pq vai gerar automatico
                animal = new Gato(null, nome, idade, peso, pelagem);
                break;
            default:
                System.out.println("Tipo inválido!");
                return;
        }

        session.save(animal);
    }

    private static void cadastrarAnimalSilvestre(Session session) {
        // Ele nn vai pedir mais ID, tá gerando automatico (UUID em banco de dados, evita dados inconsistentes)
        System.out.print("Habitat Natural: ");
        String habitat = scanner.nextLine();
        System.out.print("Origem: ");
        String origem = scanner.nextLine();
        System.out.print("Dieta padrão: ");
        String dieta = scanner.nextLine();

        System.out.println("\n=== ANIMAL SILVESTRE ===");
        System.out.println("Selecione o tipo:");
        System.out.println("1 - Serpente");
        System.out.println("2 - Tartaruga Marinha");
        System.out.print("Opção: ");

        int tipo = scanner.nextInt();
        scanner.nextLine();

        AnimalSilvestre animal = null;

        switch (tipo) {
            case 1:
                System.out.print("Comprimento em cm: ");
                Double comprimento = scanner.nextDouble();
                // Id começa null pq vai gerar automatico
                animal = new Serpente(null, habitat, origem, dieta, comprimento);
                break;
            case 2:
                System.out.print("Comprimento do casco em cm: ");
                Double comprimentoCasco = scanner.nextDouble();
                scanner.nextLine();
                System.out.print("Local de Encalhe: ");
                String localEncalhe = scanner.nextLine();
                // Id começa null pq vai gerar automatico
                animal = new TartarugaMarinha(null, habitat, origem, dieta, comprimentoCasco, localEncalhe);
                break;
            default:
                System.out.println("Tipo inválido!");
                return;
        }

        session.save(animal);
    }

    private static void listarAnimais() {
        try (Session session = sessionFactory.openSession()) {
            // Ele vai ter que usar consultas especificas pras classes concretas pq nn deu certo com as abstratas
            // Lista pra cada classe pra evitar erros
            List<Cachorro> cachorros = session.createQuery("FROM Cachorro", Cachorro.class).list();
            List<Gato> gatos = session.createQuery("FROM Gato", Gato.class).list();
            List<Serpente> serpentes = session.createQuery("FROM Serpente", Serpente.class).list();
            List<TartarugaMarinha> tartarugas = session.createQuery("FROM TartarugaMarinha", TartarugaMarinha.class).list();
            
            System.out.println("\n===== ANIMAIS DOMÉSTICOS =====");
            if (cachorros.isEmpty() && gatos.isEmpty()) {
                System.out.println("Nenhum animal doméstico cadastrado.");
            } else {
                for (Cachorro cachorro : cachorros) {
                    cachorro.exibirInfo();
                    System.out.println("------------------------");
                    // linha pra separar e deixar bonitinho
                }
                for (Gato gato : gatos) {
                    gato.exibirInfo();
                    System.out.println("------------------------");
                    // linha pra separar e deixar bonitinho
                }
            }

            System.out.println("\n===== ANIMAIS SILVESTRES =====");
            if (serpentes.isEmpty() && tartarugas.isEmpty()) {
                System.out.println("Nenhum animal silvestre cadastrado.");
            } else {
                for (Serpente serpente : serpentes) {
                    serpente.exibirInfo();
                    System.out.println("------------------------");
                    // linha pra separar e deixar bonitinho
                }
                for (TartarugaMarinha tartaruga : tartarugas) {
                    tartaruga.exibirInfo();
                    System.out.println("------------------------");
                    // linha pra separar e deixar bonitinho
                }
            }

        } catch (Exception e) {
            System.err.println("Erro ao listar animais: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void excluirAnimal() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            System.out.println("\n=== EXCLUIR ANIMAL ===");
            System.out.println("1 - Excluir Animal Doméstico");
            System.out.println("2 - Excluir Animal Silvestre");
            System.out.print("Opção: ");
            
            int tipo = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("ID do animal: ");
            Long id = scanner.nextLong();
            scanner.nextLine();

            transaction = session.beginTransaction();

            Object animal = null;
            
            // Olha as classes concretas ao inves das abstratas (Animal, AnimalDomestico e AnimalSilvestre) pra evitar erro
            if (tipo == 1) {
                // Tentar encontrar como Cachorro primeiro
                animal = session.get(Cachorro.class, id);
                if (animal == null) {
                    // Se não encontrou como Cachorro, tentar como Gato
                    animal = session.get(Gato.class, id);
                }
            } else if (tipo == 2) {
                // Tentar encontrar como Serpente primeiro
                animal = session.get(Serpente.class, id);
                if (animal == null) {
                    // Se não encontrou como Serpente, tentar como Tartaruga
                    animal = session.get(TartarugaMarinha.class, id);
                }
            } else {
                System.out.println("Tipo inválido!");
                return;
            }

            if (animal != null) {
                session.delete(animal);
                transaction.commit();
                System.out.println("Animal excluído com sucesso!");
            } else {
                System.out.println("Animal não encontrado!");
            }

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Erro ao excluir animal: " + e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}