package net.somehowsurviving.phantasystar.item;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.somehowsurviving.phantasystar.PhantasyStar;
import net.somehowsurviving.phantasystar.item.custom.*;
import net.somehowsurviving.phantasystar.item.custom.units.*;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PhantasyStar.MOD_ID);

    public static final RegistryObject<Item> CREATIVE_TAB = ITEMS.register("creative_tab",
            () -> new Item(new Item.Properties()));

    // Items
    public static final RegistryObject<Item> PHOTON_DROP = ITEMS.register("photon_drop",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ITEM_DROP = ITEMS.register("item_drop",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WEAPON_DROP = ITEMS.register("weapon_drop",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ARMOR_DROP = ITEMS.register("armor_drop",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MESETA = ITEMS.register("meseta",
            () -> new Item(new Item.Properties().stacksTo(10000)));
    public static final RegistryObject<Item> MONOMATE = ITEMS.register("monomate",
            () -> new MateItem(new Item.Properties().stacksTo(10), 8.0F));
    public static final RegistryObject<Item> DIMATE = ITEMS.register("dimate",
            () -> new MateItem(new Item.Properties().stacksTo(10), 12.0F));
    public static final RegistryObject<Item> TRIMATE = ITEMS.register("trimate",
            () -> new MateItem(new Item.Properties().stacksTo(10), 16.0F));
    public static final RegistryObject<Item> ANTIDOTE = ITEMS.register("antidote",
            () -> new StatusHealItem(new Item.Properties().stacksTo(10), MobEffects.POISON));
    public static final RegistryObject<Item> ANTIPARALYSIS = ITEMS.register("antiparalysis",
            () -> new StatusHealItem(new Item.Properties().stacksTo(10), MobEffects.DIG_SLOWDOWN));
    public static final RegistryObject<Item> SOL_ATOMIZER = ITEMS.register("sol_atomizer",
            () -> new SolAtomizerItem(new Item.Properties().stacksTo(10)));
    public static final RegistryObject<Item> STAR_ATOMIZER = ITEMS.register("star_atomizer",
            () -> new StarAtomizerItem(new Item.Properties().stacksTo(10)));
    // Units
    public static final RegistryObject<Item> WARRIOR_BODY = ITEMS.register("warrior_body",
            () -> new BodyUnitItem(new Item.Properties().stacksTo(1), 1.0F, 0.0F));
    public static final RegistryObject<Item> GENERAL_BODY = ITEMS.register("general_body",
            () -> new BodyUnitItem(new Item.Properties().stacksTo(1), 2.0F, 0.0F));
    public static final RegistryObject<Item> METAL_BODY = ITEMS.register("metal_body",
            () -> new BodyUnitItem(new Item.Properties().stacksTo(1), 3.0F, 0.0F));
    public static final RegistryObject<Item> GOD_BODY = ITEMS.register("god_body",
            () -> new BodyUnitItem(new Item.Properties().stacksTo(1), 4.0F, 1.0F));
    public static final RegistryObject<Item> HEAVENLY_BODY = ITEMS.register("heavenly_body",
            () -> new BodyUnitItem(new Item.Properties().stacksTo(1), 5.0F, 2.0F));
    public static final RegistryObject<Item> All_RESIST = ITEMS.register("all_resist",
            () -> new ResistUnitItem(new Item.Properties().stacksTo(1), 0.1F));
    public static final RegistryObject<Item> SUPER_RESIST = ITEMS.register("super_resist",
            () -> new ResistUnitItem(new Item.Properties().stacksTo(1), 0.2F));
    public static final RegistryObject<Item> PERFECT_RESIST = ITEMS.register("perfect_resist",
            () -> new ResistUnitItem(new Item.Properties().stacksTo(1), 0.35F));
    public static final RegistryObject<Item> HEAVENLY_RESIST = ITEMS.register("heavenly_resist",
            () -> new ResistUnitItem(new Item.Properties().stacksTo(1), 0.5F));
    public static final RegistryObject<Item> DIGGER_HP = ITEMS.register("digger_hp",
            () -> new HPUnitItem(new Item.Properties().stacksTo(1), 2.0F));
    public static final RegistryObject<Item> GENERAL_HP = ITEMS.register("general_hp",
            () -> new HPUnitItem(new Item.Properties().stacksTo(1), 4.0F));
    public static final RegistryObject<Item> DRAGON_HP = ITEMS.register("dragon_hp",
            () -> new HPUnitItem(new Item.Properties().stacksTo(1), 6.0F));
    public static final RegistryObject<Item> GOD_HP = ITEMS.register("god_hp",
            () -> new HPUnitItem(new Item.Properties().stacksTo(1), 8.0F));
    public static final RegistryObject<Item> HEAVENLY_HP = ITEMS.register("heavenly_hp",
            () -> new HPUnitItem(new Item.Properties().stacksTo(1), 10.0F));
    public static final RegistryObject<Item> KNIGHT_POWER = ITEMS.register("knight_power",
            () -> new PowerUnitItem(new Item.Properties().stacksTo(1), 1.0F));
    public static final RegistryObject<Item> GENERAL_POWER = ITEMS.register("general_power",
            () -> new PowerUnitItem(new Item.Properties().stacksTo(1), 2.0F));
    public static final RegistryObject<Item> OGRE_POWER = ITEMS.register("ogre_power",
            () -> new PowerUnitItem(new Item.Properties().stacksTo(1), 3.0F));
    public static final RegistryObject<Item> GOD_POWER = ITEMS.register("god_power",
            () -> new PowerUnitItem(new Item.Properties().stacksTo(1), 4.0F));
    public static final RegistryObject<Item> HEAVENLY_POWER = ITEMS.register("heavenly_power",
            () -> new PowerUnitItem(new Item.Properties().stacksTo(1), 5.0F));
    public static final RegistryObject<Item> THIEF_LEGS = ITEMS.register("thief_legs",
            () -> new LegsUnitItem(new Item.Properties().stacksTo(1), 0.05F));
    public static final RegistryObject<Item> GENERAL_LEGS = ITEMS.register("general_legs",
            () -> new LegsUnitItem(new Item.Properties().stacksTo(1), 0.1F));
    public static final RegistryObject<Item> ELF_LEGS = ITEMS.register("elf_legs",
            () -> new LegsUnitItem(new Item.Properties().stacksTo(1), 0.15F));
    public static final RegistryObject<Item> GOD_LEGS = ITEMS.register("god_legs",
            () -> new LegsUnitItem(new Item.Properties().stacksTo(1), 0.2F));
    public static final RegistryObject<Item> HEAVENLY_LEGS = ITEMS.register("heavenly_legs",
            () -> new LegsUnitItem(new Item.Properties().stacksTo(1), 0.25F));
    public static final RegistryObject<Item> GENERAL_BATTLE = ITEMS.register("general_battle",
            () -> new BattleUnitItem(new Item.Properties().stacksTo(1), 0.03F));
    public static final RegistryObject<Item> DEVIL_BATTLE = ITEMS.register("devil_battle",
            () -> new BattleUnitItem(new Item.Properties().stacksTo(1), 0.05F));
    public static final RegistryObject<Item> GOD_BATTLE = ITEMS.register("god_battle",
            () -> new BattleUnitItem(new Item.Properties().stacksTo(1), 0.08F));
    public static final RegistryObject<Item> HEAVENLY_BATTLE = ITEMS.register("heavenly_battle",
            () -> new BattleUnitItem(new Item.Properties().stacksTo(1), 0.12F));
    public static final RegistryObject<Item> V101 = ITEMS.register("v101",
            () -> new V101UnitItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> ADEPT = ITEMS.register("adept",
            () -> new AdeptUnitItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> SWORD_SAINT = ITEMS.register("proof_of_sword_saint",
            () -> new SwordSaintUnitItem(new Item.Properties().stacksTo(1)));
    // Transforming Units
    public static final RegistryObject<Item> LIMITER = ITEMS.register("poss_limiter",
            () -> new KillCountTransformItem(new Item.Properties().stacksTo(1), -4.0f,  -0.1f, -0.05f, -4.0f, 0.0f, 0.0f, ModItems.ADEPT, 1000));
    public static final RegistryObject<Item> SWORDSMAN_LORE = ITEMS.register("swordsman_lore",
            () -> new KillCountTransformItem(new Item.Properties().stacksTo(1), 0.0f,  0.1f, 0.05f, 0.0f, 0.0f, 0.0f, ModItems.SWORD_SAINT, 1000));

    // Basic Weapons
    public static final RegistryObject<Item> SABER = ITEMS.register("saber",
            () -> new SpecialRollModWeapon(Tiers.IRON, 3, -2.4f, new Item.Properties()));
    public static final RegistryObject<Item> BRAND = ITEMS.register("brand",
            () -> new SpecialRollModWeapon(Tiers.IRON, 4, -2.4f, new Item.Properties()));
    public static final RegistryObject<Item> BUSTER = ITEMS.register("buster",
            () -> new SpecialRollModWeapon(Tiers.IRON, 5, -2.4f, new Item.Properties()));
    public static final RegistryObject<Item> PALLASCH = ITEMS.register("pallasch",
            () -> new SpecialRollModWeapon(Tiers.DIAMOND, 5, -2.4f, new Item.Properties()));
    public static final RegistryObject<Item> GLADIUS = ITEMS.register("gladius",
            () -> new SpecialRollModWeapon(Tiers.DIAMOND, 6, -2.4f, new Item.Properties()));
    public static final RegistryObject<Item> DAGGER = ITEMS.register("dagger",
            () -> new SpecialRollModWeapon(Tiers.IRON, 2, -2.3f, new Item.Properties()));
    public static final RegistryObject<Item> KNIFE = ITEMS.register("knife",
            () -> new SpecialRollModWeapon(Tiers.IRON, 3, -2.3f, new Item.Properties()));
    public static final RegistryObject<Item> BLADE = ITEMS.register("blade",
            () -> new SpecialRollModWeapon(Tiers.IRON, 4, -2.3f, new Item.Properties()));
    public static final RegistryObject<Item> EDGE = ITEMS.register("edge",
            () -> new SpecialRollModWeapon(Tiers.DIAMOND, 4, -2.3f, new Item.Properties()));
    public static final RegistryObject<Item> RIPPER = ITEMS.register("ripper",
            () -> new SpecialRollModWeapon(Tiers.DIAMOND, 5, -2.3f, new Item.Properties()));
    public static final RegistryObject<Item> SWORD = ITEMS.register("sword",
            () -> new SpecialRollModWeapon(Tiers.IRON, 2, -3.0f, new Item.Properties()));
    public static final RegistryObject<Item> GIGUSH = ITEMS.register("gigush",
            () -> new SpecialRollModWeapon(Tiers.IRON, 3, -3.0f, new Item.Properties()));
    public static final RegistryObject<Item> BREAKER = ITEMS.register("breaker",
            () -> new SpecialRollModWeapon(Tiers.IRON, 4, -3.0f, new Item.Properties()));
    public static final RegistryObject<Item> CLAYMORE = ITEMS.register("claymore",
            () -> new SpecialRollModWeapon(Tiers.DIAMOND, 4, -3.0f, new Item.Properties()));
    public static final RegistryObject<Item> CALIBUR = ITEMS.register("calibur",
            () -> new SpecialRollModWeapon(Tiers.DIAMOND, 5, -3.0f, new Item.Properties()));
    public static final RegistryObject<Item> PARTISAN = ITEMS.register("partisan",
            () -> new SpecialRollModWeapon(Tiers.IRON, 2, -2.8f, new Item.Properties()));
    public static final RegistryObject<Item> HALBERT = ITEMS.register("halbert",
            () -> new SpecialRollModWeapon(Tiers.IRON, 3, -2.8f, new Item.Properties()));
    public static final RegistryObject<Item> GLAIVE = ITEMS.register("glaive",
            () -> new SpecialRollModWeapon(Tiers.IRON, 4, -2.8f, new Item.Properties()));
    public static final RegistryObject<Item> BERDYS = ITEMS.register("berdys",
            () -> new SpecialRollModWeapon(Tiers.DIAMOND, 4, -2.8f, new Item.Properties()));
    public static final RegistryObject<Item> GUNGNIR = ITEMS.register("gungnir",
            () -> new SpecialRollModWeapon(Tiers.DIAMOND, 5, -2.8f, new Item.Properties()));

    // Rare Weapons
    public static final RegistryObject<Item> FLOWEN_SWORD_REPLICA = ITEMS.register("flowen_sword_replica",
            () -> new ModWeapon(Tiers.DIAMOND, 7, -3.0f, new Item.Properties()));
    public static final RegistryObject<Item> GUREN = ITEMS.register("guren",
            () -> new ModWeapon(Tiers.NETHERITE, 11, -2.6f, new Item.Properties()));
    public static final RegistryObject<Item> SHOUREN = ITEMS.register("shouren",
            () -> new ModWeapon(Tiers.NETHERITE, 11, -2.6f, new Item.Properties()));
    public static final RegistryObject<Item> JIZAI_GUREN = ITEMS.register("jizai_guren",
            () -> new ModWeapon(Tiers.NETHERITE, 12, -2.6f, new Item.Properties()));
    public static final RegistryObject<Item> JIZAI_SHOUREN = ITEMS.register("jizai_shouren",
            () -> new ModWeapon(Tiers.NETHERITE, 12, -2.6f, new Item.Properties()));
    // Gecko Weapons
    public static final RegistryObject<Item> DB_SABER = ITEMS.register("db_saber",
            () -> new GeoSwordItem(Tiers.IRON, 3, -2.4f, new Item.Properties()));
    public static final RegistryObject<Item> MUSASHI = ITEMS.register("musashi",
            () -> new GeoSwordItem(Tiers.IRON, 6, -2.4f, new Item.Properties()));
    public static final RegistryObject<Item> LAST_SURVIVOR = ITEMS.register("last_survivor",
            () -> new GeoSwordItem(Tiers.DIAMOND, 9, -3.2f, new Item.Properties()));
    public static final RegistryObject<Item> DRAGON_SLAYER = ITEMS.register("dragon_slayer",
            () -> new GeoSwordItem(Tiers.DIAMOND, 10, -3.2f, new Item.Properties()));
    public static final RegistryObject<Item> VICTOR_AXE = ITEMS.register("victor_axe",
            () -> new GeoSwordItem(Tiers.DIAMOND, 10, -2.6f, new Item.Properties()));
    public static final RegistryObject<Item> YAMATO = ITEMS.register("yamato",
            () -> new GeoSwordItem(Tiers.DIAMOND, 7, -2.4f, new Item.Properties()));
    public static final RegistryObject<Item> ASUKA = ITEMS.register("asuka",
            () -> new GeoSwordItem(Tiers.NETHERITE, 9, -2.6f, new Item.Properties()));
    public static final RegistryObject<Item> SOUL_EATER = ITEMS.register("soul_eater",
            () -> new GeoSwordItem(Tiers.DIAMOND, 8, -3.0f, new Item.Properties()));
    public static final RegistryObject<Item> SOUL_BANISH = ITEMS.register("soul_banish",
            () -> new GeoSwordItem(Tiers.NETHERITE, 13, -3.0f, new Item.Properties()));
    public static final RegistryObject<Item> EXCALIBUR = ITEMS.register("excalibur",
            () -> new GeoSwordItem(Tiers.NETHERITE, 13, -2.6f, new Item.Properties()));
    // Special Weapon Containers
    public static final RegistryObject<Item> JIZAI_CONTAINER = ITEMS.register("jizai_container",
            () -> new WeaponCraftContainerItem(ModItems.JIZAI_GUREN, ModItems.JIZAI_SHOUREN,
                    new Item.Properties().stacksTo(1)));
    // Transform Weapons
    public static final RegistryObject<Item> LAME_DARGENT = ITEMS.register("lame_dargent",
            () -> new KillCountGeoSwordItem(Tiers.NETHERITE, 8, -2.2f, new Item.Properties(), ModItems.EXCALIBUR, 1000));

    // Frames and Armors
    public static final RegistryObject<Item> FRAME = ITEMS.register("frame",
            () -> new ModArmorItem(ModArmorMaterials.PSO, ArmorItem.Type.CHESTPLATE, new Item.Properties(), 3));
    public static final RegistryObject<Item> ARMOR = ITEMS.register("armor",
            () -> new ModArmorItem(ModArmorMaterials.PSO, ArmorItem.Type.CHESTPLATE, new Item.Properties(), 4));
    // Mags
    public static final RegistryObject<Item> BASE_MAG = ITEMS.register("base_mag",
            () -> new MagItem(new Item.Properties(), 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
    public static final RegistryObject<Item> VARUNA_MAG = ITEMS.register("varuna",
            () -> new MagItem(new Item.Properties(), 1.0f, 0.0f, 2.0f, 0.0f, 0.0f));
    public static final RegistryObject<Item> KALKI_MAG = ITEMS.register("kalki",
            () -> new MagItem(new Item.Properties(), 0.0f, 0.10f, 2.0f, 0.0f, 0.0f));
    public static final RegistryObject<Item> VRITRA_MAG = ITEMS.register("vritra",
            () -> new MagItem(new Item.Properties(), 0.0f, 0.0f, 2.0f, 0.05f, 1.0f));
    public static final RegistryObject<Item> RUDRA_MAG = ITEMS.register("rudra",
            () -> new MagItem(new Item.Properties(), 2.0f, 0.08f, 3.0f, 0.0f, 0.0f));
    public static final RegistryObject<Item> MITRA_MAG = ITEMS.register("mitra",
            () -> new MagItem(new Item.Properties(), 1.0f, 0.15f, 3.0f, 0.0f, 0.0f));
    public static final RegistryObject<Item> VARAHA_MAG = ITEMS.register("varaha",
            () -> new MagItem(new Item.Properties(), 4.0f, 0.15f, 4.0f, 0.0f, 0.0f));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
