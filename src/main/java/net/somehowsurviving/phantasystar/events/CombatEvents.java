package net.somehowsurviving.phantasystar.events;

import com.google.common.collect.Multimap;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.event.TickEvent;
import net.somehowsurviving.phantasystar.item.ModItems;
import net.somehowsurviving.phantasystar.item.custom.GeoSwordItem;
import net.somehowsurviving.phantasystar.item.custom.ModWeapon;
import net.somehowsurviving.phantasystar.registry.ModTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collection;
import java.util.Random;

@Mod.EventBusSubscriber
public class CombatEvents {

    private static final Random RANDOM = new Random();

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {

        if (!(event.getSource().getEntity() instanceof Player player)) return;

        ItemStack weapon = player.getMainHandItem();
        ItemStack weaponStack = player.getMainHandItem();
        LivingEntity target = event.getEntity();

        if (!(weapon.getItem() instanceof GeoSwordItem) && !(weapon.getItem() instanceof ModWeapon)) return;

        Item item = weapon.getItem();
        double chance;

        if (item instanceof GeoSwordItem geoSword) {
            chance = geoSword.getProcChance();
        } else if (item instanceof ModWeapon modWeapon) {
            chance = modWeapon.getProcChance();
        } else {
            return;
        }

        if (Math.random() > chance) return;

        if (weapon.is(ModTags.Items.FREEZE_WEAPONS)) {

            int duration = 60;
            target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, duration, 255));
            target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, duration, 255));
            target.setTicksFrozen(140);
        }

        if (weaponStack.is(ModTags.Items.SHOCK_WEAPONS)) {
            int duration = 60;
            target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, duration, 255));
        }

        if (weapon.is(ModTags.Items.BURN_WEAPONS)) {
            target.setSecondsOnFire(5);
        }

        if (weaponStack.is(ModTags.Items.BERSERK_WEAPONS)) {
            if (player.getHealth() < 4f) return;
            var data = player.getPersistentData();

            if (!data.getBoolean("berserk_attack_paid")) {
                float baseDamage = 1f;
                Multimap<Attribute, AttributeModifier> modifiers =
                        weaponStack.getAttributeModifiers(EquipmentSlot.MAINHAND);

                Collection<AttributeModifier> damageModifiers = modifiers.get(Attributes.ATTACK_DAMAGE);

                for (AttributeModifier mod : damageModifiers) {
                    baseDamage += mod.getAmount();
                }

                float bonusMultiplier = 2.3f;
                float extraDamage = baseDamage * bonusMultiplier;

                target.hurt(target.damageSources().playerAttack(player), extraDamage);

                float selfDamage = 2.0F;
                player.hurt(player.damageSources().magic(), selfDamage);
                data.putBoolean("berserk_attack_paid", true);
            }
        }

        if (weaponStack.is(ModTags.Items.SPIRIT_WEAPONS)) {
            int currentHunger = player.getFoodData().getFoodLevel();
            if (currentHunger >= 4) {
                var data = player.getPersistentData();

                if (!data.getBoolean("spirit_attack_paid")) {
                    float baseDamage = 1f; // fallback

                    Multimap<Attribute, AttributeModifier> modifiers =
                            weaponStack.getAttributeModifiers(EquipmentSlot.MAINHAND);

                    Collection<AttributeModifier> damageModifiers = modifiers.get(Attributes.ATTACK_DAMAGE);

                    for (AttributeModifier mod : damageModifiers) {
                        baseDamage += mod.getAmount();
                    }

                    float bonusMultiplier = 2.3f;
                    float extraDamage = baseDamage * bonusMultiplier;

                    target.hurt(target.damageSources().playerAttack(player), extraDamage);

                    int hungerDrain = 2;
                    player.getFoodData().setFoodLevel(Math.max(0, currentHunger - hungerDrain));
                    data.putBoolean("spirit_attack_paid", true);
                }
            }
        }

        if (weaponStack.is(ModTags.Items.CHARGE_WEAPONS)) {
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

            float bonusDamage = 10f;
            target.hurt(target.damageSources().playerAttack(player), bonusDamage);
        }

        if (weaponStack.is(ModTags.Items.HP_STEAL_WEAPONS)) {
            float baseDamage = 1f; // fallback

            Multimap<Attribute, AttributeModifier> modifiers =
                    weaponStack.getAttributeModifiers(EquipmentSlot.MAINHAND);

            Collection<AttributeModifier> damageModifiers = modifiers.get(Attributes.ATTACK_DAMAGE);

            for (AttributeModifier mod : damageModifiers) {
                baseDamage += mod.getAmount();
            }

            float bonusMultiplier = 0.5f;
            float reducedDamage = baseDamage * bonusMultiplier;

            target.hurt(target.damageSources().playerAttack(player), reducedDamage);

            float healAmount = 2f;
            player.heal(healAmount);
        }

        if (weaponStack.is(ModTags.Items.FOOD_STEAL_WEAPONS)) {
            float baseDamage = 1f; // fallback

            Multimap<Attribute, AttributeModifier> modifiers =
                    weaponStack.getAttributeModifiers(EquipmentSlot.MAINHAND);

            Collection<AttributeModifier> damageModifiers = modifiers.get(Attributes.ATTACK_DAMAGE);

            for (AttributeModifier mod : damageModifiers) {
                baseDamage += mod.getAmount();
            }

            float bonusMultiplier = 0.5f;
            float reducedDamage = baseDamage * bonusMultiplier;

            target.hurt(target.damageSources().playerAttack(player), reducedDamage);

            int hungerRestore = 2;
            int currentHunger = player.getFoodData().getFoodLevel();
            player.getFoodData().setFoodLevel(Math.max(0, currentHunger + hungerRestore));
        }

        if (weaponStack.is(ModTags.Items.ARREST_WEAPONS)) {
            int duration = 60;
            target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, duration, 255));
            target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, duration, 255));
        }

        if (weaponStack.is(ModTags.Items.HAVOC_WEAPONS)) {
            int duration = 80;
            target.addEffect(new MobEffectInstance(MobEffects.CONFUSION, duration, 1));
        }

        if (weaponStack.is(ModTags.Items.DEMON_WEAPONS)) {
            float percent = 0.50F;

            float bonusDamage = target.getHealth() * percent;

            target.hurt(target.damageSources().playerAttack(player), bonusDamage);
        }

        if (weaponStack.is(ModTags.Items.HELL_WEAPONS)) {
            float executeDamage = target.getHealth();
            if (target.getMaxHealth() > 100) return;
            target.hurt(target.damageSources().playerAttack(player), executeDamage);
        }
    }

    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event) {

        Player player = event.player;

        if (player.getAttackStrengthScale(0) > 0.95f) {
            player.getPersistentData().remove("meseta_attack_paid");
            player.getPersistentData().remove("berserk_attack_paid");
            player.getPersistentData().remove("spirit_attack_paid");
        }
    }
}