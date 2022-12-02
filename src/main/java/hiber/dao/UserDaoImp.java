package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
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

   @Override
   public User getUserBy(String model, int series) {
      String hql = "from User u where u.car.model = :parModel and u.car.series = :parSeries";
      Query query = sessionFactory.getCurrentSession().createQuery(hql);
      return (User) query.setParameter("parModel", model).setParameter("parSeries", series)
              .setMaxResults(1).getSingleResult();
   }
}
