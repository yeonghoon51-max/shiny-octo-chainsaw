package net.smartmoving.smartstarter.client;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.smartmoving.smartstarter.StaminaManager;

public final class HudOverlay {
    public static void init() {
        HudRenderCallback.EVENT.register(HudOverlay::onHud);
    }
    private static void onHud(DrawContext ctx, float tickDelta) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return;
        int x=10,y=10,w=100,h=8;
        float pct = StaminaManager.get()/100f;
        int fill = (int)(w*pct);
        ctx.fill(x-1,y-1,x+w+1,y+h+1,0x80000000);
        ctx.fill(x,y,x+fill,y+h,0xFF00FF00);
        ctx.drawText(mc.textRenderer, "Stamina", x, y-10, 0xFFFFFF, true);
    }
}
