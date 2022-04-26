package com.jcorpse.tgm3.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;

public class Constant {
    public static final String TWITCH_CHANNEL = "tetristhegrandmaster3";
    public static final String TWITCH_OAUTH = System.getenv("Twitch_OAUTH");
    public static final String TWITCH_CHANNEL_ID = "47281189";

//    public static final String TWITCH_CHANNEL = "tsm_imperialhal";
//    public static final String TWITCH_CHANNEL_ID = "146922206";


    public static final String DISCORD_CHANNEl = "812636716640501760";
    //    public static final String DISCORD_CHANNEl = "687712826978074675";
    public static final String DISCORD_TOKEN = System.getenv("Discord_TOKEN");
}
