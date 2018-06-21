package com.shoufubang.service.user;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.shoufubang.model.user.UserFile;


public interface UserFileService {

	Map insertUserFile(UserFile userFile);

	/**
	 * <pre>deleteUserFileByFileType(清空多文件上传)   
	 * 创建人：彭榉 pengju918@sina.com    
	 * 创建时间：2016年9月1日 下午6:37:38    
	 * 修改人：彭榉 pengju918@sina.com     
	 * 修改时间：2016年9月1日 下午6:37:38    
	 * 修改备注： 
	 * @param userFile
	 * @return</pre>
	 */
	Integer deleteUserFileByFileType(UserFile userFile);

	/**
	 * 根据父id获取文件集合
	 * getCertificateFile()   
	 * @author  zhangyiying     
	 * @date  2016年12月6日 上午11:13:11     
	 * @param userFile 	包含
	 * 					所属公司id 	fullNumber	
	 * @return    
	 */
	List<UserFile> getFileListByPid(UserFile userFile);

	/** 
	 * 根据文件类型获取文件(采用默认上传路径+日期)
	 * getFileByType()   
	 * @author  zhangyiying     
	 * @date  2016年12月6日 下午1:31:10     
	 * @param userFile 	包含
	 * 					文件类型	type
	 * 					所属公司id 	fullNumber	
	 * @return    
	 */
	List<UserFile> getFileByType(UserFile userFile);

	 
	/** 
	 * 单文件上传 (可以指定上传路径)
	 * uploadSingleFile()   
	 * @author  zhangyiying     
	 * @date  2016年12月19日 上午10:39:52     
	 * @param  MultipartFile	要上传的文件
	 * @param userFile	包含
	 * 					文件类型	type
	 * 					操作人id	userId
	 * @return    
	 */
	Map uploadSingleFile(MultipartFile multipartFile,UserFile userFile);
	
	/** 
	 * 单文件上传
	 * uploadSingleFile()   
	 * @author  zhangyiying     
	 * @date  2016年12月19日 上午10:39:52     
	 * @param  MultipartFile	要上传的文件
	 * @param userFile	包含
	 * 					文件类型	type
	 * 					操作人id	userId
	 * @param path 上传的路径
	 * @return    
	 */
	String uploadSingleFile(File multipartFile,UserFile userFile, String path);
	
	/** uploadSingleFile()   上传文件 
	 * @author  zhangyiying     
	 * @date  2017年3月23日 下午4:38:11     
	 * @param multipartFile 文件
	 * @param type	文件类型 例如:30202
	 * @param userId	操作人
	 * @param fileName	文件名(带后缀名)
	 * @param path	路径(不带文件名)
	 * @return    
	 */
	String uploadSingleFile(File file, Integer type, Integer fullNumber,Integer userId,String fileName,String path);
	
	/** insertFile()   添加文件
	 * @author  zhangyiying     
	 * @date  2017年3月23日 下午4:38:11     
	 * @param userFile(包含以下属性)
	 * @param type	文件类型 例如:30202
	 * @param userId	操作人
	 * @param fileName	文件名(带后缀名)
	 * @param path	路径(不带文件名)
	 * @return    
	 */
	void insertFile(UserFile userFile);
	
	/** 
	 * 多文件长传
	 * uploadSingleFile()   
	 * @author  zhangyiying     
	 * @date  2016年12月19日 上午10:52:40     
	 * @param  MultipartFile	要上传的文件集合
	 * @param userFile	包含
	 * 					文件类型	type
	 * 					操作人id	userId
	 * @return    
	 */
	void uploadMultiFile(List<MultipartFile> files,UserFile userFile);
	
	
	
	/** updateFile()   修改文件
	 * @author  zhangyiying     
	 * @date  2017年3月27日 上午9:52:27     
	 * @param userFile    
	 */
	void updateFile(UserFile userFile);

	/**
	 * getDetailById()   
	 * @author  zhangyiying     
	 * @date  2018年4月18日 上午11:16:16     
	 * @param userFile
	 * @return
	 */
	UserFile getDetailById(UserFile userFile);

	/**
	 * 查询可以更改状态的UserFile
	 * findUpdateableFile()   
	 * @author  zhangyiying     
	 * @date  2018年4月18日 下午4:02:42     
	 * @return
	 */
	List<UserFile> findUpdateableFile();

}
