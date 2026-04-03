package net.somehowsurviving.phantasystar.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

import static net.somehowsurviving.phantasystar.utils.GrinderUtils.addGrind;
import static net.somehowsurviving.phantasystar.utils.GrinderUtils.getGrind;

public class GrinderItem extends Item {

    private final int grindValue;

    public GrinderItem(Properties pProperties, int grindValue) {
        super(pProperties);
        this.grindValue = grindValue;
    }

    public int getGrindValue() {
        return grindValue;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack grinderStack = player.getItemInHand(hand);
        ItemStack weaponStack = player.getMainHandItem();

        if (!(weaponStack.getItem() instanceof GrindableWeapon weapon)) {
            return InteractionResultHolder.pass(grinderStack);
        }

        int current = getGrind(weaponStack);
        int max = weapon.getMaxGrind(weaponStack);

        if (current >= max) {
            if (!level.isClientSide) {
                player.displayClientMessage(Component.literal("Max grinders applied."), true);
            }
            return InteractionResultHolder.fail(grinderStack);
        }

        int amount = this.grindValue;

        addGrind(weaponStack, amount, max);

        if (!player.getAbilities().instabuild) {
            grinderStack.shrink(1);
        }

        // Feedback
        level.playSound(null, player.blockPosition(),
                SoundEvents.ANVIL_USE, SoundSource.PLAYERS, 0.8F, 1.2F);

        return InteractionResultHolder.success(grinderStack);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        tooltipComponents.add(Component.translatable(this.getDescriptionId() + "tooltip"));
    }
}
