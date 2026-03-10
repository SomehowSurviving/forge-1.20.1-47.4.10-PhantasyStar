package net.somehowsurviving.phantasystar.utils;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.somehowsurviving.phantasystar.item.ModItems;

public class CurrencyUtils {

    public static int countMeseta(Player player) {
        int total = 0;

        for (ItemStack stack : player.getInventory().items) {
            if (stack.is(ModItems.MESETA.get())) {
                total += stack.getCount();
            }
        }

        return total;
    }

    public static void removeMeseta(Player player, int amount) {

        for (ItemStack stack : player.getInventory().items) {

            if (stack.is(ModItems.MESETA.get())) {

                int remove = Math.min(amount, stack.getCount());

                stack.shrink(remove);
                amount -= remove;

                if (amount <= 0) return;
            }
        }
    }
}
