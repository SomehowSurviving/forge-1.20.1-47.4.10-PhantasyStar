package net.somehowsurviving.phantasystar.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModWeapon extends SwordItem {

    private final float procChance;
    private final float baseDamage;

    public ModWeapon(Tier pTier, int attackDamage, float pAttackSpeedModifier, Properties pProperties, float procChance) {
        super(pTier, attackDamage, pAttackSpeedModifier, pProperties);
        this.procChance = procChance;
        this.baseDamage = attackDamage;
    }

    public double getProcChance() {
        return procChance;
    }

    public int getAttackDamage() {
        return (int) baseDamage;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<net.minecraft.network.chat.Component> pTooltipComponents, TooltipFlag pIsAdvanced) {

        String key = pStack.getItem().getDescriptionId();
        pTooltipComponents.add(Component.translatable(key + ".tooltip"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}