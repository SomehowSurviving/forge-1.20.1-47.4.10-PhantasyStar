package net.somehowsurviving.phantasystar.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.somehowsurviving.phantasystar.PhantasyStar;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, PhantasyStar.MOD_ID);

    public static final RegistryObject<SoundEvent> MATE_ITEM_CONSUME = registerSoundEvents("mate_item_consume");
    public static final RegistryObject<SoundEvent> BUTTON_PRESS = registerSoundEvents("button_press");
    public static final RegistryObject<SoundEvent> RARE_DROP = registerSoundEvents("rare_drop");


    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(PhantasyStar.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
