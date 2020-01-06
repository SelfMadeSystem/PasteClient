package uwu.smsgamer.PasteClient.modules.modules.movement;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.entity.EntityPlayerSP;
import uwu.smsgamer.PasteClient.events.UpdateEvent;
import uwu.smsgamer.PasteClient.modules.Module;
import uwu.smsgamer.PasteClient.modules.ModuleCategory;
import uwu.smsgamer.PasteClient.utils.MovementUtils;
import uwu.smsgamer.PasteClient.valuesystem.ModeValue;
import uwu.smsgamer.PasteClient.valuesystem.NumberValue;

public class Speed extends Module {
    private ModeValue mode = new ModeValue("Mode", "BHop", "BHop", "OnGround");
    private ModeValue strafeMode = new ModeValue("StrafeMode", "None", "None", "Normal", "Exact");
    private NumberValue<Double> speed1 = new NumberValue<>("Speed1", 1d, 0d, 10d);
    private NumberValue<Double> speed2 = new NumberValue<>("Speed2", 1d, 0d, 10d);
    public Speed() {
        super("Speed", "Makes you go faster than legit.", ModuleCategory.MOVEMENT);
    }

    private EntityPlayerSP p;

    @EventTarget
    public void onUpdate(UpdateEvent event){
        if(!getState())
            return;
        p = mc.player;
        switch (mode.getObject()){
            case 0:
                break;
        }
        if(strafeMode.getObject() > 0){
            MovementUtils.strafe();
        }
    }

    private void bhop(){
        if(p.onGround){

        }
    }
}
