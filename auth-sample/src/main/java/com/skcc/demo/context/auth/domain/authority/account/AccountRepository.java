package com.skcc.demo.context.auth.domain.authority.account;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.skcc.demo.context.auth.domain.authority.account.model.Account;


@RepositoryRestResource
public interface AccountRepository extends QuerydslPredicateExecutor<Account>, JpaRepository<Account, Long>, PagingAndSortingRepository<Account,Long>{

	Optional<Account> findByEmail(String email);

}
