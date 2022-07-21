package com.mySpringBootProject.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mySpringBootProject.main.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	@Query("select c from Category c where c.preference=?1")
	List<Category> findByPreference(Integer preference);

}
/*
JPQL: game changer
*/
