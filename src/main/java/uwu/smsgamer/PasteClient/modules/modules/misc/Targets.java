package uwu.smsgamer.PasteClient.modules.modules.misc;

import com.darkmagician6.eventapi.EventTarget;
import uwu.smsgamer.PasteClient.events.UpdateEvent;
import uwu.smsgamer.PasteClient.modules.Module;
import uwu.smsgamer.PasteClient.modules.ModuleCategory;
import uwu.smsgamer.PasteClient.valuesystem.BooleanValue;

public class Targets extends Module {

    public static boolean players;
    public static boolean mobs;
    public static boolean animals;
    public static boolean invisibles;
    public static boolean teams;

    public BooleanValue player = new BooleanValue("Players", true);
    public BooleanValue mob = new BooleanValue("Mobs", true);
    public BooleanValue animal = new BooleanValue("Animals", true);
    public BooleanValue invisible = new BooleanValue("Invisibles", true);
    public BooleanValue team = new BooleanValue("Teams", true);

    public Targets() {
        super("Targets", "Targets to target", ModuleCategory.MISC);
    }

    @EventTarget
    public void onUpdate(UpdateEvent event) {
        players = player.getObject();
        mobs = mob.getObject();
        animals = animal.getObject();
        invisibles = invisible.getObject();
        teams = team.getObject();
    }
}
