package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.Iterator;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    public User findUser(String name, int series) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User user where user.car.name = :name and user.car.series=:series");
        query.setParameter("name", name);
        query.setParameter("series", series);
        User user = (User) query.getSingleResult();
        return user;
    }

    @Override
    public void cleanUsersTable() {
        sessionFactory.getCurrentSession().createQuery("DELETE FROM User").executeUpdate();
    }

    @Override
    public void deleteUser(long id) {
        User user1 = sessionFactory.getCurrentSession().load(User.class, id);
        if (user1 != null) {
            sessionFactory.getCurrentSession().delete(user1);
        }
    }

    @Override
    public void deleteTable() {
        sessionFactory.getCurrentSession().createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
        sessionFactory.getCurrentSession().createSQLQuery("DROP TABLE IF EXISTS car").executeUpdate();


    }


}
