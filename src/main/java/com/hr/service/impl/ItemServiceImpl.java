package com.hr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hr.mapper.ItemMapper;
import com.hr.po.Item;
import com.hr.service.ItemService;
import org.springframework.stereotype.Service;

/**
 * @author lq
 * @date 2020-11-07 - 15:05
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper,Item> implements ItemService {
}
