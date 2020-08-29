package com.yese.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yese.entity.Application;
import com.yese.mapper.ApplicationMapper;
import com.yese.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 应用注册表(Application)表服务实现类
 *
 * @author 张庆福
 * @since 2020-08-30 01:05:28
 */
@Slf4j
@Service
public class ApplicationServiceImpl extends ServiceImpl<ApplicationMapper, Application> implements ApplicationService {

}