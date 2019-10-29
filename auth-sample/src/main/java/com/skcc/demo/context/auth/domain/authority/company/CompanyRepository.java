package com.skcc.demo.context.auth.domain.authority.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.skcc.demo.context.auth.domain.authority.company.model.Company;


@RepositoryRestResource
public interface CompanyRepository extends QuerydslPredicateExecutor<Company>, JpaRepository<Company, Long>, PagingAndSortingRepository<Company,Long>{

	Company findByName(String string);

}
