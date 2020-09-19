package dev.zeeppss.lurkcommand;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class ConstructorTabCompleter implements TabCompleter {


    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args){
        if(command.getName().equalsIgnoreCase("lurk") && args.length == 1) {
            if(sender instanceof Player) {
                return Arrays.asList("reload");
            }
        }
        return null;

    }
}
