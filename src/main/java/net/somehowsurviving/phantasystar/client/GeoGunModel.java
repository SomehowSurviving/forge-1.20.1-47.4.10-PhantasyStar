package net.somehowsurviving.phantasystar.client;

import net.minecraft.resources.ResourceLocation;
import net.somehowsurviving.phantasystar.PhantasyStar;
import net.somehowsurviving.phantasystar.item.custom.GunItem;
import software.bernie.geckolib.model.GeoModel;

public class GeoGunModel extends GeoModel<GunItem> {

    @Override
    public ResourceLocation getModelResource(GunItem animatable) {
        ResourceLocation key = net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(animatable);
        return new ResourceLocation(PhantasyStar.MOD_ID, "geo/item/" + key.getPath() + ".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GunItem animatable) {
        ResourceLocation key = net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(animatable);
        return new ResourceLocation(PhantasyStar.MOD_ID, "textures/item/geo_weapon/" + key.getPath() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(GunItem animatable) {
        ResourceLocation key = net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(animatable);
        return new ResourceLocation(PhantasyStar.MOD_ID, "animations/item/" + key.getPath() + ".animation.json");
    }
}