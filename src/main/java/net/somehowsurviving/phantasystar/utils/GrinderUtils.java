package net.somehowsurviving.phantasystar.utils;

import net.minecraft.world.item.ItemStack;

public class GrinderUtils {

    public static final String TAG_GRIND = "grindAmount";

    public static int getGrind(ItemStack stack) {
        return stack.getOrCreateTag().getInt(TAG_GRIND);
    }

    public static void setGrind(ItemStack stack, int amount) {
        stack.getOrCreateTag().putInt(TAG_GRIND, amount);
    }

    public static void addGrind(ItemStack stack, int amount, int max) {
        int current = getGrind(stack);
        int newAmount = Math.min(current + amount, max);
        setGrind(stack, newAmount);
    }
}
