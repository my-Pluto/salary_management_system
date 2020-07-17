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
public class FixedItem implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 固定项目ID
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 固定项目名称
     */
    private String name;

    /**
     * 增减项，表明该项目是增加、减少、还是无影响
     */
    private Integer effect;

    /**
     * 表明该项目在生成工资条中的序号
     */
    private Integer number;

    /**
     * 是否显示
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

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer isDeleted;


}
