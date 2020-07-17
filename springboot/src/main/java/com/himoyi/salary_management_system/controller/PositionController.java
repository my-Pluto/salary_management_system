package com.himoyi.salary_management_system.controller;


import cn.hutool.core.bean.BeanUtil;
import com.himoyi.salary_management_system.common.Result;
import com.himoyi.salary_management_system.common.dto.PositionDto;
import com.himoyi.salary_management_system.pojo.Position;
import com.himoyi.salary_management_system.service.PositionService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 张玉飞 陈辰 刘月锟 宫雅琦 邵景宇
 * @since 2020-07-17
 */
@RestController
@RequestMapping("/position")
public class PositionController {

    @Autowired
    PositionService positionService;

    @GetMapping("/positions")
    @RequiresAuthentication
    public Result getPositions() {
        List<Position> positions = positionService.list();
        return Result.success("查询成功！", positions);
    }

    @PostMapping("/positions")
    @RequiresAuthentication
    public Result getPositions(@RequestBody PositionDto positionDto) {
        Map<String, Object> map = BeanUtil.beanToMap(positionDto, false, true);

        List<Position> positions = positionService.listByMap(map);
        return Result.success("查询成功！", positions);
    }

    @PostMapping
    @RequiresAuthentication
    public Result addPosition(@Validated @RequestBody Position position) {
        Position position1 = positionService.getById(position);
        if (position1 != null) {
            return Result.fail("添加失败！该岗位已存在", null);
        }

        positionService.save(position);
        return Result.success("添加成功！", null);
    }

    @PostMapping("{id}")
    @RequiresAuthentication
    public Result updatePosition(@Validated @RequestBody Position position) {
        Position position1 = positionService.getById(position);
        if (position1 == null) {
            return Result.fail("更新失败！该岗位不存在", null);
        }

        positionService.updateById(position);
        return Result.success("更新成功！", null);
    }

    @DeleteMapping("{id}")
    @RequiresAuthentication
    public Result deletePosition(@PathVariable(name = "id") Long id) {
        positionService.removeById(id);
        return Result.success("删除成功！", null);
    }
}

