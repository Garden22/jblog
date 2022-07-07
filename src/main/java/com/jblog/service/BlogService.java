package com.jblog.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jblog.dao.BlogDao;
import com.jblog.dao.CategoryDao;
import com.jblog.dao.PostDao;
import com.jblog.vo.BlogVo;
import com.jblog.vo.PostVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao bDao;
	@Autowired
	private CategoryDao cDao;
	@Autowired
	private PostDao pDao;
	
	public HashMap<String, Object> blogInfo(String id) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("bVo", bDao.selectBlog(id));
		map.put("cList", cDao.selectCate(id));
		
		return map;
	}
	
	
	public void blogBasicUpdate(BlogVo bVo, MultipartFile file, String title) {
		if (!file.isEmpty()) {
			String orgName = file.getOriginalFilename();
			String exName = orgName.substring(orgName.lastIndexOf("."));
			String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
			
			String saveDir = "C:\\javastudy\\upload";
			String filePath = saveDir + "\\" + saveName;
		
		try {
			byte[] fileData = file.getBytes();
			BufferedOutputStream bs = new BufferedOutputStream(new FileOutputStream(filePath));
			
			bs.write(fileData);
			System.out.println("[저장완료]");
			bs.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
			bVo.setLogoFile("/upload/"+saveName);
		}
		
		if (title.length() != 0 ) bVo.setBlogTitle(title);
		int count = bDao.updateBasic(bVo);
		
		if (count > 0) System.out.println("[업데이트가 완료되었습니다.]");
		else System.out.println("[업데이트가 실패했습니다.]");
		
	}
	
	
	public void writePost(PostVo post) {
		post.setPostContent(post.getPostContent().replace("\n", "<br>"));
		int count = pDao.insertPost(post);
		
		if (count > 0) System.out.println("[게시글이 등록되었습니다.]");
		else System.out.println("[게시글이 등록되지 않았습니다.]");
	}
	
	
	public boolean deleteCategory(int cateNo) {
		boolean result = false;
		int count = cDao.deleteCategory(cateNo);
		
		if (count > 0) {
			System.out.println("[카테고리 삭제 완료]");
			result = true;
		} else System.out.println("[카테고리 삭제 실패]");
		
		return result;
	}
}
