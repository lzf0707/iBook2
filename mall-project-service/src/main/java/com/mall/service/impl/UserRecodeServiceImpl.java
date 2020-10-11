package com.mall.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.common.exception.OperationException;
import com.mall.common.util.PageCalculator;
import com.mall.dao.UserPointDao;
import com.mall.dao.UserRecodeDao;
import com.mall.dto.ResultExecution;
import com.mall.entity.UserRecodeEntity;
import com.mall.enums.StateEnum;
import com.mall.model.UserPoint;
import com.mall.model.UserRecode;
import com.mall.service.UserRecodeService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserRecodeServiceImpl extends ServiceImpl<UserRecodeDao, UserRecodeEntity> implements UserRecodeService {

	@Autowired
	private UserPointDao userPointDao;
	@Autowired
	private UserRecodeDao userRecodeDao;
	
	public UserRecodeServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	@Transactional
	public ResultExecution<UserRecode> addUserRecode(UserRecode recode) {
		// TODO Auto-generated method stub
		if(recode !=null && recode.getUserId() > 0) {
			//判断章节是否已领取积分
			UserRecode temp = userRecodeDao.queryUserRecodeByUserAndArticleId(recode.getUserId(), recode.getArticle().getArticleId());
			if(temp != null) { 
				throw new OperationException("此章节领取积分");
			}
			//未领取则进行积分领取
			try {
				int effectNum = userRecodeDao.insertUserRecode(recode);
				if(effectNum <= 0) {
					throw new OperationException("积分领取失败！");
				}
			} catch (Exception e) {
				// TODO: handle exception
				log.error(e.getMessage());
				throw new OperationException("积分领取失败!");
			}
			//插入后需要更新用户个人积分
			//若个人能积分已存在，则更新，反之新增
			try {
				UserPoint uPoint = userPointDao.queryUserPointByUserId(recode.getUserId());
				UserPoint userPoint = new UserPoint();
				userPoint.setUserId(recode.getUserId());
				if(uPoint == null) {	
					userPoint.setPoint(recode.getPoint());
					int effectNum = userPointDao.insertUserPoint(userPoint);
					if(effectNum <= 0) {
						throw new OperationException("新增用户积分信息失败！");
					}
				}else {
					//在个人积分基础上叠加
					userPoint.setPoint(uPoint.getPoint() + recode.getPoint());
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
			return new ResultExecution<UserRecode>(StateEnum.SUCCESS);
		}else {
			return new ResultExecution<UserRecode>(StateEnum.EMPTY);
		}
	}

	@Override
	public ResultExecution<UserRecode> getUserRecodeList(UserRecode userRecode, int page, int pageSize) {
		// TODO Auto-generated method stub
		int pageIndex = PageCalculator.calculateRowIndex(page, pageSize);
		ResultExecution<UserRecode> rs = new ResultExecution<UserRecode>();
		List<UserRecode> list = userRecodeDao.queryUserRecodeList(userRecode, pageIndex, pageSize);
		int count = userRecodeDao.queryUserRecodeCount(userRecode);
		int totalPoint = userRecodeDao.queryAllUserRecodePoint(userRecode);
		rs.setTotalPoint(totalPoint);
		rs.setList(list);
		rs.setCount(count);
		return rs;
	}

}