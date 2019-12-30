// 
// Decompiled by Procyon v0.5.30
// 

package uwu.smsgamer.PasteClient.utils;

import org.lwjgl.opengl.GL11;

public class R3DUtils {
    public static void setup3DLightlessModel() {
        GL11.glEnable(3042);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glDisable(2896);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
    }

    public static void shutdown3DLightlessModel() {
        GL11.glDisable(3042);
        GL11.glEnable(2896);
        GL11.glEnable(3553);
        GL11.glDisable(2848);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
    }

}
