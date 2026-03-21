package net.somehowsurviving.phantasystar.entities;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.RenderUtils;

public class LauncherProjectileEntity extends AbstractArrow implements GeoAnimatable {

    private float damage;
    private ItemStack weapon;
    private int pierceCount = 0; // How many entities hit so far
    private final int maxPierce = 5;
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public LauncherProjectileEntity(EntityType<? extends LauncherProjectileEntity> type, Level level) {
        super(type, level);
        this.pickup = Pickup.DISALLOWED;
        this.setNoGravity(true); // rockets can arc
    }

    public void setDamage(float damage) { this.damage = damage; }
    public float getDamage() { return damage; }
    public void setWeapon(ItemStack weapon) { this.weapon = weapon; }
    public ItemStack getWeapon() { return weapon; }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        Entity target = result.getEntity();

        if (target instanceof LivingEntity living && getOwner() instanceof Player player) {
            living.hurt(level().damageSources().playerAttack(player), damage);

            pierceCount++;
            if (pierceCount >= maxPierce) {
                discard();
            }
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        discard();
    }

    @Override
    public void tick() {
        super.tick();

        if (this.tickCount >= 100) {
            discard();
            return;
        }
        if (this.getDeltaMovement().lengthSqr() < 1.0E-4) {
            discard();
        }
    }

    @Override
    protected ItemStack getPickupItem() {
        return ItemStack.EMPTY;
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

}
