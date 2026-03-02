package net.somehowsurviving.phantasystar.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.somehowsurviving.phantasystar.PhantasyStar;
import net.somehowsurviving.phantasystar.item.custom.MateItem;
import net.somehowsurviving.phantasystar.item.custom.MetalDetectorItem;
import net.somehowsurviving.phantasystar.item.custom.ModWeapon;
import net.somehowsurviving.phantasystar.item.custom.Musashi;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PhantasyStar.MOD_ID);

    public static final RegistryObject<Item> CREATIVE_TAB = ITEMS.register("creative_tab",
            () -> new Item(new Item.Properties()));

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

    public static final RegistryObject<Item> MONOMATE = ITEMS.register("monomate",
            () -> new MateItem(new Item.Properties().stacksTo(10), 8.0F));

    public static final RegistryObject<Item> DIMATE = ITEMS.register("dimate",
            () -> new MateItem(new Item.Properties().stacksTo(10), 12.0F));

    public static final RegistryObject<Item> TRIMATE = ITEMS.register("trimate",
            () -> new MateItem(new Item.Properties().stacksTo(10), 16.0F));

    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector",
            () -> new MetalDetectorItem(new Item.Properties().durability(100)));
    // Weapons
    public static final RegistryObject<Item> FLOWEN_SWORD_REPLICA = ITEMS.register("flowen_sword_replica",
            () -> new ModWeapon(Tiers.DIAMOND, 10, -2.8f, new Item.Properties()));


    //Gecko
    public static final RegistryObject<Item> MUSASHI = ITEMS.register("musashi",
            () -> new Musashi(Tiers.IRON, 7, -2.0f, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
