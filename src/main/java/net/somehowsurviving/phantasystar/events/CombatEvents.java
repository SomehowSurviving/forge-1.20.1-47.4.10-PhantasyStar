package net.somehowsurviving.phantasystar.events;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.somehowsurviving.phantasystar.entities.BulletEntity;
import net.somehowsurviving.phantasystar.entities.LauncherProjectileEntity;
import net.somehowsurviving.phantasystar.item.ModItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.somehowsurviving.phantasystar.utils.WeaponUtils;
import static net.somehowsurviving.phantasystar.utils.WeaponUtils.getWeaponDamage;

import java.util.Random;

@Mod.EventBusSubscriber
public class CombatEvents {

    private static final double FIRE_CHANCE = 0.20;
    private static final double SHOCK_CHANCE = 0.20;
    private static final double HP_STEAL_CHANCE = 0.50;
    private static final double FOOD_STEAL_CHANCE = 0.50;
    private static final double XP_STEAL_CHANCE = 0.30;
    private static final Random RANDOM = new Random();

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {

        LivingEntity target = event.getEntity();

        Player player = null;
        ItemStack weaponStack = ItemStack.EMPTY;

        if (event.getSource().getEntity() instanceof Player p) {
            player = p;
            weaponStack = p.getMainHandItem();
        }
        else if (event.getSource().getEntity() instanceof BulletEntity bullet) {
            if (bullet.getOwner() instanceof Player p) {
                player = p;
                weaponStack = bullet.getWeapon();
            }
        }
        else if (event.getSource().getEntity() instanceof LauncherProjectileEntity rocket) {
            if (rocket.getOwner() instanceof Player p) {
                player = p;
                weaponStack = rocket.getWeapon();
            }
        }

// 🚫 Keep your existing guards
        if (player == null) return;
        if (weaponStack.isEmpty()) return;

        String special = WeaponUtils.getWeaponSpecial(weaponStack);
        if (special == null) return;

        // Switch handles all effects
        switch (special) {

            // Fire Type
            case "heat":
                handleHeat(player);
                break;
            case "fire":
                handleFire(player);
                break;
            case "flame":
                handleFlame(player);
                break;
            case "burn":
                handleBurn(player, target);
                break;

            // Lightning Type
            case "shock":
                handleShock(target);
                break;
            case "thunder":
                handleThunder(target);
                break;
            case "storm":
                handleStorm(target);
                break;
            case "tempest":
                handleTempest(player, target);
                break;

            // HP Steal Type
            case "draw":
                handleDraw(player, target, weaponStack);
                break;
            case "drain":
                handleDrain(player, target, weaponStack);
                break;
            case "fill":
                handleFill(player, target, weaponStack);
                break;
            case "gush":
                handleGush(player, target, weaponStack);
                break;

            // Food Steal Type
            case "heart":
                handleHeart(player, target, weaponStack);
                break;
            case "mind":
                handleMind(player, target, weaponStack);
                break;
            case "soul":
                handleSoul(player, target, weaponStack);
                break;
            case "geist":
                handleGeist(player, target, weaponStack);
                break;

            // XP Steal Type
            case "master":
                handleMaster(player);
                break;
            case "lord":
                handleLord(player);
                break;
            case "king":
                handleKing(player);
                break;

            // Paralyze Type
            case "bind":
                handleBind(target);
                break;
            case "hold":
                handleHold(target);
                break;
            case "seize":
                handleSeize(target);
                break;
            case "arrest":
                handleArrest(target);
                break;

            // Confuse Type
            case "panic":
                handlePanic(target);
                break;
            case "riot":
                handleRiot(target);
                break;
            case "havoc":
                handleHavoc(target);
                break;
            case "chaos":
                handleChaos(target);
                break;

            // Ice Type
            case "ice":
                handleIce(target);
                break;
            case "frost":
                handleFrost(target);
                break;
            case "freeze":
                handleFreeze(target);
                break;
            case "blizzard":
                handleBlizzard(target);
                break;
            case "snow":
                handleSnowRifle(target);
                break;

            // Powerful - Consume Type
            case "berserk":
                handleBerserk(player, target, weaponStack);
                break;

            case "spirit":
                handleSpirit(player, target, weaponStack);
                break;

            case "charge":
                handleCharge(player, target, weaponStack);
                break;
            case "supercharge":
                handleSupercharge(player, target, weaponStack);
                break;

            // %Cut Type
            case "devil":
                handleDevil(target, weaponStack, event);
                break;
            case "demon":
                handleDemon(target, weaponStack, event);
                break;

            // One-hit Type
            case "dim":
                handleDim(target, weaponStack);
                break;
            case "shadow":
                handleShadow(target, weaponStack);
                break;
            case "dark":
                handleDark(target, weaponStack);
                break;
            case "hell":
                handleHell(target, weaponStack);
                break;
        }
    }

    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;

