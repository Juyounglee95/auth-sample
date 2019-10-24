package com.skcc.demo.context.auth.domain.authority.permission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.skcc.demo.context.auth.domain.authority.permission.model.Permission;

@RepositoryRestResource
public interface PermissionRepository extends QuerydslPredicateExecutor<Permission>, JpaRepository<Permission,Long>, PagingAndSortingRepository<Permission,Long>{

}
