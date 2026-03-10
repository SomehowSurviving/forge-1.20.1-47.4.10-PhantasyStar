package net.somehowsurviving.phantasystar.item;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.RegistryObject;

public class WeaponCraftContainerItem extends Item {
    private final RegistryObject<Item> mainhandWeapon;
    private final RegistryObject<Item> offhandWeapon;

    public WeaponCraftContainerItem(RegistryObject<Item> mainhandWeapon, RegistryObject<Item> offhandWeapon, Properties properties) {
        super(properties);
        this.mainhandWeapon = mainhandWeapon;
        this.offhandWeapon = offhandWeapon;
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level world, Player player) {
        if (world.isClientSide) return;

        int count = stack.getCount();
        stack.shrink(count);

        for (int i = 0; i < count; i++) {
            giveOrDrop(player, new ItemStack(mainhandWeapon.get()));
            giveOrDrop(player, new ItemStack(offhandWeapon.get()));
        }
    }

    private void giveOrDrop(Player player, ItemStack stack) {
        if (!player.getInventory().add(stack)) {
            player.drop(stack, false);
        }
    }
}