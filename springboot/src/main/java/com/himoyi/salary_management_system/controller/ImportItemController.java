package com.himoyi.salary_management_system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.himoyi.salary_management_system.common.Result;
import com.himoyi.salary_management_system.common.dto.FixedItemDto;
import com.himoyi.salary_management_system.common.dto.ImportItemDto;
import com.himoyi.salary_management_system.pojo.ImportItem;
import com.himoyi.salary_management_system.service.ImportItemDataService;
import com.himoyi.salary_management_system.service.ImportItemService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/importitem")
public class ImportItemController {

    @Autowired
    ImportItemService importItemService;
    @Autowired
    ImportItemDataService importItemDataService;

    @GetMapping("/importitem")
    @RequiresAuthentication
    public Result getImportItems() {
        List<ImportItem> importItems = importItemService.list();
        return Result.success("查询成功！", null);
    }

    @GetMapping("/importitem/{page}/{size}")
    @RequiresAuthentication
    public Result getImportItems(@PathVariable(name = "page") Integer page, @PathVariable(name = "size") Integer size) {
        IPage<ImportItem> importItemIPage = importItemService.selectPage(new Page<ImportItem>(page, size));
        return Result.success("查询成功！", importItemIPage);
    }

    @PostMapping("/importitem/{page}/{size}")
    @RequiresAuthentication
    public Result getImportItems(@RequestBody ImportItemDto importItemDto,
                                 @PathVariable(name = "page") Integer page, @PathVariable(name = "size") Integer size) {
        IPage<ImportItemDto> importItemDtoIPage = importItemService.selectImportItemPage
                (new Page<ImportItemDto>(page, size), importItemDto);
        return Result.success("查询成功！", importItemDtoIPage);
    }

    @PostMapping
    @RequiresAuthentication
    public Result addImportItem(@RequestBody ImportItem importItem) {
        if (importItemService.getOne(new QueryWrapper<ImportItem>().eq("name", importItem.getName())) == null) {
            return Result.fail("添加失败，该项目已存在！", null);
        }

        importItemService.save(importItem);
        return Result.success("添加成功！", null);
    }

    @PostMapping("{id}")
    public Result updateImportItem(@RequestBody ImportItem importItem, @PathVariable(name = "id") Long id) {
        if (importItemService.getById(id) == null) {
            return Result.fail("更新失败！该项目不存在！", null);
        }
        importItemService.updateById(importItem);
        return Result.success("更新成功！", null);
    }

    @DeleteMapping("{id}")
    @RequiresAuthentication
    public Result deleteImportItem(@PathVariable(name = "id") Long id) {
        if (importItemService.getById(id) == null) {
            return Result.fail("删除失败！该项目不存在！", null);
        }
        importItemService.removeById(id);
        return Result.success("删除成功！", null);
    }

}

