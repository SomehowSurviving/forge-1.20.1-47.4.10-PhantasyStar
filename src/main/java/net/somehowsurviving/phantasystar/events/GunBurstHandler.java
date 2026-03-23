package net.somehowsurviving.phantasystar.events;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.somehowsurviving.phantasystar.entities.BulletEntity;
import net.somehowsurviving.phantasystar.item.custom.GunItem;
import net.somehowsurviving.phantasystar.item.custom.SpecialRollGunItem;

@Mod.EventBusSubscriber
public class GunBurstHandler {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {

        if (event.phase != TickEvent.Phase.END) return;

        Player player = event.player;
        Level level = player.level();

        if (level.isClientSide) return;

        CompoundTag data = player.getPersistentData();

        if (!data.contains("burst_shots")) return;

        int shots = data.getInt("burst_shots");
        int timer = data.getInt("burst_timer");
        int delay = data.getInt("burst_delay");

        // If finished, clean up
        if (shots <= 0) {
            data.remove("burst_shots");
            data.remove("burst_timer");
            data.remove("burst_delay");
            data.remove("burst_weapon");
            return;
        }

        if (timer > 0) {
            data.putInt("burst_timer", timer - 1);
            return;
        }

        ItemStack weapon = ItemStack.of(data.getCompound("burst_weapon"));

        fireBullet(player, weapon);

        data.putInt("burst_shots", shots - 1);
        data.putInt("burst_timer", delay);
    }

    private static void fireBullet(Player player, ItemStack stack) {
        Level level = player.level();
        Item item = stack.getItem();

        BulletEntity bullet;
        float damage;
        EntityType<?> bulletType;

        // Handle GunItem
        if (item instanceof GunItem g) {
            bulletType = g.getBulletType();
            damage = g.getWeaponDamage();
            bullet = (BulletEntity) bulletType.create(level);
        }
        // Handle SpecialRollGunItem
        else if (item instanceof SpecialRollGunItem s) {
            bulletType = s.getBulletType();
            damage = s.getWeaponDamage();
            bullet = (BulletEntity) bulletType.create(level);
        }
        // Not a valid gun
        else {
            player.getPersistentData().remove("burst_shots");
            return;
        }

        if (bullet == null) return;

        bullet.setOwner(player);
        bullet.setWeapon(stack.copy());

        // Calculate bonus damage from player attributes
        double bonus = 0;
        for (AttributeModifier mod : player.getAttribute(Attributes.ATTACK_DAMAGE).getModifiers()) {
            if (!mod.getName().equals("Gun damage")) {
                bonus += mod.getAmount();
            }
        }

        bullet.setDamage(damage + (float) bonus);

        // Bullet position / shooting
        double forwardOffset = 0.8;
        double rightOffset = 0.0;
        double upOffset = -0.1;

        float yawRad = (float) Math.toRadians(player.getYRot());
        float pitchRad = (float) Math.toRadians(player.getXRot());

        double dx = -Math.sin(yawRad) * Math.cos(pitchRad) * forwardOffset;
        double dy = -Math.sin(pitchRad) * forwardOffset + upOffset;
        double dz = Math.cos(yawRad) * Math.cos(pitchRad) * forwardOffset;

        dx += Math.cos(yawRad) * rightOffset;
        dz += Math.sin(yawRad) * rightOffset;

        bullet.setPos(player.getX() + dx, player.getEyeY() + dy, player.getZ() + dz);
        bullet.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.0F, 1.0F);

        level.addFreshEntity(bullet);
    }
}
