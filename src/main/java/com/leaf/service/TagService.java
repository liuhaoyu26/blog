package com.leaf.service;

import com.leaf.po.Tag;
import com.leaf.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {

	Tag saveTag(Tag tag);

	Tag getTag(Long id);

	Tag updateTag(Long id,Tag tag);

	void deleteTag(Long id);

	Page<Tag> listTag(Pageable pageable);

	List<Tag> listTagTop(Integer size);

	Tag getTagByName(String name);

	List<Tag> listTag();

	List<Tag> listTag(String ids);

	/*Tag getTagByName(String name);*/
}
