package com.himoyi.salary_management_system.controller;


import com.himoyi.salary_management_system.common.Result;
import com.himoyi.salary_management_system.pojo.ImportItemData;
import com.himoyi.salary_management_system.service.ImportItemDataService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/importitemdata")
public class ImportItemDataController {

    @Autowired
    ImportItemDataService importItemDataService;


}

