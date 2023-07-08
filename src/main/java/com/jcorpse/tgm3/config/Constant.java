package com.jcorpse.tgm3.config;

public class Constant {
    public static final String TWITCH_CHANNEL = "tetristhegrandmaster3";
    public static final String TWITCH_OAUTH = System.getenv("Twitch_OAUTH");
    public static final String TWITCH_CHANNEL_ID = "47281189";


    public static final String DISCORD_CHANNEl = "812636716640501760";
    public static final String DISCORD_CHANNEl_TEST = "687712826978074675";
    public static final String DISCORD_TOKEN = System.getenv("Discord_TOKEN");

    public static final String TRAINS_COLLECTION_NAME = "HyperTrains";
    public static final String VERSION = "2.0.4";

    public static final String[] SOLT_EMOJIS = {"<:AMONsimle:807278771991609387>", "<:AmonWeird:809889506089893888>", "<:LadellAMon:867426295784538153>", "<:AMONcry2:807277761973714974>", "<:A_:823552070203867167>", "<:__:906269245074182224>", "<:FormerHypeTrain:806823733306195978>", "<:CurrentHypeTrain:806826997514502194>"};

    public static String[] getRandomEmojis(int count) {
        String[] results = new String[count];
        for (int i = 0; i < count; i++) {
            results[i] = SOLT_EMOJIS[(int) (Math.random() * SOLT_EMOJIS.length)];
        }
        return results;
    }
}
