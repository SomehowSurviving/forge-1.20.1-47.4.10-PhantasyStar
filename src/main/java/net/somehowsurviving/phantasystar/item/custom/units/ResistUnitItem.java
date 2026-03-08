package net.somehowsurviving.phantasystar.item.custom.units;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.UUID;

public class ResistUnitItem extends Item implements ICurioItem {

    private final float resistAmount;

    public ResistUnitItem(Properties pProperties, float resistAmount) {
        super(pProperties);
        this.resistAmount = resistAmount;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = LinkedHashMultimap.create();

        modifiers.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "bonus_knockback_resist", resistAmount, AttributeModifier.Operation.MULTIPLY_TOTAL));

        LivingEntity entity = slotContext.entity();
        if (entity instanceof Player player) {
        }

        return modifiers;
    }
}
