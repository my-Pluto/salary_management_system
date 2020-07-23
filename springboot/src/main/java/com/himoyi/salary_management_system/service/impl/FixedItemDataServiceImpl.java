package com.himoyi.salary_management_system.service.impl;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.himoyi.salary_management_system.common.dto.FixedItemDataDto;
import com.himoyi.salary_management_system.pojo.FixedItemData;
import com.himoyi.salary_management_system.mapper.FixedItemDataMapper;
import com.himoyi.salary_management_system.service.FixedItemDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
 * @author 张玉飞 陈辰 刘月锟 宫雅琦 邵景宇
 * @since 2020-07-17
 */
@Service
public class FixedItemDataServiceImpl extends ServiceImpl<FixedItemDataMapper, FixedItemData> implements FixedItemDataService {

    @Autowired
    FixedItemDataMapper fixedItemDataMapper;

    /**
     * 分页
     * @param fixedItemDataPage
     * @return
     */
    @Override
    public IPage<FixedItemData> selectPage(Page<FixedItemData> fixedItemDataPage) {
        return fixedItemDataMapper.selectPage(fixedItemDataPage, null);
    }

    /**
     * 分页查询
     * @param fixedItemDataDtoPage
     * @param fixedItemDataDto
     * @return
     */
    @Override
    public IPage<FixedItemData> selectFixedItemPage(Page<FixedItemDataDto> fixedItemDataDtoPage, FixedItemDataDto fixedItemDataDto) {
        return fixedItemDataMapper.selectFixedItemPage(fixedItemDataDtoPage, fixedItemDataDto);
    }

    /**
     * 获取数据
     * @param page
     * @param size
     * @param fixedItemDataDto
     * @return
     */
    @Override
    public Map<String, Object> getData(Integer page, Integer size, FixedItemDataDto fixedItemDataDto) {
        // 获取到所有id
        List<Long> ids = fixedItemDataMapper.getEmployee_id(fixedItemDataDto);

        // 获取到所有项目名称
        List<String> names = fixedItemDataMapper.getName(fixedItemDataDto);

        // 生成分页信息并存入结果
        Integer allPageNumber = ids.size() / size + 1;
        Integer first = (size * page) - size;
        int total = ids.size();

        Map<String, Object> all = MapUtil.newHashMap();
        all.put("total", total);
        all.put("allPageNumber", allPageNumber);
        all.put("names", names);

        List<Map<String, Object>> data = new ArrayList<>();

        // 循环获取数据
        for (int i = first; i < (size > ids.size() ? ids.size() : size); i++) {
            Map<String, Object> map = MapUtil.newHashMap();
            // 根据id获取到员工细腻
            Long id = ids.get(i);
            FixedItemData fixedItemData = fixedItemDataMapper.selectList(new QueryWrapper<FixedItemData>().eq("employee_id", id)).get(0);
            map.put("id", fixedItemData.getEmployeeId());
            map.put("name", fixedItemData.getEmployeeName());
            map.put("department_id", fixedItemData.getDeptId());
            map.put("department_name", fixedItemData.getDeptName());
            // 获取到数据
            for (String name : names) {
                BigDecimal bigDecimal = fixedItemDataMapper.getSalary(id, name);
                map.put(name, bigDecimal);
            }
            // 装入
            data.add(map);
        }

        all.put("data", data);
        return all;
    }

    // 根据id更新数据
    @Override
    public void updateByEmployeeId(Object object, Long id, String name) {
        fixedItemDataMapper.updateByEmployeeId(object, id, name);
    }

}
