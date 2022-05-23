package com.jcorpse.tgm3.config;

public class Constant {
    public static final String TWITCH_CHANNEL = "tetristhegrandmaster3";
    public static final String TWITCH_OAUTH = System.getenv("Twitch_OAUTH");
    public static final String TWITCH_CHANNEL_ID = "47281189";


    public static final String DISCORD_CHANNEl = "812636716640501760";
    public static final String DISCORD_CHANNEl_TEST = "687712826978074675";
    public static final String DISCORD_TOKEN = System.getenv("Discord_TOKEN");

    public static final String TRAINS_COLLECTION_NAME="HyperTrains";
    public static final String VERSION = "2.0.3";

    public static final String CWB_TOKEN = System.getenv("CWB_TOKEN");

//    public static final String[] SOLT_EMOJIS = {"<:AMONsimle:978124168610594856>","<:AmonWeird:978124157168529438>","<:LadellAMon:978124125526720582>"};
    public static final String[] SOLT_EMOJIS = {"<:AMONsimle:>","<:AmonWeird:>","<:LadellAMon:>"};

    public static String getRandomEmojis(){
        return SOLT_EMOJIS[(int) (Math.random()*SOLT_EMOJIS.length)];
    }
}
