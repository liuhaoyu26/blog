package com.leaf.service;

import com.leaf.po.Comment;

import java.util.List;

public interface CommentService {

	List<Comment> listCommentByBlogId(Long blogId);

	Comment saveComment(Comment comment);
}
