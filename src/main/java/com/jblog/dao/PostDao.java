package com.jblog.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jblog.vo.PostVo;

@Repository
public class PostDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int insertPost(PostVo post) {
		int count = -1;
		count = sqlSession.insert("post.insertPost", post);
		
		return count;
	}
	
	
	public int selectRecent(int cateNo) {
		int postNo = sqlSession.selectOne("post.selectRecent", cateNo);
		
		return postNo;
	}
	
	
	public PostVo selectPost(int postNo) {
		PostVo post = sqlSession.selectOne("post.selectPost", postNo);
		
		return post;
	}
	
	
	public List<PostVo> selectCatePost(int cateNo) {
		List<PostVo> pList = sqlSession.selectList("post.selectCatePost", cateNo);
		
		return pList;
	}

}
