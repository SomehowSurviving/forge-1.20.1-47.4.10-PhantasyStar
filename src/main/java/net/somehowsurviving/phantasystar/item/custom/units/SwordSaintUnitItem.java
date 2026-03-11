package net.somehowsurviving.phantasystar.item.custom.units;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import yesman.epicfight.world.entity.ai.attribute.EpicFightAttributes;

import java.util.List;
import java.util.UUID;

public class SwordSaintUnitItem extends Item implements ICurioItem {

    public SwordSaintUnitItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("item.phantasystar.proof_of_sword_saint.tooltip"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = LinkedHashMultimap.create();

        modifiers.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, "bonus_shred", 0.15f, AttributeModifier.Operation.MULTIPLY_TOTAL));
        modifiers.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, "bonus_move_speed", 0.3f, AttributeModifier.Operation.MULTIPLY_TOTAL));
        modifiers.put(Attributes.ARMOR, new AttributeModifier(uuid, "reduced_armor", -0.2f, AttributeModifier.Operation.MULTIPLY_TOTAL));
        modifiers.put(EpicFightAttributes.ARMOR_NEGATION.get(), new AttributeModifier(uuid, "bonus_shred", 0.2f, AttributeModifier.Operation.MULTIPLY_TOTAL));

        LivingEntity entity = slotContext.entity();
        if (entity instanceof Player player) {
        }

        return modifiers;
    }

}
