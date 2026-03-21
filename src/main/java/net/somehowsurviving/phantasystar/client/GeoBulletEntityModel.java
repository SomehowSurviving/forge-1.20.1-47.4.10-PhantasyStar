package net.somehowsurviving.phantasystar.client;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.somehowsurviving.phantasystar.PhantasyStar;
import net.somehowsurviving.phantasystar.entities.BulletEntity;
import net.somehowsurviving.phantasystar.item.custom.GeoSwordItem;
import software.bernie.geckolib.model.GeoModel;

import java.awt.*;

public class GeoBulletEntityModel extends GeoModel<BulletEntity> {

    @Override
    public ResourceLocation getModelResource(BulletEntity animatable) {
        ResourceLocation key = BuiltInRegistries.ENTITY_TYPE.getKey(animatable.getType());
        return new ResourceLocation(PhantasyStar.MOD_ID, "geo/entity/" + key.getPath() + ".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BulletEntity animatable) {
        ResourceLocation key = BuiltInRegistries.ENTITY_TYPE.getKey(animatable.getType());
        return new ResourceLocation(PhantasyStar.MOD_ID, "textures/entity/" + key.getPath() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(BulletEntity animatable) {
        ResourceLocation key = BuiltInRegistries.ENTITY_TYPE.getKey(animatable.getType());
        return new ResourceLocation(PhantasyStar.MOD_ID, "animations/entity/" + key.getPath() + ".animation.json");
    }
}