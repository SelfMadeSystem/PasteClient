/*
 * Copyright (c) 2018 superblaubeere27
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package uwu.smsgamer.PasteClient.command;

import uwu.smsgamer.PasteClient.ClientBase;
import uwu.smsgamer.PasteClient.command.commands.*;
import uwu.smsgamer.PasteClient.modules.Module;
import uwu.smsgamer.PasteClient.utils.ChatUtils;
import uwu.smsgamer.PasteClient.valuesystem.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CommandManager {
    private List<Command> commands = new ArrayList<>();

    public void addCommands() {
        addCommand(new ReloadCommand());
        addCommand(new LoadCommand());
        addCommand(new SaveCommand());
        addCommand(new ToggleCommand());
        addCommand(new BindCommand());
        addCommand(new ValueCommand());
        addCommand(new LoginCommand());
        addCommand(new SessionCommand());
        addCommand(new ConnectCommand());
        addCommand(new ScriptCommand());
        addModuleCommands();
    }

    public void addModuleCommands() {
        for (Object object : ClientBase.INSTANCE.moduleManager.getModules()) {
            if (!(object instanceof Module))
                continue;
            List<Value> values = new ArrayList<>();
            for (final Field field : object.getClass().getDeclaredFields()) {
                try {
                    field.setAccessible(true);
                    final Object obj = field.get(object);

                    if (obj instanceof Value) {
                        values.add((Value) obj);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            if (values.size() == 0)
                continue;
            addCommand(new Command(((Module) object).getName()) {
                @Override
                public void run(String alias, String[] args) {
                    String thingy = "Available arguments:";
                    StringBuilder owo = new StringBuilder(" " + values.get(0).getName());// FIXME: 2020-01-11 MAKE SURE THAT IF THERE IS NO VALUES, SEND ERROR
                    for (Value value : values) {
                        if (value == values.get(0))
                            continue;
                        owo.append(", ").append(value.getName());
                    }
                    thingy = thingy + owo + ".";
                    switch (args.length) {
                        case 0: {
                            ChatUtils.info(thingy);
                        }
                        return;
                        case 1: {
                            for (Value value : values) {
                                if (args[0].equalsIgnoreCase(value.getName())) {
                                    if (value instanceof BooleanValue)
                                        throw new CommandException("." + alias + " " + value.getName() + " <true/false>");
                                    if (value instanceof NumberValue || value instanceof StringValue)
                                        throw new CommandException("." + alias + " " + value.getName() + " <value>");
                                    if (value instanceof ModeValue) {
                                        StringBuilder uwu = new StringBuilder(((ModeValue) value).getModes()[0]);
                                        for (String mode : ((ModeValue) value).getModes()) {
                                            if (mode.equalsIgnoreCase(((ModeValue) value).getModes()[0]))
                                                continue;
                                            uwu.append(", ").append(mode);
                                        }
                                        throw new CommandException("." + alias + " " + value.getName() + " <" + uwu + ">");
                                    }
                                    return;
                                }
                            }
                            thingy = "Unknown argument. " + thingy;
                            throw new CommandException(thingy);
                        }
                        default: {
                            for (Value value : values) {
                                if (args[0].equalsIgnoreCase(value.getName())) {
                                    if (value instanceof BooleanValue) {
                                        try {
                                            if (args[1].startsWith("y") || args[1].startsWith("t") || args[1].startsWith("o")) {
                                                Objects.requireNonNull(ClientBase.INSTANCE.valueManager.get(alias, args[0])).setObject(true);
                                                ChatUtils.success("Set " + value.getName() + " to: true");
                                            } else {
                                                Objects.requireNonNull(ClientBase.INSTANCE.valueManager.get(alias, args[0])).setObject(false);
                                                ChatUtils.success("Set " + value.getName() + " to: false");
                                            }
                                            return;
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            throw new CommandException("Unable to set value.");
                                        }
                                    }
                                    if (value instanceof NumberValue) {
                                        try {
                                            NumberValue asd = (NumberValue) Objects.requireNonNull(ClientBase.INSTANCE.valueManager.get(alias, args[0]));
                                            asd.setObject(Double.parseDouble(args[1]));
                                            ChatUtils.success("Set " + value.getName() + " to: " + args[1]);
                                            return;
                                        } catch (NumberFormatException e) {
                                            throw new CommandException("Not a valid number.");
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            throw new CommandException("Unable to set value.");
                                        }
                                    }
                                    if (value instanceof ModeValue) {
                                        StringBuilder uwu = new StringBuilder(((ModeValue) value).getModes()[0]);
                                        for (String mode : ((ModeValue) value).getModes()) {
                                            if (mode.equalsIgnoreCase(((ModeValue) value).getModes()[0]))
                                                continue;
                                            uwu.append(", ").append(mode);
                                        }
                                        throw new CommandException("." + alias + " " + value.getName() + " <" + uwu + ">");
                                    }
                                    if (value instanceof StringValue) {
                                        try {
                                            StringBuilder obj = new StringBuilder(args[1]);
                                            for (String arg : args) {
                                                if (arg.equalsIgnoreCase(args[0]) || arg.equalsIgnoreCase(args[1]))
                                                    continue;
                                                obj.append(" ").append(arg);
                                            }
                                            ChatUtils.info("Set value to: " + obj);
                                            value.setObject(obj.toString());
                                            ChatUtils.info("SADJKLKGHSADJHFSAHGDJSA");
                                            return;
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            throw new CommandException("Unable to set value.");
                                        }
                                    }
                                    return;
                                }
                            }
                            thingy = "Unknown argument. " + thingy;
                            throw new CommandException(thingy);
                        }
                    }
                }

                @Override
                public List<String> autocomplete(int arg, String[] args) {
                    return null;
                }
            });
        }
    }

    public void addCommand(Command cmd) {
        commands.add(cmd);
    }

    public boolean executeCommand(String string) {
        String raw = string.substring(1);
        String[] split = raw.split(" ");

        if (split.length == 0) return false;

        String cmdName = split[0];

        Command command = commands.stream().filter(cmd -> cmd.match(cmdName)).findFirst().orElse(null);

        try {
            if (command == null) {
                ChatUtils.send("\u00A7c'" + cmdName + "' doesn't exist");
                return false;
            } else {
                String[] args = new String[split.length - 1];

                System.arraycopy(split, 1, args, 0, split.length - 1);

                command.run(split[0], args);
                return true;
            }
        } catch (CommandException e) {
            ChatUtils.send("\u00A7c" + e.getMessage());
        }
        return true;
    }

    public Collection<String> autoComplete(String currCmd) {
        String raw = currCmd.substring(1);
        String[] split = raw.split(" ");

        List<String> ret = new ArrayList<>();


        Command currentCommand = split.length >= 1 ? commands.stream().filter(cmd -> cmd.match(split[0])).findFirst().orElse(null) : null;

        if (split.length >= 2 || currentCommand != null && currCmd.endsWith(" ")) {

            if (currentCommand == null) return ret;

            String[] args = new String[split.length - 1];

            System.arraycopy(split, 1, args, 0, split.length - 1);

            List<String> autocomplete = currentCommand.autocomplete(args.length + (currCmd.endsWith(" ") ? 1 : 0), args);

            return autocomplete == null ? new ArrayList<>() : autocomplete;
        } else if (split.length == 1) {
            for (Command command : commands) {
                ret.addAll(command.getNameAndAliases());
            }

            return ret.stream().map(str -> "." + str).filter(str -> str.toLowerCase().startsWith(currCmd.toLowerCase())).collect(Collectors.toList());
        }

        return ret;
    }
}
