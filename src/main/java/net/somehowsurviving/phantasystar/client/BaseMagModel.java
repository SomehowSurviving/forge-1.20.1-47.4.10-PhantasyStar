package net.somehowsurviving.phantasystar.client;

import net.minecraft.resources.ResourceLocation;
import net.somehowsurviving.phantasystar.PhantasyStar;
import net.somehowsurviving.phantasystar.item.custom.BaseMag;
import software.bernie.geckolib.model.GeoModel;

public class BaseMagModel extends GeoModel<BaseMag> {
    @Override
    public ResourceLocation getModelResource(BaseMag mag) {
        return new ResourceLocation("phantasystar", "geo/item/base_mag.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BaseMag mag) {
        return new ResourceLocation("phantasystar", "textures/item/base_mag.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BaseMag mag) {
        return new ResourceLocation("phantasystar", "animations/item/base_mag.animation.json");
    }

}