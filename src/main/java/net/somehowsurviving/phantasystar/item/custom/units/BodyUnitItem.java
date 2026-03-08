package net.somehowsurviving.phantasystar.item.custom.units;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.UUID;

public class BodyUnitItem extends Item implements ICurioItem {

    private final float ArmorAmount;
    private final float ArmorToughnessAmount;

    public BodyUnitItem(Properties pProperties, float ArmorAmount, float ArmorToughnessAmount) {
        super(pProperties);
        this.ArmorAmount = ArmorAmount;
        this.ArmorToughnessAmount = ArmorToughnessAmount;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = LinkedHashMultimap.create();

        modifiers.put(Attributes.ARMOR, new AttributeModifier(uuid, "bonus_armor", ArmorAmount, AttributeModifier.Operation.ADDITION));
        modifiers.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "bonus_armor_toughness", ArmorToughnessAmount, AttributeModifier.Operation.ADDITION));

        LivingEntity entity = slotContext.entity();
        if (entity instanceof Player player) {
        }

        return modifiers;
    }
}
