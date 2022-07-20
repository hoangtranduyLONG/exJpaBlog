package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.service.blog.IBlogService;
import com.codegym.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private IBlogService blogService;
    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> categories(){
        return categoryService.findAll();
    }


    @GetMapping("/create-blog")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/blog/create");
        modelAndView.addObject("blog", new Blog());
        return modelAndView;
    }

    @PostMapping("/create-blog")
    public ModelAndView saveBlogs(@ModelAttribute("blog") Blog blog,@RequestParam MultipartFile file) {
        String nameImg = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(), new File("D:\\exersiseblog\\src\\main\\webapp\\WEB-INF\\img\\" + nameImg));
        } catch (IOException e) {
            e.printStackTrace();
        }
        blog.setImg("/img/" + nameImg);
        blog.setDateTime(java.time.LocalDate.now() + "");
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("/blog/create");
        modelAndView.addObject("blog", blog);
        modelAndView.addObject("message", "New blog created successfully");
        return modelAndView;
    }

    @GetMapping("/blogs")
    public ModelAndView listBlogs(@RequestParam("search") Optional<String> search, @PageableDefault(value = 5) Pageable pageable) {
        Page<Blog> blogs;
        if(search.isPresent()){
            blogs = blogService.findAllByNameContaining(search.get(), pageable);
        } else {
            blogs = blogService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/blog/list");
        modelAndView.addObject("blog", blogs);
        return modelAndView;
    }
    @GetMapping("/edit-blog/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Blog> blog = blogService.findById(id);
        if (blog.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/blog/edit");
            modelAndView.addObject("blog", blog.get());
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-blog")
    public ModelAndView updateBlog(@ModelAttribute("blog") Blog blog,@RequestParam MultipartFile file) {
        String nameImg = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(), new File("D:\\exersiseblog\\src\\main\\webapp\\WEB-INF\\img\\" + nameImg));
        } catch (IOException e) {
            e.printStackTrace();
        }
        blog.setImg("/img/" + nameImg);
        blogService.save(blog);
        return new ModelAndView("redirect:/blogs");
    }
    @GetMapping("/delete-blog/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Blog> blog = blogService.findById(id);
        if (blog.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/blog/delete");
            modelAndView.addObject("blog", blog.get());
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
    @PostMapping("/delete-blog")
    public String deleteBlog(@ModelAttribute("blog") Blog blog) {
        blogService.remove(blog.getId());
        return "redirect:blogs";
    }
    @GetMapping("/view-blog/{id}")
    public ModelAndView viewBlogs(@PathVariable Long id) {
        Optional<Blog> blog = blogService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/blog/view");
        modelAndView.addObject("blog", blog.get());
        return modelAndView;
    }

    @GetMapping("/sortByDateTime")
    public ModelAndView listBlogsSortByDateTime(@RequestParam("search") Optional<String> search, @PageableDefault(value = 5) Pageable pageable) {
        Page<Blog> blogs;
        if(search.isPresent()){
            blogs = blogService.findAllByNameContaining(search.get(), pageable);
        } else {
            blogs = blogService.findAllByOrderByDateTime(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/blog/list");
        modelAndView.addObject("blog", blogs);
        return modelAndView;
    }
}