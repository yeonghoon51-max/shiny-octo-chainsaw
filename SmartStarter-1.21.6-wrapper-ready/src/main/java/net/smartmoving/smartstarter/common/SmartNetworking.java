package net.smartmoving.smartstarter.common;

import net.minecraft.util.Identifier;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ClientPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import io.netty.buffer.Unpooled;

public final class SmartNetworking {
    public static final Identifier CHANNEL_INPUT = Identifier.of("smartstarter","input");
    public static final byte MSG_TOGGLE_CRAWL = 1;
    public static final byte MSG_DODGE = 2;

    public static void sendToggleCrawlClient() {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeByte(MSG_TOGGLE_CRAWL);
        ClientPlayNetworking.send(CHANNEL_INPUT, buf);
    }
    public static void sendDodgeClient(float strafe) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeByte(MSG_DODGE);
        buf.writeFloat(strafe);
        ClientPlayNetworking.send(CHANNEL_INPUT, buf);
    }
}
