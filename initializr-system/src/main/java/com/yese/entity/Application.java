package com.yese.entity;

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


/**
 * 应用注册表(Application)表实体类
 *
 * @author 张庆福
 * @since 2020-09-08 01:10:42
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("应用注册表")
public class Application implements Serializable {

    private static final long serialVersionUID = -83215787437827082L;

    /**
     * 递增主键
     */
    @ApiModelProperty(value = "递增主键")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 应用图标URL
     */
    @ApiModelProperty(value = "应用图标URL")
    private String icon;

    /**
     * 应用详情图
     */
    @ApiModelProperty(value = "应用详情图")
    private String imgurls;

    /**
     * 应用名称
     */
    @ApiModelProperty(value = "应用名称")
    private String name;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 访问次数
     */
    @ApiModelProperty(value = "访问次数")
    private Integer visitNum;

    /**
     * 收藏次数
     */
    @ApiModelProperty(value = "收藏次数")
    private Integer collectionNum;

    /**
     * 应用URL
     */
    @ApiModelProperty(value = "应用URL")
    private String webappUrl;

    /**
     * 创建/更新用户的唯一标识
     */
    @ApiModelProperty(value = "创建/更新用户的唯一标识")
    private String userCode;

    /**
     * 删除标志(1:删除0:未删除)
     */
    @ApiModelProperty(value = "删除标志(1:删除0:未删除)")
    private Integer deleted;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 状态，1:显示，0:隐藏
     */
    @ApiModelProperty(value = "状态，1:显示，0:隐藏")
    private Integer status;

    /**
     * 是否置顶（0:否，1:置顶)
     */
    @ApiModelProperty(value = "是否置顶（0:否，1:置顶)")
    private Integer top;

    /**
     * 置顶更新时间
     */
    @ApiModelProperty(value = "置顶更新时间")
    private Date updateTopTime;

    /**
     * 应用上架者联系方式
     */
    @ApiModelProperty(value = "应用上架者联系方式")
    private String mobile;

    /**
     * 应用上架者组织机构
     */
    @ApiModelProperty(value = "应用上架者组织机构")
    private String officeName;

    /**
     * 应用上架者姓名
     */
    @ApiModelProperty(value = "应用上架者姓名")
    private String creatorName;

    /**
     * 应用开发者联系方式
     */
    @ApiModelProperty(value = "应用开发者联系方式")
    private String developMobile;

    /**
     * 应用开发者公司
     */
    @ApiModelProperty(value = "应用开发者公司")
    private String developCompany;

    /**
     * 应用开发者姓名
     */
    @ApiModelProperty(value = "应用开发者姓名")
    private String developName;

    /**
     * 应用使用范围
     */
    @ApiModelProperty(value = "应用使用范围")
    private String useRange;

    /**
     * 应用开发语言
     */
    @ApiModelProperty(value = "应用开发语言")
    private String developLanguage;

    /**
     * 应用提供的微服务
     */
    @ApiModelProperty(value = "应用提供的微服务")
    private String provideApi;

    /**
     * 应用调用的接口
     */
    @ApiModelProperty(value = "应用调用的接口")
    private String invokeApi;

    /**
     * 应用调用的微服务
     */
    @ApiModelProperty(value = "应用调用的微服务")
    private String invokeService;

    /**
     * 应用简介视频
     */
    @ApiModelProperty(value = "应用简介视频")
    private String videourl;

}