package com.codegym.repository;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBlogRepository extends PagingAndSortingRepository<Blog, Long> {
    Iterable<Blog> findAllByCategory(Category category);

    Page<Blog> findAllByNameContaining(String name, Pageable pageable);

    Page<Blog> findAllByOrderByDateTime(Pageable pageable);

}