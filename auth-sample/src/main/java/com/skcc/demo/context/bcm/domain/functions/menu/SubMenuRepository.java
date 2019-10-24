package com.skcc.demo.context.bcm.domain.functions.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.skcc.demo.context.bcm.domain.functions.menu.model.SubMenu;


@RepositoryRestResource
public interface SubMenuRepository extends QuerydslPredicateExecutor<SubMenu>, JpaRepository<SubMenu, Long>, PagingAndSortingRepository<SubMenu,Long>{

}
