package app.database.DAO;

import app.database.entities.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class UserDAO {
    private Session session;

    public UserDAO(Session session) {
        this.session = session;
    }

    public User get(long id) throws HibernateException {
        return (User) session.get(User.class, id);
    }

    public User get(String login) throws HibernateException {
        Criteria criteria = session.createCriteria(User.class);
        return (User) criteria.add(Restrictions.eq("login", login)).uniqueResult();
    }

    public User getUserByEmail(String email) throws HibernateException {
        Criteria criteria = session.createCriteria(User.class);
        return (User) criteria.add(Restrictions.eq("email", email)).uniqueResult();
    }

    public long insertUser(User user) throws HibernateException {
        return (Long) session.save(user);
    }
}
