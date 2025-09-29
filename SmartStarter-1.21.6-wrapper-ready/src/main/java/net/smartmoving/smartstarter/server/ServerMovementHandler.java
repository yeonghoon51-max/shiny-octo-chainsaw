package net.smartmoving.smartstarter.server;

import net.smartmoving.smartstarter.SmartConfig;
import net.smartmoving.smartstarter.StaminaManager;
import net.smartmoving.smartstarter.common.SmartNetworking;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.EntityPose;

public final class ServerMovementHandler {
    public static void init() {
        ServerTickEvents.END_SERVER_TICK.register(ServerMovementHandler::onTick);
        SmartNetworking.registerServerReceivers((code, buf) -> {
            // Simple placeholder: real player binding handled via mixin
        });
    }
    private static void onTick(MinecraftServer server) {
        for (ServerPlayerEntity p : server.getPlayerManager().getPlayerList()) {
            StaminaManager.tickRegen(false);
            Vec3d v = p.getVelocity();
            double max = 0.6;
            double s = v.horizontalLength();
            if (s > max) {
                double scale = max / s;
                p.setVelocity(v.x*scale, v.y, v.z*scale);
                p.velocityDirty = true;
            }
            if (!p.getWorld().isSpaceEmpty(p, p.getBoundingBox().stretch(0, 1.2, 0))) {
                p.setPose(EntityPose.SWIMMING);
            }
        }
    }
}
