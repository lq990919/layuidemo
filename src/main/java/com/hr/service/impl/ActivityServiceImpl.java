package com.hr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hr.mapper.ActivityMapper;
import com.hr.po.Activity;
import com.hr.service.ActivityService;
import org.springframework.stereotype.Service;

/**
 * @author lq
 * @date 2020-11-07 - 15:05
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {
}
