package net.somehowsurviving.phantasystar;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.somehowsurviving.phantasystar.block.ModBlocks;
import net.somehowsurviving.phantasystar.client.GeoBulletRenderer;
import net.somehowsurviving.phantasystar.client.GeoLauncherProjectileRenderer;
import net.somehowsurviving.phantasystar.client.MagItemCurioRenderer;
import net.somehowsurviving.phantasystar.entities.ModEntities;
import net.somehowsurviving.phantasystar.item.ModCreativeModeTabs;
import net.somehowsurviving.phantasystar.item.ModItems;
import net.somehowsurviving.phantasystar.sound.ModSounds;
import org.slf4j.Logger;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(PhantasyStar.MOD_ID)
public class PhantasyStar {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "phantasystar";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public PhantasyStar(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModSounds.register(modEventBus);
        ModEntities.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);

        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private static final Map<UUID, Integer> lingerMap = new HashMap<>();

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code

        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

            CuriosRendererRegistry.register(ModItems.BASE_MAG.get(), () -> new MagItemCurioRenderer(1.0f, 0.1f, 0.75f, 0.4f, 0.4f, 0.4f, 180f, 180f));
            CuriosRendererRegistry.register(ModItems.VARUNA_MAG.get(), () -> new MagItemCurioRenderer(1.0f, 0.1f, 0.75f, 0.4f, 0.4f, 0.4f, 180f, 180f));
            CuriosRendererRegistry.register(ModItems.KALKI_MAG.get(), () -> new MagItemCurioRenderer(0.0f, -0.1f, 0.5f, 0.7f, 0.7f, 0.7f, 180f, 180f));
            CuriosRendererRegistry.register(ModItems.VRITRA_MAG.get(), () -> new MagItemCurioRenderer(1.0f, -0.2f, 0.75f, 0.4f, 0.4f, 0.4f, 180f, 180f));
            CuriosRendererRegistry.register(ModItems.RUDRA_MAG.get(), () -> new MagItemCurioRenderer(0.0f, 0.1f, 0.6f, 1.0f, 1.0f, 1.0f, 180f, 180f));
            CuriosRendererRegistry.register(ModItems.MITRA_MAG.get(), () -> new MagItemCurioRenderer(0.8f, 0.2f, 0.75f, 0.5f, 0.5f, 0.5f, 180f, 160f));
            CuriosRendererRegistry.register(ModItems.VARAHA_MAG.get(), () -> new MagItemCurioRenderer(0.0f, 0.3f, 0.4f, 0.8f, 1.0f, 1.0f, 180f, 180f));

            EntityRenderers.register(ModEntities.BULLET_GREEN.get(), GeoBulletRenderer::new);
            EntityRenderers.register(ModEntities.BULLET_BLUE.get(), GeoBulletRenderer::new);
            EntityRenderers.register(ModEntities.BULLET_PINK.get(), GeoBulletRenderer::new);
            EntityRenderers.register(ModEntities.BULLET_RED.get(), GeoBulletRenderer::new);
            EntityRenderers.register(ModEntities.BULLET_YELLOW.get(), GeoBulletRenderer::new);

            EntityRenderers.register(ModEntities.MECHGUN_BULLET_GREEN.get(), GeoBulletRenderer::new);
            EntityRenderers.register(ModEntities.MECHGUN_BULLET_BLUE.get(), GeoBulletRenderer::new);
            EntityRenderers.register(ModEntities.MECHGUN_BULLET_PINK.get(), GeoBulletRenderer::new);
            EntityRenderers.register(ModEntities.MECHGUN_BULLET_RED.get(), GeoBulletRenderer::new);
            EntityRenderers.register(ModEntities.MECHGUN_BULLET_YELLOW.get(), GeoBulletRenderer::new);

            EntityRenderers.register(ModEntities.LAUNCHER_PROJECTILE_GREEN.get(), GeoLauncherProjectileRenderer::new);
            EntityRenderers.register(ModEntities.LAUNCHER_PROJECTILE_BLUE.get(), GeoLauncherProjectileRenderer::new);
            EntityRenderers.register(ModEntities.LAUNCHER_PROJECTILE_RED.get(), GeoLauncherProjectileRenderer::new);
            LOGGER.info("HELLO FROM PIONEER 2");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
