package com.jmSpringBoot.crud.dao;

import com.jmSpringBoot.crud.model.Role;

import java.util.List;

public interface RoleDAO {
    Role getRoleById(long id);
    Role getRoleByName(String roleName);
    void addRole(Role role);
    void updateRole(Role role);
    void deleteRole(long id);
    List<Role> getRoles();
}
