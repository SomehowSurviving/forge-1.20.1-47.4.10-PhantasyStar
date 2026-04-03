package net.somehowsurviving.phantasystar.item.custom;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.somehowsurviving.phantasystar.client.GeoSwordRenderer;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.RenderUtils;
import net.minecraft.world.item.ItemStack;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import static net.somehowsurviving.phantasystar.utils.GrinderUtils.getGrind;

public class GeoSwordItem extends SwordItem implements GeoItem, GrindableWeapon {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private static final UUID GRIND_MODIFIER_UUID = UUID.fromString("BC5A6EF0-1234-4F56-ABCD-9876543210FE");
    private final int maxGrind;


    public GeoSwordItem(Tier tier, int attackDamage, float attackSpeed, Properties properties, int maxGrind) {
        super(tier, attackDamage, attackSpeed, properties);
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
            private GeoSwordRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if(this.renderer == null) {
                    renderer = new GeoSwordRenderer();
                }

                return this.renderer;
            }
        });
    }
}