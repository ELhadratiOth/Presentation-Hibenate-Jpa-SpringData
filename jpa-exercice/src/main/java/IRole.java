import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IRole implements IGeneric<Role> {
    private static final Logger logger = Logger.getLogger(IRole.class.getName());

    private EntityManager entityManager;

    public IRole(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Role findById(int id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public List<Role> findAll() {
        return entityManager.createQuery("SELECT r FROM Role r", Role.class).getResultList();
    }

    @Override
    public void save(Role entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de l'ajout du rôle", e);
        }
    }

    @Override
    public void update(Role entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de la mise à jour du rôle", e);
        }
    }

    @Override
    public void delete(Role entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Role managedRole = entityManager.find(Role.class, entity.getIdRole());
            if (managedRole != null) {
                logger.info("Deleting role: " + managedRole);

                // Remove associations with users
                List<User> usersToRemove = new ArrayList<>(managedRole.getUsers());
                for (User user : usersToRemove) {
                    user.getRoles().remove(managedRole);
                    entityManager.merge(user);
                }

                entityManager.remove(managedRole);
                transaction.commit();
                logger.info("Role deleted successfully: " + managedRole);
            } else {
                logger.warning("Role not found: " + entity);
                transaction.rollback();
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.log(Level.SEVERE, "Erreur lors de la suppression du rôle", e);
            throw new RuntimeException("Erreur lors de la suppression du rôle", e);
        }
    }

    public Role findByRoleName(String roleName) {
        return entityManager.createQuery("SELECT r FROM Role r WHERE r.roleName = :roleName", Role.class)
                .setParameter("roleName", roleName)
                .getSingleResult();
    }

    public List<Role> findRolesByUser(User user) {
        return entityManager.createQuery("SELECT r FROM Role r JOIN r.users u WHERE u = :user", Role.class)
                .setParameter("user", user)
                .getResultList();
    }
}
