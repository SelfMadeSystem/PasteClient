package uwu.smsgamer.Paste;


import org.lwjgl.opengl.Display;

import net.minecraft.client.Minecraft;
import net.minecraft.util.IChatComponent;
import uwu.smsgamer.Paste.Events.EventManager;

public class PasteClient {
	
	public static PasteClient instance = new PasteClient();

    public static float timer = 0.5f;
    public Minecraft mc = Minecraft.getMinecraft();
    private boolean owo = false;

    public String name = "Paste", version = "1.0", creator = "Sms_Gamer & EthoCryantic";
    
    public void initialize() {
        Display.setTitle(name + " " + version);
        
        EventManager.register(this);
    }
    
    public void uninitialize() {
    	EventManager.unregister(this);
    }
}
