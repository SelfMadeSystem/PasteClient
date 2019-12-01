package uwu.smsgamer.Paste.Module.Movement;

import org.lwjgl.input.Keyboard;
import uwu.smsgamer.Paste.Events.EventTarget;
import uwu.smsgamer.Paste.Events.Events.EventUpdate;
import uwu.smsgamer.Paste.Module.Category;
import uwu.smsgamer.Paste.Module.Module;
import uwu.smsgamer.Paste.Utils.MovementUtils;

public class Fly extends Module {
    private static int ticks = 0;

    public Fly() {
        super("KillMeNow", Keyboard.KEY_F, Category.MOVEMENT);
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        this.setDisplayName("I want die");
        ticks++;
        if (mc.thePlayer.moveStrafing != 0 || mc.thePlayer.moveForward != 0) {
            MovementUtils.strafe(1);
        } else {
            MovementUtils.xzMotion(0, 0);
        }
        if (mc.gameSettings.keyBindJump.isKeyDown()) {
            MovementUtils.yMotion(1, 0);
        }
        if (mc.gameSettings.keyBindSneak.isKeyDown()) {
            MovementUtils.yMotion(-1, 0);
        }
        if ((mc.gameSettings.keyBindJump.isKeyDown() && mc.gameSettings.keyBindSneak.isKeyDown()) || !(mc.gameSettings.keyBindJump.isKeyDown() || mc.gameSettings.keyBindSneak.isKeyDown())) {
            MovementUtils.yMotion(0, 0);
        }
    }

    @Override
    public void onEnable() {
        super.onEnable();
        ticks = 0;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        MovementUtils.yMotion(0, 0);
        MovementUtils.xzMotion(0, 0);
    }
}
