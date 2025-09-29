package net.smartmoving.smartstarter;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public final class Keybinds {
    public static KeyBinding SLIDE_KEY;
    public static KeyBinding CLIMB_KEY;

    private Keybinds(){}

    public static void register() {
        SLIDE_KEY = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.smartstarter.slide",
            InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_C,
            "category.smartstarter.movement"
        ));
        CLIMB_KEY = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.smartstarter.climb",
            InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_V,
            "category.smartstarter.movement"
        ));
    }
}
