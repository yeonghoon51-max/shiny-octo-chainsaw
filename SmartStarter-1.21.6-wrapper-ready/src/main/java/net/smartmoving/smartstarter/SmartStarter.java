package net.smartmoving.smartstarter;

import net.fabricmc.api.ModInitializer;
import net.smartmoving.smartstarter.server.ServerMovementHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmartStarter implements ModInitializer {
    public static final String MOD_ID = "smartstarter";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ServerMovementHandler.init();
        LOGGER.info("[SmartStarter] Initialized (server/common).");
    }
}
