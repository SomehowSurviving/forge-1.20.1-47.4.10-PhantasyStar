package net.somehowsurviving.phantasystar.item.custom;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class StatusHealItem extends Item {

    private MobEffect effect;

    public StatusHealItem(Properties pProperties, MobEffect effect) {
        super(pProperties);
        this.effect = effect;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if(!level.isClientSide) {
            if (player.getTicksFrozen() <= 0)

                if(player.hasEffect(effect)) {
                    player.removeEffect(effect);
                    player.setTicksFrozen(0);

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
