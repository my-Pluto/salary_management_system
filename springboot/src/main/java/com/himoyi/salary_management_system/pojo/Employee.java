package com.himoyi.salary_management_system.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 张玉飞 陈辰 刘月锟 宫雅琦 邵景宇
 * @since 2020-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Employee implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 员工工号
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 员工姓名
     */
    private String name;

    /**
     * 员工性别
     */
    private String sex;

    /**
     * 员工部门
     */
    private Long department;

    /**
     * 员工职位
     */
    private Long position;

    /**
     * 员工联系方式
     */
    private String email;

    /**
     * 入职时间
     */
    private Date entryTime;

    /**
     * 版本信息
     */
    @Version
    private Integer version;

    /**
     * 创建时间
     */
      @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    /**
     * 修改时间
     */
      @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;



}
