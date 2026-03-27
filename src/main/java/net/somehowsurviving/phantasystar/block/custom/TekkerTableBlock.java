package net.somehowsurviving.phantasystar.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.somehowsurviving.phantasystar.item.custom.SpecialRollModWeapon;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TekkerTableBlock extends Block {
    public TekkerTableBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos,
                                 Player player, InteractionHand hand, BlockHitResult hit) {

        ItemStack stack = player.getItemInHand(hand);
        if (!stack.isEmpty() && !level.isClientSide) {
            CompoundTag tag = stack.getOrCreateTag();

            // Only act if the item has "hidden_special" and isn't yet identified
            if (tag.contains("hidden_special") && !tag.getBoolean("identified")) {
                // Move the hidden_special to special
                tag.put("special", tag.get("hidden_special"));
                tag.remove("hidden_special");

                // Mark as identified
                tag.putBoolean("identified", true);

                // Update display name if your weapon class has it
                if (stack.getItem() instanceof SpecialRollModWeapon weapon) {
                    weapon.getName(stack);
                }

                // Feedback to player
                player.displayClientMessage(Component.translatable("item.phantasystar.identify"), true);
                level.playSound(null, pos, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.PLAYERS, 1f, 1f);

                // Optional particle effect
                for (int i = 0; i < 10; i++) {
                    double offsetX = (level.random.nextDouble() - 0.5) * 0.5;
                    double offsetY = level.random.nextDouble() * 0.5 + 0.5;
                    double offsetZ = (level.random.nextDouble() - 0.5) * 0.5;
                    level.addParticle(ParticleTypes.ENCHANT, player.getX() + offsetX,
                            player.getY() + offsetY, player.getZ() + offsetZ,
                            0, 0.05, 0);
                }
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

}
