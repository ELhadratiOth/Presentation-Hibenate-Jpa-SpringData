import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

public class UnitTestCommand {
    public static void main(String[] args) {
        // Création  d'EntityManagerFactory 
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("WebStoreOrc");

        // Création d'EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Création d;instance de ICommand
        ICommand commandDao = new ICommand(entityManager);

        // Génération des dates de début et de fin aléatoires
        Date start = new Date();
        Date startDate = new Date(start.getTime() - (1000 * 60 * 60 * 24 * 7)); // Soustraction de 7 jours à la date actuelle
        Date endDate = new Date(start.getTime() + (1000 * 60 * 60 * 24 * 7)); // Ajout de 7 jours à la date actuelle

        // Trouver les commandes entre les dates spécifiées
        List<Command> commands = commandDao.findCommandsBetweenDates(startDate, endDate);

        // affichage  des commandes
        System.out.println("les commandes Trouve :");
        for (int i = 0; i < commands.size(); i++) {
            Command command = commands.get(i);
            System.out.println(command);
        }

        // Fermer l'EntityManager
        entityManager.close();

        // Fermer l'EntityManagerFactory
        entityManagerFactory.close();
    }
}
