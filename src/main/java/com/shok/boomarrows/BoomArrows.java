package com.shok.boomarrows;

import com.shok.boomarrows.registry.ModEntities;
import com.shok.boomarrows.registry.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoomArrows implements ModInitializer {
    public static final String MOD_ID = "boomarrows";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModEntities.register();
        ModItems.register();
        LOGGER.info("BoomArrows mod initialized!");
    }
}
