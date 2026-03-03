package net.somehowsurviving.phantasystar.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class MagCurioRenderer implements ICurioRenderer {

    private final float xOffset;
    private final float yOffset;
    private final float zOffset;

    public MagCurioRenderer(float xOffset, float yOffset, float zOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.zOffset = zOffset;
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

        poseStack.scale(0.5f, 0.5f, 0.5f);

        poseStack.translate(xOffset, yOffset, zOffset);

        poseStack.mulPose(com.mojang.math.Axis.XP.rotationDegrees(180f));
        poseStack.mulPose(com.mojang.math.Axis.YP.rotationDegrees(180f));

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

        return new net.minecraft.resources.ResourceLocation("phantasystar", "textures/item/base_mag.png");
    }
}