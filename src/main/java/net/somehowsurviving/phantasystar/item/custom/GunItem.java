package net.somehowsurviving.phantasystar.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.registries.RegistryObject;
import net.somehowsurviving.phantasystar.client.GeoGunRenderer;
import net.somehowsurviving.phantasystar.entities.BulletEntity;
import net.somehowsurviving.phantasystar.entities.LauncherProjectileEntity;
import net.minecraft.world.entity.EntityType;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.RenderUtils;

import java.util.List;
import java.util.function.Consumer;

public class GunItem extends Item implements GeoItem {

    private final GunType gunType;
    private final float baseDamage;
    private final RegistryObject<EntityType<BulletEntity>> bulletType;
    private final RegistryObject<EntityType<LauncherProjectileEntity>> rocketType;
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public GunItem(Properties properties, GunType gunType, float baseDamage, RegistryObject<EntityType<BulletEntity>> bulletType, RegistryObject<EntityType<LauncherProjectileEntity>> rocketType) {
        super(properties);
        this.gunType = gunType;
        this.baseDamage = baseDamage;
        this.bulletType = bulletType;
        this.rocketType = rocketType;
    }

    public GunType getGunType() {
        return gunType;
    }

    public float getWeaponDamage() {
        return baseDamage;
    }

    public EntityType<? extends BulletEntity> getBulletType() {
        return bulletType.get();
    }
    public EntityType<? extends LauncherProjectileEntity> getRocketType() {
        return rocketType.get();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        if (slot == EquipmentSlot.MAINHAND) {
            builder.put(
                    Attributes.ATTACK_DAMAGE,
                    new AttributeModifier("Gun damage", baseDamage, AttributeModifier.Operation.ADDITION)
            );
        }
        return builder.build();
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            shoot(level, player, stack);
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    private void shoot(Level level, Player player, ItemStack stack) {
        switch (gunType) {
            case HANDGUN -> fireHandgun(level, player, stack);
            case RIFLE -> fireRifle(level, player, stack);
            case MECHGUN -> fireMechgun(level, player, stack);
            case SHOTGUN -> fireShotgun(level, player, stack);
            case LAUNCHER -> fireLauncher(level, player, stack);
        }
        double atkSpeed = Math.max(0.1, player.getAttribute(Attributes.ATTACK_SPEED).getValue());

        double ratio = switch (gunType) {
            case HANDGUN -> 44.0;
            case RIFLE -> 48.0;
            case MECHGUN -> 32.0;
            case SHOTGUN -> 72.0;
            case LAUNCHER -> 80.0;
        };

        int cooldown = (int) Math.round(ratio / atkSpeed);
        player.getCooldowns().addCooldown(this, cooldown);
    }

    private float getPlayerAttackBonuses(Player player) {
        double bonus = 0;

        for (AttributeModifier mod : player.getAttribute(Attributes.ATTACK_DAMAGE).getModifiers()) {
            if (!mod.getName().equals("Gun damage")) { // ignore this gun
                bonus += mod.getAmount();
            }
        }

        return (float) bonus;
    }

    private void fireHandgun(Level level, Player player, ItemStack stack) {
        BulletEntity bullet = new BulletEntity(this.bulletType.get(), level);

        bullet.setOwner(player);
        bullet.setWeapon(stack.copy());

        float finalDamage = getWeaponDamage() + getPlayerAttackBonuses(player);
        bullet.setDamage(finalDamage);

        double forwardOffset = 0.9;
        double rightOffset = -0.2;
        double upOffset = -0.1;

        float yawRad = (float) Math.toRadians(player.getYRot());
        float pitchRad = (float) Math.toRadians(player.getXRot());

        double dx = -Math.sin(yawRad) * Math.cos(pitchRad) * forwardOffset;
        double dy = -Math.sin(pitchRad) * forwardOffset + upOffset;
        double dz = Math.cos(yawRad) * Math.cos(pitchRad) * forwardOffset;

        dx += Math.cos(yawRad) * rightOffset;
        dz += Math.sin(yawRad) * rightOffset;

        bullet.setPos(player.getX() + dx, player.getEyeY() + dy, player.getZ() + dz);
        bullet.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F, 1.0F);

        level.addFreshEntity(bullet);
    }

