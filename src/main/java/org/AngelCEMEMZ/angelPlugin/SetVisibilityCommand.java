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

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetVisibilityCommand implements CommandExecutor {
    private final VisibilityManager visibilityManager;

    public SetVisibilityCommand(VisibilityManager visibilityManager) {
        this.visibilityManager = visibilityManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("angelplugin.setvisibility")) {
            if (args.length == 2) {
                String action = args[0];
                Player targetPlayer = Bukkit.getPlayer(args[1]);

                if (targetPlayer == null) {
                    sender.sendMessage("Player not found.");
                    return false;
                }

                if (action.equalsIgnoreCase("ON")) {
                    visibilityManager.setVisible(targetPlayer);
                    sender.sendMessage(targetPlayer.getName() + " is now visible to other players.");
                    return true;
                } else if (action.equalsIgnoreCase("OFF")) {
                    visibilityManager.setInvisible(targetPlayer);
                    sender.sendMessage(targetPlayer.getName() + " is now invisible to other players.");
                    return true;
                } else {
                    sender.sendMessage("Invalid argument! Use /setvisibility ON <player> or /setvisibility OFF <player>.");
                    return false;
                }
            } else {
                sender.sendMessage("Please specify ON or OFF and the player name.");
                return false;
            }
        } else {
            sender.sendMessage("You do not have permission to use this command.");
            return false;
        }
    }
}