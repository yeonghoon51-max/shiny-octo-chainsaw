package net.smartmoving.smartstarter;

public final class StaminaManager {
    private static float stamina = SmartConfig.maxStamina;
    public static float get() { return stamina; }
    public static void tickRegen(boolean consuming) {
        if (!consuming) stamina = Math.min(SmartConfig.maxStamina, stamina + SmartConfig.regenPerTick);
    }
    public static boolean consume(float amount) {
        if (stamina >= amount) { stamina -= amount; return true; }
        return false;
    }
}
