package net.somehowsurviving.phantasystar.item.custom;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
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
import java.util.function.Supplier;

public class KillCountTransformItem extends Item implements IKillTrackerItem, ICurioItem {

    private final float pAttackDamage;
    private final float pAttackSpeed;
    private final float pMovementSpeed;
    private final float pArmor;
    private final float pLuck;
    private final float pArmorPen;

    private final Supplier<Item> evolutionItem;
    private final int killsRequired;

    public KillCountTransformItem(Properties pProperties, float pAttackDamage, float pAttackSpeed, float pMovementSpeed, float pArmor, float pLuck, float pArmorPen, Supplier<Item> evolutionItem, int killsRequired) {
        super(pProperties);
        this.pAttackDamage = pAttackDamage;
        this.pAttackSpeed = pAttackSpeed;
        this.pMovementSpeed = pMovementSpeed;
        this.pArmor = pArmor;
        this.pLuck = pLuck;
        this.pArmorPen = pArmorPen;
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

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = LinkedHashMultimap.create();

        modifiers.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, "bonus_attack", pAttackDamage, AttributeModifier.Operation.ADDITION));
        modifiers.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, "bonus_attack_speed", pAttackSpeed, AttributeModifier.Operation.MULTIPLY_TOTAL));
        modifiers.put(EpicFightAttributes.ARMOR_NEGATION.get(), new AttributeModifier(uuid, "bonus_armor_pen", pArmorPen, AttributeModifier.Operation.ADDITION));
        modifiers.put(Attributes.ARMOR, new AttributeModifier(uuid, "bonus_armor", pArmor, AttributeModifier.Operation.ADDITION));
        modifiers.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, "bonus_move_speed", pMovementSpeed, AttributeModifier.Operation.MULTIPLY_TOTAL));
        modifiers.put(Attributes.LUCK, new AttributeModifier(uuid, "bonus_luck", pLuck, AttributeModifier.Operation.ADDITION));

        LivingEntity entity = slotContext.entity();
        if (entity instanceof Player player) {
        }

        return modifiers;
    }
}
