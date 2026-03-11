package net.somehowsurviving.phantasystar.events;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.somehowsurviving.phantasystar.item.custom.IKillTrackerItem;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

@Mod.EventBusSubscriber
public class KillTrackerEvents {

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {

        if (!(event.getSource().getEntity() instanceof Player player)) return;

        handleItem(player.getMainHandItem(), player);

        handleItem(player.getOffhandItem(), player);

        CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
            handler.getCurios().forEach((id, stacksHandler) -> {
                IDynamicStackHandler stacks = stacksHandler.getStacks();

                for (int i = 0; i < stacks.getSlots(); i++) {
                    handleItem(stacks.getStackInSlot(i), player);
                }
            });
        });
    }

    private static void handleItem(ItemStack stack, Player player) {

        if (!(stack.getItem() instanceof IKillTrackerItem tracker)) return;

        tracker.onKill(stack, player);
    }
}
