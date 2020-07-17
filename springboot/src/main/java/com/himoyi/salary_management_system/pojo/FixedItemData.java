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
public class FixedItemData implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 记录id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 项目id
     */
    private Long itemId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 部门id
     */
    private Long deptId;

    /**
     * 员工名称
     */
    private String employeeName;

    /**
     * 员工id
     */
    private Long employeeId;

    /**
     * 金额
     */
    private BigDecimal salary;

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

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer isDeleted;


}
