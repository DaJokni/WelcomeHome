package fi.jokni.welcomehome;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class WelcomeHome extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        this.getConfig();
        this.saveDefaultConfig();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void oj(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        boolean motdtoggle = this.getConfig().getBoolean("Send a MOTD");
        boolean titletoggle = this.getConfig().getBoolean("Send a Title");
        boolean actionbartoggle = this.getConfig().getBoolean("Send a Actionbar");
        boolean soundtoggle = this.getConfig().getBoolean("Send a MOTD");
        String motdmsg = this.getConfig().getString("MOTD Message");
        String titlemsg = this.getConfig().getString("Title Message");
        String subtitlemsg = this.getConfig().getString("Subtitle Message");
        String actionbarmsg = this.getConfig().getString("Actionbar Message");
        String sound = this.getConfig().getString("Sound");
        Integer volume = this.getConfig().getInt("Volume");
        motdmsg = motdmsg.replace("%nl%", "\n");
        if (motdtoggle) {
            p.sendMessage(motdmsg);
        }
        if (titletoggle) {
            p.sendTitle((titlemsg), (subtitlemsg), 1, 80, 1);
        }
        if (actionbartoggle) {
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(actionbarmsg));
        }
        if (soundtoggle) {
            p.playSound(p.getLocation(), Sound.valueOf(sound), 1F, 1F);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("welcomehome")) {
            if (args.length == 1) {
                String s = args[0];
                Player p = (Player) sender;
                switch (s.toLowerCase()) {
                    case "reload":
                        if (sender.hasPermission("welcomehome.admin")) {
                            sender.sendMessage("§6§lWelcome§e§lHome §8> §fPlugin successfully reloaded!");
                            this.reloadConfig();
                            break;
                        } else {
                            sender.sendMessage("§6§lWelcome§e§lHome §8> §fPlugin unsuccessfully reloaded. Silly you aint got permissions.");
                            break;
                        }
                }
            }
        }return false;
    }
}