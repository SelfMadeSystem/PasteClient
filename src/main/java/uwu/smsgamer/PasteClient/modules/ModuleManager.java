/*
 * Copyright (c) 2018 superblaubeere27
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package uwu.smsgamer.PasteClient.modules;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import uwu.smsgamer.PasteClient.ClientBase;
import uwu.smsgamer.PasteClient.events.KeyEvent;
import uwu.smsgamer.PasteClient.modules.modules.combat.*;
import uwu.smsgamer.PasteClient.modules.modules.fUn.Boinger;
import uwu.smsgamer.PasteClient.modules.modules.misc.*;
import uwu.smsgamer.PasteClient.modules.modules.movement.*;
import uwu.smsgamer.PasteClient.modules.modules.render.*;
import uwu.smsgamer.PasteClient.scripting.ScriptModule;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    private List<Module> modules = new ArrayList<>();

    public ModuleManager() {
        EventManager.register(this);
    }


    public void addModules() {
        addModule(new Targets());
        addModule(new PacketListener());
        addModule(new Flagger());

        addModule(new Boinger());

        addModule(new AimBot1());
        //addModule(new AimBot2());
        addModule(new AimBot3());
        addModule(new AimBot4());
        addModule(new AutoClicker());

        addModule(new SetbackDetector());
        addModule(new Step());
        addModule(new Flight());

        addModule(new ESP());
    }

    public void addLast() {
        addModule(new ClickGUIModule());
        addModule(new HUD());
    }

    private void addModule(Module module) {
        modules.add(module);
        EventManager.register(module);
        ClientBase.INSTANCE.valueManager.registerObject(module.getName(), module);
    }


    public List<Module> getModules() {
        return modules;
    }


    public <T extends Module> T getModule(Class<T> clazz) {
        return (T) modules.stream().filter(mod -> mod.getClass() == clazz).findFirst().orElse(null);
    }

    public Module getModule(String name, boolean caseSensitive) {
        return modules.stream().filter(mod -> !caseSensitive && name.equalsIgnoreCase(mod.getName()) || name.equals(mod.getName())).findFirst().orElse(null);
    }

    @EventTarget
    private void onKey(KeyEvent event) {
        for (Module module : modules) if (module.getKeybind() == event.getKey()) module.setState(!module.getState());
    }

    public void addScriptModule(ScriptModule module) {
        addModule(module);
    }
}
