package net.somehowsurviving.phantasystar.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.somehowsurviving.phantasystar.utils.WeaponUtils;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class SpecialRollModWeapon extends SwordItem {

    public SpecialRollModWeapon(Tier pTier, int attackDamage, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, attackDamage, pAttackSpeedModifier, pProperties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (level.isClientSide) return;

        CompoundTag tag = stack.getOrCreateTag();

        if (!tag.getBoolean("rolled")) {
            WeaponUtils.rollHiddenSpecial(stack);
            tag.putBoolean("rolled", true);
        }
    }


    @Override
    public Component getName(ItemStack stack) {

        Component baseName = super.getName(stack);

        CompoundTag tag = stack.getTag();

        if (tag != null && tag.contains("identified")) {

            boolean identified = tag.getBoolean("identified");

            if (!identified) {
                return Component.literal("??? ").append(baseName).withStyle(ChatFormatting.AQUA);
            }

            String special = tag.getString("special");

            if (!special.isEmpty()) {
                return Component.literal(capitalize(special) + " ").append(baseName).withStyle(ChatFormatting.WHITE);
            }
        }

        return baseName;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {

        String key = pStack.getItem().getDescriptionId();
        pTooltipComponents.add(Component.translatable(key + ".tooltip"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    private String capitalize(String text) {
        if (text == null || text.isEmpty()) return text;
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }
}