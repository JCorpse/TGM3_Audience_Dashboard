package com.jcorpse.tgm3.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class HyperTrain implements Serializable {
    private String TrainID;
    private String StartedAt;
    private String ExpiresAt;
    private String EndAt;
    private Integer LastLevel;
    private Integer Progress;
    private Integer Goal;
    private String Percent;
}
