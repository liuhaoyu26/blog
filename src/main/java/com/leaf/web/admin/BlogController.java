package com.leaf.web.admin;

import com.leaf.po.Blog;
import com.leaf.po.User;
import com.leaf.service.BlogService;
import com.leaf.service.TagService;
import com.leaf.service.TypeService;
import com.leaf.vo.BlogQuery;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class BlogController {

	@Autowired
	private BlogService blogService;

	@Autowired
	private TypeService typeService;

	@Autowired
	private TagService tagService;

	@GetMapping("/blogs")
	public String list(@PageableDefault(size = 4,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
	                   BlogQuery blog, Model model){
		model.addAttribute("types",typeService.listType());
		model.addAttribute("page",blogService.listBlog(pageable,blog));
		return "admin/blogs";
	}

	@PostMapping("/blogs/search")
	public String search(@PageableDefault(size = 4,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
	                     BlogQuery blog, Model model){
		model.addAttribute("page",blogService.listBlog(pageable,blog));
		return "admin/blogs :: blogList";
	}

	@GetMapping("/blogs/input")
	public String toInput(Model model){

		model.addAttribute("types",typeService.listType());
		model.addAttribute("tags",tagService.listTag());
		model.addAttribute("blog",new Blog());
		return "admin/blogs-input";
	}

	@GetMapping("/blogs/input/{id}")
	public String toEditInput(@PathVariable Long id, Model model){
		Blog blog = blogService.getBlog(id);
		blog.init();
		model.addAttribute("types",typeService.listType());
		model.addAttribute("tags",tagService.listTag());
		model.addAttribute("blog",blog);
		return "admin/blogs-input";
	}


	@PostMapping("/blogs")
	public String post(Blog blog, RedirectAttributes attributes, HttpSession session){
		blog.setUser((User) session.getAttribute("user"));
		blog.setType(typeService.getType(blog.getType().getId()));
		blog.setTags(tagService.listTag(blog.getTagIds()));
		Blog b ;
		if (blog.getId() == null) {
			b =  blogService.saveBlog(blog);
		} else {
			b = blogService.updateBlog(blog.getId(), blog);
		}

		if(b == null){
			attributes.addFlashAttribute("message","????????????1");
		}else{
			attributes.addFlashAttribute("message","?????????,????????????!!!");
		}
		return "redirect:/admin/blogs";
	}

	@GetMapping("/blog/delete/{id}")
	public String delete(@PathVariable Long id,RedirectAttributes attributes){
		typeService.deleteType(id);
		attributes.addFlashAttribute("message","????????????!!!");
		return "redirect:/admin/blogs";
	}
}
