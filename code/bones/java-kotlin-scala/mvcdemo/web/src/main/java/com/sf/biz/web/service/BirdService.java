package com.sf.biz.web.service;

import com.sf.biz.web.entity.Bird;
import com.sf.biz.web.mapper.BirdDynamicSqlSupport;
import com.sf.biz.web.mapper.BirdMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BirdService {
    @Autowired
    BirdMapper birdMapper;

    public Bird selectBirdById(int id){
        return birdMapper.selectByPrimaryKey(id).get();
    }
}
