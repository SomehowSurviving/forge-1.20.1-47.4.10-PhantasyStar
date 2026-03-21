package net.somehowsurviving.phantasystar.client;

import net.minecraft.resources.ResourceLocation;
import net.somehowsurviving.phantasystar.PhantasyStar;
import net.somehowsurviving.phantasystar.item.custom.GunItem;
import net.somehowsurviving.phantasystar.item.custom.SpecialRollGunItem;
import software.bernie.geckolib.model.GeoModel;

public class GeoSpecialGunModel extends GeoModel<SpecialRollGunItem> {

    @Override
    public ResourceLocation getModelResource(SpecialRollGunItem animatable) {
        ResourceLocation key = net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(animatable);
        return new ResourceLocation(PhantasyStar.MOD_ID, "geo/item/" + key.getPath() + ".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SpecialRollGunItem animatable) {
        ResourceLocation key = net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(animatable);
        return new ResourceLocation(PhantasyStar.MOD_ID, "textures/item/geo_weapon/" + key.getPath() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(SpecialRollGunItem animatable) {
        ResourceLocation key = net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(animatable);
        return new ResourceLocation(PhantasyStar.MOD_ID, "animations/item/" + key.getPath() + ".animation.json");
    }
}