package net.somehowsurviving.phantasystar.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class MagItemCurioRenderer implements ICurioRenderer {

    private final float xOffset;
    private final float yOffset;
    private final float zOffset;
    private final float xScale;
    private final float yScale;
    private final float zScale;
    private final float xRot;
    private final float yRot;

    public MagItemCurioRenderer(float xOffset, float yOffset, float zOffset, float xScale, float yScale, float zScale, float xRot, float yRot) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.zOffset = zOffset;
        this.xScale = xScale;
        this.yScale = yScale;
        this.zScale = zScale;
        this.xRot = xRot;
        this.yRot = yRot;
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(
            ItemStack stack,
            SlotContext context,
            PoseStack poseStack,
            RenderLayerParent<T, M> parent,
            MultiBufferSource buffer,
            int light,
            float limbSwing,
            float limbSwingAmount,
            float partialTicks,
            float ageInTicks,
            float netHeadYaw,
            float headPitch
    ) {
        T entity = (T) context.entity();
        if (entity == null || stack.isEmpty()) return;

        poseStack.pushPose();

        float bodyYaw = net.minecraft.util.Mth.rotLerp(partialTicks, entity.yRotO, entity.getYRot());
        float pitch = net.minecraft.util.Mth.rotLerp(partialTicks, entity.xRotO, entity.getXRot());

        poseStack.scale(xScale, yScale, zScale);

        poseStack.translate(xOffset, yOffset, zOffset);

        poseStack.mulPose(com.mojang.math.Axis.XP.rotationDegrees(xRot));
        poseStack.mulPose(com.mojang.math.Axis.YP.rotationDegrees(yRot));

        Minecraft.getInstance().getItemRenderer().renderStatic(
                stack,
                ItemDisplayContext.FIXED,
                light,
                OverlayTexture.NO_OVERLAY,
                poseStack,
                buffer,
                entity.level(),
                0
        );

        poseStack.popPose();
    }

    protected net.minecraft.resources.ResourceLocation getTextureLocation(ItemStack stack) {

        ResourceLocation key = net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(stack.getItem());
        return new ResourceLocation("phantasystar", "textures/item/mag/" + key.getPath() + ".png");
    }
}