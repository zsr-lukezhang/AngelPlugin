package org.AngelCEMEMZ.angelPlugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommandPreprocessListener implements Listener {
    private final VisibilityManager visibilityManager;

    public PlayerCommandPreprocessListener(VisibilityManager visibilityManager) {
        this.visibilityManager = visibilityManager;
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        String[] args = event.getMessage().split(" ");
        if (args.length > 1) {
            Player targetPlayer = Bukkit.getPlayer(args[1]);
            if (targetPlayer != null && visibilityManager.isInvisible(targetPlayer)) {
                event.setCancelled(true);
                event.getPlayer().sendMessage("You cannot interact with an invisible player.");
            }
        }
    }
}