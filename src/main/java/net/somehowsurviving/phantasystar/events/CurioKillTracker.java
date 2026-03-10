package net.somehowsurviving.phantasystar.events;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.somehowsurviving.phantasystar.PhantasyStar;
import net.somehowsurviving.phantasystar.item.custom.IKillTrackerItem;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

@Mod.EventBusSubscriber
public class CurioKillTracker {

    @SubscribeEvent
    public static void onEntityKilled(LivingDeathEvent event) {

        if (!(event.getSource().getEntity() instanceof Player player)) return;

        trackInventoryKills(player);
        trackCurioKills(player);
    }

    private static void trackInventoryKills(Player player) {

        for (ItemStack stack : player.getInventory().items) {

            if (!(stack.getItem() instanceof IKillTrackerItem tracker)) continue;

            addKill(stack, tracker);

            if (shouldTransform(stack, tracker)) {
                player.getInventory().removeItem(stack);
                player.getInventory().add(tracker.getEvolutionResult(stack));
            }
        }
    }

    private static void trackCurioKills(Player player) {

        CuriosApi.getCuriosInventory(player).ifPresent(handler -> {

            handler.getCurios().forEach((id, stacksHandler) -> {

                IDynamicStackHandler stacks = stacksHandler.getStacks();

                for (int i = 0; i < stacks.getSlots(); i++) {

                    ItemStack stack = stacks.getStackInSlot(i);

                    if (!(stack.getItem() instanceof IKillTrackerItem tracker)) continue;

                    addKill(stack, tracker);

                    if (shouldTransform(stack, tracker)) {
                        stacks.setStackInSlot(i, tracker.getEvolutionResult(stack));
                    }
                }
            });
        });
    }

    private static void addKill(ItemStack stack, IKillTrackerItem tracker) {

        CompoundTag tag = stack.getOrCreateTag();

        int kills = tag.getInt("kills") + 1;

        tag.putInt("kills", kills);
    }

    private static boolean shouldTransform(ItemStack stack, IKillTrackerItem tracker) {

        int kills = stack.getOrCreateTag().getInt("kills");

        return kills >= tracker.getKillsRequired();
    }
}
