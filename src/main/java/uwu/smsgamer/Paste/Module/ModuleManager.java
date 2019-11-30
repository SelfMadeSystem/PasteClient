package uwu.smsgamer.Paste.Module;

import java.util.ArrayList;

import uwu.smsgamer.Paste.Module.Movement.Sprint;

public class ModuleManager {
	private ArrayList<Module> modules = new ArrayList<Module>();

    public ModuleManager() {
        // COMBAT
        
    	// MOVEMENT
    	modules.add(new Sprint());
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
