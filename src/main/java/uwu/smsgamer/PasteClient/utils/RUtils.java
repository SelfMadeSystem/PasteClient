package uwu.smsgamer.PasteClient.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public final class RUtils {
    public static boolean lookChanged;
    public static float targetYaw;
    public static float targetPitch;
    public static boolean keepRotation = false;
    public static float[] lastLook = new float[]{0.0F, 0.0F};
    private static int keepLength;
    private static double x = Math.random();
    private static double y = Math.random();
    private static double z = Math.random();

    public RUtils() {
    }

    public static void faceBlockPacket(BlockPos blockPos) {
        EntityPlayerSP p = Minecraft.getMinecraft().player;
        if (blockPos != null) {
            double diffX = (double) blockPos.getX() + 0.5D - p.posX;
            double diffY = (double) blockPos.getY() + 0.5D - (p.getEntityBoundingBox().minY + (double) p.getEyeHeight());
            double diffZ = (double) blockPos.getZ() + 0.5D - p.posZ;
            double sqrt = Math.sqrt(diffX * diffX + diffZ * diffZ);
            float yaw = (float) (Math.atan2(diffZ, diffX) * 180.0D / 3.141592653589793D) - 90.0F;
            float pitch = (float) (-(Math.atan2(diffY, sqrt) * 180.0D / 3.141592653589793D));
            setTargetRotation(p.rotationYaw + MathHelper.wrapDegrees(yaw - p.rotationYaw), p.rotationPitch + MathHelper.wrapDegrees(pitch - p.rotationPitch));
        }
    }

    /*public static void faceBow(Entity target, boolean silent, boolean predict, float predictSize) {

        double posX = target.x + (predict ? (target.x - target.prevX) * (double)predictSize : 0.0D) - (p.posX + (predict ? p.posX - p.prevX : 0.0D));
        double posY = target.getEntityBoundingBox().minY + (predict ? (target.getEntityBoundingBox().minY - target.prevY) * (double)predictSize : 0.0D) + (double)target.getEyeHeight() - 0.15D - (p.getEntityBoundingBox().minY + (predict ? p.field_70163_u - p.prevY : 0.0D)) - (double)p.getEyeHeight();
        double posZ = target.z + (predict ? (target.z - target.prevZ) * (double)predictSize : 0.0D) - (p.posZ + (predict ? p.posZ - p.prevZ : 0.0D));
        double sqrt = Math.sqrt(posX * posX + posZ * posZ);
        float velocity = (float)p.getItemInUseCount() / 20.0F;
        velocity = (velocity * velocity + velocity * 2.0F) / 3.0F;
        if (velocity > 1.0F) {
            velocity = 1.0F;
        }

        float yaw = (float)(Math.atan2(posZ, posX) * 180.0D / 3.141592653589793D) - 90.0F;
        float pitch = (float)(-Math.toDegrees(Math.atan(((double)(velocity * velocity) - Math.sqrt((double)(velocity * velocity * velocity * velocity) - 0.006000000052154064D * (0.006000000052154064D * sqrt * sqrt + 2.0D * posY * (double)(velocity * velocity)))) / (0.006000000052154064D * sqrt))));
        float[] rotations;
        if (velocity < 0.1F) {
            rotations = getNeededRotations(getCenter(target.getEntityBoundingBox()), true);
            yaw = rotations[0];
            pitch = rotations[1];
        }

        if (silent) {
            setTargetRotation(yaw, pitch);
        } else {
            rotations = limitAngleChange(new float[]{p.rotationYaw, p.rotationPitch}, new float[]{yaw, pitch}, (float)(10 + RandomUtils.getRandom().nextInt(6)));
            if (rotations == null) {
                return;
            }

            p.rotationYaw = rotations[0];
            p.rotationPitch = rotations[1];
        }

    }*/

    /*public static boolean isLookingAtTarget(Entity target, boolean predict){
        boolean looking = false;
        AxisAlignedBB bb = target.getEntityBoundingBox();
        EntityPlayerSP p = Minecraft.getMinecraft().player;
        Vec3d eyesPos = getEyesPos();

        float yaw = MathHelper.wrapDegrees(p.rotationYaw);
        float pitch = p.rotationPitch;

        Vec3d max = new Vec3d(bb.maxX, bb.maxY, bb.maxZ);
        Vec3d min = new Vec3d(bb.minX, bb.minY, bb.minZ);

        //ChatUtils.noPXMessage(yaw+" "+getNeededRotations(getCenter(bb), predict)[0]);

        if(getNeededRotations(getCenter(bb), predict)[0]<0 && getNeededRotations(getCenter(bb), predict)[0]>-90) {
            double minDiffX = min.x - eyesPos.x;
            double minDiffY = min.y - eyesPos.y;
            double minDiffZ = max.z - eyesPos.z;
            double minDiffXZ = Math.sqrt(minDiffX * minDiffX + minDiffZ * minDiffZ);
            float minYaw = (float) MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(minDiffZ, minDiffX)) - 90.0F);
            float minPitch = (float) -Math.toDegrees(Math.atan2(minDiffY, minDiffXZ));

            double maxDiffX = max.x - eyesPos.x;
            double maxDiffY = max.y - eyesPos.y;
            double maxDiffZ = min.z - eyesPos.z;
            double maxDiffXZ = Math.sqrt(maxDiffX * maxDiffX + maxDiffZ * maxDiffZ);
            float maxYaw = (float) MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(maxDiffZ, maxDiffX)) - 90.0F);
            float maxPitch = (float) -Math.toDegrees(Math.atan2(maxDiffY, maxDiffXZ));

            if (yaw <= minYaw && yaw >= maxYaw && pitch <= minPitch && pitch >= maxPitch)
                looking = true;

            //ChatUtils.noPXMessage(minYaw + " " + maxYaw + " " + yaw + "\n" + (yaw <= minYaw) + " " + (yaw >= maxYaw) + "\n"
                    //+ maxPitch + " " + minPitch + " " + pitch + "\n" + (pitch <= minPitch) + " " + (pitch >= maxPitch));'
            //ChatUtils.noPXMessage("min max");
        }

        if(getNeededRotations(getCenter(bb), predict)[0]<90 && getNeededRotations(getCenter(bb), predict)[0]>0) {
            double minDiffX = min.x - eyesPos.x;
            double minDiffY = min.y - eyesPos.y;
            double minDiffZ = min.z - eyesPos.z;
            double minDiffXZ = Math.sqrt(minDiffX * minDiffX + minDiffZ * minDiffZ);
            float minYaw = (float) MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(minDiffZ, minDiffX)) - 90.0F);
            float minPitch = (float) -Math.toDegrees(Math.atan2(minDiffY, minDiffXZ));

            double maxDiffX = max.x - eyesPos.x;
            double maxDiffY = max.y - eyesPos.y;
            double maxDiffZ = max.z - eyesPos.z;
            double maxDiffXZ = Math.sqrt(maxDiffX * maxDiffX + maxDiffZ * maxDiffZ);
            float maxYaw = (float) MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(maxDiffZ, maxDiffX)) - 90.0F);
            float maxPitch = (float) -Math.toDegrees(Math.atan2(maxDiffY, maxDiffXZ));

            if (yaw <= minYaw && yaw >= maxYaw && pitch <= minPitch && pitch >= maxPitch)
                looking = true;

            //ChatUtils.noPXMessage(minYaw + " " + maxYaw + " " + yaw + "\n" + (yaw <= minYaw) + " " + (yaw >= maxYaw) + "\n"
                    //+ maxPitch + " " + minPitch + " " + pitch + "\n" + (pitch <= minPitch) + " " + (pitch >= maxPitch));
            //ChatUtils.noPXMessage("min min");
        }

        if(getNeededRotations(getCenter(bb), predict)[0]<180 && getNeededRotations(getCenter(bb), predict)[0]>90) {
            double minDiffX = max.x - eyesPos.x;
            double minDiffY = min.y - eyesPos.y;
            double minDiffZ = min.z - eyesPos.z;
            double minDiffXZ = Math.sqrt(minDiffX * minDiffX + minDiffZ * minDiffZ);
            float minYaw = (float) MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(minDiffZ, minDiffX)) - 90.0F);
            float minPitch = (float) -Math.toDegrees(Math.atan2(minDiffY, minDiffXZ));

            double maxDiffX = min.x - eyesPos.x;
            double maxDiffY = max.y - eyesPos.y;
            double maxDiffZ = max.z - eyesPos.z;
            double maxDiffXZ = Math.sqrt(maxDiffX * maxDiffX + maxDiffZ * maxDiffZ);
            float maxYaw = (float) MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(maxDiffZ, maxDiffX)) - 90.0F);
            float maxPitch = (float) -Math.toDegrees(Math.atan2(maxDiffY, maxDiffXZ));

            if (yaw <= minYaw && yaw >= maxYaw && pitch <= minPitch && pitch >= maxPitch)
                looking = true;

            //ChatUtils.noPXMessage(minYaw + " " + maxYaw + " " + yaw + "\n" + (yaw <= minYaw) + " " + (yaw >= maxYaw) + "\n"
                    //+ maxPitch + " " + minPitch + " " + pitch + "\n" + (pitch <= minPitch) + " " + (pitch >= maxPitch));
            //ChatUtils.noPXMessage("max min");
        }

        if(getNeededRotations(getCenter(bb), predict)[0]<-90 && getNeededRotations(getCenter(bb), predict)[0]>-180) {
            double minDiffX = max.x - eyesPos.x;
            double minDiffY = min.y - eyesPos.y;
            double minDiffZ = max.z - eyesPos.z;
            double minDiffXZ = Math.sqrt(minDiffX * minDiffX + minDiffZ * minDiffZ);
            float minYaw = (float) MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(minDiffZ, minDiffX)) - 90.0F);
            float minPitch = (float) -Math.toDegrees(Math.atan2(minDiffY, minDiffXZ));

            double maxDiffX = min.x - eyesPos.x;
            double maxDiffY = max.y - eyesPos.y;
            double maxDiffZ = min.z - eyesPos.z;
            double maxDiffXZ = Math.sqrt(maxDiffX * maxDiffX + maxDiffZ * maxDiffZ);
            float maxYaw = (float) MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(maxDiffZ, maxDiffX)) - 90.0F);
            float maxPitch = (float) -Math.toDegrees(Math.atan2(maxDiffY, maxDiffXZ));

            if (yaw <= minYaw && yaw >= maxYaw && pitch <= minPitch && pitch >= maxPitch)
                looking = true;

            //ChatUtils.noPXMessage(minYaw + " " + maxYaw + " " + yaw + "\n" + (yaw <= minYaw) + " " + (yaw >= maxYaw) + "\n"
                    //+ maxPitch + " " + minPitch + " " + pitch + "\n" + (pitch <= minPitch) + " " + (pitch >= maxPitch));
            //ChatUtils.noPXMessage("max max");
        }

        // -153 -130
        // -2.24 37

        // 120 112
        // -2.8 7.75

        return looking;
    }*/

    public static float[] getTargetRotation(Entity entity) {
        EntityPlayerSP p = Minecraft.getMinecraft().player;
        return entity != null && p != null ? getNeededRotations(getRandomCenter(entity.getEntityBoundingBox(), false), true) : null;
    }

    public static float[] getNeededRotations(Vec3d vec, boolean predict) {
        EntityPlayerSP p = Minecraft.getMinecraft().player;
        Vec3d eyesPos = getEyesPos();
        if (predict) {
            eyesPos.addVector(p.motionX, p.motionY, p.motionZ);
        }

        double diffX = vec.xCoord - eyesPos.xCoord;
        double diffY = vec.yCoord - eyesPos.yCoord;
        double diffZ = vec.zCoord - eyesPos.zCoord;
        double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
        float yaw = (float) Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0F;
        float pitch = (float) (-Math.toDegrees(Math.atan2(diffY, diffXZ)));
        return new float[]{MathHelper.wrapDegrees(yaw), MathHelper.wrapDegrees(pitch)};
    }

    public static Vec3d getCenter(AxisAlignedBB bb) {
        return new Vec3d(bb.minX + (bb.maxX - bb.minX) * 0.5D, bb.minY + (bb.maxY - bb.minY) * 0.5D, bb.minZ + (bb.maxZ - bb.minZ) * 0.5D);
    }

    public static Vec3d getTop(AxisAlignedBB bb) {
        return new Vec3d(bb.minX + (bb.maxX - bb.minX) * 0.5D, bb.maxY, bb.minZ + (bb.maxZ - bb.minZ) * 0.5D);
    }

    public static Vec3d getBottom(AxisAlignedBB bb) {
        return new Vec3d(bb.minX + (bb.maxX - bb.minX) * 0.5D, bb.minY, bb.minZ + (bb.maxZ - bb.minZ) * 0.5D);
    }

    public static Vec3d getHead(Entity target) {
        AxisAlignedBB bb = target.getEntityBoundingBox();
        return new Vec3d(bb.minX + (bb.maxX - bb.minX) * 0.5D, bb.minY + target.getEyeHeight(), bb.minZ + (bb.maxZ - bb.minZ) * 0.5D);
    }

    public static Vec3d getCustom(Entity target, double add) {
        AxisAlignedBB bb = target.getEntityBoundingBox();
        return new Vec3d(bb.minX + (bb.maxX - bb.minX) * 0.5D, bb.minY + add, bb.minZ + (bb.maxZ - bb.minZ) * 0.5D);
    }

    public static Vec3d getRandomCenter(AxisAlignedBB bb, boolean outBorder) {
        return outBorder ? new Vec3d(bb.minX + (bb.maxX - bb.minX) * (x * 0.3D + 1.0D), bb.minY + (bb.maxY - bb.minY) * (y * 0.3D + 1.0D), bb.minZ + (bb.maxZ - bb.minZ) * (z * 0.3D + 1.0D)) : new Vec3d(bb.minX + (bb.maxX - bb.minX) * x * 0.8D, bb.minY + (bb.maxY - bb.minY) * y * 0.8D, bb.minZ + (bb.maxZ - bb.minZ) * z * 0.8D);
    }

    public static double getRotationDifference(Entity entity) {
        float[] rotations = getTargetRotation(entity);
        return rotations == null ? 0.0D : getRotationDifference(rotations[0], rotations[1]);
    }

    public static double getRotationDifference(float yaw, float pitch) {
        return Math.sqrt(Math.pow(Math.abs(angleDifference(lastLook[0] % 360.0F, yaw)), 2.0D) + Math.pow(Math.abs(angleDifference(lastLook[1], pitch)), 2.0D));
    }

    public static float[] limitAngleChange(float[] current, float[] target, float turnSpeedYaw, float turnSpeedPitch) {
        double yawDifference = angleDifference(target[0], current[0]);
        double pitchDifference = angleDifference(target[1], current[1]);
        current[0] = (float) ((double) current[0] + (yawDifference > (double) turnSpeedYaw ? (double) turnSpeedYaw : (Math.max(yawDifference, -turnSpeedYaw))));
        current[1] = (float) ((double) current[1] + (pitchDifference > (double) turnSpeedPitch ? (double) turnSpeedPitch : (Math.max(pitchDifference, -turnSpeedPitch))));
        return current;
    }

    private static double angleDifference(double a, double b) {
        return ((a - b) % 360.0D + 540.0D) % 360.0D - 180.0D;
    }

    public static Vec3d getEyesPos() {
        EntityPlayerSP p = Minecraft.getMinecraft().player;
        return new Vec3d(p.posX, p.getEntityBoundingBox().minY + (double) p.getEyeHeight(), p.posZ);
    }

    //public static boolean isFaced(Entity targetEntity, double blockReachDistance) {
    //    return RaycastUtils.raycastEntities(blockReachDistance).contains(targetEntity);
    //}

    public static Vec3d getVectorForRotation(float pitch, float yaw) {
        float f = MathHelper.cos(-yaw * 0.017453292F - 3.1415927F);
        float f1 = MathHelper.sin(-yaw * 0.017453292F - 3.1415927F);
        float f2 = -MathHelper.cos(-pitch * 0.017453292F);
        float f3 = MathHelper.sin(-pitch * 0.017453292F);
        return new Vec3d(f1 * f2, f3, f * f2);
    }

    public static void setTargetRotation(float yaw, float pitch) {
        if (!Double.isNaN(yaw) && !Double.isNaN(pitch)) {
            targetYaw = yaw;
            targetPitch = pitch;
            lookChanged = true;
            keepLength = 0;
        }
    }

    public static void reset() {
        lookChanged = false;
        keepLength = 0;
        targetYaw = 0.0F;
        targetPitch = 0.0F;
    }

    public void onUpdate() {
        if (lookChanged) {
            ++keepLength;
            if (keepLength > 15) {
                reset();
            }
        }

        if (RandomUtils.getRandom().nextGaussian() * 100.0D > 80.0D) {
            x = Math.random();
        }

        if (RandomUtils.getRandom().nextGaussian() * 100.0D > 80.0D) {
            y = Math.random();
        }

        if (RandomUtils.getRandom().nextGaussian() * 100.0D > 80.0D) {
            z = Math.random();
        }
    }
}
