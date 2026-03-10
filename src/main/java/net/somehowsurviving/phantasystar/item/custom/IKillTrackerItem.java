package net.somehowsurviving.phantasystar.item.custom;

import net.minecraft.world.item.ItemStack;

public interface IKillTrackerItem {

    int getKillsRequired();

    ItemStack getEvolutionResult(ItemStack original);
}
