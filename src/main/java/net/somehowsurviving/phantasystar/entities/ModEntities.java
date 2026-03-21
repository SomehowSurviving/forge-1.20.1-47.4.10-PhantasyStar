package net.somehowsurviving.phantasystar.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.somehowsurviving.phantasystar.PhantasyStar;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, PhantasyStar.MOD_ID);

    public static final RegistryObject<EntityType<BulletEntity>> BULLET_GREEN =
            ENTITIES.register("bullet_green", () ->
                    EntityType.Builder.<BulletEntity>of(BulletEntity::new, MobCategory.MISC)
            .sized(0.25f, 0.25f)
            .clientTrackingRange(8)
            .updateInterval(1)
            .build("bullet_green")
            );
    public static final RegistryObject<EntityType<BulletEntity>> BULLET_BLUE =
            ENTITIES.register("bullet_blue", () ->
                    EntityType.Builder.<BulletEntity>of(BulletEntity::new, MobCategory.MISC)
            .sized(0.25f, 0.25f)
            .clientTrackingRange(8)
            .updateInterval(1)
            .build("bullet_blue"));
    public static final RegistryObject<EntityType<BulletEntity>> BULLET_PINK =
            ENTITIES.register("bullet_pink", () ->
                    EntityType.Builder.<BulletEntity>of(BulletEntity::new, MobCategory.MISC)
            .sized(0.25f, 0.25f)
            .clientTrackingRange(8)
            .updateInterval(1)
            .build("bullet_pink"));

    public static final RegistryObject<EntityType<BulletEntity>> BULLET_RED =
            ENTITIES.register("bullet_red", () ->
                    EntityType.Builder.<BulletEntity>of(BulletEntity::new, MobCategory.MISC)
            .sized(0.25f, 0.25f)
            .clientTrackingRange(8)
            .updateInterval(1)
            .build("bullet_red"));

    public static final RegistryObject<EntityType<BulletEntity>> BULLET_YELLOW =
            ENTITIES.register("bullet_yellow", () ->
                    EntityType.Builder.<BulletEntity>of(BulletEntity::new, MobCategory.MISC)
            .sized(0.25f, 0.25f)
            .clientTrackingRange(8)
            .updateInterval(1)
            .build("bullet_yellow"));

    public static final RegistryObject<EntityType<LauncherProjectileEntity>> LAUNCHER_PROJECTILE_GREEN =
            ENTITIES.register("launcher_projectile_green",
                    () -> EntityType.Builder.<LauncherProjectileEntity>of(LauncherProjectileEntity::new, MobCategory.MISC)
                            .sized(0.75f, 0.75f)    // hitbox size
                            .clientTrackingRange(12)
                            .updateInterval(1)
                            .build("launcher_projectile_green")
            );
    public static final RegistryObject<EntityType<LauncherProjectileEntity>> LAUNCHER_PROJECTILE_BLUE =
            ENTITIES.register("launcher_projectile_blue",
                    () -> EntityType.Builder.<LauncherProjectileEntity>of(LauncherProjectileEntity::new, MobCategory.MISC)
                            .sized(0.75f, 0.75f)    // hitbox size
                            .clientTrackingRange(12)
                            .updateInterval(1)
                            .build("launcher_projectile_blue")
            );
    public static final RegistryObject<EntityType<LauncherProjectileEntity>> LAUNCHER_PROJECTILE_RED =
            ENTITIES.register("launcher_projectile_red",
                    () -> EntityType.Builder.<LauncherProjectileEntity>of(LauncherProjectileEntity::new, MobCategory.MISC)
                            .sized(0.75f, 0.75f)    // hitbox size
                            .clientTrackingRange(12)
                            .updateInterval(1)
                            .build("launcher_projectile_red")
            );

    public static void register(IEventBus bus) {
        ENTITIES.register(bus);
    }
}
