package net.somehowsurviving.phantasystar.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.somehowsurviving.phantasystar.PhantasyStar;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PhantasyStar.MOD_ID);

    public static final RegistryObject<Item> PHOTON_DROP = ITEMS.register("photon_drop",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ITEM_DROP = ITEMS.register("item_drop",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WEAPON_DROP = ITEMS.register("weapon_drop",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ARMOR_DROP = ITEMS.register("armor_drop",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MESETA = ITEMS.register("meseta",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
