package com.shoufubang.service.user;

import com.shoufubang.model.user.LoginLog;
import com.shoufubang.model.user.User;


/**
 * Created by 陈蓓 on 2015/11/19.
 */
public interface IUserLoginService {
	LoginLog findByUserId(String userId);

	Integer insertUserLogin(LoginLog loginLog);



	User login(User user);
}
