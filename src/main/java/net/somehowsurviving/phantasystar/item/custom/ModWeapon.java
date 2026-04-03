package net.somehowsurviving.phantasystar.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

import static net.somehowsurviving.phantasystar.utils.GrinderUtils.getGrind;

public class ModWeapon extends SwordItem implements GrindableWeapon {

    private static final UUID GRIND_MODIFIER_UUID = UUID.fromString("AF2BF3E0-1234-4F56-ABCD-9876543210FE");
    private final int maxGrind;

    public ModWeapon(Tier pTier, int attackDamage, float pAttackSpeedModifier, Properties pProperties, int maxGrind) {
        super(pTier, attackDamage, pAttackSpeedModifier, pProperties);
        this.maxGrind = maxGrind;
    }

    @Override
    public int getMaxGrind(ItemStack stack) {
        return this.maxGrind;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = super.getDefaultAttributeModifiers(slot);

        if (slot == EquipmentSlot.MAINHAND) {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
            builder.putAll(modifiers);

            float currentGrind = (float) getGrind(stack) / 10f;

            builder.put(Attributes.ATTACK_DAMAGE,
                    new AttributeModifier(
                            GRIND_MODIFIER_UUID,
                            "Grind bonus",
                            currentGrind,
                            AttributeModifier.Operation.ADDITION
                    ));

            return builder.build();
        }

        return modifiers;
    }

    @Override
    public Component getName(ItemStack stack) {

        Component baseName = super.getName(stack);

        int grind = getGrind(stack);
        if (grind > 0) {
            return Component.literal(baseName.getString() + " +" + grind);
        }

        return baseName;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<net.minecraft.network.chat.Component> pTooltipComponents, TooltipFlag pIsAdvanced) {

        String key = pStack.getItem().getDescriptionId();
        pTooltipComponents.add(Component.translatable(key + ".tooltip"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}