package net.somehowsurviving.phantasystar.events;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.somehowsurviving.phantasystar.item.custom.MagItem;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

@Mod.EventBusSubscriber
public class MagPhotonEvents {

    @SubscribeEvent
    public static void onPlayerHurt(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (player.level().isClientSide) return;

        CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
            var stacksHandler = handler.getCurios().get("mag");
            if (stacksHandler == null) return;

            IDynamicStackHandler stacks = stacksHandler.getStacks();

            for (int i = 0; i < stacks.getSlots(); i++) {
                ItemStack stack = stacks.getStackInSlot(i);

                if (stack.getItem() instanceof MagItem mag) {
                    mag.onPlayerDamaged(player, stack, event);
                }
            }
        });
    }
}
