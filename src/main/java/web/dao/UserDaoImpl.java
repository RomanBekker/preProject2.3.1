package web.dao;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private final EntityManager em;

    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(User user) {
        em.persist(user);
    }

    @Override
    public void delete(User user) {
        User merged = em.merge(user);
        em.remove(merged);
    }

    @Override
    public void update(long id, User newUser) {
        User oldUser = backByID(id);
        oldUser.setName(newUser.getName());
        oldUser.setAge(newUser.getAge());
        oldUser.setEmail(newUser.getEmail());
    }

    @Override
    public List<User> back() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User backByID(@Param("id") Long id) {
        Query query = em.createQuery("select u from User u where u.id = :paramName").setParameter("paramName", id);
        return (User) query.getSingleResult();
    }
}
