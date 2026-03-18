package net.somehowsurviving.phantasystar.events;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.somehowsurviving.phantasystar.item.custom.SpecialRollModWeapon;
import net.somehowsurviving.phantasystar.utils.WeaponUtils;

import java.util.Random;

@Mod.EventBusSubscriber
public class SpecialWeaponPickEvents {

    private static final Random RANDOM = new Random();

    @SubscribeEvent
    public static void onPickup(EntityItemPickupEvent event) {

        ItemStack stack = event.getItem().getItem();
        if (!(stack.getItem() instanceof SpecialRollModWeapon)) return;

        CompoundTag tag = stack.getOrCreateTag();

        if (!tag.contains("special") && !tag.contains("hidden_special")) {
            WeaponUtils.rollHiddenSpecial(stack);
        }
    }
}
