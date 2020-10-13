package com.mall.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.dto.ResultExecution;
import com.mall.entity.BookArticleEntity;
import com.mall.model.BookArticle;
import org.springframework.cache.annotation.Cacheable;

/**
 * 
 *
 * @author lzf
 * @email 1670775501@qq.com
 * @date 2020-06-13 18:36:25
 */
public interface BookArticleService extends IService<BookArticleEntity> {
	/*
	 * 查询所有章节列表，不分页，不查询，按章节顺序排序
	 * @return
	 * */
	List<BookArticle> getBookArticleList(BookArticle bookArticle,String sortFlag,Integer userId);
	
	/*
	 * 通过章节ID查询唯一的章节信息
	 * @return
	 * */
	ResultExecution<BookArticle> getBookArticleById(long articleId,int page,int pageSize);
	
}

