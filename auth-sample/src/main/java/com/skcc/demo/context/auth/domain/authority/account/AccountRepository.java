package com.skcc.demo.context.auth.domain.authority.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.skcc.demo.context.auth.domain.authority.members.model.Account;


@RepositoryRestResource
public interface AccountRepository extends QuerydslPredicateExecutor<Account>, JpaRepository<Account, Long>, PagingAndSortingRepository<Account,Long>{

	Account findByEmail(@Param(value = "email")String email);

}
