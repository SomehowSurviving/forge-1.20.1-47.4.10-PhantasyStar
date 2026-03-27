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
                        // Items
                        output.accept(ModItems.MESETA.get());
                        output.accept(ModItems.PHOTON_DROP.get());
                        output.accept(ModItems.ITEM_DROP.get());
                        output.accept(ModItems.WEAPON_DROP.get());
                        output.accept(ModItems.ARMOR_DROP.get());
                        output.accept(ModItems.MONOMATE.get());
                        output.accept(ModItems.DIMATE.get());
                        output.accept(ModItems.TRIMATE.get());
                        output.accept(ModItems.ANTIDOTE.get());
                        output.accept(ModItems.ANTIPARALYSIS.get());
                        output.accept(ModItems.SOL_ATOMIZER.get());
                        output.accept(ModItems.STAR_ATOMIZER.get());
                        // Weapons
                        output.accept(ModItems.SABER.get());
                        output.accept(ModItems.BRAND.get());
                        output.accept(ModItems.BUSTER.get());
                        output.accept(ModItems.PALLASCH.get());
                        output.accept(ModItems.GLADIUS.get());
                        output.accept(ModItems.DAGGER.get());
                        output.accept(ModItems.KNIFE.get());
                        output.accept(ModItems.BLADE.get());
                        output.accept(ModItems.EDGE.get());
                        output.accept(ModItems.RIPPER.get());
                        output.accept(ModItems.SWORD.get());
                        output.accept(ModItems.GIGUSH.get());
                        output.accept(ModItems.BREAKER.get());
                        output.accept(ModItems.CLAYMORE.get());
                        output.accept(ModItems.CALIBUR.get());
                        output.accept(ModItems.PARTISAN.get());
                        output.accept(ModItems.HALBERT.get());
                        output.accept(ModItems.GLAIVE.get());
                        output.accept(ModItems.BERDYS.get());
                        output.accept(ModItems.GUNGNIR.get());
                        output.accept(ModItems.DB_SABER.get());
                        output.accept(ModItems.FLOWEN_SWORD_REPLICA.get());
                        output.accept(ModItems.FLOWEN_SWORD_3084.get());
                        output.accept(ModItems.MUSASHI.get());
                        output.accept(ModItems.LAST_SURVIVOR.get());
                        output.accept(ModItems.DRAGON_SLAYER.get());
                        output.accept(ModItems.VICTOR_AXE.get());
                        output.accept(ModItems.YAMATO.get());
                        output.accept(ModItems.ASUKA.get());
                        output.accept(ModItems.SOUL_EATER.get());
                        output.accept(ModItems.SOUL_BANISH.get());
                        output.accept(ModItems.SANGE.get());
                        output.accept(ModItems.YASHA.get());
                        output.accept(ModItems.SAY_SANGE.get());
                        output.accept(ModItems.SAY_YASHA.get());
                        output.accept(ModItems.AGITO_REPLICA.get());
                        output.accept(ModItems.AGITO_1975.get());
                        output.accept(ModItems.OROTIAGITO.get());
                        output.accept(ModItems.GUREN.get());
                        output.accept(ModItems.SHOUREN.get());
                        output.accept(ModItems.JIZAI_GUREN.get());
                        output.accept(ModItems.JIZAI_SHOUREN.get());
                        output.accept(ModItems.LAME_DARGENT.get());
                        output.accept(ModItems.EXCALIBUR.get());
                        output.accept(ModItems.SEALED_J_SWORD.get());
                        output.accept(ModItems.TSUMIKIRI_J_SWORD.get());
                        // Guns
                        output.accept(ModItems.HANDGUN.get());
                        output.accept(ModItems.AUTOGUN.get());
                        output.accept(ModItems.LOCKGUN.get());
                        output.accept(ModItems.RAILGUN.get());
                        output.accept(ModItems.RAYGUN.get());
                        output.accept(ModItems.RIFLE.get());
                        output.accept(ModItems.SNIPER.get());
                        output.accept(ModItems.BLASTER.get());
                        output.accept(ModItems.BEAM.get());
                        output.accept(ModItems.LASER.get());
                        output.accept(ModItems.MECHGUN.get());
                        output.accept(ModItems.ASSAULT.get());
                        output.accept(ModItems.REPEATER.get());
                        output.accept(ModItems.GATLING.get());
                        output.accept(ModItems.VULCAN.get());
                        output.accept(ModItems.SHOT.get());
                        output.accept(ModItems.SPREAD.get());
                        output.accept(ModItems.CANNON.get());
                        output.accept(ModItems.LAUNCHER.get());
                        output.accept(ModItems.ARMS.get());
                        output.accept(ModItems.VISK.get());
                        output.accept(ModItems.MASTER_RAVEN.get());
                        output.accept(ModItems.LAST_SWAN.get());
                        output.accept(ModItems.DB_MASTER_RAVEN.get());
                        output.accept(ModItems.DB_LAST_SWAN.get());
                        output.accept(ModItems.PHOTON_LAUNCHER.get());
                        output.accept(ModItems.GUILTY_LIGHT.get());
                        output.accept(ModItems.RED_SCORPIO.get());
                        // Weapon Containers
                        output.accept(ModItems.SANGE_YASHA_CONTAINER.get());
                        output.accept(ModItems.JIZAI_CONTAINER.get());
                        output.accept(ModItems.DUAL_BIRD_CONTAINER.get());
                        // Armors
                        output.accept(ModItems.FRAME.get());
                        output.accept(ModItems.ARMOR.get());
                        // Mags
                        output.accept(ModItems.BASE_MAG.get());
                        output.accept(ModItems.VARUNA_MAG.get());
                        output.accept(ModItems.KALKI_MAG.get());
                        output.accept(ModItems.VRITRA_MAG.get());
                        output.accept(ModItems.RUDRA_MAG.get());
                        output.accept(ModItems.MITRA_MAG.get());
                        output.accept(ModItems.VARAHA_MAG.get());
                        // Units
                        output.accept(ModItems.WARRIOR_BODY.get());
                        output.accept(ModItems.GENERAL_BODY.get());
                        output.accept(ModItems.METAL_BODY.get());
                        output.accept(ModItems.GOD_BODY.get());
                        output.accept(ModItems.HEAVENLY_BODY.get());
                        output.accept(ModItems.DIGGER_HP.get());
                        output.accept(ModItems.GENERAL_HP.get());
                        output.accept(ModItems.DRAGON_HP.get());
                        output.accept(ModItems.GOD_HP.get());
                        output.accept(ModItems.HEAVENLY_HP.get());
                        output.accept(ModItems.KNIGHT_POWER.get());
                        output.accept(ModItems.GENERAL_POWER.get());
                        output.accept(ModItems.OGRE_POWER.get());
                        output.accept(ModItems.GOD_POWER.get());
                        output.accept(ModItems.HEAVENLY_POWER.get());
                        output.accept(ModItems.THIEF_LEGS.get());
                        output.accept(ModItems.GENERAL_LEGS.get());
                        output.accept(ModItems.ELF_LEGS.get());
                        output.accept(ModItems.GOD_LEGS.get());
                        output.accept(ModItems.HEAVENLY_LEGS.get());
                        output.accept(ModItems.All_RESIST.get());
                        output.accept(ModItems.SUPER_RESIST.get());
                        output.accept(ModItems.PERFECT_RESIST.get());
                        output.accept(ModItems.HEAVENLY_RESIST.get());
                        output.accept(ModItems.GENERAL_BATTLE.get());
                        output.accept(ModItems.DEVIL_BATTLE.get());
                        output.accept(ModItems.GOD_BATTLE.get());
                        output.accept(ModItems.HEAVENLY_BATTLE.get());
                        output.accept(ModItems.V101.get());
                        output.accept(ModItems.LIMITER.get());
                        output.accept(ModItems.SWORDSMAN_LORE.get());
                        output.accept(ModItems.ADEPT.get());
                        output.accept(ModItems.SWORD_SAINT.get());
                        // Blocks
                        output.accept(ModBlocks.PHOTON_CRYSTAL.get());
                        output.accept(ModBlocks.PHOTON_CRYSTAL_ORE.get());
                        output.accept(ModBlocks.FOREST_BOX_NORMAL.get());
                        output.accept(ModBlocks.CAVES_BOX_NORMAL.get());
                        output.accept(ModBlocks.MINES_BOX_NORMAL.get());
                        output.accept(ModBlocks.RUINS_BOX_NORMAL.get());
                        output.accept(ModBlocks.TEKKER_TABLE.get());

                        output.accept(ModBlocks.MINES_HEX_FLOOR.get());
                        output.accept(ModBlocks.MINES_OCT_FLOOR.get());
                        output.accept(ModBlocks.SOUND_BLOCK.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
