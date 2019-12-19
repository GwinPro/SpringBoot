package com.jmSpringBoot.crud.dao.daoImpl;

import com.jmSpringBoot.crud.dao.UserDao;
import com.jmSpringBoot.crud.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class UserDaoHibernateImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public User getUserByUserName(String userName) {
        Query query = entityManager.createQuery("FROM User us JOIN FETCH us.roles where us.name = :paramName");
        query.setParameter("paramName", userName);
        List user = query.getResultList();
        return (user.isEmpty()) ? null : (User) user.get(0);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return entityManager.createQuery("FROM User ").getResultList();
    }
}
