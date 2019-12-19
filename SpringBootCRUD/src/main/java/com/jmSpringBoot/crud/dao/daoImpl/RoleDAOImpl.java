package com.jmSpringBoot.crud.dao.daoImpl;

import com.jmSpringBoot.crud.dao.RoleDAO;
import com.jmSpringBoot.crud.model.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Role getRoleById(long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    @Transactional
    public Role getRoleByName(String roleName) {
        Query query = entityManager.createQuery("FROM Role where role = :paramName");
        query.setParameter("paramName", roleName);
        List roles = query.getResultList();
        return (roles.isEmpty()) ? null : (Role) roles.get(0);
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    @Transactional
    public void updateRole(Role role) {
        Role result = getRoleById(role.getId());
        result.setRole(role.getAuthority());
        entityManager.flush();
    }

    @Override
    @Transactional
    public void deleteRole(long id) {
        entityManager.remove(getRoleById(id));
    }

    @Override
    @Transactional
    public List<Role> getRoles() {
        return entityManager.createQuery("FROM Role").getResultList();
    }
}
