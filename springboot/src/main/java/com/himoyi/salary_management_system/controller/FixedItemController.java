package com.himoyi.salary_management_system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.himoyi.salary_management_system.common.Result;
import com.himoyi.salary_management_system.common.dto.EmployeeDto;
import com.himoyi.salary_management_system.common.dto.EmployeesDto;
import com.himoyi.salary_management_system.common.dto.FixedItemDto;
import com.himoyi.salary_management_system.pojo.FixedItem;
import com.himoyi.salary_management_system.pojo.FixedItemData;
import com.himoyi.salary_management_system.service.FixedItemDataService;
import com.himoyi.salary_management_system.service.FixedItemService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 张玉飞 陈辰 刘月锟 宫雅琦 邵景宇
 * @since 2020-07-17
 */
@RestController
@CrossOrigin
@RequestMapping("/fixeditem")
public class FixedItemController {

    @Autowired
    FixedItemService fixedItemService;
    @Autowired
    FixedItemDataService fixedItemDataService;

    @GetMapping("/fixeditem")
    @RequiresAuthentication
    public Result getFixiedItem() {
        List<FixedItem> fixedItems = fixedItemService.list();
        return Result.success("查询成功！", null);
    }

    @GetMapping("/fixeditem/{page}/{size}")
    @RequiresAuthentication
    public Result getFixedItem(@PathVariable(name = "page") Integer page, @PathVariable(name = "size") Integer size){
        IPage<FixedItem> fixedItemIPage = fixedItemService.selectPage(new Page<FixedItem>(page, size));
        return Result.success("查询成功！", fixedItemIPage);
    }

    @PostMapping("/fixeditem/{page}/{size}")
    @RequiresAuthentication
    public Result getFixedItem(@RequestBody FixedItemDto fixedItemDto,
                               @PathVariable(name = "page") Integer page, @PathVariable(name = "size") Integer size) {
        IPage<FixedItemDto> fixedItemIPage = fixedItemService.selectFixedItemPage(new Page<FixedItemDto>(page, size), fixedItemDto);
        return Result.success("查询成功！", fixedItemIPage);
    }

    @PostMapping
    @RequiresAuthentication
    public Result addFixedItem(@RequestBody FixedItem fixedItem) {
        fixedItemService.save(fixedItem);
        return Result.success("添加成功!", null);
    }

    @PostMapping("{id}")
    @RequiresAuthentication
    public Result updateFixedItem(@RequestBody FixedItem fixedItem) {
        FixedItem fixedItem1 = fixedItemService.getById(fixedItem.getId());
        if (fixedItem1 == null) {
            return Result.fail("更新失败！该固定项目不存在！", null);
        }

        fixedItemService.saveOrUpdate(fixedItem);
        return Result.success("更新成功！", null);
    }

    @DeleteMapping("{id}")
    @RequiresAuthentication
    public Result deleteFixedItem(@PathVariable(name = "id") Long id) {
        FixedItem fixedItem1 = fixedItemService.getById(id);
        if (fixedItem1 == null) {
            return Result.fail("删除失败！该固定项目不存在！", null);
        }

        fixedItemDataService.remove(new QueryWrapper<FixedItemData>().eq("item_id", id));
        fixedItemService.removeById(id);
        return Result.success("删除成功！", null);
    }

}

