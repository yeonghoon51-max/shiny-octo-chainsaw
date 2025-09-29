package net.smartmoving.smartstarter.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.smartmoving.smartstarter.Keybinds;
import net.smartmoving.smartstarter.SmartConfig;
import net.smartmoving.smartstarter.StaminaManager;
import net.smartmoving.smartstarter.common.SmartNetworking;
import net.minecraft.util.math.Vec3d;

public final class ClientControls {
    private static long lastDodgeMs = 0;
    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(ClientControls::onTick);
    }
    private static void onTick(MinecraftClient client) {
        ClientPlayerEntity p = client.player;
        if (p == null) return;

        boolean tryingDodge = (p.input.sneaking && Math.abs(p.input.movementSideways) > 0.7f);
        long now = System.currentTimeMillis();
        if (tryingDodge && now - lastDodgeMs > 700) {
            lastDodgeMs = now;
            float strafe = Math.signum(p.input.movementSideways);
            SmartNetworking.sendDodgeClient(strafe);
        }
        if (Keybinds.CLIMB_KEY.wasPressed()) {
            SmartNetworking.sendToggleCrawlClient();
        }
    }
}
