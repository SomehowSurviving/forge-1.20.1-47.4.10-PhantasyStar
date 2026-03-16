package net.somehowsurviving.phantasystar.item.custom;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class StarAtomizerItem extends Item {

    public StarAtomizerItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if(!level.isClientSide) {
            if (player.getTicksFrozen() <= 0) {
                player.heal(player.getMaxHealth());
                player.getFoodData().setFoodLevel(20);
                player.getFoodData().setSaturation(20);
                player.removeEffect(MobEffects.POISON);
                player.removeEffect(MobEffects.DIG_SLOWDOWN);
            }

            if(!player.getAbilities().instabuild) {
                stack.shrink(1);
            }

            level.playSound(
                        null,
                        player.getX(),
                        player.getY(),
                        player.getZ(),
                        SoundEvents.AMETHYST_CLUSTER_HIT,
                        SoundSource.PLAYERS,
                        1.0F,
                        1.0F
            );
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }
}
