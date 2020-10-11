package com.mall.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.dao.LocalAuthDao;
import com.mall.entity.LocalAuthEntity;
import com.mall.service.LocalAuthService;


@Service
public class LocalAuthServiceImpl extends ServiceImpl<LocalAuthDao, LocalAuthEntity> implements LocalAuthService {

}