# 项目简介

用一两句话简单描述该项目所实现的业务功能；

# 技术选型

列出项目的技术栈，包括语言、框架和中间件等；

# 本地构建

列出本地开发过程中所用到的工具命令；

# 领域模型

核心的领域概念，比如对于示例电商系统来说有Order、Product等；

# 测试策略

自动化测试如何分类，哪些必须写测试，哪些没有必要写测试；

# 技术架构

技术架构图；

# 部署架构

部署架构图；

# 外部依赖

项目运行时所依赖的外部集成方，比如订单系统会依赖于会员系统；

# 环境信息

各个环境的访问方式，数据库连接等；

# 编码实践

统一的编码实践，比如异常处理原则、分页封装等；

# FAQ

开发过程中常见问题的解答。















```
EasyCode模板快速生成CRUD代码
主键自增
配置mybatisplus的sql日志输出
注册mybatisplus分页插件
分页查询
配置全局数据库逻辑删除字段
别名包扫描
指定Mapper.xml文件位置，自定义 mapper.xml
更换 druid 数据源,开启监控页面
统一响应结果封装及生成工具(ResponseControllerAdvice全局处理响应数据优化，https://juejin.im/post/6844904101940117511#heading-12，暂未处理)
参数校验
自定义业务异常
全局异常处理
多环境配置
在线接口文档swagger
swagger-bootstrap-ui
日志文件输出
跨域配置



- 表名，建议使用小写，多个单词使用下划线拼接

- 建议业务失败直接使用```ServiceException("message")```抛出，由统一异常处理器来封装业务失败的响应结果，比如```throw new ServiceException("该手机号已被注册")```，会直接被封装为```{"code":400,"message":"该手机号已被注册"}```返回，无需自己处理，尽情抛出




待完成:
jwt
spring session


日志入库（操作账号，操作人 id，操作人名称，操作内容，操作结果，ip，请求路径url，开始时间，结束时间，方法执行时间，日志创建时间
方法的入参，模块，功能，操作方法，方法描述）

mybatisplus乐观锁
```

# EasyCode模板

###### entity.java

```
##导入宏定义
$!define
##保存文件（宏定义）
#save("/entity", ".java")
##包路径（宏定义）
#setPackageSuffix("entity")
##自动导入包（全局变量）
$!autoImport
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


##表注释（宏定义）
#tableComment("表实体类")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("$!{tableInfo.comment}")
public class $!{tableInfo.name} implements Serializable {

private static final long serialVersionUID = $!tool.serial();

#foreach($column in $tableInfo.fullColumn)
    #if(${column.comment})/**
    * ${column.comment}
    */#end
    #if(${column.comment})@ApiModelProperty(value = "${column.comment}")#end
    #if($column.name.equals('id'))@TableId(type = IdType.AUTO)#end
    private $!{tool.getClsNameByFullName($column.type)} $!{column.name};
    
#end
}
```



###### mapper.java

```
##导入宏定义
$!define
##设置表后缀（宏定义）
#setTableSuffix("Mapper")
##保存文件（宏定义）
#save("/mapper", "Mapper.java")
##包路径（宏定义）
#setPackageSuffix("mapper")
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import $!{tableInfo.savePackageName}.entity.$!tableInfo.name;

##表注释（宏定义）
#tableComment("表数据库访问层")
public interface $!{tableName} extends BaseMapper<$!tableInfo.name> {

}

```



###### service.java

```
##导入宏定义
$!define
##设置表后缀（宏定义）
#setTableSuffix("Service")
##保存文件（宏定义）
#save("/service", "Service.java")
##包路径（宏定义）
#setPackageSuffix("service")
import com.baomidou.mybatisplus.extension.service.IService;
import $!{tableInfo.savePackageName}.entity.$!tableInfo.name;

##表注释（宏定义）
#tableComment("表服务接口")
public interface $!{tableName} extends IService<$!tableInfo.name> {

}
```



###### serviceImpl.java

```
##导入宏定义
$!define
##设置表后缀（宏定义）
#setTableSuffix("ServiceImpl")
##保存文件（宏定义）
#save("/service/impl", "ServiceImpl.java")
##包路径（宏定义）
#setPackageSuffix("service.impl")
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import $!{tableInfo.savePackageName}.entity.$!{tableInfo.name};
import $!{tableInfo.savePackageName}.mapper.$!{tableInfo.name}Mapper;
import $!{tableInfo.savePackageName}.service.$!{tableInfo.name}Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

##表注释（宏定义）
#tableComment("表服务实现类")
@Slf4j
@Service
public class $!{tableName} extends ServiceImpl<$!{tableInfo.name}Mapper, $!{tableInfo.name}> implements $!{tableInfo.name}Service {

}
```



