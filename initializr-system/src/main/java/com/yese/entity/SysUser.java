package com.yese.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;


/**
 * 系统用户
 */
@ApiModel("系统用户")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户账号
     */
    @ApiModelProperty("用户账号")
    @NotBlank(message = "用户账号不能为空")
    @Size(min = 0, max = 30, message = "用户账号长度不能超过30个字符")
    private String userName;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;

    /**
     * 用户名称
     */
    @ApiModelProperty("用户名称")
    @Size(min = 0, max = 30, message = "用户名称长度不能超过30个字符")
    private String nickName;

    /**
     * 真实姓名
     */
    @ApiModelProperty("真实姓名")
    private String realName;

    /**
     * 身份证
     */
    @ApiModelProperty("身份证")
    private String idCard;

    /**
     * 生日
     */
    @ApiModelProperty("生日")
    private Date birthday;

    /**
     * 用户邮箱
     */
    @ApiModelProperty("用户邮箱")
    @Email(message = "邮箱格式不正确")
    @Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
    private String email;

    /**
     * 手机号码
     */
    @ApiModelProperty("手机号码")
    @Size(min = 0, max = 11, message = "手机号码长度不能超过11个字符")
    private String phone;

    /**
     * 用户性别,0=女,1=男,2=未知
     */
    @ApiModelProperty("用户性别,0=女,1=男,2=未知")
    private Integer sex;

    /**
     * 帐号状态,0=正常,1=停用
     */
    @ApiModelProperty("帐号状态,0=正常,1=停用")
    private Integer status;

    /**
     * 删除标志,0=未删除,1=删除
     */
    @ApiModelProperty("删除标志,0=未删除,1=删除")
    private Integer delFlag;

    /**
     * 最后登陆IP
     */
    @ApiModelProperty("最后登陆IP")
    private String loginIp;

    /**
     * 最后登陆时间
     */
    @ApiModelProperty("最后登陆时间")
    private Date loginDate;


    // /** 部门对象 */
    // private SysDept dept;
    //
    // /** 角色对象 */
    // private List<SysRole> roles;
    //
    // /** 角色组 */
    // private Long[] roleIds;
    //
    // /** 岗位组 */
    // private Long[] postIds;


    /**
     * 部门ID
     */
    @ApiModelProperty("部门ID")
    private Long deptId;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Date updateTime;

}