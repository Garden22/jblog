package com.jblog.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jblog.vo.CategoryVo;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int insertBlog(String id) {
		int count = -1;
		count = sqlSession.insert("category.insertBlog", id);
		
		return count;
	}
	
	
	public List<CategoryVo> selectCate(String id) {
		List<CategoryVo> cList = sqlSession.selectList("category.selectCate", id);
		
		return cList;
	}
	
	
	public int deleteCategory(int cateNo) {
		int count = -1;
		count = sqlSession.delete("category.deleteCategory", cateNo);
		
		return count;
	}
}
