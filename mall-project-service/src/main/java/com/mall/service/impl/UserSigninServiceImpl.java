package com.mall.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.dao.UserPointDao;
import com.mall.dao.UserSigninDao;
import com.mall.dto.ResultExecution;
import com.mall.entity.UserSigninEntity;
import com.mall.enums.StateEnum;
import com.mall.model.UserPoint;
import com.mall.model.UserSignin;
import com.mall.common.exception.OperationException;
import com.mall.service.UserSigninService;

@Service
public class UserSigninServiceImpl extends ServiceImpl<UserSigninDao, UserSigninEntity> implements UserSigninService {

	@Autowired
	private UserPointDao userPointDao;
	@Autowired
	private UserSigninDao userSigninDao;
	private static Logger log = LoggerFactory.getLogger(UserSigninServiceImpl.class);
	
	public UserSigninServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Transactional //此处需要添加事务处理
	@Override
	public ResultExecution<UserSignin> addUserSignin(UserSignin userSignin) {
		// TODO Auto-generated method stub
		
		if(userSignin != null && userSignin.getUserId() > 0) {
			UserSignin temp = userSigninDao.queryUsersigninByCTime(userSignin.getUserId());
			//需要判断今日是否签到，若已签到则提示
			if(temp != null) {
				throw new OperationException("今日已签到！");
			}
			//若未签到则进行签到记录插入
			try {
				int effectNum = userSigninDao.insertUserSignin(userSignin);
				if(effectNum <= 0) {
					throw new OperationException("插入签到记录失败！");
				}
			} catch (Exception e) {
				// TODO: handle exception
				log.error(e.getMessage());
				throw new OperationException("插入签到记录失败！");
			}
			//插入后需要更新用户个人积分
			//若一个人能积分已存在，则更新，反之新增
			try {
				UserPoint uPoint = userPointDao.queryUserPointByUserId(userSignin.getUserId());
				UserPoint userPoint = new UserPoint();
				userPoint.setUserId(userSignin.getUserId());
				if(uPoint == null) {
					//在个人积分基础上叠加
					userPoint.setPoint(userSignin.getPoint());
					int effectNum = userPointDao.insertUserPoint(userPoint);
					if(effectNum <= 0) {
						throw new OperationException("新增用户积分信息失败！");
					}
				}else {
					userPoint.setPoint(uPoint.getPoint() + userSignin.getPoint());
					int effectNum = userPointDao.updateUserPoint(userPoint);
					if(effectNum <= 0) {
						throw new OperationException("更新用户积分信息失败！");
					}
				}		
			} catch (Exception e) {
				// TODO: handle exception
				log.error(e.getMessage());
				throw new OperationException("更新用户积分信息失败！");
			}
			return new ResultExecution<UserSignin>(StateEnum.SUCCESS);
		}else {
			return new ResultExecution<UserSignin>(StateEnum.EMPTY);
		}
	}

	@Override
	public ResultExecution<UserSignin> getUserSigninList(UserSignin userSignin,Page<UserSignin> page) {
		// TODO Auto-generated method stub
		ResultExecution<UserSignin> resultExecution = new ResultExecution<UserSignin>();
		IPage<UserSignin> list = userSigninDao.queryUserSigninList(page,userSignin);
		int count = (int)list.getTotal();
		int totalPoint = userSigninDao.queryAllUserSigninPoint(userSignin);
		resultExecution.setList(list.getRecords());
		resultExecution.setCount(count);
		resultExecution.setTotalPoint(totalPoint);
		return resultExecution;
	}

	@Override
	public UserSignin getUsersigninByCTime(long userId) {
		// TODO Auto-generated method stub
		return userSigninDao.queryUsersigninByCTime(userId);
	}

}