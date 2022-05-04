package com.jcorpse.tgm3.dao;

import com.jcorpse.tgm3.dto.HyperTrain;

import java.util.List;

public interface TwitchDao<T> {

    boolean saveData( T Object);

    List<HyperTrain> getAllData();
}
