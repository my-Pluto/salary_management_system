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
 * @since 2020-07-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CountItem implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 计算项目ID
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 计算项目名称
     */
    private String name;

    /**
     * 增减项
     */
    private Integer effect;

    /**
     * 工资条中的序号
     */
    private Integer number;

    /**
     * 在工资条中是否显示
     */
    private Integer isDisplay;

    /**
     * 第一个运算数
     */
    private Long firstOperand;

    /**
     * 运算符
     */
    private String operator;

    /**
     * 第二个运算数
     */
    private Long secentOperand;

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
