package dev.neqo.pausesky;

import org.bukkit.GameRule;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PauseSky extends JavaPlugin implements Listener {
  @Override
  public void onEnable() {
    getServer().getPluginManager().registerEvents(this, this);
  }

  @EventHandler
  public void onLoad(WorldLoadEvent event) {
    if (getServer().getOnlinePlayers().isEmpty()) {
      setSkyTransitionEnabled(false);
    }
    else {
      setSkyTransitionEnabled(true);
    }
  }

  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
    setSkyTransitionEnabled(true);
  }

  @EventHandler
  public void onLeave(PlayerQuitEvent event) {
    //There will be no online players since the last one player is leaving.
    if (getServer().getOnlinePlayers().size() == 1) {
      setSkyTransitionEnabled(false);
    }
  }

  private void setSkyTransitionEnabled(boolean value) {
    for(var world: getServer().getWorlds()) {
      world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, value);
      world.setGameRule(GameRule.DO_WEATHER_CYCLE, value);
    }
  }
}
