package net.somehowsurviving.phantasystar.item.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface IKillTrackerItem {

    int getKillsRequired();

    ItemStack getEvolutionResult(ItemStack original);

    default void onKill(ItemStack stack, Player player) {

        CompoundTag tag = stack.getOrCreateTag();
        int kills = tag.getInt("kills") + 1;
        tag.putInt("kills", kills);

        if (kills >= getKillsRequired()) {
            evolve(stack, player);
        }
    }

    default void evolve(ItemStack stack, Player player) {

        ItemStack result = getEvolutionResult(stack);

        if (player.getMainHandItem() == stack) {
            player.setItemInHand(InteractionHand.MAIN_HAND, result);
        }
        else if (player.getOffhandItem() == stack) {
            player.setItemInHand(InteractionHand.OFF_HAND, result);
        }
    }
}
