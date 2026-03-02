package net.somehowsurviving.phantasystar.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.somehowsurviving.phantasystar.PhantasyStar;
import net.somehowsurviving.phantasystar.block.ModBlocks;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PhantasyStar.MOD_ID);

    public static final RegistryObject<CreativeModeTab> PHANTASY_STAR = CREATIVE_MODE_TABS.register("phantasy_star_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.CREATIVE_TAB.get()))
                    .title(Component.translatable("creativetab.phantasy_star_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.MESETA.get());
                        output.accept(ModItems.PHOTON_DROP.get());
                        output.accept(ModItems.ITEM_DROP.get());
                        output.accept(ModItems.WEAPON_DROP.get());
                        output.accept(ModItems.ARMOR_DROP.get());
                        output.accept(ModItems.MONOMATE.get());
                        output.accept(ModItems.DIMATE.get());
                        output.accept(ModItems.TRIMATE.get());
                        output.accept(ModItems.METAL_DETECTOR.get());
                        output.accept(ModItems.MUSASHI.get());
                        output.accept(ModItems.FLOWEN_SWORD_REPLICA.get());

                        output.accept(ModBlocks.PHOTON_CRYSTAL.get());
                        output.accept(ModBlocks.PHOTON_CRYSTAL_ORE.get());
                        output.accept(ModBlocks.FOREST_BOX_NORMAL.get());
                        output.accept(ModBlocks.MINES_HEX_FLOOR.get());
                        output.accept(ModBlocks.MINES_OCT_FLOOR.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
