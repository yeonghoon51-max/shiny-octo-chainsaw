package net.smartmoving.smartstarter;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.Vec3d;

public final class MovementController {
    private MovementController(){}

    public static void initClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> onTick(client));
    }

    private static void onTick(MinecraftClient client) {
        ClientPlayerEntity p = client.player;
        if (p == null) return;

        boolean consuming = false;

        if (p.isSprinting() && p.isSneaking() && Keybinds.SLIDE_KEY.isPressed()) {
            if (StaminaManager.consume(SmartConfig.slideCostPerTick)) {
                consuming = true;
                Vec3d look = p.getRotationVec(1.0f).normalize();
                Vec3d horiz = new Vec3d(look.x, 0, look.z).normalize().multiply(SmartConfig.slideSpeedBoost);
                p.addVelocity(horiz.x, 0, horiz.z);
            }
        }
        if (p.isTouchingWater() && p.isSprinting()) {
            if (StaminaManager.consume(SmartConfig.swimCostPerTick)) {
                consuming = true;
                Vec3d look = p.getRotationVec(1.0f).normalize().multiply(SmartConfig.swimBoost);
                p.addVelocity(look.x, look.y * 0.5, look.z);
            }
        }
        StaminaManager.tickRegen(consuming);
    }
}
