package uwu.smsgamer.Paste.Utils;

import net.minecraft.util.IChatComponent;
import uwu.smsgamer.Paste.PasteClient;

public class ChatUtils {

    public static final String colorSymbol = "§";

    public static void chatMessage(String msg){
        PasteClient.instance.mc.thePlayer.addChatMessage(IChatComponent.Serializer.jsonToComponent("{text:\"" + msg + "\"}"));
    }
}
