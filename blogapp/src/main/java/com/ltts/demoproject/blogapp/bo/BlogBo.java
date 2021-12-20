package com.ltts.demoproject.blogapp.bo;
import com.ltts.demoproject.blogapp.model.*;
import com.ltts.demoproject.ticketapplication.model.movie;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Repository;


@Repository
public class BlogBo{
	@Autowired
	SessionFactory sf;
	public List<Blog> getAllBlogs(){
		List<Blog> li =null;
		Session s = sf.openSession();
		s.beginTransaction();
		li = s.createQuery("from Blog", Blog.class).getResultList();
		System.out.println(li);
		s.getTransaction().commit();
		s.close();
		return li;
	}
	
	public boolean insertBlog(Blog b) {
		boolean bool=false;
		Session s = sf.openSession();
		s.beginTransaction();
		s.save(b);
		s.getTransaction().commit();
		s.close();
		return false;
	}
	
	public Blog getById(String blogid) {
		Session s = sf.openSession();
		s.beginTransaction();
		Blog m = s.createQuery("from Blog where blog_id = "+ blogid, Blog.class).getSingleResult();
		s.getTransaction().commit();
		s.close();
		return m;	
	}
	
	public void updateForm(Blog b, String blogT, String blogC, String blogL) {
		Session s = sf.openSession();
		Transaction tx =s.beginTransaction();
		b.setBlogTitle(blogT);
		b.setBlogContent(blogC);
		b.setLanguage(blogL);
		s.update(b);
		tx.commit();
		s.close();
	}
	
	public List<Blog> getByLikes(){
		Session s = sf.openSession();
		s.beginTransaction();
		List<Blog> li = s.createQuery("from Blog order by likes desc", Blog.class).getResultList();
		System.out.println(li);
		s.getTransaction().commit();
		s.close();
		return li;
	}
	
	public List<Blog> getByDislikes(){
		Session s = sf.openSession();
		s.beginTransaction();
		List<Blog> li = s.createQuery("from Blog order by dislikes desc", Blog.class).getResultList();
		System.out.println(li);
		s.getTransaction().commit();
		s.close();
		return li;
	}
	
	public List<Blog> getByComments(){
		Session s = sf.openSession();
		s.beginTransaction();
		List<Blog> li = s.createQuery("from Blog order by comments desc", Blog.class).getResultList();
		System.out.println(li);
		s.getTransaction().commit();
		s.close();
		return li;
	}
	
	public void deleteBlog(String blogid) {
		int blogi = Integer.parseInt(blogid);
		Session s = sf.openSession();
		s.beginTransaction();
		Blog b = s.load(Blog.class, blogi);
		s.delete(b);
		s.getTransaction().commit();
		s.close();
	}
}
