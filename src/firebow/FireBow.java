
package firebow; 

import java.util.logging.Logger;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class FireBow extends JavaPlugin implements Listener {
      public static Logger log = Logger.getLogger("Minecraft");
  public static boolean enabled = true;

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
    if (enabled) {

      Entity arrow = event.getProjectile();
      World world = arrow.getWorld();
      Vector vel = arrow.getVelocity();
      Entity snowball = world.spawn(arrow.getLocation(), TNTPrimed.class);

      snowball.setVelocity(vel);

      event.setCancelled(true);
    }
  }
  
  public boolean onCommand(CommandSender sender, Command command, 
                           String commandLabel, String[] args) {         
    if (commandLabel.equalsIgnoreCase("boombow") || args[0].equalsIgnoreCase("off")) {
      enabled = false;
      sender.sendMessage("\u00a76\u00a7l[BoomBow] -> \u00a72\u00a7lYou: \u00a7b\u00a7lBoomBow turned off!");
      return true;
    }
    if (commandLabel.equalsIgnoreCase("boombow")) {
      enabled = true;
      sender.sendMessage("\u00a76\u00a7l[BoomBow] -> \u00a72\u00a7lYou: \u00a7b\u00a7lBoomBow turned on!");

      return true;
    }
    return false;
  }
}
