package com.himoyi.salary_management_system.service.impl;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.himoyi.salary_management_system.common.dto.FixedItemDataDto;
import com.himoyi.salary_management_system.common.dto.FixedItemDto;
import com.himoyi.salary_management_system.common.dto.StatementDto;
import com.himoyi.salary_management_system.mapper.FixedItemDataMapper;
import com.himoyi.salary_management_system.mapper.FixedItemMapper;
import com.himoyi.salary_management_system.pojo.*;
import com.himoyi.salary_management_system.mapper.StatementMapper;
import com.himoyi.salary_management_system.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.sf.saxon.trans.SymbolicName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * 报表、工资发放服务
 *
 * @author 张玉飞 陈辰 刘月锟 宫雅琦 邵景宇
 * @since 2020-07-17
 */
@Service
public class StatementServiceImpl extends ServiceImpl<StatementMapper, Statement> implements StatementService {

    @Autowired
    FixedItemService fixedItemService;
    @Autowired
    FixedItemDataService fixedItemDataService;
    @Autowired
    ImportItemService importItemService;
    @Autowired
    ImportItemDataService importItemDataService;
    @Autowired
    CountItemService countItemService;
    @Autowired
    FixedItemDataMapper fixedItemDataMapper;

    @Autowired
    StatementMapper statementMapper;

    /**
     * 计算工资
     * @param department
     * @param month
     */
    @Override
    public void countSalary(Long department, String month) {

        //生成固定项目信息
        FixedItemDataDto fixedItemDataDto = new FixedItemDataDto();
        fixedItemDataDto.setDeptId(department);

        // 获取所有员工id
        List<Long> ids = fixedItemDataMapper.getEmployee_id(fixedItemDataDto);

        BigDecimal salary = BigDecimal.valueOf(0);

        // 循环获取固定信息并将信息填入工资表单中
        for (Long employee_id : ids) {
            List<FixedItemData> fixedItemDatas =
                    fixedItemDataService.list(new QueryWrapper<FixedItemData>().eq("employee_id", employee_id));
            for (FixedItemData fixedItemData : fixedItemDatas) {
                FixedItem fixedItem = fixedItemService.
                        getOne(new QueryWrapper<FixedItem>().eq("name", fixedItemData.getName()));
                Statement statement = new Statement();
                statement.setName(fixedItemData.getName());
                statement.setEmployeeId(fixedItemData.getEmployeeId());
                statement.setEmployeeName(fixedItemData.getEmployeeName());
                statement.setDepartmentId(fixedItemData.getDeptId());
                statement.setDepartmentName(fixedItemData.getDeptName());
                statement.setEffect(fixedItem.getEffect());
                statement.setData(fixedItemData.getSalary());
                statement.setMonth(month);
                statement.setIsDisplay(fixedItem.getIsDisplay());
                statement.setIsProvided(0);
                statement.setNumber(fixedItem.getNumber());
                if (fixedItem.getEffect() == 0) {
                    salary = salary.subtract(statement.getData());
                } else if (fixedItem.getEffect() == 2) {
                    salary = salary.add(statement.getData());
                }
                statementMapper.insert(statement);
            }

            // 循环获取导入想项目信息并填入表单中
            List<ImportItemData> importItemDatas =
                    importItemDataService.list(new QueryWrapper<ImportItemData>().eq("employee_id", employee_id));
            for (ImportItemData importItemData : importItemDatas) {
                ImportItem importItem = importItemService.
                        getOne(new QueryWrapper<ImportItem>().eq("name", importItemData.getItemName()));
                Statement statement = new Statement();
                statement.setName(importItemData.getItemName());
                statement.setEmployeeId(importItemData.getEmployeeId());
                statement.setEmployeeName(importItemData.getEmployeeName());
                statement.setDepartmentId(importItemData.getDeptId());
                statement.setDepartmentName(importItemData.getDeptName());
                statement.setEffect(importItem.getEffect());
                statement.setData(importItemData.getData());
                statement.setMonth(month);
                statement.setIsDisplay(importItem.getIsDisplay());
                statement.setIsProvided(0);
                statement.setNumber(importItem.getNumber());
                if (importItem.getEffect() == 0) {
                    salary = salary.subtract(statement.getData());
                } else if (importItem.getEffect() == 2) {
                    salary = salary.add(statement.getData());
                }
                statementMapper.insert(statement);
            }

            // 循环获取计算项目信息并填入表单
            List<CountItem> countItems = countItemService.list();
            for (CountItem countItem : countItems) {
                ImportItemData importItemData = importItemDataService.
                        list(new QueryWrapper<ImportItemData>().eq("employee_id", employee_id)).get(0);
                BigDecimal bigDecimal = fixedItemDataService.
                        getOne(new QueryWrapper<FixedItemData>()
                                .eq("name", countItem.getFirstOperand())
                                .eq("employee_id", employee_id))
                        .getSalary();
                if (bigDecimal == null) {
                    bigDecimal = importItemDataService.
                            getOne(new QueryWrapper<ImportItemData>().eq("name", countItem.getFirstOperand()))
                            .getData();
                }
                Statement statement = new Statement();
                statement.setName(countItem.getName());
                statement.setEmployeeId(importItemData.getEmployeeId());
                statement.setEmployeeName(importItemData.getEmployeeName());
                statement.setDepartmentId(importItemData.getDeptId());
                statement.setDepartmentName(importItemData.getDeptName());
                statement.setEffect(countItem.getEffect());
                BigDecimal data;
                if (countItem.getOperator().equals("+")) {
                    data = bigDecimal.add(countItem.getSecentOperand());
                } else if (countItem.getOperator().equals("-")) {
                    data = bigDecimal.subtract(countItem.getSecentOperand());
                } else if (countItem.getOperator().equals("*")) {
                    data = bigDecimal.multiply(countItem.getSecentOperand());
                } else {
                    data = bigDecimal.divide(countItem.getSecentOperand());
                }
                statement.setData(data);
                statement.setMonth(month);
                statement.setIsDisplay(countItem.getIsDisplay());
                statement.setIsProvided(0);
                statement.setNumber(countItem.getNumber());
                if (countItem.getEffect() == 0) {
                    salary = salary.subtract(statement.getData());
                } else if (countItem.getEffect() == 2) {
                    salary = salary.add(statement.getData());
                }
                statementMapper.insert(statement);
            }

            // 生成实发工资信息
            FixedItemData fixedItemData = fixedItemDatas.get(0);
            Statement statement = new Statement();
            statement.setName("实发工资");
            statement.setEmployeeId(fixedItemData.getEmployeeId());
            statement.setEmployeeName(fixedItemData.getEmployeeName());
            statement.setDepartmentId(fixedItemData.getDeptId());
            statement.setDepartmentName(fixedItemData.getDeptName());
            statement.setEffect(0);
            statement.setData(salary);
            statement.setMonth(month);
            statement.setIsDisplay(1);
            statement.setIsProvided(0);
            statement.setNumber(100);
            statementMapper.insert(statement);
        }
    }

