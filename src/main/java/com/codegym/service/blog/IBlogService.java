package com.codegym.service.blog;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IBlogService extends IGeneralService<Blog> {

    Iterable<Blog> findAllByCategory(Category category);

    Page<Blog> findAll(Pageable pageable);

    Page<Blog> findAllByNameContaining(String name, Pageable pageable);

    Page<Blog> findAllByOrderByDateTime(Pageable pageable);

}