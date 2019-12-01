package uwu.smsgamer.Paste.Module;

import uwu.smsgamer.Paste.Module.Movement.Fly;
import uwu.smsgamer.Paste.Module.Movement.Sprint;
import uwu.smsgamer.Paste.Module.Render.ClickGui;

import java.util.ArrayList;

public class ModuleManager {
    private ArrayList<Module> modules = new ArrayList<Module>();

    public ModuleManager() {
        // COMBAT

        // MOVEMENT
        modules.add(new Sprint());
        modules.add(new Fly());
        modules.add(new ClickGui());
        // PLAYER

        // RENDER

        // WORLD

        // MISC

    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public Module getModuleByName(String name) {
        return modules.stream().filter(module -> module.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
}
