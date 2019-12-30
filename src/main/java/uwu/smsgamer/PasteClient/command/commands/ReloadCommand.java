package uwu.smsgamer.PasteClient.command.commands;

import uwu.smsgamer.PasteClient.ClientBase;
import uwu.smsgamer.PasteClient.command.Command;
import uwu.smsgamer.PasteClient.utils.ChatUtils;

import java.util.List;

public class ReloadCommand extends Command {
    public ReloadCommand() {
        super("reload", "");
    }

    @Override
    public void run(String alias, String[] args) {
        try{
            ClientBase.INSTANCE.fileManager.load();
            ChatUtils.success("Successfully reloaded!");
        }catch (Exception e){
            e.printStackTrace();
            ChatUtils.info("Unsuccessfully reloaded!");
        }
    }

    @Override
    public List<String> autocomplete(int arg, String[] args) {
        return null;
    }
}
