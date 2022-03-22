package com.hr.service.impl;

import com.hr.mapper.FileMapper;
import com.hr.po.FileInfo;
import com.hr.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.io.File;
import java.util.UUID;

/**
 * @author lq
 * @date 2021-06-18 - 20:35
 */
@Service
public class FileServiceImpl implements FileService {
    @Resource
    private FileMapper fileMapper;

    /**
     * 文件上传
     * @param multipartFile
     * @return
     */
    public FileInfo upload(MultipartFile multipartFile){
        String uuid = UUID.randomUUID().toString();
        String realFileName = uuid +"."+ multipartFile.getOriginalFilename().split("\\.")[1];
        try {

            File file = new File("F:\\upload\\file"+File.separator+realFileName);
            //判断父路径文件夹是否存在
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            multipartFile.transferTo(file);
            return save(multipartFile.getOriginalFilename(),file);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 创建文件
     * @param originalFileName
     * @param file
     * @return
     */
    public FileInfo save(String originalFileName,File file){
        try {
            FileInfo fileInfo = new FileInfo();
            fileInfo.setOriginalFileName(originalFileName);
            fileInfo.setRealFileName(file.getName());
            fileInfo.setAblatePath(file.getAbsolutePath());
            System.out.println("保存文件信息："+fileInfo);
            fileMapper.save(fileInfo);
            return fileInfo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public FileInfo getByName(String fileName) {
        FileInfo fileInfo = fileMapper.findByRealFileName(fileName);
        if(fileInfo!=null) {
            System.out.println("图片路径："+fileInfo.getAblatePath());
            fileInfo.setAblatePath(fileInfo.getAblatePath());
        }
        return fileInfo;
    }

}
