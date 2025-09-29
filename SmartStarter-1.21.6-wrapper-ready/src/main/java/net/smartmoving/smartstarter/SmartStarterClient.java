package net.smartmoving.smartstarter;

import net.fabricmc.api.ClientModInitializer;
import net.smartmoving.smartstarter.client.ClientControls;
import net.smartmoving.smartstarter.client.HudOverlay;

public class SmartStarterClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Keybinds.register();
        MovementController.initClient();
        ClientControls.init();
        HudOverlay.init();
        SmartStarter.LOGGER.info("[SmartStarter] Client movement + UI ready.");
    }
}
