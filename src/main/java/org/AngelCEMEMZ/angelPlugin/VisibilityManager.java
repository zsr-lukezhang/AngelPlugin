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

import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class VisibilityManager {
    private final Set<Player> invisiblePlayers = new HashSet<>();

    public void setInvisible(Player player) {
        invisiblePlayers.add(player);
        for (Player onlinePlayer : player.getServer().getOnlinePlayers()) {
            onlinePlayer.hidePlayer(player);
        }
    }

    public void setVisible(Player player) {
        invisiblePlayers.remove(player);
        for (Player onlinePlayer : player.getServer().getOnlinePlayers()) {
            onlinePlayer.showPlayer(player);
        }
    }

    public boolean isInvisible(Player player) {
        return invisiblePlayers.contains(player);
    }

    public void applyVisibility(Player joiningPlayer) {
        for (Player invisiblePlayer : invisiblePlayers) {
            joiningPlayer.hidePlayer(invisiblePlayer);
        }
    }
}
