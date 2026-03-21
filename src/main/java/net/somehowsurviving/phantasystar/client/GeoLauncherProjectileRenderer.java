package net.somehowsurviving.phantasystar.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.somehowsurviving.phantasystar.entities.BulletEntity;
import net.somehowsurviving.phantasystar.entities.LauncherProjectileEntity;
import org.joml.Quaternionf;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GeoLauncherProjectileRenderer extends GeoEntityRenderer<LauncherProjectileEntity> {
    public GeoLauncherProjectileRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new GeoLauncherProjectileEntityModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public void render(LauncherProjectileEntity entity, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int packedLight) {
        matrixStack.pushPose();

        Vec3 motion = entity.getDeltaMovement();
        if (!motion.equals(Vec3.ZERO)) {
            Vec3 look = motion.normalize();

            // Compute yaw & pitch in degrees
            float yaw = (float) Math.toDegrees(Math.atan2(look.x, look.z));
            float pitch = (float) Math.toDegrees(Math.asin(look.y));

            // Use Quaternionf to rotate along X and Y
            Quaternionf q = new Quaternionf().rotateY((float)Math.toRadians(yaw)).rotateX((float)Math.toRadians(-pitch));
            matrixStack.mulPose(q);
        }

        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
        matrixStack.popPose();
    }

    @Override
    public RenderType getRenderType(LauncherProjectileEntity animatable,
                                    ResourceLocation texture,
                                    MultiBufferSource bufferSource,
                                    float partialTick) {
        return RenderType.entityTranslucent(texture);
    }
}
