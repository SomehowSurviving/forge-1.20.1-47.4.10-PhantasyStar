package net.somehowsurviving.phantasystar.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.somehowsurviving.phantasystar.client.KillCountGeoSwordRenderer;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.RenderUtils;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class KillCountGeoSwordItem extends SwordItem implements IKillTrackerItem, GeoItem {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    private final Supplier<Item> evolutionItem;
    private final int killsRequired;

    public KillCountGeoSwordItem(Tier pTier, int attackDamage, float pAttackSpeedModifier, Properties pProperties, Supplier<Item> evolutionItem, int killsRequired) {
        super(pTier, attackDamage, pAttackSpeedModifier, pProperties);
        this.evolutionItem = evolutionItem;
        this.killsRequired = killsRequired;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {

        String key = pStack.getItem().getDescriptionId();
        pTooltipComponents.add(Component.translatable(key + ".tooltip"));
        int kills = pStack.getOrCreateTag().getInt("kills");

        pTooltipComponents.add(Component.literal("Enemies killed: " + kills + "/" + killsRequired).withStyle(ChatFormatting.DARK_RED));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public int getKillsRequired() {
        return killsRequired;
    }

    @Override
    public ItemStack getEvolutionResult(ItemStack original) {
        return new ItemStack(evolutionItem.get());
    }

    private PlayState predicate(AnimationState animationState) {
        animationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object itemStack) {
        return RenderUtils.getCurrentTick();
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private KillCountGeoSwordRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if(this.renderer == null) {
                    renderer = new KillCountGeoSwordRenderer();
                }

                return this.renderer;
            }
        });
    }
}
