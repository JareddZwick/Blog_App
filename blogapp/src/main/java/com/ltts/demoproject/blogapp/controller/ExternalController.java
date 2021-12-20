package com.ltts.demoproject.blogapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltts.demoproject.blogapp.bo.BlogBo;
import com.ltts.demoproject.blogapp.model.Blog;

@RestController
public class ExternalController {
	@Autowired
	BlogBo bo;
	
	@GetMapping("/External/blogs")
	public List<Blog> getAllBlogs(){
		return bo.getAllBlogs();
	}
	
	@GetMapping("/external/blog/{id}")
	public Blog getBlogById(@PathVariable String id) {
		return bo.getById(id);
	}
	
	@PostMapping("/External/Members")
	public boolean insertBlog(Blog b) {
		return bo.insertBlog(b);
	}
	
	
	@DeleteMapping("/External/member")
	public List<Blog> deletemember(String id) {
		bo.deleteBlog(id);
		return bo.getAllBlogs();
	}
	
}
