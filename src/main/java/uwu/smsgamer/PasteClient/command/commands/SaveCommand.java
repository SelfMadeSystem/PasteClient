package uwu.smsgamer.PasteClient.command.commands;

import uwu.smsgamer.PasteClient.command.Command;
import uwu.smsgamer.PasteClient.utils.ChatUtils;

import java.util.List;

public class SaveCommand extends Command {
    public SaveCommand() {
        super("save", "");
    }

    @Override
    public void run(String alias, String[] args) {
        if(args.length == 0){
            ChatUtils.info(".save <file>");
            return;
        }
        try{
            ChatUtils.info("Successfully saved to config " + args[0] + "!");
        }catch (Exception e){
            e.printStackTrace();
            ChatUtils.info("Unsuccessfully saved to config " + args[0] + "!");
        }
    }

    @Override
    public List<String> autocomplete(int arg, String[] args) {
        return null;
    }
}
