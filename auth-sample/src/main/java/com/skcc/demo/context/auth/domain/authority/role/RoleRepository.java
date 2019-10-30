package com.skcc.demo.context.auth.domain.authority.role;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.skcc.demo.context.auth.domain.authority.role.model.Role;
import com.skcc.demo.context.auth.domain.authority.role.model.RoleDivision;


@RepositoryRestResource
public interface RoleRepository extends QuerydslPredicateExecutor<Role>, JpaRepository<Role, Long>, PagingAndSortingRepository<Role,Long>{

	List<Role> findAllByRoleDivision(RoleDivision roleDivision);

	Role findByName(String name);

}
