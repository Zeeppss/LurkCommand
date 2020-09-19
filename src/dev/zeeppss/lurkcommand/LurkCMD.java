package dev.zeeppss.lurkcommand;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class LurkCMD extends JavaPlugin implements Listener {
    public static String pre = "§8[§3Lurk§bCommand§8] §e| ";

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(this, this);
        getCommand("lurk").setTabCompleter(new ConstructorTabCompleter());
    }

    @Override
    public void onDisable() {

    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("lurk")) {
            if (!sender.hasPermission("lurk.reload")) {
                sender.sendMessage(this.pre + "§cPlayer only!");
                return true;
            }
            if (args.length == 0) {
                sender.sendMessage(this.pre + "§cUsage: /lurk reload");
                return true;
            }
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("reload")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            this.getConfig().getString("reload-message")));
                    this.reloadConfig();
                }
            }

        }
        return false;

    }

    @EventHandler
    public void commandLurk(PlayerCommandPreprocessEvent e) {
        boolean toggled = getConfig().getBoolean("messages.lurk-toggled");
        Player player = (Player) e.getPlayer();
        if (player.hasPermission("lurk.see.commands")) {
            if (toggled == false) {
                return;
            }
            if (toggled == true) {
                String message = getConfig().getString("messages.lurk-message");
                message.replace("%player%", player.getName());
                message.replace("%message%", e.getMessage());
                message = message.replace("%player%", player.getName()).replace("%command%", e.getMessage());
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
            }
        }
    }
}
