package com.himoyi.salary_management_system.service.impl;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.himoyi.salary_management_system.common.dto.ImportItemDataDto;
import com.himoyi.salary_management_system.common.dto.ImportItemDto;
import com.himoyi.salary_management_system.mapper.ImportItemMapper;
import com.himoyi.salary_management_system.pojo.FixedItemData;
import com.himoyi.salary_management_system.pojo.ImportItemData;
import com.himoyi.salary_management_system.mapper.ImportItemDataMapper;
import com.himoyi.salary_management_system.service.ImportItemDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 张玉飞 陈辰 刘月锟 宫雅琦 邵景宇
 * @since 2020-07-17
 */
@Service
public class ImportItemDataServiceImpl extends ServiceImpl<ImportItemDataMapper, ImportItemData> implements ImportItemDataService {

    @Autowired
    ImportItemDataMapper importItemDataMapper;

    /**
     * 获取数据
     * @param page
     * @param size
     * @param importItemDataDto
     * @return
     */
    @Override
    public Map<String, Object> getData(Integer page, Integer size, ImportItemDataDto importItemDataDto) {
        // 获取所有id
        List<Long> ids = importItemDataMapper.getEmployee_id(importItemDataDto);

        // 获取所有项目名称
        List<String> names = importItemDataMapper.getName(importItemDataDto);

        // 填入分页信息
        Integer allPageNumber = ids.size() / size + 1;
        Integer first = (size * page) - size;
        int total = ids.size();

        Map<String, Object> all = MapUtil.newHashMap();
        all.put("total", total);
        all.put("allPageNumber", allPageNumber);
        all.put("names", names);

        List<Map<String, Object>> data = new ArrayList<>();

        // 循环获取用户数据
        for (int i = first; i < (size > ids.size() ? ids.size() : size); i++) {
            Map<String, Object> map = MapUtil.newHashMap();
            Long id = ids.get(i);
            ImportItemData importItemData = importItemDataMapper.selectList(new QueryWrapper<ImportItemData>().eq("employee_id", id)).get(0);
            map.put("id", importItemData.getEmployeeId());
            map.put("name", importItemData.getEmployeeName());
            map.put("department_id", importItemData.getDeptId());
            map.put("department_name", importItemData.getDeptName());
            for (String name : names) {
                BigDecimal bigDecimal = importItemDataMapper.getSalary(id, name, importItemDataDto);
                map.put(name, bigDecimal);
            }
            data.add(map);
        }
        all.put("data", data);
        return all;
    }

    // 根据id更新数据
    @Override
    public void updateByEmployeeId(Object object, Long id, String name, String month) {
        importItemDataMapper.updateByEmployeeId(object, id, name, month);
    }

    public String getLastMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // 设置为当前时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月
        date = calendar.getTime();
        String accDate = format.format(date);
        return accDate;
    }
}
