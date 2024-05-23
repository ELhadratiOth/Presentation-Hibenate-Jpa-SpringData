import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public class ICommand implements IGeneric<Command> {

    private EntityManager entityManager;

    public ICommand(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Command findById(int id) {
        return entityManager.find(Command.class, id);
    }

    @Override
    public List<Command> findAll() {
        Query query = entityManager.createQuery("SELECT c FROM Command c");
        return query.getResultList();
    }

    @Override
    public void save(Command entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Command entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Command entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    public List<Command> findCommandsByUser(User user) {
        Query query = entityManager.createQuery("SELECT c FROM Command c WHERE c.user = :user");
        query.setParameter("user", user);
        return query.getResultList();
    }

    public List<Command> findCommandsBetweenDates(Date startDate, Date endDate) {
        Query query = entityManager.createQuery("SELECT c FROM Command c WHERE c.commandDate BETWEEN :startDate AND :endDate");
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }
}