    private void fireRifle(Level level, Player player, ItemStack stack) {
        BulletEntity bullet = new BulletEntity(this.bulletType.get(), level);

        bullet.setOwner(player);
        bullet.setWeapon(stack.copy());

        float finalDamage = getWeaponDamage() + getPlayerAttackBonuses(player);
        bullet.setDamage(finalDamage);

        double forwardOffset = 0.5;
        double rightOffset = 0.2;
        double upOffset = -0.1;

        float yawRad = (float) Math.toRadians(player.getYRot());
        float pitchRad = (float) Math.toRadians(player.getXRot());

        double dx = -Math.sin(yawRad) * Math.cos(pitchRad) * forwardOffset;
        double dy = -Math.sin(pitchRad) * forwardOffset + upOffset;
        double dz = Math.cos(yawRad) * Math.cos(pitchRad) * forwardOffset;

        dx += Math.cos(yawRad) * rightOffset;
        dz += Math.sin(yawRad) * rightOffset;

        bullet.setPos(player.getX() + dx, player.getEyeY() + dy, player.getZ() + dz);
        bullet.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 3.0F, 0.5F);

        level.addFreshEntity(bullet);
    }

    private void fireMechgun(Level level, Player player, ItemStack stack) {
        CompoundTag data = player.getPersistentData();

        data.putInt("burst_shots", 3);
        data.putInt("burst_timer", 0);
        data.putInt("burst_delay", 6);

        // Save the weapon AND the bullet type key
        ItemStack copy = stack.copy();
        CompoundTag nbt = copy.save(new CompoundTag());

        ResourceLocation bulletKey = BuiltInRegistries.ENTITY_TYPE.getKey(this.getBulletType());
        nbt.putString("bullet_type", bulletKey.toString());

        data.put("burst_weapon", nbt);

        player.getCooldowns().addCooldown(this, 6); // 3 shots * 2 delay
    }

    private void fireShotgun(Level level, Player player, ItemStack stack) {
        int pelletCount = 5;
        float totalArc = 60f;

        float step = totalArc / (pelletCount - 1);
        float start = -totalArc / 2f;

        for (int i = 0; i < pelletCount; i++) {

            float yawOffset = start + (i * step);

            BulletEntity bullet = new BulletEntity(this.bulletType.get(), level);

            bullet.setOwner(player);
            bullet.setWeapon(stack.copy());

            float finalDamage = getWeaponDamage() + getPlayerAttackBonuses(player);
            bullet.setDamage(finalDamage);

            double forwardOffset = 0.5;
            double rightOffset = 0.2;
            double upOffset = -0.1;

            float yawRad = (float) Math.toRadians(player.getYRot());
            float pitchRad = (float) Math.toRadians(player.getXRot());

            double dx = -Math.sin(yawRad) * Math.cos(pitchRad) * forwardOffset;
            double dy = -Math.sin(pitchRad) * forwardOffset + upOffset;
            double dz = Math.cos(yawRad) * Math.cos(pitchRad) * forwardOffset;

            dx += Math.cos(yawRad) * rightOffset;
            dz += Math.sin(yawRad) * rightOffset;

            bullet.setPos(player.getX() + dx, player.getEyeY() + dy, player.getZ() + dz);

            bullet.shootFromRotation(
                    player,
                    player.getXRot(),
                    player.getYRot() + yawOffset,
                    0.0F,
                    2.0F,
                    0.0F
            );

            level.addFreshEntity(bullet);
        }
    }
    private void fireLauncher(Level level, Player player, ItemStack stack) {
        LauncherProjectileEntity rocket = new LauncherProjectileEntity(this.rocketType.get(), level);

        rocket.setOwner(player);
        rocket.setWeapon(stack.copy());

        float finalDamage = getWeaponDamage() + getPlayerAttackBonuses(player);
        rocket.setDamage(finalDamage);

        double forwardOffset = 1.0;
        double rightOffset = -0.2;
        double upOffset = -0.1;

        float yawRad = (float) Math.toRadians(player.getYRot());
        float pitchRad = (float) Math.toRadians(player.getXRot());

        double dx = -Math.sin(yawRad) * Math.cos(pitchRad) * forwardOffset;
        double dy = -Math.sin(pitchRad) * forwardOffset + upOffset;
        double dz = Math.cos(yawRad) * Math.cos(pitchRad) * forwardOffset;

        dx += Math.cos(yawRad) * rightOffset;
        dz += Math.sin(yawRad) * rightOffset;

        rocket.setPos(player.getX() + dx, player.getEyeY() + dy, player.getZ() + dz);

        rocket.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 0.5F);

        level.addFreshEntity(rocket);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {

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
            private GeoGunRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if(this.renderer == null) {
                    renderer = new GeoGunRenderer();
                }

                return this.renderer;
            }
        });
    }
}
