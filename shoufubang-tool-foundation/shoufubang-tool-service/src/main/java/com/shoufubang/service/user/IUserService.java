package com.shoufubang.service.user;

import java.util.List;
import java.util.Map;

import com.shoufubang.model.user.AccountLog;
import com.shoufubang.model.user.Invite;
import com.shoufubang.model.user.LoginInfo;
import com.shoufubang.model.user.User;
import com.shoufubang.model.user.UserInfo;

/**
 * @author chenbei
 */
public interface IUserService {
  

    User findById(String id);

    User findByMobile(String mobile);


    String register(Map<String, String> map);

  

    /**
     * 获取密码明文
     */
    String pwdMD5(String pwdDes, String mobile);

  

    /**
     * 查找用户最初加密password时用的盐
     *
     * @param mobile 电话号码
     * @return 只有盐
     */
    String findSaltByMobile(String mobile);

    
    /**
     * 个人信息
     *
     * @param userId 用户ID
     * @return 个人信息
     */
    Map userInfo(String userId);

    Boolean isExist(String mobile);


    /**
     * 根据用户id获取用户详细信息
     *
     * @param userId 用户id
     * @return
     */
    public UserInfo findUserInfoByUserId(String userId);


  

    public Integer resetUserPwd(Map<String, Object> info);

    
	User findByinviteFrom(String inviteFrom);

	
	/** 查询账户信息
	 * 
	 * @param user
	 * @return
	 */
	Map<String, Object> getAccount(User user);

	/** 
	 * 根据用户id查询其邀请记录
	 * @param id
	 * @return
	 */
	List<Map<String, Object>> findInvitedListByUserId(User user);

	/**
	 * 根据用户id查询账户(挖矿)记录
	 * findAccountLogListByUserId()   
	 * @author  zhangyiying     
	 * @date  2018年3月6日 下午6:49:43     
	 * @param accountLog
	 * @return
	 */
	List<Map<String, Object>> findAccountLogListByUserId(AccountLog accountLog);
	
	
	
	/** 查询账户信息
	 * 
	 * @param 查询user 账户
	 * @return
	 */
	Map<String, Object> getAccountIndex(User u);

	
	
	/**  实名认证
	 * @param user
	 * @return
	 */
	Boolean realName(User user);

	
	
	/** 该idcard是否实名认证过
	 * @param userId
	 * @return
	 */
	boolean findRealNameByIdCard(String idcard);

	
	/** 该用户认证信息
	 * @param userId
	 * @return
	 */
	User findRealNameByIdUserid(Integer userId);

	
	/** 算力排行
	 * @return
	 */
	List<Map<String, Object>> findstressRank();
	
	
	
	/** 查询平台数据
	 * @param userId
	 * @return
	 */
	Map<String, Object> getdataInfo(Integer userId);

	/** 查询用户邀请码以及剩余邀请次数
	 * @param userId
	 * @return
	 */
	Map<String, Object> getInvCode(Integer userId);


	/**查询是否已经认证通用写法
	 * @param userId
	 * @return
	 */
	int isMobileAuth(Integer userId, String string);

	/** 三要素认证
	 * @param userRealName
	 * @return 
	 */
	boolean mobileAuth(User userRealName);

	Map<String, Object> getIsOpenAPI(Integer userId);

	/** 查询 注册人数量
	 * @return
	 */
	int findCountRegister();

	/**查询全网算力
	 * @return
	 */
	long findTotalstressRank();

	/**查询自己算力 和邀请链接**/
	Map<String, Object> findDataforBegin(String mobile);
	
	/**
	 * 分配创世纪糖果
	 * giveGenesisCandy()   
	 * @author  zhangyiying     
	 * @date  2018年4月25日 上午9:53:38     
	 * @param d 分发总糖果数
	 * @param countUser 当前总人数
	 * @param begin_user 设定创世纪人数
	 * @return
	 */
	String giveGenesisCandy(double d, Integer begin_user, Integer countUser);

	/**
	 * 统计邀请人数和获取奖励
	 */
	Map<String, Object> findInvStatisticsByUserid(User user);

	
	/** 修改密码
	 * @param map
	 * @return
	 */
	String updatePassword(Map<String,Object> map);
	

  
}
