package net.somehowsurviving.phantasystar.item.custom;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

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

        Level level = player.level();

        level.playSound(
                null, // null = play for everyone nearby
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.PLAYER_LEVELUP,
                SoundSource.PLAYERS,
                3.0f,
                1.0f
        );

        if (player.getMainHandItem() == stack) {
            player.setItemInHand(InteractionHand.MAIN_HAND, result);
        }
        else if (player.getOffhandItem() == stack) {
            player.setItemInHand(InteractionHand.OFF_HAND, result);
        }
    }
}
