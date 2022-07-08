package com.jblog.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jblog.vo.CommentsVo;

@Repository
public class CommentsDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	public List<CommentsVo> selectComment(int postNo) {
		List<CommentsVo> cmtList = sqlSession.selectList("comments.selectComment", postNo);
		
		return cmtList;
	}
	
	
	public CommentsVo selectRecent(int cmtNo) {
		CommentsVo cmt = sqlSession.selectOne("comments.selectRecent", cmtNo);
		
		return cmt;
	}
	
	
	public int insertComment(CommentsVo cmt) {
		int count = -1;
		count = sqlSession.insert("comments.insertComment", cmt);
		
		return count;
	}
	
	
	public int deleteComment(int cmtNo) {
		int count = -1;
		count = sqlSession.delete("comments.deleteComment", cmtNo);
		
		return count;
	}
}
