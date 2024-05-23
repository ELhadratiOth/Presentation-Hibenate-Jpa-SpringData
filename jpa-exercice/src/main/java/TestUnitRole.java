import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class TestUnitRole {
    public static void main(String[] args) {
        //création l'EntityManage
        EntityManager entityManager = Persistence.createEntityManagerFactory("WebStoreOrc").createEntityManager();
        
        //  création des instances
        IUser iuser = new IUser(entityManager);
        IRole irole = new IRole(entityManager);

        // création d'un nouvel utilisateur
        User user = new User("user1", "password", 789);
        iuser.save(user);
        
        //  création d'un nouveau role
        Role role = new Role();
        role.setRoleName("BOSS");
        role.setIdRole(123);
        irole.save(role);
        
        // affectation du role au utilisateur
        iuser.assignRoleToUser(user, role);
        
        // recherche des roles en se basant  au user 
        ArrayList<Role> roles = (ArrayList<Role>) irole.findRolesByUser(user);
        
        // recherche des user en se basant  sur ses roles
        ArrayList<User> users = (ArrayList<User>) iuser.findUsersByRole(role);
        
        // On affiche les rôles trouvés
        for (Role r : roles) {
            System.out.println("Rôle trouvé: " + r.getRoleName());
        }
        
        // On affiche les utilisateurs trouvés
        for (User u : users) {
            System.out.println("Utilisateur trouvé: " + u.getLogin());
        }

        entityManager.close();
    }
}
