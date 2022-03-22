package com.hr.controller;

import com.alibaba.excel.EasyExcel;
import com.hr.dto.ExcelDictDTO;
import com.hr.po.Dict;
import com.hr.po.R;
import com.hr.service.DictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author lq
 * @date 2021-07-11 - 9:31
 */
@RequestMapping("/dict")
@RestController
@Slf4j
public class EexcelDictController {

    @Autowired
    private DictService dictService;

    /**
     *
     * Eecel文件导入
     *
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/import")
    public R batchImport(@RequestParam("file")MultipartFile file) throws Exception {

        try {

            //获取文件流
            InputStream inputStream = file.getInputStream();

            dictService.importData(inputStream);

            return R.ok().message("数据字典数据批量导入成功");
        } catch (IOException e) {
            throw new Exception("文件上传失败",e);
        }

    }

    /**
     *
     * Excel文件导出
     *
     * @param response
     * @throws IOException
     */
    @GetMapping("/export")
    public void download(HttpServletResponse response) throws IOException {

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");

        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("mydict", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        //核心 获取流对象 数据类 业务数据
        EasyExcel.write(response.getOutputStream(), ExcelDictDTO.class).sheet("数据字典").doWrite(dictService.listDictData());
    }

    /**
     * 数据字典 前端渲染
     * @param parentId
     * @return
     */
    @GetMapping("/list/{parentId}")
    private R getDictListByParenId(@PathVariable Long parentId){

        List<Dict> dictList = dictService.selListByParenId(parentId);

        return R.nice().data("list",dictList);
    }





    @GetMapping("/demo")
    public String download(){
        return "Demo 功能实现 。。";
    }


}
