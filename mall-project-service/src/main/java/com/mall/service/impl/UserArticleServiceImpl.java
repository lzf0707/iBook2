package com.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.common.exception.OperationException;
import com.mall.dao.BookArticleDao;
import com.mall.dao.UserPointDao;
import com.mall.dto.ResultExecution;
import com.mall.entity.BookArticleEntity;
import com.mall.entity.UserPointEntity;
import com.mall.enums.StateEnum;
import com.mall.model.UserArticle;
import com.mall.model.UserHistory;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.dao.UserArticleDao;
import com.mall.entity.UserArticleEntity;
import com.mall.service.UserArticleService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class UserArticleServiceImpl extends ServiceImpl<UserArticleDao, UserArticleEntity> implements UserArticleService {
    @Autowired
    private UserArticleDao userArticleDao;
    @Autowired
    private BookArticleDao bookArticleDao;
    @Autowired
    private UserPointDao userPointDao;

    @Override
    @Transactional
    public ResultExecution<UserArticleEntity> unLockArticle(String flag, Integer bookId, Integer articleId, Integer userId) {
        Integer rs = 0;
        int totalPoint = 0; //解锁所需的积分
        //获取用户积分信息
        UserPointEntity userPointEntity = userPointDao.selectOne(
                new LambdaQueryWrapper<UserPointEntity>().eq(UserPointEntity::getUserId,userId));
        if(StringUtils.equals(flag,"addAll")){ //所有章节解锁
            totalPoint = userArticleDao.getUnLockArticlesTotalPoint(userId,bookId);
            if(userPointEntity.getPoint() < totalPoint){ //判断积分足够
                return new ResultExecution<>(StateEnum.POINT_ERROR);
            }
            //获取指定书籍的所有未解锁章节
            List<BookArticleEntity> list = userArticleDao.getUnLockArticleList(userId,bookId);
            rs = userArticleDao.batchInsert(list,userId);
        }
        if(StringUtils.equals(flag,"add")){ //单章解锁
            BookArticleEntity bookArticleEntity = bookArticleDao.selectById(articleId);
            totalPoint = bookArticleEntity.getPoint();
            if(userPointEntity.getPoint() < totalPoint){ //判断积分足够
                return new ResultExecution<>(StateEnum.POINT_ERROR);
            }
            rs = userArticleDao.insert(getUserArticleEntity(bookArticleEntity,userId));
        }
        if(rs > 0){
            userPointEntity.setPoint(userPointEntity.getPoint() - totalPoint);
            rs = userPointDao.updateById(userPointEntity);
            if(rs > 0){
                return new ResultExecution<>(StateEnum.OK);
            }
        }
        return new ResultExecution<>(StateEnum.FAILD);
    }

    @Override
    public int getUnLockArticleTotalPoint(Integer bookId,Integer userId) {
        try {
            return userArticleDao.getUnLockArticlesTotalPoint(userId,bookId);
        }catch (Exception e){
            throw new OperationException("获取未解锁章节所需积分失败-" + e.getMessage());
        }
    }

    @Override
    public int getExChangePoint(Integer userId) {
        try {
            return userArticleDao.selectExChangePoint(userId);
        }catch (Exception e){
            throw new OperationException("获取用户兑换总积分失败-" + e.getMessage());
        }
    }

    @Override
    public ResultExecution<UserArticle> getUserArticleList(Page<UserArticle> page, String info, Long userId, Long bookId) {
        ResultExecution<UserArticle> rs = new ResultExecution<>();
        try{
            IPage<UserArticle> userArticleIPage = userArticleDao.selectUserArticleList(page,info,userId,bookId);
            rs.setList(userArticleIPage.getRecords());
            rs.setCount((int) userArticleIPage.getTotal());
        }catch (Exception e){
            throw new OperationException("获取用户兑换记录失败-" + e.getMessage());
        }
        return rs;
    }

    private UserArticleEntity getUserArticleEntity(BookArticleEntity bookArticleEntity,Integer userId){
        UserArticleEntity userArticleEntity = new UserArticleEntity();
        userArticleEntity.setCreateTime(new Date());
        userArticleEntity.setArticleId(bookArticleEntity.getArticleId());
        userArticleEntity.setPoint(bookArticleEntity.getPoint());
        userArticleEntity.setBookId(bookArticleEntity.getBookId());
        userArticleEntity.setUserId(userId);
        return  userArticleEntity;
    }
}