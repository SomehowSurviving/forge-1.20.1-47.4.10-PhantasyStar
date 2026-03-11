package net.somehowsurviving.phantasystar.client;

import net.minecraft.resources.ResourceLocation;
import net.somehowsurviving.phantasystar.PhantasyStar;
import net.somehowsurviving.phantasystar.item.custom.KillCountGeoSwordItem;
import software.bernie.geckolib.model.GeoModel;

public class KillCountGeoSwordModel extends GeoModel<KillCountGeoSwordItem> {

    @Override
    public ResourceLocation getModelResource(KillCountGeoSwordItem animatable) {
        ResourceLocation key = net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(animatable);
        return new ResourceLocation(PhantasyStar.MOD_ID, "geo/item/" + key.getPath() + ".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(KillCountGeoSwordItem animatable) {
        ResourceLocation key = net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(animatable);
        return new ResourceLocation(PhantasyStar.MOD_ID, "textures/item/" + key.getPath() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(KillCountGeoSwordItem animatable) {
        ResourceLocation key = net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(animatable);
        return new ResourceLocation(PhantasyStar.MOD_ID, "animations/item/" + key.getPath() + ".animation.json");
    }
}