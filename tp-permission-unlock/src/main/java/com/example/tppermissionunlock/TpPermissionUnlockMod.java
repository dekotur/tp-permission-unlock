package com.example.tppermissionunlock;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TpPermissionUnlockMod implements ModInitializer {
    public static final String MOD_ID = "tp-permission-unlock";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("TP Permission Unlock initialized.");
    }
}


