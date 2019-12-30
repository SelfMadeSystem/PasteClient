/*
 * Copyright (c) 2018 superblaubeere27
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package uwu.smsgamer.PasteClient.scripting;


import com.darkmagician6.eventapi.EventTarget;
import uwu.smsgamer.PasteClient.ClientBase;
import uwu.smsgamer.PasteClient.events.*;
import uwu.smsgamer.PasteClient.modules.Module;
import uwu.smsgamer.PasteClient.modules.ModuleCategory;
import uwu.smsgamer.PasteClient.scripting.runtime.events.ScriptLoadEvent;
import uwu.smsgamer.PasteClient.scripting.runtime.events.ScriptMotionUpdateEvent;
import uwu.smsgamer.PasteClient.scripting.runtime.events.ScriptPacketEvent;
import uwu.smsgamer.PasteClient.valuesystem.Value;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class ScriptModule extends Module {
    private ScriptEngine engine;
    private String name;
    private String desc;
    private ModuleCategory category;

    ScriptModule(String name, String desc, ModuleCategory category) {
        super(name, desc, category);
        this.name = name;
        this.desc = desc;
        this.category = category;
    }

    public void setScriptEngine(ScriptEngine scriptEngine) {
        engine = scriptEngine;
    }

    public void onLoad() {
        ScriptLoadEvent ev = new ScriptLoadEvent();
        try {
            ((Invocable) engine).invokeFunction("onLoad", ev);
        } catch (NoSuchMethodException ignored) {
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(ev.values);
        for (Object value : ev.values){
            System.out.println(((Value)value).getName());
            ClientBase.INSTANCE.valueManager.addObject(this.name, value);
        }
        System.out.println(ClientBase.INSTANCE.valueManager.getAllValuesFrom(this.name));
    }

    @Override
    public void onEnable() {
        try {
            ((Invocable) engine).invokeFunction("onEnable");
        } catch (NoSuchMethodException ignored) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        try {
            ((Invocable) engine).invokeFunction("onDisable");
        } catch (NoSuchMethodException ignored) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @EventTarget
    public void onUpdate(UpdateEvent event) {
        if (!getState()) return;
        try {
            ((Invocable) engine).invokeFunction("onUpdate");
        } catch (NoSuchMethodException ignored) {
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    @EventTarget
    public void onRender2D(Render2DEvent event) {
        if (!getState()) return;
        try {
            ((Invocable) engine).invokeFunction("onRender2D");
        } catch (NoSuchMethodException ignored) {
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    @EventTarget
    public void onRender3D(Render3DEvent event) {
        if (!getState()) return;
        try {
            ((Invocable) engine).invokeFunction("onRender3D");
        } catch (NoSuchMethodException ignored) {
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    @EventTarget
    public void onPacket(PacketEvent event) {
        if (!getState()) return;
        ScriptPacketEvent ev = new ScriptPacketEvent(event.getEventType(), event.getPacket(), event.isCancelled());
        try {
            ((Invocable) engine).invokeFunction("onPacket", ev);
        } catch (NoSuchMethodException ignored) {
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        ev.apply(event);
    }

    @EventTarget
    public void onMotionUpdate(MotionUpdateEvent event) {
        if (!getState()) return;
        ScriptMotionUpdateEvent ev = new ScriptMotionUpdateEvent(event.getEventType(), event.getX(), event.getY(), event.getZ(), event.getYaw(), event.getPitch(), event.isOnGround());

        try {
            ((Invocable) engine).invokeFunction("onMotionUpdate", ev);
        } catch (NoSuchMethodException ignored) {
        } catch (Exception e) {
            e.printStackTrace();
        }

        ev.apply(event);
    }
}
