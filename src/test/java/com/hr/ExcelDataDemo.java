package com.hr;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.hr.dto.DemoData;
import com.hr.listen.DemoDataListener;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lq
 * @date 2021-07-10 - 20:58
 */
@SpringBootTest
public class ExcelDataDemo {

    @Test
    public void simpleWrite() {
        // 写法1
        String fileName = "F:/upload/excel/simpleWrite.xls";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, DemoData.class).excelType(ExcelTypeEnum.XLS).sheet("模板").doWrite(data());

    }

    @Test
    public void simpleRead() {
        // 有个很重要的点 DemoDataListener 不能被spring管理，
        // 要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法1：
        String fileName = "F:/upload/excel/simpleWrite.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().doRead();

    }

    private List<DemoData> data() {
        List<DemoData> list = new ArrayList<DemoData>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }

}
