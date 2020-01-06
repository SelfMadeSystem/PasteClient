// 
// Decompiled by Procyon v0.5.30
// 

package uwu.smsgamer.PasteClient.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public class MovementUtils {
    private static Minecraft mc;

    static {
        MovementUtils.mc = Minecraft.getMinecraft();
    }

    public static int getMode(String mode) {
        if (mode.equalsIgnoreCase("Set")) {
            return 0;
        } else if (mode.equalsIgnoreCase("Add")) {
            return 1;
        } else if (mode.equalsIgnoreCase("Times")) {
            return 2;
        } else {
            return 0;
        }
    }

    public static float getSpeed() {
        EntityPlayerSP p = mc.player;
        return (float) Math.sqrt(p.motionX * p.motionX + p.motionZ * p.motionZ);
    }

    public static void strafe() {
        strafe(getSpeed());
    }

    public static boolean isMoving() {
        EntityPlayerSP p = mc.player;
        return p != null && (p.moveForward != 0.0F || p.moveStrafing != 0.0F);
    }

    public static float getDirection() {
        EntityPlayerSP p = mc.player;
        float yaw = p.rotationYaw;
        float forward = p.moveForward;
        float strafe = p.moveStrafing;
        yaw += (forward < 0.0F ? 180 : 0);
        final int i = forward < 0.0F ? -45 : forward == 0.0F ? 90 : 45;
        if (strafe < 0.0F) {
            yaw += i;
        }
        if (strafe > 0.0F) {
            yaw -= i;
        }
        return yaw * 0.017453292F;
    }

    public static void strafe(float speed) {
        EntityPlayerSP p = mc.player;
        if (isMoving()) {
            double yaw = getDirection();
            p.setVelocity((-Math.sin(yaw) * speed), p.motionY, Math.cos(yaw) * speed);
        }
    }

    public static void clipStrafe(float speed) {
        EntityPlayerSP p = mc.player;
        if (isMoving()) {
            double yaw = getDirection();
            p.setPosition(p.posX + (-Math.sin(yaw) * speed), p.posY, p.posZ + Math.cos(yaw) * speed);
        }
    }

    //0 SET; 1 ADD; 2 TIMES
    public static void xzMotion(double offset, int type) {
        EntityPlayerSP p = mc.player;
        double playerYaw = ((p.rotationYawHead + 90) * Math.PI / 180);
        switch (type) {
            case 0:
                p.setVelocity((Math.cos(playerYaw) * offset), p.motionY, (Math.sin(playerYaw) * offset));
                return;
            case 1:
                p.setVelocity(p.motionX + (Math.cos(playerYaw) * offset), p.motionY, p.motionZ + (Math.sin(playerYaw) * offset));
                return;
            case 2:
                p.setVelocity(p.motionX * offset, p.motionY, p.motionZ * offset);
        }
    }

    //0 SET; 1 ADD; 2 TIMES
    public static void yMotion(double offset, int type) {
        EntityPlayerSP p = mc.player;
        switch (type) {
            case 0:
                p.setVelocity(p.motionX, offset, p.motionZ);
                return;
            case 1:
                p.setVelocity(p.motionX, p.motionY + offset, p.motionZ);
                return;
            case 2:
                p.setVelocity(p.motionX, p.motionY * offset, p.motionZ);
        }
    }

    public static void hClip(double offset) {
        EntityPlayerSP p = mc.player;
        double playerYaw = ((p.rotationYawHead + 90) * Math.PI / 180);
        p.setPosition(p.posX + (Math.cos(playerYaw) * offset), p.posY, p.posZ + (Math.sin(playerYaw) * offset));
    }

    public static void vClip(double offset) {
        EntityPlayerSP p = mc.player;
        p.setPosition(p.posX, p.posY + offset, p.posZ);
    }

    /*public static void damage(int dmg){
        EntityPlayerSP p = mc.player;
        double posX = p.posX;
        double posY = p.posY;
        double posZ = p.posZ;
        double offset = 0.0625;

        // apply damage

        // old
        if(DamageHack.mode.getSelected().type == 0){
            for(int i = 0; i < 80 + 20 * (dmg - 1D); ++i)
            {
                p.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionOnly(posX, posY + 0.049D, posZ, false));
                p.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionOnly(posX, posY, posZ, false));
            }
            p.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionOnly(posX, posY, posZ, true));
        }

        // new
        if(DamageHack.mode.getSelected().type == 1){
            for(int i = 0; i < 70; ++i) {
                p.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionOnly(p.x, p.y + 0.06D, p.z, false));
                p.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionOnly(p.x, p.y, p.z, false));
            }

            p.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionOnly(p.x, p.y + 0.1D, p.z, false));
        }

        // new 2
        if(DamageHack.mode.getSelected().type == 2){
            double x = p.x;
            double y = p.y;
            double z = p.z;

            for(int i = 0; i < 65 * dmg; ++i) {
                p.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionOnly(x, y + 0.049D, z, false));
                p.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionOnly(x, y, z, false));
            }

            p.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionOnly(x, y, z, true));
        }

        // Sigma
        if(DamageHack.mode.getSelected().type == 3){
            if (p.onGround) {
                for (int i = 0; i <= ((3 + dmg) / offset); i++) {
                    p.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionOnly(posX,posY + offset, posZ, false));
                    p.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionOnly(posX,posY, posZ, (i == ((3 + dmg) / offset))));
                }
            }
        }

        // hypixel
        if(DamageHack.mode.getSelected().type == 4){
            if (p.onGround) {
                for (int i = 0; i <= ((3 + dmg) / offset); i++) {
                    p.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionOnly(posX,posY + offset, posZ, false));
                    p.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionOnly(posX,posY, posZ, (i == ((3 + dmg) / offset))));
                }
            }
            if(p.onGround)
                p.setVelocity(p.motionX, 0.42, p.motionZ);
        }
    }*/
}