        if (player.getAttackStrengthScale(0) > 0.95f) {
            var data = player.getPersistentData();
            data.remove("meseta_attack_paid");
            data.remove("berserk_attack_paid");
            data.remove("spirit_attack_paid");
        }
    }

    private static void handleHeat(Player player) {

        if (RANDOM.nextDouble() > FIRE_CHANCE) return;

        Level level = player.level();

        if (!level.isClientSide) {

            Vec3 look = player.getLookAngle();
            SmallFireball fireball = new SmallFireball(level, player, look.x, look.y, look.z);
            fireball.setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());

            level.addFreshEntity(fireball);
        }
    }

    private static void handleFire(Player player) {

        if (RANDOM.nextDouble() > FIRE_CHANCE) return;

        Level level = player.level();

        if (!level.isClientSide) {

            Vec3 look = player.getLookAngle();
            SmallFireball fireball1 = new SmallFireball(level, player, look.x, look.y, look.z);
            fireball1.setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());

            Vec3 offsetLook = look.add(0.1, 0, 0);
            SmallFireball fireball2 = new SmallFireball(level, player, offsetLook.x, offsetLook.y, offsetLook.z);
            fireball2.setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());

            level.addFreshEntity(fireball1);
            level.addFreshEntity(fireball2);
        }
    }

    private static void handleFlame(Player player) {

        if (RANDOM.nextDouble() > FIRE_CHANCE) return;

        Level level = player.level();

        if (!level.isClientSide) {

            Vec3 look = player.getLookAngle();

            SmallFireball fireball1 = new SmallFireball(level, player, look.x, look.y, look.z);
            fireball1.setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());

            Vec3 offset1Look = look.add(0.1, 0, 0);
            SmallFireball fireball2 = new SmallFireball(level, player, offset1Look.x, offset1Look.y, offset1Look.z);
            fireball2.setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());

            Vec3 offset2Look = look.add(-0.1, 0, 0);
            SmallFireball fireball3 = new SmallFireball(level, player, offset2Look.x, offset2Look.y, offset2Look.z);
            fireball3.setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());

            level.addFreshEntity(fireball1);
            level.addFreshEntity(fireball2);
            level.addFreshEntity(fireball3);
        }
    }

    private static void handleBurn(Player player, LivingEntity target) {

        if (RANDOM.nextDouble() > FIRE_CHANCE) return;

        target.setSecondsOnFire(8);
        target.hurt(target.damageSources().magic(), 4);
    }

    private static void handleShock(LivingEntity target) {

        if (RANDOM.nextDouble() > SHOCK_CHANCE) return;

        int duration = 40;
        target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, duration, 255));
    }

    private static void handleThunder(LivingEntity target) {

        if (RANDOM.nextDouble() > SHOCK_CHANCE) return;

        int duration = 60;
        target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, duration, 255));
    }

    private static void handleStorm(LivingEntity target) {

        if (RANDOM.nextDouble() > SHOCK_CHANCE) return;

        int duration = 80;
        target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, duration, 255));
    }

    private static void handleTempest(Player player, LivingEntity target) {

        if (RANDOM.nextDouble() > SHOCK_CHANCE) return;

        int duration = 80;
        target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, duration, 255));
        Level level = player.level();

        if (!level.isClientSide) {

            LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(level);

            if (lightning != null) {
                lightning.moveTo(target.getX(), target.getY() + target.getBbHeight() / 2, target.getZ());
                lightning.setCause(player instanceof ServerPlayer sp ? sp : null);
                level.addFreshEntity(lightning);
            }
        }
    }

    private static void handleDraw(Player player, LivingEntity target, ItemStack weaponStack) {

        if (RANDOM.nextDouble() > HP_STEAL_CHANCE) return;

        float reducedDamage = getWeaponDamage(weaponStack) * 0.7f;
        target.hurt(target.damageSources().magic(), reducedDamage);
        player.heal(2f);
    }
    private static void handleDrain(Player player, LivingEntity target, ItemStack weaponStack) {

        if (RANDOM.nextDouble() > HP_STEAL_CHANCE) return;

        float reducedDamage = getWeaponDamage(weaponStack) * 0.7f;
        target.hurt(target.damageSources().magic(), reducedDamage);
        player.heal(4f);
    }
    private static void handleFill(Player player, LivingEntity target, ItemStack weaponStack) {

        if (RANDOM.nextDouble() > HP_STEAL_CHANCE) return;

        float reducedDamage = getWeaponDamage(weaponStack) * 0.7f;
        target.hurt(target.damageSources().magic(), reducedDamage);
        player.heal(6f);
    }
    private static void handleGush(Player player, LivingEntity target, ItemStack weaponStack) {

        if (RANDOM.nextDouble() > HP_STEAL_CHANCE) return;

        float reducedDamage = getWeaponDamage(weaponStack) * 0.7f;
        target.hurt(target.damageSources().magic(), reducedDamage);
        player.heal(8f);
    }

    private static void handleHeart(Player player, LivingEntity target, ItemStack weaponStack) {

        if (RANDOM.nextDouble() > FOOD_STEAL_CHANCE) return;

        float reducedDamage = getWeaponDamage(weaponStack) * 0.7f;
        target.hurt(target.damageSources().magic(), reducedDamage);
        int currentHunger = player.getFoodData().getFoodLevel();
        player.getFoodData().setFoodLevel(Math.min(20, currentHunger + 1));
    }
    private static void handleMind(Player player, LivingEntity target, ItemStack weaponStack) {

        if (RANDOM.nextDouble() > FOOD_STEAL_CHANCE) return;

        float reducedDamage = getWeaponDamage(weaponStack) * 0.7f;
        target.hurt(target.damageSources().magic(), reducedDamage);
        int currentHunger = player.getFoodData().getFoodLevel();
        player.getFoodData().setFoodLevel(Math.min(20, currentHunger + 2));
    }
    private static void handleSoul(Player player, LivingEntity target, ItemStack weaponStack) {

        if (RANDOM.nextDouble() > FOOD_STEAL_CHANCE) return;

        float reducedDamage = getWeaponDamage(weaponStack) * 0.7f;
        target.hurt(target.damageSources().magic(), reducedDamage);
        int currentHunger = player.getFoodData().getFoodLevel();
        player.getFoodData().setFoodLevel(Math.min(20, currentHunger + 3));
    }
    private static void handleGeist(Player player, LivingEntity target, ItemStack weaponStack) {

        if (RANDOM.nextDouble() > FOOD_STEAL_CHANCE) return;

        float reducedDamage = getWeaponDamage(weaponStack) * 0.7f;
        target.hurt(target.damageSources().magic(), reducedDamage);
        int currentHunger = player.getFoodData().getFoodLevel();
        player.getFoodData().setFoodLevel(Math.min(20, currentHunger + 4));
    }

    private static void handleMaster(Player player) {

        if (RANDOM.nextDouble() > XP_STEAL_CHANCE) return;

        player.giveExperiencePoints(4);
    }

    private static void handleLord(Player player) {

        if (RANDOM.nextDouble() > XP_STEAL_CHANCE) return;

        player.giveExperiencePoints(6);
    }
    private static void handleKing(Player player) {

        if (RANDOM.nextDouble() > XP_STEAL_CHANCE) return;

        player.giveExperiencePoints(8);
    }

    private static void handleBind(LivingEntity target) {

        if (RANDOM.nextDouble() > 0.05) return;

        int duration = 80;
        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, duration, 255));
        target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, duration, 255));
    }
    private static void handleHold(LivingEntity target) {

        if (RANDOM.nextDouble() > 0.10) return;

        int duration = 80;
        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, duration, 255));
        target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, duration, 255));
    }
    private static void handleSeize(LivingEntity target) {

        if (RANDOM.nextDouble() > 0.20) return;

        int duration = 80;
        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, duration, 255));
        target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, duration, 255));
    }
    private static void handleArrest(LivingEntity target) {

        if (RANDOM.nextDouble() > 0.40) return;

        int duration = 80;
        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, duration, 255));
        target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, duration, 255));
    }

    private static void handlePanic(LivingEntity target) {

        if (RANDOM.nextDouble() > 0.05) return;

        target.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 80, 1));
    }
    private static void handleRiot(LivingEntity target) {

        if (RANDOM.nextDouble() > 0.10) return;

        target.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 80, 1));
    }
    private static void handleHavoc(LivingEntity target) {

        if (RANDOM.nextDouble() > 0.20) return;

        target.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 80, 1));
    }
    private static void handleChaos(LivingEntity target) {

        if (RANDOM.nextDouble() > 0.40) return;

        target.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 80, 1));
    }

    private static void handleIce(LivingEntity target) {

        if (RANDOM.nextDouble() > 0.05) return;

        int duration = 60;
        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, duration, 255));
        target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, duration, 255));
        target.setTicksFrozen(140);
    }
    private static void handleFrost(LivingEntity target) {

        if (RANDOM.nextDouble() > 0.10) return;

        int duration = 60;
        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, duration, 255));
        target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, duration, 255));
        target.setTicksFrozen(140);
    }
    private static void handleFreeze(LivingEntity target) {

        if (RANDOM.nextDouble() > 0.20) return;

        int duration = 60;
        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, duration, 255));
        target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, duration, 255));
        target.setTicksFrozen(140);
    }
    private static void handleBlizzard(LivingEntity target) {

        if (RANDOM.nextDouble() > 0.40) return;

        int duration = 60;
        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, duration, 255));
        target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, duration, 255));
        target.setTicksFrozen(140);
    }
    private static void handleSnowRifle(LivingEntity target) {

        if (RANDOM.nextDouble() > 0.99) return;

        int duration = 60;
        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, duration, 255));
        target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, duration, 255));
        target.setTicksFrozen(140);
    }

    private static void handleBerserk(Player player, LivingEntity target, ItemStack weaponStack) {

        if (RANDOM.nextDouble() > 0.20) return;

        if (player.getHealth() < 4f) return;
        var data = player.getPersistentData();
        if (data.getBoolean("berserk_attack_paid")) return;

        float baseDamage = getWeaponDamage(weaponStack);
        float extraDamage = baseDamage * 2.3f;
        target.hurt(target.damageSources().playerAttack(player), extraDamage);

        float selfDamage = 2f;
        player.hurt(player.damageSources().magic(), selfDamage);
        data.putBoolean("berserk_attack_paid", true);
    }

    private static void handleSpirit(Player player, LivingEntity target, ItemStack weaponStack) {

        if (RANDOM.nextDouble() > 0.20) return;

        int currentHunger = player.getFoodData().getFoodLevel();
        if (currentHunger < 4) return;

        var data = player.getPersistentData();
        if (data.getBoolean("spirit_attack_paid")) return;

        float baseDamage = getWeaponDamage(weaponStack);
        float extraDamage = baseDamage * 2.3f;
        target.hurt(target.damageSources().magic(), extraDamage);

        player.getFoodData().setFoodLevel(Math.max(0, currentHunger - 2));
        data.putBoolean("spirit_attack_paid", true);
    }

    private static void handleCharge(Player player, LivingEntity target, ItemStack weaponStack) {

        if (RANDOM.nextDouble() > 0.50) return;

        var data = player.getPersistentData();

        if (!data.getBoolean("meseta_attack_paid")) {

            int cost = 10;
            int mesetaCount = 0;

            for (ItemStack stack : player.getInventory().items) {
                if (stack.is(ModItems.MESETA.get())) {
                    mesetaCount += stack.getCount();
                }
            }

            if (mesetaCount >= cost) {

                int remaining = cost;

                for (ItemStack stack : player.getInventory().items) {
                    if (stack.is(ModItems.MESETA.get())) {

                        int remove = Math.min(remaining, stack.getCount());

                        stack.shrink(remove);
                        remaining -= remove;

                        if (remaining <= 0) break;
                    }
                }

                data.putBoolean("meseta_attack_paid", true);
            } else {
                return;
            }
        }

        float bonusDamage = getWeaponDamage(weaponStack) * 2.3f;
        target.hurt(target.damageSources().magic(), bonusDamage);

    }
    private static void handleSupercharge(Player player, LivingEntity target, ItemStack weaponStack) {

        if (RANDOM.nextDouble() > 0.50) return;

        var data = player.getPersistentData();

        if (!data.getBoolean("meseta_attack_paid")) {

            int cost = 100;
            int mesetaCount = 0;

            for (ItemStack stack : player.getInventory().items) {
                if (stack.is(ModItems.MESETA.get())) {
                    mesetaCount += stack.getCount();
                }
            }

            if (mesetaCount >= cost) {

                int remaining = cost;

                for (ItemStack stack : player.getInventory().items) {
                    if (stack.is(ModItems.MESETA.get())) {

                        int remove = Math.min(remaining, stack.getCount());

                        stack.shrink(remove);
                        remaining -= remove;

                        if (remaining <= 0) break;
                    }
                }

                data.putBoolean("meseta_attack_paid", true);
            } else {
                return;
            }
        }

        float bonusDamage = getWeaponDamage(weaponStack) * 4.6f;
        target.hurt(target.damageSources().magic(), bonusDamage);

    }

    private static void handleDevil(LivingEntity target, ItemStack weaponStack, LivingHurtEvent event) {

        if (RANDOM.nextDouble() > 0.10) return;
        if (target.getMaxHealth() > getWeaponDamage(weaponStack) * 20) return;

        float bonusDamage = target.getHealth() * 0.5f;

        if (bonusDamage <= 0f) return;
        target.hurt(target.damageSources().magic(), bonusDamage);
    }
    private static void handleDemon(LivingEntity target, ItemStack weaponStack, LivingHurtEvent event) {

        if (RANDOM.nextDouble() > 0.10) return;
        if (target.getMaxHealth() > getWeaponDamage(weaponStack) * 20) return;

        float bonusDamage = target.getHealth() * 0.75f;

        if (bonusDamage <= 0f) return;
        target.hurt(target.damageSources().magic(), bonusDamage);
    }

    private static void handleDim(LivingEntity target, ItemStack weaponStack) {

        if (RANDOM.nextDouble() > 0.05) return;
        if (target.getMaxHealth() > getWeaponDamage(weaponStack) * 5) return;

        float remainingHealth = target.getHealth();
        target.hurt(target.damageSources().magic(), remainingHealth);
    }
    private static void handleShadow(LivingEntity target, ItemStack weaponStack) {

        if (RANDOM.nextDouble() > 0.08) return;
        if (target.getMaxHealth() > getWeaponDamage(weaponStack) * 7) return;

        float remainingHealth = target.getHealth();
        target.hurt(target.damageSources().magic(), remainingHealth);
    }
    private static void handleDark(LivingEntity target, ItemStack weaponStack) {

        if (RANDOM.nextDouble() > 0.12) return;
        if (target.getMaxHealth() > getWeaponDamage(weaponStack) * 10) return;

        float remainingHealth = target.getHealth();
        target.hurt(target.damageSources().magic(), remainingHealth);
    }
    private static void handleHell(LivingEntity target, ItemStack weaponStack) {

        if (RANDOM.nextDouble() > 0.15) return;
        if (target.getMaxHealth() > getWeaponDamage(weaponStack) * 15) return;

        float remainingHealth = target.getHealth();
        target.hurt(target.damageSources().magic(), remainingHealth);
    }
}