###### controller.java

```
##导入宏定义
$!define
##设置表后缀（宏定义）
#setTableSuffix("Controller")
##保存文件（宏定义）
#save("/controller", "Controller.java")
##包路径（宏定义）
#setPackageSuffix("controller")
##定义服务名
#set($serviceName = $!tool.append($!tool.firstLowerCase($!tableInfo.name), "Service"))
##定义实体对象名
#set($entityName = $!tool.firstLowerCase($!tableInfo.name))
import $!{tableInfo.savePackageName}.entity.$!tableInfo.name;
import $!{tableInfo.savePackageName}.service.$!{tableInfo.name}Service;


import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.api.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

##表注释（宏定义）
#tableComment("表控制层[不建议修改，如果有新增的方法，写在子类中]")
public class $!{tableName} {
  
    /**
     * 服务对象
     */
    @Autowired
    $!{tableInfo.name}Service $!{serviceName};   
  
   /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param $!entityName 查询实体
     * @return 所有数据
     */
    @ApiOperation("分页查询所有数据")
    @GetMapping
    public R<IPage<$!tableInfo.name>>  selectAll(Page<$!tableInfo.name> page, $!tableInfo.name $!entityName) {
        return R.ok ($!{serviceName}.page(page, new QueryWrapper<>($!entityName)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("通过主键查询单条数据")
    @GetMapping("{id}")
    public R<$!tableInfo.name> selectOne(@PathVariable Serializable id) {
        return R.ok($!{serviceName}.getById(id));
    }

    /**
     * 新增数据
     *
     * @param $!entityName 实体对象
     * @return 新增结果
     */
    @ApiOperation("新增数据")
    @PostMapping
    public R<Long> insert(@RequestBody $!tableInfo.name $!entityName) {
        boolean rs = $!{serviceName}.save($!entityName);
        return R.ok(rs?$!{entityName}.getId():0);
    }

    /**
     * 修改数据
     *
     * @param $!entityName 实体对象
     * @return 修改结果
     */
    @ApiOperation("修改数据")
    @PutMapping
    public R<Boolean>  update(@RequestBody $!tableInfo.name $!entityName) {
        return R.ok($!{serviceName}.updateById($!entityName));
    }

    /**
     * 单条/批量删除数据
     *
     * @param idList 主键集合
     * @return 删除结果
     */
    @ApiOperation("单条/批量删除数据")
    @DeleteMapping
    public R<Boolean> delete(@RequestParam("idList") List<Long> idList) {
        return R.ok($!{serviceName}.removeByIds(idList));
    }
}

```





###### extendController.java

```
##导入宏定义
$!define
##设置表后缀（宏定义）
#setTableSuffix("ExtendController")
##保存文件（宏定义）
#save("/controller", "ExtendController.java")
##包路径（宏定义）
#setPackageSuffix("controller")
##定义服务名
#set($serviceName = $!tool.append($!tool.firstLowerCase($!tableInfo.name), "Service"))
##定义实体对象名
#set($entityName = $!tool.firstLowerCase($!tableInfo.name))
import $!{tableInfo.savePackageName}.pojo.$!tableInfo.name;
import $!{tableInfo.savePackageName}.service.$!{tableInfo.name}Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.api.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import java.io.Serializable;
import java.util.List;

##表注释（宏定义）
#tableComment("表控制层扩展类，一般初次生成，后续不再覆盖。这个类提供编写自己定义的方法。")
@Api(tags = "$!{tableInfo.comment}接口")
@Slf4j
@RestController
@RequestMapping("$!tool.firstLowerCase($!tableInfo.name)")
public class $!{tableName} extends $!{tableInfo.name}Controller {

}

```





###### mapper.xml

```
##引入mybatis支持
$!mybatisSupport

##设置保存名称与保存位置
$!callback.setFileName($tool.append($!{tableInfo.name}, "Mapper.xml"))
$!callback.setSavePath($tool.append($modulePath, "/src/main/resources/mapper"))

##拿到主键
#if(!$tableInfo.pkColumn.isEmpty())
    #set($pk = $tableInfo.pkColumn.get(0))
#end

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="$!{tableInfo.savePackageName}.mapper.$!{tableInfo.name}Mapper">
    <sql id="Base_Column_List">
            #foreach($column in $tableInfo.fullColumn)
        $!column.obj.name,
#end<!--删除最后一个,-->
    </sql>
    <resultMap type="$!{tableInfo.savePackageName}.entity.$!{tableInfo.name}" id="$!{tableInfo.name}BaseResultMap">
#foreach($column in $tableInfo.fullColumn)
        <result property="$!column.name" column="$!column.obj.name"/>
#end
    </resultMap>

</mapper>
```













