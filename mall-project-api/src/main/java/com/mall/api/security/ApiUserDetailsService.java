package com.mall.api.security;

//import java.util.Collections;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.dao.LocalAuthDao;
import com.mall.entity.LocalAuthEntity;
import com.mall.security.exception.UserNotFoundException;
import com.mall.security.service.MyUserDetailsService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor //生成全参数构造函数
class ApiUserDetailsService implements MyUserDetailsService,SocialUserDetailsService {

	private final LocalAuthDao localAuthDao; //@AllArgsConstructorz注解作用，可以加final、不使用@Autowired
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
		// TODO Auto-generated method stub
		//LocalAuth localAuth =  localAuthDao.selectByUsername(username);
		LocalAuthEntity localAuth = localAuthDao.selectOne((new LambdaQueryWrapper<LocalAuthEntity>().eq(LocalAuthEntity :: getUserName,username)));
		return buildUser(localAuth);
	}

	@Override
	public UserDetails loadUserByMobile(String mobile) throws UserNotFoundException {
		// TODO Auto-generated method stub
		//LocalAuth localAuth =  localAuthDao.selectByMobile(mobile);
		LocalAuthEntity localAuth = localAuthDao.selectOne((new LambdaQueryWrapper<LocalAuthEntity>().eq(LocalAuthEntity :: getMobile,mobile)));
		return buildUser(localAuth);
	}

	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UserNotFoundException {
		// TODO Auto-generated method stub
		//这里的userId 默认为 localAuthId
		//LocalAuth localAuth = localAuthDao.selectByUsername(userId);
		LocalAuthEntity localAuth = localAuthDao.selectOne((new LambdaQueryWrapper<LocalAuthEntity>().eq(LocalAuthEntity :: getUserName,userId)));
		return buildUser(localAuth);
	}
	
	private SocialUserDetails buildUser(LocalAuthEntity localAuth) {
		if (localAuth == null) {
			throw new UserNotFoundException("用户不存在");
		}
		
		//账号是否可用、账号是否过期、密码是否过期、账号是否锁定，权限列表
		return new ApiUser(
			localAuth.getUserId(),localAuth.getMobile(),localAuth.getLocalAuthId(),
			localAuth.getUserName(), localAuth.getPassword(), true,
			true, true, true,
			AuthorityUtils.commaSeparatedStringToAuthorityList("/frontend/p/getLoginStatus,/frontend/p/getTodaySignin")//Collections.emptyList()
		);
	}

}
