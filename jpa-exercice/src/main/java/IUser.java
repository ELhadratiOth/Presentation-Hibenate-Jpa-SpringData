import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List ;

public class IUser implements IGeneric<User> {
	 private EntityManager entityManager;

	    public IUser(EntityManager entityManager) {
	        this.entityManager = entityManager;
	    }

	    @Override
	    public User findById(int id) {
	        return entityManager.find(User.class, id);
	    }

	    @Override
	    public List<User> findAll() {
	        Query query = entityManager.createQuery("SELECT u FROM User u");
	        return query.getResultList();
	    }

	    @Override
	    public void save(User entity) {
	    	 EntityTransaction transaction = entityManager.getTransaction();
	    	    try {
	    	        transaction.begin();
	    	        entityManager.persist(entity);
	    	        transaction.commit();
	    	    } catch (Exception e) {
	    	        transaction.rollback();
	    	        throw new RuntimeException("Erreur lors d'ajout de l'utilisateur");
	    	    }
	    }

	    @Override
	    public void update(User entity) {
	        EntityTransaction transaction = entityManager.getTransaction();
	        try {
	            transaction.begin();
	            entityManager.merge(entity);
	            transaction.commit();
	        } catch (Exception e) {
	            transaction.rollback();	           
	            throw new RuntimeException("Erreur lors de la mise à jour de l'utilisateur");
	        }
	    }


	    @Override
	    public void delete(User entity) {
	        EntityTransaction transaction = entityManager.getTransaction();
	        try {
	            transaction.begin();
	            if (!entityManager.contains(entity)) {
	                entity = entityManager.merge(entity);
	            }
	            entityManager.remove(entity);
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction.isActive()) {
	                transaction.rollback();
	            }
	            throw new RuntimeException("Erreur lors de la suppression de l'utilisateur");
	        }
	    }


	    public List<User> findByLogin(String login) {
	        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.login = :login");
	        query.setParameter("login", login);
	        return (List<User>) query.getResultList();
	    }

	    public List<User> findUsersByRole(Role role) {
	        Query query = entityManager.createQuery("SELECT u FROM User u JOIN u.roles r WHERE r = :role");
	        query.setParameter("role", role);
	        return (List<User>) query.getResultList();
	    }
	    
	    public void assignRoleToUser(User user, Role role) {
	        EntityTransaction transaction = entityManager.getTransaction();
	        try {
	            transaction.begin();
	            if (user == null || role == null) {
	                throw new RuntimeException("User or Role n'existe pas");
	            }

	            System.out.println("Le role est : " + role);
	            System.out.println("L'utilisateur est : " + user);
	            
	            // Assigner le role à l'utilisateur
	            user.assignRole(role);

	            entityManager.merge(user);
	            entityManager.merge(role);

	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction.isActive()) {
	                transaction.rollback();
	            }
	            throw new RuntimeException("Erreur  lors d'assigneation du role");
	        }
	    }

	    
}
