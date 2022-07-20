package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.service.blog.IBlogService;
import com.codegym.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blog")
public class BlogControllerRESTful {
    @Autowired
    public IBlogService blogService;
    @Autowired
    public ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<Iterable<Blog>> findAllBlog() {
        //ResponseEntity đại diện cho toàn bộ phản hồi HTTP
        List<Blog> blogs = (List<Blog>) blogService.findAll();
        if (blogs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> viewBlog(@PathVariable Long id){
        Blog blog = blogService.findById(id).get();
        if (blog == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }



}