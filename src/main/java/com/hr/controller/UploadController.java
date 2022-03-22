//package com.hr.controller;
//
//import com.hr.po.FileInfo;
//import com.hr.po.R;
//import com.hr.service.FileService;
//import com.hr.utils.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//
///**
// * @author lq
// * @date 2021-06-18 - 20:34
// */
//@RequestMapping("/file")
//@RestController
//public class UploadController {
//
//    @Autowired
//    private static  final Logger logger = LoggerFactory.getLogger(UploadController.class);
//
//    @Autowired
//    private FileService fileService;
//
//    /**
//     * 上传文件
//     * @param multipartFile
//     * @return
//     */
//    @RequestMapping(method = RequestMethod.POST)
//    public Object upload(@RequestPart("file") MultipartFile multipartFile) {
//
//        try {
//            FileInfo fileInfo = fileService.upload(multipartFile);
//            System.out.println("文件上传信息反馈："+fileInfo);
//            return R.FileSuccess(fileInfo);
//        } catch (Exception e) {
//            logger.error("上传文件异常",e);
//            return R.failupload();
//        }
//    }
//
//    /**
//     * 获取图片流
//     * @param response
//     */
//    @RequestMapping(value="getImgStream",method = RequestMethod.GET)
//    public void getImgStream(HttpServletResponse response,
//                             @RequestParam(value = "fileName",required = false)String fileName){
//        FileInfo fileInfo = null;
//        if(StringUtils.isNotEmpty(fileName)){
//            fileInfo = fileService.getByName(fileName);
//        }
//
//        FileInputStream fis = null;
//
//        try {
//            response.setContentType("image/"+fileInfo.getRealFileName().split("\\.")[1]);
//            OutputStream out = response.getOutputStream();
//            File file = new File(fileInfo.getAblatePath());
//            fis = new FileInputStream(file);
//            byte[] b = new byte[fis.available()];
//            fis.read(b);
//            out.write(b);
//            out.flush();
//        } catch (Exception e) {
//            logger.error("文件不存在",e.getMessage());
//        } finally {
//            if (fis != null) {
//                try {
//                    fis.close();
//                } catch (IOException e) {
//                    logger.error("close getImgStream error",e);
//                }
//            }
//        }
//    }
//
//}
