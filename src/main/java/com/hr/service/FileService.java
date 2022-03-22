package com.hr.service;

import com.hr.po.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author lq
 * @date 2021-06-18 - 20:34
 */
public interface FileService {

    public FileInfo upload(MultipartFile multipartFile);

    public FileInfo save(String originalFileName, File file);

    public FileInfo getByName(String fileName);

}
