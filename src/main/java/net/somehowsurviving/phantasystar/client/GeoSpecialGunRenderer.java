package net.somehowsurviving.phantasystar.client;

import net.somehowsurviving.phantasystar.item.custom.GunItem;
import net.somehowsurviving.phantasystar.item.custom.SpecialRollGunItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class GeoSpecialGunRenderer extends GeoItemRenderer<SpecialRollGunItem> {
    public GeoSpecialGunRenderer() {
        super(new GeoSpecialGunModel());
    }
}
