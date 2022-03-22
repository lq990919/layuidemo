package com.hr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hr.dto.ExcelDictDTO;
import com.hr.po.Dict;

import java.io.InputStream;
import java.util.List;

/**
 * @author lq
 * @date 2021-07-10 - 21:40
 */
public interface DictService extends IService<Dict> {

    void importData(InputStream inputStream);

    List<ExcelDictDTO> listDictData();

    List<Dict> selListByParenId(Long parentId);
}
