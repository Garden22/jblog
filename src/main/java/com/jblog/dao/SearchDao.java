package com.jblog.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jblog.vo.BlogVo;

@Repository
public class SearchDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<BlogVo> selectByTitle(String keyword) {
		List<BlogVo> bList = sqlSession.selectList("blog.selectByTitle", keyword);
		
		return bList;
	}
	
	
	public List<BlogVo> selectByName(String keyword) {
		List<BlogVo> bList = sqlSession.selectList("blog.selectByName", keyword);
		
		return bList;
	}
	
}
