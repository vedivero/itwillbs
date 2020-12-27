package com.bestpricemarket.utils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bestpricemarket.domain.GoodsVO;

import net.coobird.thumbnailator.Thumbnails;

@Component("fileUtils")
public class FileUtils {
	

	
	
	private static final String filePath = "C:\\mp\\file\\"; // 파일이 저장될 위치
	
	public List<Map<String, Object>> parseInsertFileInfo(GoodsVO vo, 
			MultipartHttpServletRequest mpRequest) throws Exception{
		
		
		
		Iterator<String> iterator = mpRequest.getFileNames();
		
		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> listMap = null;
		
		int gno = vo.getGno();
		
		File file = new File(filePath);
		if(file.exists() == false) {
			file.mkdirs();
		}
		
		while(iterator.hasNext()) {
			multipartFile = mpRequest.getFile(iterator.next());
			if(multipartFile.isEmpty() == false) {
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = getRandomString() + originalFileExtension;
				file = new File(filePath + storedFileName);
				//String filePath = mpRequest.getSession().getServletContext().getRealPath("/imgUpload");//
				multipartFile.transferTo(file);
				listMap = new HashMap<String, Object>();
				listMap.put("gno", gno);
				listMap.put("f_oname", originalFileName);
				listMap.put("f_name", storedFileName);
				listMap.put("f_size", multipartFile.getSize());
				//listMap.put("path", filePath+"/"+storedFileName);//
				list.add(listMap);
			}
			
		}
		return list;
	}
	
	public List<Map<String, Object>> parseUpdateFileInfo(GoodsVO vo, String[] files, String[] fileNames, MultipartHttpServletRequest mpRequest) throws Exception{ 
		Iterator<String> iterator = mpRequest.getFileNames();
		
		System.out.println("%#$%$%^$%^$%: "+mpRequest.getFileNames());
		
		MultipartFile multipartFile = null; 
		String originalFileName = null; 
		String originalFileExtension = null; 
		String storedFileName = null; 
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> listMap = null; 
		
		int gno = vo.getGno();
		
		while(iterator.hasNext()){ 
			multipartFile = mpRequest.getFile(iterator.next()); 
			if(multipartFile.isEmpty() == false){ 
				originalFileName = multipartFile.getOriginalFilename(); 
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf(".")); 
				storedFileName = getRandomString() + originalFileExtension; 
				multipartFile.transferTo(new File(filePath + storedFileName)); 
				listMap = new HashMap<String,Object>();
				listMap.put("IS_NEW", "Y");
				listMap.put("gno", gno); 
				listMap.put("f_oname", originalFileName);
				listMap.put("f_name", storedFileName); 
				listMap.put("f_size", multipartFile.getSize()); 
				list.add(listMap);
				System.out.println("WWWWWWWWWWWWW"+list);
			} 
			System.out.println("IIIIIIIIIIIIII"+list);
		}
		if(files != null && fileNames != null){ 
			for(int i = 0; i<fileNames.length; i++) {
					listMap = new HashMap<String,Object>();
                    listMap.put("IS_NEW", "N");
					listMap.put("fno", files[i]); 
					list.add(listMap); 
			}
		}
		System.out.println("$$$UUUUU :"+list);
		return list; 
	}

	
	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}