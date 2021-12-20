package com.ltts.demoproject.blogapp.bo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ltts.demoproject.blogapp.model.Blog;
import com.ltts.demoproject.blogapp.model.Comment;
import com.ltts.demoproject.blogapp.model.User;

@Repository
public class CommentBo {
	@Autowired
	SessionFactory sf;
	public boolean insertComment(Comment c) {
		boolean bool=false;
		Session s = sf.openSession();
		s.beginTransaction();
		s.save(c);
		s.getTransaction().commit();
		s.close();
		return false;
	}
	
	public Comment getById(String blogid) {
		Session s = sf.openSession();
		s.beginTransaction();
		Comment m = s.createQuery("from Comment where comment_id = "+ blogid, Comment.class).getSingleResult();
		s.getTransaction().commit();
		s.close();
		return m;	
	}
	
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
	
	public List<Comment> getAllById(String reqid){
		List<Comment> li = null;
		Session s = sf.openSession();
		s.beginTransaction();
		li = s.createQuery("from Comment where blog_id = " + reqid, Comment.class).getResultList();
		s.getTransaction().commit();
		s.close();
		return li;
	}
	
	public void updateForm(Comment u) {
		Session s = sf.openSession();
		Transaction tx =s.beginTransaction();
		s.update(u);
		tx.commit();
		s.close();
	}
	
}
