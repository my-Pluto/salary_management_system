package com.himoyi.salary_management_system.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.himoyi.salary_management_system.common.Result;
import com.himoyi.salary_management_system.common.dto.EmployeeDto;
import com.himoyi.salary_management_system.common.dto.EmployeesDto;
import com.himoyi.salary_management_system.common.dto.PositionDto;
import com.himoyi.salary_management_system.pojo.Employee;
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
 * 岗位
 *
 * @author 张玉飞 陈辰 刘月锟 宫雅琦 邵景宇
 * @since 2020-07-17
 */
@RestController
@CrossOrigin
@RequestMapping("/position")
public class PositionController {

    @Autowired
    PositionService positionService;

    /**
     * 获取所有
     * @return
     */
    @GetMapping("/positions")
    @RequiresAuthentication
    public Result getPositions() {
        List<Position> positions = positionService.list();
        return Result.success("查询成功！", positions);
    }

    /**
     * 分页获取所有
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/positions/{page}/{size}")
    @RequiresAuthentication
    public Result getPositions(@PathVariable(name = "page") Integer page, @PathVariable(name = "size") Integer size) {
        Page<Position> positionPage = new Page<>(page, size);
        IPage<Position> positions = positionService.selectPage(positionPage);
        return Result.success("查询成功！", positions);
    }

    /**
     * 查询
     * @param positionDto
     * @return
     */
    @PostMapping("/positions")
    @RequiresAuthentication
    public Result getPositions(@RequestBody PositionDto positionDto) {
        Map<String, Object> map = BeanUtil.beanToMap(positionDto, false, true);
        List<Position> positions = positionService.listByMap(map);
        return Result.success("查询成功！", positions);
    }

    /**
     * 分页查询
     * @param positionDto
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/positions/{page}/{size}")
    @RequiresAuthentication
    public Result getPositionsPage(@RequestBody PositionDto positionDto,
                               @PathVariable(name = "page") Integer page, @PathVariable(name = "size") Integer size) {
        IPage<Position> positions =positionService.selectPositionPage(new Page<PositionDto>(page, size), positionDto);
        return Result.success("查询成功！", positions);
    }

    /**
     * 添加
     * @param position
     * @return
     */
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

    /**
     * 根据id更新
     * @param position
     * @return
     */
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

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    @RequiresAuthentication
    public Result deletePosition(@PathVariable(name = "id") Long id) {
        positionService.removeById(id);
        return Result.success("删除成功！", null);
    }
}

