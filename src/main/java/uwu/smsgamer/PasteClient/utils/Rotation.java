package uwu.smsgamer.PasteClient.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

//LIQUIDBOUNCE SKID !!!!!!!!!!
public class Rotation {

    public float yaw;
    public float pitch;

    public Rotation(float yaw, float pitch){
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public void toPlayer(EntityPlayer player) {
        Minecraft mc = Minecraft.getMinecraft();
        if (new Float(yaw).isNaN() || new Float(pitch).isNaN())
            return;

        fixedSensitivity(mc.gameSettings.mouseSensitivity);

        player.rotationYaw = yaw;
        player.rotationPitch = pitch;
    }

    public void fixedSensitivity(float sensitivity) {
        float f = sensitivity * 0.6F + 0.2F;
        float gcd = f * f * f * 1.2F;

        yaw -= yaw % gcd;
        pitch -= pitch % gcd;
    }
}
