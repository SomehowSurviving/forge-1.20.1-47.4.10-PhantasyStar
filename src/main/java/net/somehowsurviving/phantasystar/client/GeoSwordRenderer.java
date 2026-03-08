package net.somehowsurviving.phantasystar.client;

import net.somehowsurviving.phantasystar.item.custom.GeoSwordItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class GeoSwordRenderer extends GeoItemRenderer<GeoSwordItem> {
    public GeoSwordRenderer() {
        super(new GeoSwordModel());
    }
}
