package uwu.smsgamer.Paste;


import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.Display;
import uwu.smsgamer.Paste.Events.EventManager;
import uwu.smsgamer.Paste.Events.EventTarget;
import uwu.smsgamer.Paste.Events.Events.EventKey;
import uwu.smsgamer.Paste.Module.Module;
import uwu.smsgamer.Paste.Module.ModuleManager;
import uwu.smsgamer.Paste.valuesystem.ValueManager;

public class PasteClient {

    public static PasteClient instance = new PasteClient();

    public static String clientName = "Paste";

    public static float timer = 0.5f;
    public Minecraft mc = Minecraft.getMinecraft();
    public String name = "Paste", version = "1.0", creator = "Sms_Gamer & EthoCryantic";
    public EventManager eventManager;
    public ModuleManager moduleManager;
    public ValueManager valueManager;

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
