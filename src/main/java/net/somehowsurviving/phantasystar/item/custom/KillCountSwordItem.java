package net.somehowsurviving.phantasystar.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class KillCountSwordItem extends SwordItem implements IKillTrackerItem {

    private final Supplier<Item> evolutionItem;
    private final int killsRequired;

    public KillCountSwordItem(Tier pTier, int attackDamage, float pAttackSpeedModifier, Properties pProperties, Supplier<Item> evolutionItem, int killsRequired) {
        super(pTier, attackDamage, pAttackSpeedModifier, pProperties);
        this.evolutionItem = evolutionItem;
        this.killsRequired = killsRequired;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {

        String key = pStack.getItem().getDescriptionId();
        pTooltipComponents.add(Component.translatable(key + ".tooltip"));
        int kills = pStack.getOrCreateTag().getInt("kills");

        pTooltipComponents.add(Component.literal("Enemies killed: " + kills + "/" + killsRequired).withStyle(ChatFormatting.DARK_RED));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public int getKillsRequired() {
        return killsRequired;
    }

    @Override
    public ItemStack getEvolutionResult(ItemStack original) {
        return new ItemStack(evolutionItem.get());
    }

}
