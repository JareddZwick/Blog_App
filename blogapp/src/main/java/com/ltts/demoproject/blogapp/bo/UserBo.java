package com.ltts.demoproject.blogapp.bo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ltts.demoproject.blogapp.model.Blog;
import com.ltts.demoproject.blogapp.model.User;

@Repository
public class UserBo{
	@Autowired
	SessionFactory sf;
	
	public List<String> mobiles = new ArrayList<String>(100);
	
	public void updateForm(User u) {
		Session s = sf.openSession();
		Transaction tx =s.beginTransaction();
		s.update(u);
		tx.commit();
		s.close();
	}
	
	
	public boolean save(User u) {
		boolean bool=false;
		Session s = sf.openSession();
		s.beginTransaction();
		s.save(u);
		s.getTransaction().commit();
		s.close();
		return false;
	}
	
	public User getById(String blogid) {
		Session s = sf.openSession();
		s.beginTransaction();
		User m = s.createQuery("from User where email_id = "+ blogid, User.class).getSingleResult();
		s.getTransaction().commit();
		s.close();
		return m;	
	}
	
	public User getByMobile(String mobile) {
		System.out.println(mobile);
		Session s = sf.openSession();
		s.beginTransaction();
		User u = s.createQuery("from User where mobile = "+mobile, User.class).getSingleResult();
		s.getTransaction().commit();
		s.close();
		return u;
	}
}