    /**
     * 工资发放
     * @param department
     * @param month
     */
    public void salaryProvided(Long department, String month) {
        statementMapper.salaryProvided(department, month);
    }

    /**
     * 根据id更新信息
     * @param valueOf
     * @param employee_id
     * @param month
     * @param name
     */
    @Override
    public void updateByEmployeeId(BigDecimal valueOf, Long employee_id, String month, String name) {
        statementMapper.updateByEmployeeId(valueOf, employee_id, month, name);
    }

    /**
     * 获取数据
     * @param page
     * @param size
     * @param statementDto
     * @return
     */
    @Override
    public Map<String, Object> getData(Integer page, Integer size, StatementDto statementDto) {
        List<Long> ids = statementMapper.getEmployee_id(statementDto);

        Integer allPageNumber = ids.size() / size + 1;
        Integer first = (size * page) - size;
        int total = ids.size();

        List<FixedItem> fixedItems = fixedItemService.getName();
        List<CountItem> countItems = countItemService.getName();
        List<ImportItem> importItems = importItemService.getName();
        List<String> names = new ArrayList<>();

        for (FixedItem fixedItem : fixedItems) {
            names.add(fixedItem.getName());
        }
        for (CountItem countItem : countItems) {
            names.add(countItem.getName());
        }
        for (ImportItem importItem : importItems) {
            names.add(importItem.getName());
        }
        names.add("实发工资");

        Map<String, Object> all = MapUtil.newHashMap();
        all.put("total", total);
        all.put("allPageNumber", allPageNumber);
        all.put("names", names);

        List<Map<String, Object>> data = new ArrayList<>();

        for (int i = first; i < (size > ids.size() ? ids.size() : size); i++) {
            Map<String, Object> map = MapUtil.newHashMap();
            Long id = ids.get(i);
            Statement statement = statementMapper.selectList(new QueryWrapper<Statement>().eq("employee_id", id)).get(0);
            map.put("id", statement.getEmployeeId());
            map.put("name", statement.getEmployeeName());
            map.put("department_id", statement.getDepartmentId());
            map.put("department_name", statement.getDepartmentName());
            for (String name : names) {
                BigDecimal bigDecimal = statementMapper.getData(id, name, statementDto);
                map.put(name, bigDecimal);
            }
            data.add(map);
        }

        all.put("data", data);
        return all;
    }

}

