package uwu.smsgamer.Paste.Module.Movement;

import org.lwjgl.input.Keyboard;

import uwu.smsgamer.Paste.Events.EventTarget;
import uwu.smsgamer.Paste.Events.Events.EventUpdate;
import uwu.smsgamer.Paste.Module.Category;
import uwu.smsgamer.Paste.Module.Module;

public class Sprint extends Module{
	public Sprint() {
        super("Sprint", Keyboard.KEY_M, Category.MOVEMENT);
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        if(!mc.thePlayer.isCollidedHorizontally && mc.thePlayer.moveForward > 0)
            mc.thePlayer.setSprinting(true);
    }

    @Override
    public void onDisable() {
        super.onDisable();

        mc.thePlayer.setSprinting(false);
    }
}
