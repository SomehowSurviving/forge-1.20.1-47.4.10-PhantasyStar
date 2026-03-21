package net.somehowsurviving.phantasystar.client;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.somehowsurviving.phantasystar.PhantasyStar;
import net.somehowsurviving.phantasystar.entities.BulletEntity;
import net.somehowsurviving.phantasystar.entities.LauncherProjectileEntity;
import software.bernie.geckolib.model.GeoModel;

public class GeoLauncherProjectileEntityModel extends GeoModel<LauncherProjectileEntity> {

    @Override
    public ResourceLocation getModelResource(LauncherProjectileEntity animatable) {
        ResourceLocation key = BuiltInRegistries.ENTITY_TYPE.getKey(animatable.getType());
        return new ResourceLocation(PhantasyStar.MOD_ID, "geo/entity/" + key.getPath() + ".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LauncherProjectileEntity animatable) {
        ResourceLocation key = BuiltInRegistries.ENTITY_TYPE.getKey(animatable.getType());
        return new ResourceLocation(PhantasyStar.MOD_ID, "textures/entity/" + key.getPath() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(LauncherProjectileEntity animatable) {
        ResourceLocation key = BuiltInRegistries.ENTITY_TYPE.getKey(animatable.getType());
        return new ResourceLocation(PhantasyStar.MOD_ID, "animations/entity/" + key.getPath() + ".animation.json");
    }
}