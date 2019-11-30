package uwu.smsgamer.Paste;


import org.lwjgl.opengl.Display;

import net.minecraft.client.Minecraft;
import net.minecraft.util.IChatComponent;
import uwu.smsgamer.Paste.Events.EventManager;
import uwu.smsgamer.Paste.Events.EventTarget;
import uwu.smsgamer.Paste.Events.Events.EventKey;
import uwu.smsgamer.Paste.Module.Module;
import uwu.smsgamer.Paste.Module.ModuleManager;

public class PasteClient {
	
	public static PasteClient instance = new PasteClient();

    public static float timer = 0.5f;
    public Minecraft mc = Minecraft.getMinecraft();
    private boolean owo = false;

    public String name = "Paste", version = "1.0", creator = "Sms_Gamer & EthoCryantic";
    public EventManager eventManager;
    public ModuleManager moduleManager;
    
    
    public void initialize() {
        Display.setTitle(name + " " + version);
        eventManager = new EventManager();
        moduleManager = new ModuleManager();
        
        EventManager.register(this);
    }
    
    public void uninitialize() {
    	EventManager.unregister(this);
    }
    
    @EventTarget
    public void onKey(EventKey event) {
        moduleManager.getModules().stream().filter(module -> module.getKey() == event.getKey()).forEach(Module::toggle);
    }
}
