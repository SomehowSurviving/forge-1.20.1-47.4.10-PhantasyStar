package net.somehowsurviving.phantasystar.client;

import net.minecraft.resources.ResourceLocation;
import net.somehowsurviving.phantasystar.PhantasyStar;
import net.somehowsurviving.phantasystar.item.custom.Musashi;
import software.bernie.geckolib.model.GeoModel;

public class MusashiModel extends GeoModel<Musashi> {
    @Override
    public ResourceLocation getModelResource(Musashi animatable) {
        return new ResourceLocation(PhantasyStar.MOD_ID, "geo/musashi.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Musashi animatable) {
        return new ResourceLocation(PhantasyStar.MOD_ID, "textures/item/musashi.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Musashi animatable) {
        return new ResourceLocation(PhantasyStar.MOD_ID, "animations/item/musashi.animation.json");
    }
}