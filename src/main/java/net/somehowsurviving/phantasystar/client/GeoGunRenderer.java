package net.somehowsurviving.phantasystar.client;

import net.somehowsurviving.phantasystar.item.custom.GeoSwordItem;
import net.somehowsurviving.phantasystar.item.custom.GunItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class GeoGunRenderer extends GeoItemRenderer<GunItem> {
    public GeoGunRenderer() {
        super(new GeoGunModel());
    }
}
