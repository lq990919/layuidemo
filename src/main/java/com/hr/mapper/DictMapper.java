package com.hr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hr.dto.ExcelDictDTO;
import com.hr.po.Dict;

import java.util.List;

/**
 * @author lq
 * @date 2021-07-10 - 21:45
 */
public interface DictMapper extends BaseMapper<Dict> {


    void insertBatch(List<ExcelDictDTO> dlist);
}
