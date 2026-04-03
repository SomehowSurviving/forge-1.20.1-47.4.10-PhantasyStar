package net.somehowsurviving.phantasystar.client;

import net.minecraft.resources.ResourceLocation;
import net.somehowsurviving.phantasystar.PhantasyStar;
import net.somehowsurviving.phantasystar.item.custom.MagItem;
import software.bernie.geckolib.model.GeoModel;

public class GeoMagModel extends GeoModel<MagItem> {

    @Override
    public ResourceLocation getModelResource(MagItem animatable) {
        ResourceLocation key = net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(animatable);
        return new ResourceLocation(PhantasyStar.MOD_ID, "geo/item/" + key.getPath() + ".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MagItem animatable) {
        ResourceLocation key = net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(animatable);
        return new ResourceLocation(PhantasyStar.MOD_ID, "textures/item/mag/" + key.getPath() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(MagItem animatable) {
        ResourceLocation key = net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(animatable);
        return new ResourceLocation(PhantasyStar.MOD_ID, "animations/item/mag/" + key.getPath() + ".animation.json");
    }
}