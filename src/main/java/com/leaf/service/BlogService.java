package com.leaf.service;

import com.leaf.po.Blog;
import com.leaf.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {

	Blog getBlog(Long id);

	//获取并转换
	Blog getAndConvert(Long id);

	Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

	Page<Blog> listBlog(Pageable pageable);

	Page<Blog> listBlog(Long tagId,Pageable pageable);

	Page<Blog> listBlog(String query,Pageable pageable);

	List<Blog> listRecommendBlogTop(Integer size);

	Map<String,List<Blog>> archiveBlog();

	Long countBlog();

	Blog saveBlog(Blog blog);

	Blog updateBlog(Long id, Blog blog);

	void deleteBlog(Long id);
}
