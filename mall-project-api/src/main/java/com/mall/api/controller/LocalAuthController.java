package com.mall.api.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.mall.api.security.SecurityUtils;
import com.mall.enums.StateEnum;
import com.mall.service.LocalAuthService;
import com.mall.service.PersonInfoService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.api.security.ApiUser;
import com.mall.common.util.CodeUtil;
import com.mall.common.util.HttpServletRequestUtil;
import com.mall.common.util.TradeIdGenerate;
import com.mall.entity.LocalAuthEntity;
import com.mall.entity.PersonInfoEntity;
import com.mall.security.dto.SimpleResponse;
import com.mall.security.properties.SecurityProperties;
import com.mall.security.service.SignUpUtils;


@Controller
@RequestMapping("/frontend/p")
@Slf4j
public class LocalAuthController {

	@Autowired
	public LocalAuthService localAuthService;
	
	@Autowired
	public PersonInfoService personInfoService;
	
	@Autowired
	public ProviderSignInUtils providerSignInUtils;
	
	@Autowired(required = false)
	public SignUpUtils appSignUpUtils;
	
	@Autowired
	public SecurityProperties securityProperties;
	
	@Autowired
	public PasswordEncoder passwordEncoder;
	
	public LocalAuthController() {
		// TODO Auto-generated constructor stub
	}
	
