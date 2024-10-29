package com.iafenvoy.mdc;

import com.google.gson.JsonParser;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Identifier;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class MDCConfig {
    public static final Codec<Map<Identifier, MusicConfig>> CODEC = Codec.unboundedMap(Identifier.CODEC, RecordCodecBuilder.create(i -> i.group(
            Codec.BOOL.optionalFieldOf("enable", true).forGetter(MusicConfig::enable),
            Codec.INT.optionalFieldOf("minDelay", 0).forGetter(MusicConfig::minDelay),
            Codec.INT.optionalFieldOf("maxDelay", 0).forGetter(MusicConfig::maxDelay)
    ).apply(i, MusicConfig::new)));
    public static final String CONFIG_PATH = "./config/" + MusicDelayController.MOD_ID + ".json";
    public static final Map<Identifier, MusicConfig> INSTANCE;

    static {
        Map<Identifier, MusicConfig> map;
        try {
            map = CODEC.parse(JsonOps.INSTANCE, JsonParser.parseReader(new FileReader(CONFIG_PATH))).getOrThrow(true, MusicDelayController.LOGGER::error);
        } catch (Exception e) {
            map = new HashMap<>();
            MusicDelayController.LOGGER.error("Failed to load config file", e);
            try {
                FileUtils.write(new File(CONFIG_PATH), CODEC.encodeStart(JsonOps.INSTANCE, map).getOrThrow(true, MusicDelayController.LOGGER::error).toString(), StandardCharsets.UTF_8);
            } catch (Exception ex) {
                MusicDelayController.LOGGER.error("Failed to create config file", ex);
            }
        }
        INSTANCE = map;
    }

    public record MusicConfig(boolean enable, int minDelay, int maxDelay) {
    }
}
