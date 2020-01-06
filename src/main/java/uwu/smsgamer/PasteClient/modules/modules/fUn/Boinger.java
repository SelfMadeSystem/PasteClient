package uwu.smsgamer.PasteClient.modules.modules.fUn;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.network.play.client.CPacketPlayerAbilities;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import uwu.smsgamer.PasteClient.events.LandEvent;
import uwu.smsgamer.PasteClient.events.UpdateEvent;
import uwu.smsgamer.PasteClient.modules.Module;
import uwu.smsgamer.PasteClient.modules.ModuleCategory;
import uwu.smsgamer.PasteClient.utils.ChatUtils;
import uwu.smsgamer.PasteClient.valuesystem.ModeValue;
import uwu.smsgamer.PasteClient.valuesystem.NumberValue;

public class Boinger extends Module {
    private NumberValue<Integer> amount = new NumberValue<>("Amount", 1, 1, 100);
    private NumberValue<Double> customValue = new NumberValue<>("CustomValue", 0.42, 0d, 5d);
    private ModeValue mode = new ModeValue("Mode", "Legit", "Legit", "Boing", "SlimeBlock", "Bed", "Mineplex", "Custom");
    //                                                                                  0          1         2           3        4          5
    public Boinger() {
        super("Boinger", "Makes you go boing!", ModuleCategory.FUN);
    }

    private double prevMotionY;

    @EventTarget
    public void onBoing(LandEvent event){
        if (!getState() || !(event.entity instanceof EntityPlayerSP))
            return;
        switch (mode.getObject()) {
            case 2:
                event.newMotion = -event.originalMotion;
                break;
            case 3:
                event.newMotion = -event.originalMotion * 0.6600000262260437D;
                break;
        }
    }

    @EventTarget
    public void onUpdate(UpdateEvent event) {
        if (!getState())
            return;
        EntityPlayerSP p = mc.player;
        switch (mode.getObject()) {
            case 0:
                if (p.onGround) {
                    p.jump();
                }
                break;
            case 1:
                if (p.onGround) {
                    p.motionY = -prevMotionY;
                }
                break;
            case 4:
                if (p.onGround) {
                    for (int i = 0; i < amount.getObject(); i++) {
                        PlayerCapabilities pc = new PlayerCapabilities();
                        pc.isCreativeMode = true;
                        pc.isFlying = true;
                        pc.allowFlying = true;
                        pc.allowEdit = true;
                        pc.disableDamage = true;
                        p.connection.sendPacket(new CPacketPlayerAbilities(pc));
                    }
                }
                break;
            case 5:
                if (p.onGround) {
                    p.motionY = customValue.getObject();
                }
        }
        prevMotionY = p.motionY;
    }
}
