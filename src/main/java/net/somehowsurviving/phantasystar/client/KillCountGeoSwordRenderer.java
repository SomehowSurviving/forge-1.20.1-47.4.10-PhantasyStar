package net.somehowsurviving.phantasystar.client;

import net.somehowsurviving.phantasystar.item.custom.KillCountGeoSwordItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class KillCountGeoSwordRenderer extends GeoItemRenderer<KillCountGeoSwordItem> {
    public KillCountGeoSwordRenderer() {
        super(new KillCountGeoSwordModel());
    }
}
