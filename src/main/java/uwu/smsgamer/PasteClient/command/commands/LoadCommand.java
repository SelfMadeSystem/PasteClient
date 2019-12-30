package uwu.smsgamer.PasteClient.command.commands;

import uwu.smsgamer.PasteClient.ClientBase;
import uwu.smsgamer.PasteClient.command.Command;
import uwu.smsgamer.PasteClient.utils.ChatUtils;

import java.util.List;

public class LoadCommand extends Command {
    public LoadCommand() {
        super("load", "");
    }

    @Override
    public void run(String alias, String[] args) {
        if(args.length == 0){
            ChatUtils.info(".load <file>");
            return;
        }
        try{
            ClientBase.INSTANCE.fileManager.load(args[0]);
            ChatUtils.success("Successfully loaded config " + args[0] + "!");
        }catch (Exception e){
            e.printStackTrace();
            ChatUtils.info("Unsuccessfully loaded config " + args[0] + "!");
        }
    }

    @Override
    public List<String> autocomplete(int arg, String[] args) {
        return null;
    }
}
