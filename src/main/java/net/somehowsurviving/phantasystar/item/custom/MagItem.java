package net.somehowsurviving.phantasystar.item.custom;


import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.somehowsurviving.phantasystar.client.GeoMagItemRenderer;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.RenderUtils;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.UUID;
import java.util.function.Consumer;

public class MagItem extends Item implements GeoItem, ICurioItem {


    private final float pAttackDamage;
    private final float pAttackSpeed;
    private final float pMovementSpeed;
    private final float pArmor;
    private final float pLuck;
    public static final String PHOTON_CHARGE = "PhotonCharge";

    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public MagItem(Properties properties, float pAttackDamage, float pAttackSpeed, float pArmor, float pMovementSpeed, float pLuck) {
        super(properties);
        this.pAttackDamage = pAttackDamage;
        this.pAttackSpeed = pAttackSpeed;
        this.pArmor = pArmor;
        this.pMovementSpeed = pMovementSpeed;
        this.pLuck = pLuck;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = LinkedHashMultimap.create();

        modifiers.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, "bonus_attack", pAttackDamage, AttributeModifier.Operation.ADDITION));
        modifiers.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, "bonus_attack_speed", pAttackSpeed, AttributeModifier.Operation.MULTIPLY_TOTAL));
        modifiers.put(Attributes.ARMOR, new AttributeModifier(uuid, "bonus_armor", pArmor, AttributeModifier.Operation.ADDITION));
        modifiers.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, "bonus_move_speed", pMovementSpeed, AttributeModifier.Operation.MULTIPLY_TOTAL));
        modifiers.put(Attributes.LUCK, new AttributeModifier(uuid, "bonus_luck", pLuck, AttributeModifier.Operation.ADDITION));

        return modifiers;
    }

    public void onPlayerDamaged(Player player, ItemStack stack, LivingHurtEvent event) {

        int gain = 5 + player.getRandom().nextInt(5); // 3–5
        addPhotonCharge(stack, gain);

        int charge = getPhotonCharge(stack);

        if (charge >= 100) {
            triggerPhotonBlast(player, stack);

            setPhotonCharge(stack, 0);
        }
    }

    private void triggerPhotonBlast(Player player, ItemStack stack) {

        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 1));
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 1));
        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 1));

        player.level().playSound(null, player.blockPosition(),
                SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 1.0F, 1.0F);

        ((ServerLevel) player.level()).sendParticles(
                ParticleTypes.END_ROD,
                player.getX(), player.getY() + 1, player.getZ(),
                20, 0.5, 0.5, 0.5, 0.1
        );
    }

    public static int getPhotonCharge(ItemStack stack) {
        return stack.getOrCreateTag().getInt(PHOTON_CHARGE);
    }

    public static void setPhotonCharge(ItemStack stack, int value) {
        stack.getOrCreateTag().putInt(PHOTON_CHARGE, value);
    }

    public static void addPhotonCharge(ItemStack stack, int amount) {
        CompoundTag tag = stack.getOrCreateTag();
        int current = tag.getInt(PHOTON_CHARGE);
        tag.putInt(PHOTON_CHARGE, Math.min(current + amount, 100));
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
