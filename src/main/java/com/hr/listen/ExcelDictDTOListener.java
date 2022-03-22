package com.hr.listen;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.hr.dto.ExcelDictDTO;
import com.hr.mapper.DictMapper;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 核心  监听器
 *
 * @author lq
 * @date 2021-07-10 - 21:37
 *
 * 此类没有被spring容器管理   加入 @component 组件 注解 构造函数 setter注入等..
 *
 * @Autowired
 * private DictMapper dictMapper
 *
 * 无法自动注入
 *
 *      读功能的核心业务
 *
 */
@Slf4j
@NoArgsConstructor
public class ExcelDictDTOListener extends AnalysisEventListener<ExcelDictDTO> {


    private DictMapper dictMapper;

    //数据列表
    List<ExcelDictDTO> list = new ArrayList<ExcelDictDTO>();

    static final int BATCH_COUNT=5;

    //构造函数注入
    public ExcelDictDTOListener(DictMapper dictMapper) {
        this.dictMapper = dictMapper;
    }

    @Override
    public void invoke(ExcelDictDTO data, AnalysisContext analysisContext) {
        log.info("解析到一条数据{}",data);
        if(list.size()>=5){
            //解析到的数据存储到数据库
            saveData();
            //清空列表
            list.clear();
        }
        list.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("所有数据解析完成！");
        saveData();
    }

    //数据库业务
    private void saveData() {
        log.info("{} 条数据被存储到数据库...",list.size());
        dictMapper.insertBatch(list);
        log.info("{} 条数据被存储到数据库成功",list.size());

    }
}
