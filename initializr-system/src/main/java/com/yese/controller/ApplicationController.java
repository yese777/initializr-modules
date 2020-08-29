package com.yese.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yese.common.annotation.Log;
import com.yese.entity.Application;
import com.yese.service.ApplicationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * 应用注册表(Application)表控制层[不建议修改，如果有新增的方法，写在子类中]
 *
 * @author 张庆福
 * @since 2020-08-30 01:05:28
 */
public class ApplicationController {

    /**
     * 服务对象
     */
    @Autowired
    ApplicationService applicationService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param application 查询实体
     * @return 所有数据
     */
    @Log(module = "测试模块", content = "分页查询所有数据")
    @ApiOperation("分页查询所有数据")
    @GetMapping
    public R<IPage<Application>> selectAll(Page<Application> page, Application application) {
        return R.ok(applicationService.page(page, new QueryWrapper<>(application)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Log(module = "测试模块", content = "通过主键查询单条数据")
    @ApiOperation("通过主键查询单条数据")
    @GetMapping("{id}")
    public R<Application> selectOne(@PathVariable Serializable id) {
        return R.ok(applicationService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param application 实体对象
     * @return 新增结果
     */
    @Log(module = "测试模块", content = "新增数据")
    @ApiOperation("新增数据")
    @PostMapping
    public R<Long> insert(@RequestBody Application application) {
        boolean rs = applicationService.save(application);
        return R.ok(rs ? application.getId() : 0);
    }

    /**
     * 修改数据
     *
     * @param application 实体对象
     * @return 修改结果
     */
    @Log(module = "测试模块", content = "修改数据")
    @ApiOperation("修改数据")
    @PutMapping
    public R<Boolean> update(@RequestBody Application application) {
        return R.ok(applicationService.updateById(application));
    }

    /**
     * 单条/批量删除数据
     *
     * @param idList 主键集合
     * @return 删除结果
     */
    @Log(module = "测试模块", content = "单条/批量删除数据")
    @ApiOperation("单条/批量删除数据")
    @DeleteMapping
    public R<Boolean> delete(@RequestParam("idList") List<Long> idList) {
        return R.ok(applicationService.removeByIds(idList));
    }
}