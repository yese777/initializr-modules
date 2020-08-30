# 简介

项目骨架（使用 idea 模块化搭建），用于快速构建中小型RESTful API项目。

# 技术选型

| 框架         | 备注                          |
| ------------ | ----------------------------- |
| java         | jdk8                          |
| ide          | idea                          |
| SpringBoot   | 基础框架，版本：2.3.3.RELEASE |
| Maven        | 依赖管理                      |
| EasyCode     | 代码生成插件                  |
| MySQL        | 数据库，8.0 以上版本          |
| Druid        | 数据库连接池                  |
| MyBatis-Plus | mybatis 增强工具              |
| Swagger      | 在线接口文档工具              |
| Lombok       | 简化开发                      |
| Fastjson     | json 工具                     |
| hutool       | 工具包                        |



# 特性

- 使用 idea 插件 `EasyCode` 快速生成基于`mybatis-plus`的entity、mapper、mapperXML、service、serviceimpl、controller代码，实现实现单表业务零SQL；配置数据库全局逻辑删除字段，实现逻辑删除；
- 统一响应结果封装`R`；
- 全局异常处理`GlobalExceptionHandler`；
- `Hibernate Validator`实现参数校验，在全局异常处理中封装异常信息，统一定义参数校验的提示信息；
- 自定义业务异常`BusinessException`；
- 集成Druid数据库连接池与监控，`http://${host}:${port}/项目名/druid/`；
- knife4j替换 Swagger 默认界面，更符合国人习惯，`http://${host}:${port}/项目名/doc.html`；
- 基于自定义注解`@Log` + Aop切面实现全方位日记记录；
- 基于`SpringBoot 2.3`的优雅关机
- 



简单的接口签名认证

常用基础方法抽象封装







# 内置功能

1. 用户管理：用户是系统操作者，该功能主要完成系统用户配置。
2. 部门管理：配置系统组织机构（公司、部门、小组），树结构展现支持数据权限。
3. 岗位管理：配置系统用户所属担任职务。
4. 菜单管理：配置系统菜单，操作权限，按钮权限标识等。
5. 角色管理：角色菜单权限分配、设置角色按机构进行数据范围权限划分。
6. 字典管理：对系统中经常使用的一些较为固定的数据进行维护。
7. 参数管理：对系统动态配置常用参数。
8. 通知公告：系统通知公告信息发布维护。
9. 操作日志：系统正常操作日志记录和查询；系统异常信息日志记录和查询。
10. 登录日志：系统登录日志记录查询包含登录异常。
11. 在线用户：当前系统中活跃用户状态监控。
12. 定时任务：在线（添加、修改、删除)任务调度包含执行结果日志。
13. 代码生成：前后端代码的生成（java、html、xml、sql）支持CRUD下载 。
14. 系统接口：根据业务代码自动生成相关的api接口文档。
15. 服务监控：监视当前系统CPU、内存、磁盘、堆栈等相关信息。
16. 在线构建器：拖动表单元素生成相应的HTML代码。
17. 连接池监视：监视当前系统数据库连接池状态，可进行分析SQL找出系统性能瓶颈。

# 快速开始

1. 克隆项目
2. 对```test```包内的代码生成器```CodeGenerator```进行配置，主要是JDBC，因为要根据表名来生成代码
3. 如果只是想根据上面的演示来亲自试试的话可以使用```test resources```目录下的```demo-user.sql```，否则忽略该步
4. 输入表名，运行```CodeGenerator.main()```方法，生成基础代码（可能需要刷新项目目录才会出来）
5. 根据业务在基础代码上进行扩展
6. 对开发环境配置文件```application-dev.properties```进行配置，启动项目，Have Fun！

# 开发建议

- 表名，建议使用小写，多个单词使用下划线拼接
- Model内成员变量建议与表字段数量对应，如需扩展成员变量（比如连表查询）建议创建DTO，否则需在扩展的成员变量上加```@Transient```注解，详情见[通用Mapper插件文档说明](https://mapperhelper.github.io/docs/2.use/)
- 建议业务失败直接使用```ServiceException("message")```抛出，由统一异常处理器来封装业务失败的响应结果，比如```throw new ServiceException("该手机号已被注册")```，会直接被封装为```{"code":400,"message":"该手机号已被注册"}```返回，无需自己处理，尽情抛出
- 需要工具类的话建议先从```apache-commons-*```和```guava```中找，实在没有再造轮子或引入类库，尽量精简项目
- 开发规范建议遵循阿里巴巴Java开发手册（[最新版下载](https://github.com/alibaba/p3c))
- 建议在公司内部使用[ShowDoc](https://github.com/star7th/showdoc)、[SpringFox-Swagger2](https://github.com/springfox/springfox) 、[RAP](https://github.com/thx/RAP)等开源项目来编写、管理API文档

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
主键自增
配置mybatisplus的sql日志输出
注册mybatisplus分页插件
分页查询
别名包扫描
指定Mapper.xml文件位置，自定义 mapper.xml
更换 druid 数据源,开启监控页面
统一响应结果封装及生成工具(ResponseControllerAdvice全局处理响应数据优化，https://juejin.im/post/6844904101940117511#heading-12，暂未处理)
多环境配置
日志文件输出
跨域配置


- 表名，建议使用小写，多个单词使用下划线拼接
- 建议业务失败直接使用```ServiceException("message")```抛出，由统一异常处理器来封装业务失败的响应结果，比如```throw new ServiceException("该手机号已被注册")```，会直接被封装为```{"code":400,"message":"该手机号已被注册"}```返回，无需自己处理，尽情抛出




待完成:
jwt
spring session
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




