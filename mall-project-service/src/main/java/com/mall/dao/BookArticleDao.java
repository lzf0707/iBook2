package com.mall.dao;

import com.mall.entity.BookArticleEntity;
import com.mall.model.BookArticle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * @author lzf
 * @email 1670775501@qq.com
 * @date 2020-06-13 18:26:53
 */
public interface BookArticleDao extends BaseMapper<BookArticleEntity> {
	/*
	 * 章节添加
	 * @return
	 * */
	int insertArticle(BookArticle bookArticle);
	
	/*
	 * 章节删除
	 * @return
	 * */
	int deleteArticle(Long articleId);
	
	/*
	 * 章节修改
	 * @return
	 * */
	int updateArticle(BookArticle bookArticle);
	
	/*
	 * 章节列表查询
	 * @return
	 * */
	List<BookArticle> queryArticleList(@Param("articleCondition") BookArticle articleCondition,
			@Param("sortFlag") String srotFlag,@Param("userId") Integer userId);
	
	/*
	 * 章节列表总数获取
	 * @return
	 * */
	int queryArticleCount(@Param("articleCondition") BookArticle articleCondition,
			@Param("sortFlag") String srotFlag);
	
	/*
	 * 根据章节ID获取章节信息
	 * @return
	 * */
	BookArticle queryArticleById(@Param("articleId") long articleId,
			@Param("rowIndex") int rowIndex,@Param("pageSize") int pageSieze);

	/*
	 * 根据章节ID获取章节信息总记录数
	 * @return
	 * */
	int queryArticleCountById(@Param("articleId") long articleId,
			@Param("rowIndex") int rowIndex,@Param("pageSize") int pageSieze);

	
	/*
	 * 根据本章节ID获取上一章节、下一章节ID
	 * @return
	 * */
	BookArticle queryLastNextArticleId(long articleId);
}