	@PostMapping(value = "/bindLocalAuth")
	@ResponseBody
	public Map<String, Object> bindLocalAuth(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		//验证码校验
		if(!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错误的验证码");
			return modelMap;
		}
		String userName = HttpServletRequestUtil.getString(request, "userName");
		// 获取原密码
		String password = HttpServletRequestUtil.getString(request, "password");
		if(userName != null && password != null) {
			//查询是否已有平台账号
			LocalAuthEntity localAuthEntity = localAuthService.getOne(new LambdaQueryWrapper<LocalAuthEntity>().eq(LocalAuthEntity::getUserName, userName));
			if(localAuthEntity != null && localAuthEntity.getLocalAuthId() > 0) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "用户名已绑定！");
				return modelMap;
			}
			//不管是注册用户、还是绑定用户，都会拿到一个唯一UserId标识
			//用户信息注册，昵称、头像为系统默认，待登录后，用户自己完善
			PersonInfoEntity personInfoEntity = new PersonInfoEntity();
			personInfoEntity.setCreateTime(new Date());
			personInfoEntity.setLastEditTime(new Date());
			personInfoEntity.setEnableStatus(0);
			//随机字母+数字
			personInfoEntity.setName(TradeIdGenerate.getTradeId(10));
			//头像网上随机选一张
			personInfoEntity.setProfileImg("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3215943721,3617856399&fm=26&gp=0.jpg");
			personInfoEntity.setUserType(1);
			personInfoService.save(personInfoEntity);
			LocalAuthEntity localAuth = new LocalAuthEntity();
			localAuth.setUserId(personInfoEntity.getUserId());
			localAuth.setPassword(passwordEncoder.encode(password));
			localAuth.setUserName(userName);
			localAuth.setCreateTime(new Date());
			localAuth.setLastEditTime(new Date());
			//账号绑定
			Boolean rs = localAuthService.save(localAuth);
			if(rs) {
				modelMap.put("success",true);
				modelMap.put("msg", "绑定成功！");
			}else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "绑定失败！");
			}
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "未登录或输入信息为空");
		}
		return modelMap;
	}
	
	@PostMapping(value = "/changeLocalPwd")
	@ResponseBody
	public Map<String, Object> changeLocalPwd(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		//验证码校验
		if(!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错误的验证码");
			return modelMap;
		}
		String userName = HttpServletRequestUtil.getString(request, "userName");
		// 获取原密码
		String password = HttpServletRequestUtil.getString(request, "password");
		// 获取新密码
		String newPassword = HttpServletRequestUtil.getString(request, "newPassword");
		//从session中获取用户 信息
		ApiUser apiUser = SecurityUtils.getUser();
		PersonInfoEntity user = personInfoService.getById(apiUser.getuId());
		if(userName != null && password != null 
				&& user != null && user.getUserId() > 0) {
			//查看账号是否一致
			LocalAuthEntity temp = localAuthService.getById(apiUser.getLocalAuthId());
			if(temp == null || !temp.getUserName().equals(userName)) {
				// 不一致则直接退出
				modelMap.put("success", false);
				modelMap.put("errMsg", "输入的帐号非本次登录的帐号");
				return modelMap;
			}
			//判断旧密码是否正确
			if(!passwordEncoder.matches(password, temp.getPassword())) {
				// 不一致则直接退出
				modelMap.put("success", false);
				modelMap.put("errMsg", "旧密码不正确，请重新输入！");
				return modelMap;
			}
			
			//判断新旧密码是否相同
			if(StringUtils.equals(password,newPassword)) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "新密码与旧密码相同，请重新设置！");
				return modelMap;
			}
			temp.setLastEditTime(new Date());
			temp.setPassword(passwordEncoder.encode(newPassword));
			//修改密码
			Boolean rs = localAuthService.updateById(temp);
			if(rs) {
				modelMap.put("success",true);
				modelMap.put("msg", "更新成功！");
			}else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "更新失败！");
			}
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "未登录或输入信息为空");
		}
		return modelMap;
	}
	
	@GetMapping("/getLoginStatus")
	@ResponseBody
	/**
	 * 获取登录状态
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public ResponseEntity<SimpleResponse> getLoginStatus(
			//@AuthenticationPrincipal UserDetails user
			Authentication user,HttpServletRequest request) throws Exception {
		String header = request.getHeader("Authorization");
		if(!StringUtils.isBlank(header)) {
			String token = StringUtils.substringAfter(header, "bearer ");
			Claims claims = Jwts.parser().setSigningKey(securityProperties.getOauth2().getJwtSigningKey().getBytes("UTF-8"))
					.parseClaimsJws(token).getBody();
			log.info(claims.toString());
		}	
		return ResponseEntity.ok (new SimpleResponse(user.getPrincipal(), StateEnum.OK.getState()));
	}
	
	/**
	 * 	注册用户
	 * @param request
	 * @return
	 */
	@PostMapping("/regist")
	@ResponseBody
	public ResponseEntity<SimpleResponse> regist(@RequestParam(required = true) String username,
			@RequestParam(required = true) String password,HttpServletRequest request) {
		//校验传入值是否为空
		if(StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			return ResponseEntity.ok(new SimpleResponse("用户名或密码不可为空", StateEnum.OK.getState()));
		}
		//不管是注册用户、还是绑定用户，都会拿到一个唯一UserId标识
		//用户信息注册，昵称、头像为系统默认，待登录后，用户自己完善
		PersonInfoEntity personInfoEntity = new PersonInfoEntity();
		personInfoEntity.setCreateTime(new Date());
		personInfoEntity.setLastEditTime(new Date());
		personInfoEntity.setEnableStatus(0);
		//随机字母+数字
		personInfoEntity.setName(TradeIdGenerate.getTradeId(10));
		//头像网上随机选一张
		personInfoEntity.setProfileImg("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3215943721,3617856399&fm=26&gp=0.jpg");
		personInfoEntity.setUserType(1);
		personInfoService.save(personInfoEntity);
		//创建本地账号
		LocalAuthEntity localAuthEntity = new LocalAuthEntity();
		localAuthEntity.setCreateTime(new Date());
		localAuthEntity.setLastEditTime(new Date());
		localAuthEntity.setUserName(username);
		localAuthEntity.setPassword(passwordEncoder.encode(password));
		localAuthEntity.setUserId(personInfoEntity.getUserId());
		localAuthService.save(localAuthEntity);
		//然后将唯一的标识与QQ第三方社交系统进行一对一的绑定关联
		if(appSignUpUtils == null) {
			providerSignInUtils.doPostSignUp(username, new ServletWebRequest(request)); //浏览器调用
		}else {
			appSignUpUtils.doPostSignUp(new ServletWebRequest(request), username);
		}
		return ResponseEntity.ok(new SimpleResponse("注册成功！", StateEnum.OK.getState()));
	}
}
