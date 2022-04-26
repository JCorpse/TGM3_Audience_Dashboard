package com.jcorpse.tgm3.dao.impl;


import com.jcorpse.tgm3.dao.TwitchDao;
import com.jcorpse.tgm3.dto.HyperTrain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class HyperTrainDaoImpl implements TwitchDao<HyperTrain> {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public boolean save(HyperTrain Object) {
        return mongoTemplate.save(Object, "HyperTrains") != null;
    }
}
