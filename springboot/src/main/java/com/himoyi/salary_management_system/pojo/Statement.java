package com.himoyi.salary_management_system.pojo;

import java.math.BigDecimal;
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
public class Statement implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 项目ID
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 项目在工资条中的序号
     */
    private Integer number;

    /**
     * 员工工号
     */
    private Long employeeId;

    private String employeeName;

    /**
     * 员工所在部门
     */
    private String departmentName;

    private Long departmentId;

    /**
     * 增减项
     */
    private Integer effect;

    /**
     * 项目数据
     */
    private BigDecimal data;

    /**
     * 是否已经发放
     */
    private Integer isProvided;

    /**
     * 年月信息
     */
    private String month;

    /**
     * 是否在工资条中显示
     */
    private Integer isDisplay;

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
