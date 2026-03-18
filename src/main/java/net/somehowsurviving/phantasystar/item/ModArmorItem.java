package net.somehowsurviving.phantasystar.item;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class ModArmorItem extends ArmorItem {

    private final int armorValue;
    private static final UUID ARMOR_SLOT_UUID =
            UUID.fromString("aaaaaaaa-0d8b-4a87-9c07-7c0b5f27d7e1");
    private static final UUID CHARM_SLOT_UUID =
            UUID.fromString("bbbbbbbb-0d8b-4a87-9c07-7c0b5f27d7e1");
    private static final Random RANDOM = new Random();

    public ModArmorItem(ArmorMaterial material, Type type, Properties properties, int armorValue) {
        super(material, type, properties);
        this.armorValue = armorValue;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(
            EquipmentSlot slot, ItemStack stack) {

        if (slot != EquipmentSlot.CHEST) {
            return super.getAttributeModifiers(slot, stack);
        }

        Multimap<Attribute, AttributeModifier> modifiers = LinkedHashMultimap.create();

        modifiers.put(
                Attributes.ARMOR,
                new AttributeModifier(
                        ARMOR_SLOT_UUID,
                        "bonus_armor",
                        armorValue,
                        AttributeModifier.Operation.ADDITION
                )
        );

        CompoundTag tag = stack.getTag();
        int slots = stack.getTag().getInt("charm_slots");

        CuriosApi.getCuriosHelper().addSlotModifier(
                modifiers,              // <- must be a mutable Multimap
                "charm",
                CHARM_SLOT_UUID,
                slots,
                AttributeModifier.Operation.ADDITION
        );

        // Optionally add vanilla armor attributes here if needed

        return modifiers;
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        ResourceLocation key = BuiltInRegistries.ITEM.getKey(stack.getItem());
        String path = key.getPath();
        return "phantasystar:textures/models/armor/" + path + "_layer_1.png";
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (level.isClientSide) return;

        CompoundTag tag = stack.getOrCreateTag();

        if (!tag.contains("charm_slots")) {
            int slots = RANDOM.nextInt(4); // 1–4 (or whatever you want)
            tag.putInt("charm_slots", slots);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
        String key = stack.getItem().getDescriptionId();
        CompoundTag tag = stack.getTag();
        int slots = stack.getTag().getInt("charm_slots");
        tooltip.add(Component.translatable(key + ".tooltip"));
        tooltip.add(Component.translatable("item.phantasystar.armorSlots").append(": " + slots));
        super.appendHoverText(stack, level, tooltip, flag);
    }
}
