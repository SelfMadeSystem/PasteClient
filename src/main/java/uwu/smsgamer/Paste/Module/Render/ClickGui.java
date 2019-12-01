package uwu.smsgamer.Paste.Module.Render;

import org.lwjgl.input.Keyboard;
import uwu.smsgamer.Paste.ClickGui.ClickGUI;
import uwu.smsgamer.Paste.Module.Category;
import uwu.smsgamer.Paste.Module.Module;
import uwu.smsgamer.Paste.PasteClient;

public class ClickGui extends Module {
    public ClickGui() {
        super("ClickGui", Keyboard.KEY_RSHIFT, Category.RENDER);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        ClickGUI clickGui = new ClickGUI();
        mc.displayGuiScreen(clickGui);
        PasteClient.instance.moduleManager.getModuleByName("ClickGui").onDisable();
        mc.thePlayer.motionY = 5;
        toggle();
    }
}
