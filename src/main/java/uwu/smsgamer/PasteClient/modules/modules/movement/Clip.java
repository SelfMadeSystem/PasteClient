package uwu.smsgamer.PasteClient.modules.modules.movement;

import com.darkmagician6.eventapi.EventTarget;
import uwu.smsgamer.PasteClient.events.UpdateEvent;
import uwu.smsgamer.PasteClient.modules.Module;
import uwu.smsgamer.PasteClient.modules.ModuleCategory;
import uwu.smsgamer.PasteClient.utils.MovementUtils;
import uwu.smsgamer.PasteClient.valuesystem.NumberValue;

public class Clip extends Module {
    public NumberValue<Double> horizontal = new NumberValue<>("Horizontal", 0.0, -10.0, 10.0);
    public NumberValue<Double> vertical = new NumberValue<>("Vertical", 0.0, -10.0, 10.0);
    public Clip() {
        super("Clip", "Makes you clip.", ModuleCategory.MOVEMENT);
    }

    @Override
    public void onEnable() {
        MovementUtils.hClip(horizontal.getObject());
        MovementUtils.vClip(vertical.getObject());
    }

    @EventTarget
    public void onUpdate(UpdateEvent event) {
        if(getState())
            setState(false);
    }
}
