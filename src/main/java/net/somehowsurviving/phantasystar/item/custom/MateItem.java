package net.somehowsurviving.phantasystar.item.custom;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MateItem extends Item {

    private final float healAmount;

    public MateItem(Properties pProperties, float healAmount) {
        super(pProperties);
        this.healAmount = healAmount;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if(!level.isClientSide) {
            if(player.getHealth() < player.getMaxHealth() && player.getTicksFrozen() <= 0) {
                player.heal(healAmount);

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
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }
}
