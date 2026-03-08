package net.somehowsurviving.phantasystar.item.custom;


import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
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
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.somehowsurviving.phantasystar.client.GeoMagItemRenderer;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.RenderUtils;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

public class MagItem extends Item implements GeoItem, ICurioItem {


    private final float pAttackDamage;
    private final float pAttackSpeed;
    private final float pMovementSpeed;
    private final float pArmor;
    private final float pLuck;

    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public MagItem(Properties properties, float pAttackDamage, float pAttackSpeed, float pArmor, float pMovementSpeed, float pLuck) {
        super(new Properties().stacksTo(1).defaultDurability(0));
        this.pAttackDamage = pAttackDamage;
        this.pAttackSpeed = pAttackSpeed;
        this.pArmor = pArmor;
        this.pMovementSpeed = pMovementSpeed;
        this.pLuck = pLuck;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {

        String key = pStack.getItem().getDescriptionId();
        pTooltipComponents.add(Component.translatable(key + ".tooltip"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = LinkedHashMultimap.create();

        // +3 Attack Damage
        modifiers.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, "bonus_attack", pAttackDamage, AttributeModifier.Operation.ADDITION));
        modifiers.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, "bonus_attack_speed", pAttackSpeed, AttributeModifier.Operation.MULTIPLY_TOTAL));
        modifiers.put(Attributes.ARMOR, new AttributeModifier(uuid, "bonus_armor", pArmor, AttributeModifier.Operation.ADDITION));
        modifiers.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, "bonus_move_speed", pMovementSpeed, AttributeModifier.Operation.MULTIPLY_TOTAL));
        modifiers.put(Attributes.LUCK, new AttributeModifier(uuid, "bonus_luck", pLuck, AttributeModifier.Operation.ADDITION));

        // Optional: conditional bonus
        LivingEntity entity = slotContext.entity();
        if (entity instanceof Player player) {
        }

        return modifiers;
    }

    // Geckolib Animatable
    private PlayState predicate(AnimationState animationState) {
        animationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object itemStack) {
        return RenderUtils.getCurrentTick();
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private GeoMagItemRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if(this.renderer == null) {
                    renderer = new GeoMagItemRenderer();
                }

                return this.renderer;
            }
        });
    }
}
