package net.somehowsurviving.phantasystar.client;

import net.somehowsurviving.phantasystar.item.custom.MagItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class GeoMagItemRenderer extends GeoItemRenderer<MagItem> {
    public GeoMagItemRenderer() {
        super(new GeoMagModel());
    }
}
