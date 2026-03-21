package net.somehowsurviving.phantasystar.item.custom;

import net.minecraft.world.entity.EntityType;
import net.somehowsurviving.phantasystar.entities.BulletEntity;

public interface IGunItem {
    float getWeaponDamage();
    EntityType<? extends BulletEntity> getBulletType();
}
