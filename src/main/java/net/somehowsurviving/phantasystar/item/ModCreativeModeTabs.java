package net.somehowsurviving.phantasystar.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.somehowsurviving.phantasystar.PhantasyStar;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PhantasyStar.MOD_ID);

    public static final RegistryObject<CreativeModeTab> PHANTASY_STAR = CREATIVE_MODE_TABS.register("phantasy_star_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.PHOTON_DROP.get()))
                    .title(Component.translatable("creativetab.phantasy_star_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.MESETA.get());
                        output.accept(ModItems.PHOTON_DROP.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
