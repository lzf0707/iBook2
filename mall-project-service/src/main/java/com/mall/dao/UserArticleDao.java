package com.mall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.entity.BookArticleEntity;
import com.mall.entity.UserArticleEntity;
import com.mall.model.UserArticle;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 
 * 
 * @author lzf
 * @email 1670775501@qq.com
 * @date 2020-06-13 18:26:53
 */
public interface UserArticleDao extends BaseMapper<UserArticleEntity> {
	int batchInsert(@Param("list") List<BookArticleEntity> list,@Param("userId") Integer userId);

	//根据指定用户id、书籍id，获取剩余（未解锁）章节列表
	List<BookArticleEntity> getUnLockArticleList(@Param("userId") Integer userId, @Param("bookId") Integer bookId);

	//根据指定用户id、书籍id，获取解锁剩余章节所需要的积分
	int getUnLockArticlesTotalPoint(@Param("userId") Integer userId, @Param("bookId") Integer bookId);

	IPage<UserArticle> selectUserArticleList(Page<UserArticle> page, @Param("info") String info, @Param("userId") Long userId, @Param("bookId") Long bookId);

	int selectExChangePoint(@Param("userId") Integer userId);
}
