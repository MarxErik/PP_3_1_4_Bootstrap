package org.marx.spring.bootstrap.service;


import org.marx.spring.bootstrap.model.Role;

import java.util.List;

public interface RoleService {
    void save(Role role);

    List<Role> findAllRoles();
}