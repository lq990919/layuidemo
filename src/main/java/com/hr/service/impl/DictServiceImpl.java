package com.hr.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hr.dto.ExcelDictDTO;
import com.hr.listen.ExcelDictDTOListener;
import com.hr.mapper.DictMapper;
import com.hr.po.Dict;
import com.hr.service.DictService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author lq
 * @date 2021-07-10 - 21:41
 */
@Service
@Slf4j
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Resource
    private RedisTemplate redisTemplate;

    /**
     *  Excel导入
     * @param inputStream 流
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void importData(InputStream inputStream) {
        // 流  指定读用哪个class去读 监听器（mapper）构造注入
        EasyExcel.read(inputStream, ExcelDictDTO.class, new ExcelDictDTOListener(baseMapper)).sheet().doRead();
    }

    /**
     *
     *  Excel导出
     * @return Excel数据字典表整合数据
     */
    @Override
    public List<ExcelDictDTO> listDictData() {

        //字典数据表----数据库获取
        List<Dict> dlist = baseMapper.selectList(null);

        //Excel数据类型表 ---- list容器
        List<ExcelDictDTO> edtlist = new ArrayList<ExcelDictDTO>(dlist.size());

        dlist.forEach(dict -> {
            ExcelDictDTO excelDictDTO = new ExcelDictDTO();
            BeanUtils.copyProperties(dict,excelDictDTO);
            edtlist.add(excelDictDTO);
        });

        return edtlist;
    }

    /**
     *  根据父节点id查出一下数据
     * @param parentId
     * @return
     *
     * 后期优化 加入redis缓存
     */
    @Override
    public List<Dict> selListByParenId(Long parentId) {
        //try catch包裹 是为了redis如果出现异常不影响其他进程
        try {
            log.info("检查是否有Redis缓存");
            //查redis是否有缓存数据
            List<Dict> dictList = (List<Dict>)redisTemplate.opsForValue().get("srb:core:dictList:" + parentId);
            if(dictList != null){
                //缓存中有数据
                log.info("正在查询Redis中的缓存数据....");
                return dictList;
            }
        } catch (Exception e) {
            log.error("redis服务异常：", ExceptionUtils.getStackTrace(e));
        }

        //Redis中没有缓存就查数据库
        QueryWrapper<Dict> dictQueryWrapper = new QueryWrapper<>();
        dictQueryWrapper.eq("parent_id",parentId);


        List<Dict> dlist = baseMapper.selectList(dictQueryWrapper);

        //填充 hasChildren => 物理属性
        dlist.forEach(dict -> {
            //根据 字典数据id
            Boolean hasChildren = this.hasChildren(dict.getId());
            dict.setHasChildren(hasChildren);
        });

        try {
            //将数据存入Redis
            log.info("将数据存入Redis");
            redisTemplate.opsForValue().set("srb:core:dictList:"+parentId,dlist,30, TimeUnit.MINUTES);
        } catch (Exception e) {
            log.error("redis服务异常：", ExceptionUtils.getStackTrace(e));
        }

        return dlist;
    }

    /**
     * 判断当前id节点下是否有子节点
     * @param id
     * @return
     */
    private Boolean hasChildren(Long id){

        QueryWrapper<Dict> dictQueryWrapper = new QueryWrapper<>();
        dictQueryWrapper.eq("parent_id",id);

        Integer count = baseMapper.selectCount(dictQueryWrapper);
        if( count.intValue() > 0){
            return true;
        }
        return false;
    }



}
