package com.jblog.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jblog.service.BlogService;
import com.jblog.vo.BlogVo;
import com.jblog.vo.CategoryVo;
import com.jblog.vo.PostVo;
import com.jblog.vo.UserVo;

@RequestMapping("/blog")
@Controller
public class BlogController {

	@Autowired
	private BlogService bService;
	
	@RequestMapping(value = "/{id}", method={RequestMethod.GET, RequestMethod.POST})
	public String main(@PathVariable("id") String id, Model model) {
		System.out.println("blog > " + id + " > main");

		HashMap<String, Object> map = bService.blogInfo(id);
		model.addAttribute("bVo", (BlogVo)map.get("bVo"));
		model.addAttribute("cList", (List<CategoryVo>)map.get("cList"));
						
		return "/blog/blog-main";
	}
	
	
	@RequestMapping(value="/{id}/admin/basic", method={RequestMethod.GET, RequestMethod.POST})
	public String adminBasic(@PathVariable("id") String id, Model model, HttpSession session) throws UnsupportedEncodingException {
		System.out.println("blog > " + id + "> admin > basic");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if (authUser == null || !authUser.getId().equals(id)) {
			id = URLEncoder.encode(id, "UTF-8");
			
			return "redirect:/blog/" + id;
		}
		
		HashMap<String, Object> map = bService.blogInfo(id);
		model.addAttribute("bVo", (BlogVo)map.get("bVo"));
		
		return "/blog/admin/blog-admin-basic";
	}
	
	
	@RequestMapping(value="/{id}/basicChange", method={RequestMethod.GET, RequestMethod.POST})
	public String basicChange(@PathVariable("id") String id, @RequestParam("file") MultipartFile file, @RequestParam("blogTitle") String title) throws UnsupportedEncodingException {
		System.out.println("blog > " + id + "> admin > basicChange");

		HashMap<String, Object> map = bService.blogInfo(id);
		bService.blogBasicUpdate((BlogVo)map.get("bVo"), file, title);
		
		id = URLEncoder.encode(id, "UTF-8");
		return "redirect:/blog/" + id + "/admin/basic";
	}
	
	
	@RequestMapping(value="/{id}/admin/category", method={RequestMethod.GET, RequestMethod.POST})
	public String adminCategory(@PathVariable("id") String id, Model model, HttpSession session) throws UnsupportedEncodingException {
		System.out.println("blog > " + id + "> admin > category");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if (authUser == null || !authUser.getId().equals(id)) {
			id = URLEncoder.encode(id, "UTF-8");
			
			return "redirect:/blog/" + id;
		}
		
		HashMap<String, Object> map = bService.blogInfo(id);
		model.addAttribute("bVo", (BlogVo)map.get("bVo"));
		model.addAttribute("cList", (List<CategoryVo>)map.get("cList"));
		
		return "/blog/admin/blog-admin-cate";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/{id}/admin/deleteCategory", method={RequestMethod.GET, RequestMethod.POST})
	public boolean deleteCategory(@RequestBody PostVo post) {
		System.out.println("blog > admin > deleteCategory");
		
		boolean result = bService.deleteCategory(post.getCateNo());
		
		return result;
	}
	
	
	@RequestMapping(value="/{id}/admin/writeForm", method={RequestMethod.GET, RequestMethod.POST})
	public String adminWrite(@PathVariable("id") String id, Model model, HttpSession session) throws UnsupportedEncodingException {
		System.out.println("blog > " + id + "> admin > write");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if (authUser == null || !authUser.getId().equals(id)) {
			id = URLEncoder.encode(id, "UTF-8");
			
			return "redirect:/blog/" + id;
		}
		
		HashMap<String, Object> map = bService.blogInfo(id);
		model.addAttribute("bVo", (BlogVo)map.get("bVo"));
		model.addAttribute("cList", (List<CategoryVo>)map.get("cList"));
		
		return "/blog/admin/blog-admin-write";
	}
	
	@RequestMapping(value="/{id}/admin/write", method={RequestMethod.GET, RequestMethod.POST})
	public String writePost(@PathVariable("id") String id, @ModelAttribute PostVo post) throws UnsupportedEncodingException {
		System.out.println("blog > write");
		
		bService.writePost(post);
		
		id = URLEncoder.encode(id, "UTF-8");
		return "redirect:/blog/" + id + "/admin/writeForm";
	}
}
