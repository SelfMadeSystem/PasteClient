/*
 * Copyright (c) 2018 superblaubeere27
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package uwu.smsgamer.PasteClient;

import com.darkmagician6.eventapi.EventManager;
import uwu.smsgamer.PasteClient.AltManager.AltManager;
import uwu.smsgamer.PasteClient.scripting.ScriptManager;
import uwu.smsgamer.PasteClient.command.CommandManager;
import uwu.smsgamer.PasteClient.fileSystem.FileManager;
import uwu.smsgamer.PasteClient.modules.ModuleManager;
import uwu.smsgamer.PasteClient.utils.RUtils;
import uwu.smsgamer.PasteClient.valuesystem.ValueManager;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ClientBase {
    /*
    Metadata
     */
    @NotNull
    public static final String CLIENT_NAME = "PasteClient";
    @NotNull
    public static final String CLIENT_AUTHOR = "superblaubeere27, Sms_Gamer_3808";
    public static final double CLIENT_VERSION_NUMBER = 1.0;
    @NotNull
    public static final String CLIENT_VERSION = CLIENT_VERSION_NUMBER + "-DEV";
    @NotNull
    public static final String CLIENT_INITIALS;
    public static ClientBase INSTANCE;
    public AltManager altManager;

    static {
        List<Character> chars = new ArrayList<>();

        for (char c : CLIENT_NAME.toCharArray())
            if (Character.toUpperCase(c) == c) chars.add(c);

        char[] c = new char[chars.size()];

        for (int i = 0; i < chars.size(); i++) {
            c[i] = chars.get(i);
        }

        CLIENT_INITIALS = new String(c);

    }

    public ModuleManager moduleManager;
    public CommandManager commandManager;
    public ValueManager valueManager;
    public FileManager fileManager;
    public ScriptManager scriptManager;

    public ClientBase() {
        INSTANCE = this;
    }

    public void startClient() {
        EventManager.register(new RUtils());

        scriptManager = new ScriptManager();
        fileManager = new FileManager();
        valueManager = new ValueManager();
        commandManager = new CommandManager();
        moduleManager = new ModuleManager();
        altManager = new AltManager();

        fileManager.loadScripts();

        moduleManager.addModules();
        moduleManager.addLast();

        commandManager.addCommands();

        fileManager.load();
    }

    public void stopClient() {
        try {
            fileManager.save();
        } catch (Exception e) {
            System.err.println("Failed to save settings:");
            e.printStackTrace();
        }
    }

}
