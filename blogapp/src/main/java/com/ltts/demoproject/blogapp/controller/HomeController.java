package com.ltts.demoproject.blogapp.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ltts.demoproject.blogapp.bo.BlogBo;
import com.ltts.demoproject.blogapp.bo.CommentBo;
import com.ltts.demoproject.blogapp.bo.UserBo;
import com.ltts.demoproject.blogapp.files.FileUploadUtil;
import com.ltts.demoproject.blogapp.model.Blog;
import com.ltts.demoproject.blogapp.model.Comment;
import com.ltts.demoproject.blogapp.model.User;

@RestController
public class HomeController {
	
	@Autowired
	UserBo ub;
	
	@Autowired
	BlogBo bo;
	
	@Autowired
	CommentBo co;
	
	@RequestMapping("/")
	public ModelAndView m1() {
		return new ModelAndView("index");
	}
	
	
	// @Configuration
	// @EnableWebSecurity
	// class x extends WebSecurityConfigAdaptor
	@RequestMapping("/login")
	public ModelAndView m2() {
		return new ModelAndView("login");
	}
	
	@RequestMapping("/register")
	public ModelAndView m3() {
		return new ModelAndView("register");
	}
	
	@RequestMapping(value="insertuser", method=RequestMethod.POST)
	public ModelAndView m4(HttpServletRequest req, @RequestParam("image") MultipartFile multipartFile, HttpSession Session) {
		ModelAndView mv=null;
		String email = req.getParameter("uemail");
		String mobile = req.getParameter("mobile");
		String gender=req.getParameter("gender");
		String location=req.getParameter("loc");
		String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		if(email.contains("@") & mobile.contains("789")) {
		User u = new User(email, mobile, gender, location);
		u.setPhotos(filename);
		ub.save(u);
		String uploadDir = "user-photos/" + u.getMobile();
		
		try {
			FileUploadUtil.saveFile(uploadDir, filename, multipartFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Session.setAttribute("mobile", mobile);
		mv = new ModelAndView("home");
		return mv;}
		else {
			mv = new ModelAndView("register");
			return mv;
		}
	}
	
	@RequestMapping( value="checkuser", method=RequestMethod.POST)
	public ModelAndView m6(HttpServletRequest req, Model model, HttpSession Session) {
		ModelAndView mv=null;
		String email=req.getParameter("email");
		String pass=req.getParameter("password");
		
		//User m = ub.getById(email);
		User m = ub.getByMobile(pass);
		Session.setAttribute("mobile", m.getMobile());
		String s = (String) Session.getAttribute(pass);
		System.out.println("Email id: " + s);
		Session.getAttribute(pass);
			if(m.getEmailId().equals(email)) {
				if(m.getMobile().equals(pass)) {
					System.out.println(m.isAdmin());
					if(m.isAdmin()) {
						Session.setAttribute(pass, m.getMobile());
						model.addAttribute("user", s);
						mv = new ModelAndView("admin");
					}
					else {
						model.addAttribute("user", m);
						List<Blog> li = bo.getAllBlogs();
						model.addAttribute("blog", li);
						return new ModelAndView("home");
					}
				}
				else {
					mv = new ModelAndView("login");
				}
			}
			else {
				mv = new ModelAndView("login");
			}
			return mv;
		}
	
	@RequestMapping("/home")
	public ModelAndView m7(HttpServletRequest req, Model model) {
		List<Blog> li = bo.getAllBlogs();
		model.addAttribute("blog", li);
		return new ModelAndView("home");
	}
	
	
	@RequestMapping("/addblog")
	public ModelAndView m8() {
		return new ModelAndView("addblog");
	}
	
	@RequestMapping(value="insertblog", method=RequestMethod.POST)
	public ModelAndView m9(HttpServletRequest req, Model model) {
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String lang = req.getParameter("lang");
		String datetime = new SimpleDateFormat("yyyy-MM-dd : HH-mm-ss").format(new java.util.Date());
		Blog b = new Blog(title, content, lang, datetime, 0, 0, 0);
		bo.insertBlog(b);
		List<Blog> li = bo.getAllBlogs();
		model.addAttribute("blog", li);
		return new ModelAndView("home");	
	}
	
	@RequestMapping(value="viewblog", method=RequestMethod.GET)
	public ModelAndView m10(HttpServletRequest req, Model model) {
		String blogid = req.getParameter("blog");
		List<Comment>li = co.getAllById(blogid);
		Blog b = bo.getById(blogid);
		model.addAttribute("blog", b);
		model.addAttribute("comment", li);
		return new ModelAndView("viewblog");
	}
	
	@RequestMapping(value="insertcomment", method=RequestMethod.POST)
	public ModelAndView m11(HttpServletRequest req, HttpSession Session, Model model) {
		String comment = req.getParameter("comment");
		String blog = req.getParameter("blog");
		String s = (String) Session.getAttribute("mobile");
		User u = ub.getByMobile(s);
		String datentime = new SimpleDateFormat("yyyy-MM-dd : HH-mm-ss").format(new java.util.Date());
		System.out.println("Email id: " + s);
		Comment c = new Comment(blog, u.getEmailId(), comment, datentime);
		Blog b = bo.getById(blog);
		b.addComment();
		bo.updateForm(b, b.getBlogTitle(), b.getBlogContent(), b.getLanguage());
		co.insertComment(c);
		List<Comment>li = co.getAllById(blog);
		model.addAttribute("blog", b);
		model.addAttribute("comment", li);
		return new ModelAndView("viewblog");
	}
	
	@RequestMapping(value="chooseupdate")
	public ModelAndView m12(Model model) {
		List<Blog> li = bo.getAllBlogs();
		System.out.println(li);
		model.addAttribute("blog", li);
		return new ModelAndView("chooseupdate");
	}
	
	@RequestMapping(value="updateblog")
	public ModelAndView m13(HttpServletRequest req, Model model) {
		String blogid = req.getParameter("blogname");
		Blog b = bo.getById(blogid);
		model.addAttribute("blog", b);
		return new ModelAndView("updateblog");
	}
	
	@RequestMapping(value="updatesubmit")
	public ModelAndView m14(HttpServletRequest req, Model model) {
		String blog = req.getParameter("blog");
		Blog b = bo.getById(blog);
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String lang = req.getParameter("lang");
		bo.updateForm(b,title, content, lang);
		List<Blog> li = bo.getAllBlogs();
		model.addAttribute("blog", li);
		return new ModelAndView("home");	
	}
	
	@RequestMapping(value = "viewbylang", method=RequestMethod.GET)
	public ModelAndView m15(HttpServletRequest req, Model model) {
		String language = req.getParameter("lang");
		List<Blog> li = bo.getAllBlogs();
		List<Blog> filterli = new ArrayList<Blog>();
		for(Blog b:li) {
			if(b.getLanguage().equals(language)) {
				filterli.add(b);
			}
		}
		model.addAttribute("blog", filterli);
		return new ModelAndView("home");
	}
	
	@RequestMapping(value="like", method=RequestMethod.GET)
	public ModelAndView m16(HttpServletRequest req, Model model, HttpSession Session) {
		String blog = req.getParameter("blog");
		Blog b = bo.getById(blog);
		String s = (String) Session.getAttribute("mobile");
		User u = ub.getByMobile(s);
		String likeStr = blog + ", ";
		System.out.println(likeStr);
		if(u.getLikes().contains(likeStr)) {
			System.out.println(u.getLikes());
			List<Comment>li = co.getAllById(blog);
			model.addAttribute("blog", b);
			model.addAttribute("comment", li);
			return new ModelAndView("viewblog");
		}
		else if(u.getDislikes().contains(likeStr)) {
			// get all dislikes blogs from user
			String newStr = u.getDislikes();
			System.out.println(newStr);
			// remove blog form dislikes string
			newStr = newStr.replace(likeStr, "");
			System.out.println(newStr);
			u.setDislikes(newStr);
			// Add blog to likes string
			String newLikeStr = u.getLikes();
			System.out.println(newLikeStr);
			newLikeStr = newLikeStr + likeStr;
			System.out.println(newLikeStr);
			u.setLikes(newLikeStr);
			ub.updateForm(u);
			b.subDislike();
			b.addLike();
			bo.updateForm(b, b.getBlogTitle(), b.getBlogContent(), b.getLanguage());
			System.out.println(u.getLikes());
			List<Comment>li = co.getAllById(blog);
			model.addAttribute("blog", b);
			model.addAttribute("comment", li);
			return new ModelAndView("viewblog");
		}
		else {
			String newLikeStr = u.getLikes();
			System.out.println(newLikeStr);
			newLikeStr = newLikeStr + likeStr;
			System.out.println(newLikeStr);
			u.setLikes(newLikeStr);
			ub.updateForm(u);
			b.addLike();
			bo.updateForm(b, b.getBlogTitle(), b.getBlogContent(), b.getLanguage());
			System.out.println(u.getLikes());
			List<Comment>li = co.getAllById(blog);
			model.addAttribute("blog", b);
			model.addAttribute("comment", li);
			return new ModelAndView("viewblog");
		}
	}
	
	@RequestMapping(value="dislike", method=RequestMethod.GET)
	public ModelAndView m17(HttpServletRequest req, Model model, HttpSession Session) {
		String blog = req.getParameter("blog");
		Blog b = bo.getById(blog);
		String s = (String) Session.getAttribute("mobile");
		User u = ub.getByMobile(s);
		String DislikeStr = blog + ", ";
		System.out.println(DislikeStr);
		if(u.getDislikes().contains(DislikeStr)) {
			// If user already dislikes blog, do nothing
			List<Comment>li = co.getAllById(blog);
			model.addAttribute("blog", b);
			model.addAttribute("comment", li);
			return new ModelAndView("viewblog");
		}
		else if(u.getLikes().contains(DislikeStr)) {
			// Else if user liked blog,
			String newStr = u.getLikes();
			System.out.println(newStr);
			// remove blog form likes string
			newStr = newStr.replace(DislikeStr, "");
			System.out.println(newStr);
			u.setLikes(newStr);
			String newLikeStr = u.getDislikes();
			System.out.println(newLikeStr);
			newLikeStr = newLikeStr + DislikeStr;
			System.out.println(newLikeStr);
			u.setDislikes(newLikeStr);
			ub.updateForm(u);
			b.subLike();
			b.addDislike();
			bo.updateForm(b, b.getBlogTitle(), b.getBlogContent(), b.getLanguage());
			System.out.println(u.getLikes());
			List<Comment>li = co.getAllById(blog);
			model.addAttribute("blog", b);
			model.addAttribute("comment", li);
			return new ModelAndView("viewblog");
		}
		else {
			// User has not liked/disliked before
			String newLikeStr = u.getDislikes();
			System.out.println(newLikeStr);
			newLikeStr = newLikeStr + DislikeStr;
			System.out.println(newLikeStr);
			u.setDislikes(newLikeStr);
			ub.updateForm(u);
			b.addDislike();
			bo.updateForm(b, b.getBlogTitle(), b.getBlogContent(), b.getLanguage());
			System.out.println(u.getLikes());
			List<Comment>li = co.getAllById(blog);
			model.addAttribute("blog", b);
			model.addAttribute("comment", li);
			return new ModelAndView("viewblog");
		}
	}
	
	@RequestMapping("/profile")
	public ModelAndView m18(HttpSession Session, Model model) {
		String s = (String) Session.getAttribute("mobile");
		User u = ub.getByMobile(s);
		String imgsrc = "../user-photos/"+u.getMobile()+"/"+u.getPhotos();
		u.setImgsrc(imgsrc);
		model.addAttribute("prof", u);
		return new ModelAndView("updateprofile");
	}
	
	@RequestMapping(value="updateprofile")
	public ModelAndView m19(HttpServletRequest req, Model model, HttpSession Session) {
		String s = (String) Session.getAttribute("mobile");
		User u = ub.getByMobile(s);
		String gender = req.getParameter("gender");
		String loco = req.getParameter("location");
		u.setGender(gender);
		u.setLocation(loco);
		ub.save(u);
		List<Blog> li = bo.getAllBlogs();
		model.addAttribute("blog", li);
		return new ModelAndView("home");	
	}
	
	@RequestMapping(value = "sortBy", method=RequestMethod.GET)
	public ModelAndView m20(HttpServletRequest req, Model model) {
		String crit = req.getParameter("crit");
		List<Blog> filterli = new ArrayList<Blog>();
		switch(crit) {
		case "likes":
			filterli = bo.getByLikes();
			System.out.println("Ordered by likes");
			break;
		case "dislikes":
			filterli = bo.getByDislikes();
			System.out.println("Ordered by Dislikes");
			break;
		case "comments":
			filterli = bo.getByComments();
			System.out.println("Ordered by Comments");
			break;
		}
		model.addAttribute("blog", filterli);
		return new ModelAndView("home");
	}
	
	@RequestMapping(value = "delete", method=RequestMethod.GET)
	public ModelAndView m21(HttpServletRequest req, Model model) {
		String blog = req.getParameter("blog");
		bo.deleteBlog(blog);
		return new ModelAndView("index");
	}
	
	@RequestMapping(value="likeC", method=RequestMethod.GET)
	public ModelAndView m22(HttpServletRequest req, Model model, HttpSession Session) {
		String blog = req.getParameter("com");
		String blogid = req.getParameter("blog");
		Comment b = co.getById(blog);
		String s = (String) Session.getAttribute("mobile");
		User u = ub.getByMobile(s);
		String likeStr = blog + ", ";
		System.out.println(likeStr);
		if(u.getcLikes().contains(likeStr)) {
			System.out.println(u.getcLikes());
			List<Comment>li = co.getAllById(blogid);
			Blog bl = bo.getById(blogid);
			model.addAttribute("blog", bl);
			model.addAttribute("comment", li);
			return new ModelAndView("viewblog");
		}
		else if(u.getcDislikes().contains(likeStr)) {
			// get all dislikes blogs from user
			String newStr = u.getcDislikes();
			System.out.println(newStr);
			// remove blog form dislikes string
			newStr = newStr.replace(likeStr, "");
			System.out.println(newStr);
			u.setDislikes(newStr);
			// Add blog to likes string
			String newLikeStr = u.getcLikes();
			System.out.println(newLikeStr);
			newLikeStr = newLikeStr + likeStr;
			System.out.println(newLikeStr);
			u.setcLikes(newLikeStr);
			ub.updateForm(u);
			b.subDislike();
			b.addLike();
			co.updateForm(b);
			System.out.println(u.getcLikes());
			List<Comment>li = co.getAllById(blogid);
			Blog bl = bo.getById(blogid);
			model.addAttribute("blog", bl);
			model.addAttribute("comment", li);
			return new ModelAndView("viewblog");
		}
		else {
			String newLikeStr = u.getcLikes();
			System.out.println(newLikeStr);
			newLikeStr = newLikeStr + likeStr;
			System.out.println(newLikeStr);
			u.setcLikes(newLikeStr);
			ub.updateForm(u);
			b.addLike();
			co.updateForm(b);
			System.out.println(u.getcLikes());
			List<Comment>li = co.getAllById(blogid);
			Blog bl = bo.getById(blogid);
			model.addAttribute("blog", bl);
			model.addAttribute("comment", li);
			return new ModelAndView("viewblog");
		}
	}
	
	@RequestMapping(value="dislikeC", method=RequestMethod.GET)
	public ModelAndView m23(HttpServletRequest req, Model model, HttpSession Session) {
		String blog = req.getParameter("com");
		String blogid = req.getParameter("blog");
		Comment b = co.getById(blog);
		String s = (String) Session.getAttribute("mobile");
		User u = ub.getByMobile(s);
		String DislikeStr = blog + ", ";
		System.out.println(DislikeStr);
		if(u.getcDislikes().contains(DislikeStr)) {
			// If user already dislikes comment, do nothing
			List<Comment>li = co.getAllById(blogid);
			Blog bl = bo.getById(blogid);
			model.addAttribute("blog", bl);
			model.addAttribute("comment", li);
			return new ModelAndView("viewblog");
		}
		else if(u.getcLikes().contains(DislikeStr)) {
			// Else if user liked blog,
			String newStr = u.getcLikes();
			System.out.println(newStr);
			// remove blog form likes string
			newStr = newStr.replace(DislikeStr, "");
			System.out.println(newStr);
			u.setcLikes(newStr);
			String newLikeStr = u.getcDislikes();
			System.out.println(newLikeStr);
			newLikeStr = newLikeStr + DislikeStr;
			System.out.println(newLikeStr);
			u.setcDislikes(newLikeStr);
			ub.updateForm(u);
			b.subLike();
			b.addDislike();
			co.updateForm(b);
			System.out.println(u.getcLikes());
			List<Comment>li = co.getAllById(blogid);
			Blog bl = bo.getById(blogid);
			model.addAttribute("blog", bl);
			model.addAttribute("comment", li);
			return new ModelAndView("viewblog");
		}
		else {
			// User has not liked/disliked before
			String newLikeStr = u.getcDislikes();
			System.out.println(newLikeStr);
			newLikeStr = newLikeStr + DislikeStr;
			System.out.println(newLikeStr);
			u.setcDislikes(newLikeStr);
			ub.updateForm(u);
			b.addDislike();
			co.updateForm(b);
			System.out.println(u.getcLikes());
			List<Comment>li = co.getAllById(blogid);
			Blog bl = bo.getById(blogid);
			model.addAttribute("blog", bl);
			model.addAttribute("comment", li);
			return new ModelAndView("viewblog");
		}
	}
	
	@RequestMapping("/logout")
	public ModelAndView m24(HttpSession Session) {
		Session.removeAttribute("mobile");
		return new ModelAndView("index");
	}
	
	@RequestMapping("/admindash")
	public ModelAndView m25(HttpSession Session) {
		return new ModelAndView("admin");
	}
	
}
