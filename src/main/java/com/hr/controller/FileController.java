package com.hr.controller;

import com.hr.utils.DateUtils;
import com.hr.utils.FileUtils;
import com.hr.utils.StringUtils;
import com.hr.utils.ToolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lq
 * @date 2021-05-28 - 21:43
 */
@Controller
@RequestMapping("/")
public class FileController {

    private static final Logger log = LoggerFactory.getLogger(FileController.class);

    /**
     * 通用下载请求
     *
     * @param delete   是否删除
     */
    @RequestMapping("common/download")
    public void fileDownload(@RequestParam("file") MultipartFile file, Boolean delete, HttpServletResponse response, HttpServletRequest request) {
        try {
            //获取下载文件
            String fileName = file.getOriginalFilename();
            System.out.println("选择文件名："+fileName);

            if (!FileUtils.isValidFilename(fileName)) {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }

            String realFileName = DateUtils.dateTimeNow() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = ToolUtil.getDownloadPath() + fileName;

            System.out.println(realFileName);//文件名
            System.out.println(filePath);//本地路径

            response.setCharacterEncoding("utf-8");
            // 下载使用"application/octet-stream"更标准
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + FileUtils.setFileDownloadHeader(request, realFileName));
            FileUtils.writeBytes(filePath, response.getOutputStream());
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }

    @GetMapping("/hello")
    public String hello (){
        return "upload";
    }

    @GetMapping("/test")
    public String uploadFile (){
        return "test";
    }


}
