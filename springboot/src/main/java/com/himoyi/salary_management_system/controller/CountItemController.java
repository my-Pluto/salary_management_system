package com.himoyi.salary_management_system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.himoyi.salary_management_system.common.Result;
import com.himoyi.salary_management_system.common.dto.CountItemDto;
import com.himoyi.salary_management_system.common.dto.ImportItemDto;
import com.himoyi.salary_management_system.pojo.CountItem;
import com.himoyi.salary_management_system.service.CountItemService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * 计算项目控制器
 * @author 张玉飞 陈辰 刘月锟 宫雅琦 邵景宇
 * @since 2020-07-17
 */
@RestController
@CrossOrigin
@RequestMapping("/countitem")
public class CountItemController {

    @Autowired
    CountItemService countItemService;

    /**
     * 查询所有结果
     * @return
     */
    @GetMapping("/countitem")
    @RequiresAuthentication
    public Result getCountItem() {
        List<CountItem> countItems = countItemService.list();
        return Result.success("查询成功！", countItems);
    }

    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/countitem/{page}/{size}")
    @RequiresAuthentication
    public Result getCountItem(@PathVariable(name = "page") Integer page, @PathVariable(name = "size") Integer size) {
        IPage<CountItem> countItemIPage = countItemService.selectPage(new Page<CountItem>(page, size));
        return Result.success("查询成功！", countItemIPage);
    }

    /**
     * 模糊分页查询
     * @param countItemDto
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/countitem/{page}/{size}")
    @RequiresAuthentication
    public Result getCountItem(@RequestBody CountItemDto countItemDto,
                               @PathVariable(name = "page") Integer page, @PathVariable(name = "size") Integer size) {

        IPage<CountItemDto> countItemDtoIPage = countItemService.selectCountItemPage
                (new Page<ImportItemDto>(page, size), countItemDto);
        return Result.success("查询成功！", countItemDtoIPage);
    }


    /**
     * 添加项目
     * @param countItem
     * @return
     */
    @PostMapping
    @RequiresAuthentication
    public Result addCountItem(@RequestBody CountItem countItem) {
        if (countItemService.getOne(new QueryWrapper<CountItem>().eq("name", countItem.getName())) != null) {
            return Result.fail("添加失败，该项目已存在！", null);
        }

        if (countItem.getIsDisplay() != 0 &&
                countItemService.getOne(new QueryWrapper<CountItem>().eq("number", countItem.getNumber())) != null) {
            return Result.fail("添加项目失败！工资条显示位置冲突！", null);
        }

        countItemService.save(countItem);
        return Result.success("添加成功！", null);
    }

    /**
     * 根据id更新项目
     * @param countItem
     * @param id
     * @return
     */
    @PostMapping("{id}")
    public Result updateCountItem(@RequestBody CountItem countItem, @PathVariable(name = "id") Long id) {
        if (countItemService.getById(id) == null) {
            return Result.fail("更新失败！该项目不存在！", null);
        }
        if (countItem.getIsDisplay() != 0 &&
                countItemService.getOne(new QueryWrapper<CountItem>().eq("number", countItem.getNumber()).ne("id", countItem.getId())) != null) {
            return Result.fail("更新项目失败！工资条显示位置冲突！", null);
        }
        countItemService.updateById(countItem);
        return Result.success("更新成功！", null);
    }

    /**
     * 删除项目
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    @RequiresAuthentication
    public Result deleteCountItem(@PathVariable(name = "id") Long id) {
        if (countItemService.getById(id) == null) {
            return Result.fail("删除失败！该项目不存在！", null);
        }
        countItemService.removeById(id);
        return Result.success("删除成功！", null);
    }

    @GetMapping("/countitem/firstOperation")
    @RequiresAuthentication
    public Result getFirstOperation() {
        List<String> list = countItemService.getFirstOperation();
        return Result.success("获取成功！", list);
    }
}

