///     AngelPlugin - A Minecraft plugin that adds useful commands and features.
///     Copyright (C) 2025 Luke Zhang & AngelCEMEMZ
///
///     This program is free software: you can redistribute it and/or modify
///     it under the terms of the GNU General Public License as published by
///     the Free Software Foundation, either version 3 of the License, or
///     (at your option) any later version.
///
///     This program is distributed in the hope that it will be useful,
///     but WITHOUT ANY WARRANTY; without even the implied warranty of
///     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
///     GNU General Public License for more details.
///
///     You should have received a copy of the GNU General Public License
///     along with this program.  If not, see <https://www.gnu.org/licenses/>.

package org.AngelCEMEMZ.angelPlugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.entity.Player;

public class PlayerTeleportListener implements Listener {
    private final VisibilityManager visibilityManager;

    public PlayerTeleportListener(VisibilityManager visibilityManager) {
        this.visibilityManager = visibilityManager;
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        if (visibilityManager.isInvisible(player)) {
            event.setCancelled(true);
            player.sendMessage("You cannot teleport while invisible.");
        }
    }
}