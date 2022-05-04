package com.jcorpse.tgm3.dao.impl;


import com.jcorpse.tgm3.config.Constant;
import com.jcorpse.tgm3.dao.TwitchDao;
import com.jcorpse.tgm3.dto.HyperTrain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class HyperTrainDaoImpl implements TwitchDao<HyperTrain> {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public boolean saveData(HyperTrain Object) {
        return mongoTemplate.save(Object, Constant.TRAINS_COLLECTION_NAME) != null;
    }

    @Override
    public List<HyperTrain> getAllData() {
        return mongoTemplate.findAll(HyperTrain.class,Constant.TRAINS_COLLECTION_NAME);
    }


}
