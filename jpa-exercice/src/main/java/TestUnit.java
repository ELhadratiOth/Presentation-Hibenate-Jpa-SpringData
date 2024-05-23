import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestUnit {

    public static void main(String[] args) throws Exception {
            // Création de l'EntityManagerFactory
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("WebStoreOrc");
            EntityManager entityManager = entityManagerFactory.createEntityManager();

             //Création des instances
            IUser iuser= new IUser(entityManager);                
            IRole irole = new IRole(entityManager);
            
            
//          creation d'un user
            User us = new User("othman" , "1212" , 12) ;
            iuser.save(us) ;
            
            
            
            // liste de tout  le  role existants 
            List<Role>  roles  =  irole.findAll() ;
            
            for(  Role x : roles ) {
                System.out.println(x); 
            }

            // liste de tout  le users existants 

            List<User> test = iuser.findAll() ;
            System.out.println(test);
                        
                
            System.out.println("finishhh");



            entityManager.close();
            entityManagerFactory.close(); 
    
    }
}
