package net.somehowsurviving.phantasystar.client;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.somehowsurviving.phantasystar.item.custom.GeoSwordItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class GeoSwordRenderer extends GeoItemRenderer<GeoSwordItem> {
    public GeoSwordRenderer() {
        super(new GeoSwordModel());
    }

    @Override
    public RenderType getRenderType(GeoSwordItem animatable,
                                    ResourceLocation texture,
                                    MultiBufferSource bufferSource,
                                    float partialTick) {
        return RenderType.entityTranslucent(texture);
    }
}
