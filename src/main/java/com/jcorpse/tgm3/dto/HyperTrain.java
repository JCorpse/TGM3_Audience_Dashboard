package com.jcorpse.tgm3.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
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
