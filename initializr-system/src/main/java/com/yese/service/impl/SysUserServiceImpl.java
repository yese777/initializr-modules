package com.yese.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yese.entity.SysUser;
import com.yese.mapper.SysUserMapper;
import com.yese.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 系统用户表(SysUser)表服务实现类
 *
 * @author 张庆福
 * @since 2020-09-07 23:44:51
 */
@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

}