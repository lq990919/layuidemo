package com.hr.mapper;

import com.hr.po.FileInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lq
 * @date 2020-11-07 - 15:05
 */
@Mapper
public interface FileMapper{

    public FileInfo findByRealFileName(String fileName);

    public FileInfo getByName(String fileName);

    public void save(FileInfo fileInfo);

}
