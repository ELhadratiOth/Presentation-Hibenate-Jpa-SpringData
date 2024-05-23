import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Test {
    public static void main(String[] args) {
        // Créer une instance de EntityManagerFactory en utilisant le nom de l'unité de persistance définie dans persistence.xml
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MyPersistenceUnit");

        // Créer un EntityManager à partir de EntityManagerFactory
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Commencer une transaction
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            // Créer la table
            entityManager.createNativeQuery(
                    "CREATE TABLE IF NOT EXISTS Example (id INT AUTO_INCREMENT, name VARCHAR(255), PRIMARY KEY (id))"
            ).executeUpdate();

            // Insérer des données dans la table
            entityManager.createNativeQuery(
                    "INSERT INTO Example (name) VALUES ('John')"
            ).executeUpdate();

            entityManager.createNativeQuery(
                    "INSERT INTO Example (name) VALUES ('Alice')"
            ).executeUpdate();

            // Valider la transaction
            transaction.commit();
            System.out.println("Data inserted successfully.");
        } catch (Exception e) {
            // En cas d'erreur, annuler la transaction
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Fermer l'EntityManager et l'EntityManagerFactory
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
