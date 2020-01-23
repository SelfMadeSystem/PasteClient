package uwu.smsgamer.PasteClient.modules.modules.movement;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.entity.EntityPlayerSP;
import uwu.smsgamer.PasteClient.events.UpdateEvent;
import uwu.smsgamer.PasteClient.modules.Module;
import uwu.smsgamer.PasteClient.modules.ModuleCategory;
import uwu.smsgamer.PasteClient.utils.ChatUtils;
import uwu.smsgamer.PasteClient.utils.MovementUtils;
import uwu.smsgamer.PasteClient.valuesystem.ModeValue;
import uwu.smsgamer.PasteClient.valuesystem.NumberValue;

public class Speed extends Module {
    public ModeValue mode = new ModeValue("Mode", "BHop", "BHop", "OnGround");
    public ModeValue strafeMode = new ModeValue("StrafeMode", "None", "None", "Normal", "Exact");
    public NumberValue<Double> speed1 = new NumberValue<>("Speed1", 1d, 0d, 10d);
    public Speed() {
        super("Speed", "Makes you go faster than legit.", ModuleCategory.MOVEMENT);
    }

    public EntityPlayerSP p;

    @EventTarget
    public void onUpdate(UpdateEvent event){
        if(!getState())
            return;
        p = mc.player;
        //ChatUtils.info(mc.gameSettings.keyBindForward.isKeyDown()+"");
        if(!mc.gameSettings.keyBindForward.isKeyDown())
            return;
        //ChatUtils.info(mode.getObject()+"");
        switch (mode.getObject()){
            case 0:
                bhop();
                break;
        }
        if(strafeMode.getObject() > 0){
            MovementUtils.strafe();
        }
    }

    private void bhop(){
        if(p.onGround){
            p.jump();
            MovementUtils.xzMotion(speed1.getObject(), 2);
        }
    }
}
