package net.somehowsurviving.phantasystar.client;

import net.somehowsurviving.phantasystar.item.custom.Musashi;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class MusashiRenderer extends GeoItemRenderer<Musashi> {
    public MusashiRenderer() {
        super(new MusashiModel());
    }
}
