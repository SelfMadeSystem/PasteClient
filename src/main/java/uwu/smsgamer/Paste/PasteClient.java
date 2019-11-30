package uwu.smsgamer.Paste;


import net.minecraft.client.Minecraft;
import net.minecraft.util.IChatComponent;

public enum PasteClient
{
    INSTANCE;

    public static float timer = 0.5f;
    public Minecraft mc = Minecraft.getMinecraft();
    private boolean owo = false;

    public void initialize() {
        mc.timer.timerSpeed = timer;
    }

    public void onUpdate(){
        if(mc.thePlayer == null)
            return;

    }
}
