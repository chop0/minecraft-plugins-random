import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.logging.Logger;

public class FireBow extends JavaPlugin implements Listener {
    public static Logger log = Logger.getLogger("Minecraft");
    public static boolean enabled = true;
    protected ArrayList<String> playersEnabled = new ArrayList<String>();

    public void onLoad() {
        log.info("[BoomBow] Server loaded.");
    }

    public void onEnable() {
        log.info("[BoomBow] enabling.");
        getServer().getPluginManager().registerEvents(this, this);
    }

    public void onDisable() {
        log.info("[Plugin] Server stopping.");
    }

    @EventHandler
    public void onArrowLaunch(EntityShootBowEvent event) {
        if (enabled && event.getEntity() instanceof Player) {

            Entity arrow = event.getProjectile();
            Player player = (Player) event.getEntity();
            World world = arrow.getWorld();
            Vector vel = arrow.getVelocity();

            if (playersEnabled.contains(player.getDisplayName())) {
                Entity tnt = world.spawn(arrow.getLocation(), TNTPrimed.class);

                tnt.setVelocity(vel);

                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        e.setDeathMessage("\247k\2474\247b" + e.getEntity().getPlayerListName() + e.getDeathMessage());
    }

    public boolean onCommand(CommandSender sender, Command command,
                             String commandLabel, String[] args) {
        Player player = (Player) sender;
        if (commandLabel.equalsIgnoreCase("boombow") && args[0].equalsIgnoreCase("off")) {
            enabled = false;
            sender.sendMessage("\u00a76\u00a7l[BoomBow] -> \u00a72\u00a7lYou: \u00a7b\u00a7lBoomBow turned off!");
            playersEnabled.remove(player.getDisplayName());
            return true;
        }
        if (commandLabel.equalsIgnoreCase("boombow") && args[0].equalsIgnoreCase("on") && sender.hasPermission("boombow.boombow")) {
            enabled = true;
            sender.sendMessage("\u00a76\u00a7l[BoomBow] -> \u00a72\u00a7lYou: \u00a7b\u00a7lBoomBow turned on!");
            playersEnabled.add(player.getDisplayName());
            return true;
        }
        if (commandLabel.equalsIgnoreCase("msg")) {
            Player playerToSendTo = player.getServer().getPlayer(args[0]);
            playerToSendTo.sendMessage("\247b\2476[" + sender.getName() + "] ->\2472\247b {You}: " + args[1]);
            return true;
        }
        if (commandLabel.equalsIgnoreCase("tnt") && sender.hasPermission("boombow.tnt")) {
            Entity tnt = sender.getServer().getWorlds().get(0).spawn(player.getLocation(), TNTPrimed.class);
            return true;
        }
        return false;
    }
}
