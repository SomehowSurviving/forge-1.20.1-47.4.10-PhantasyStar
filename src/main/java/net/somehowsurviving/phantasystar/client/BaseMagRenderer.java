package net.somehowsurviving.phantasystar.client;

import net.somehowsurviving.phantasystar.item.custom.BaseMag;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class BaseMagRenderer extends GeoItemRenderer<BaseMag> {
    public BaseMagRenderer() {
        super(new BaseMagModel());
    }
}
