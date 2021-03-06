package com.leaf.po.dao;

import com.leaf.po.Comment;
import net.bytebuddy.TypeCache;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

	List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);

}
