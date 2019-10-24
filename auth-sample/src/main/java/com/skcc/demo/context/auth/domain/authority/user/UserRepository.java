package com.skcc.demo.context.auth.domain.authority.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.skcc.demo.context.auth.domain.authority.user.model.User;


@RepositoryRestResource
public interface UserRepository extends QuerydslPredicateExecutor<User>, JpaRepository<User, Long>, PagingAndSortingRepository<User,Long>{

}
