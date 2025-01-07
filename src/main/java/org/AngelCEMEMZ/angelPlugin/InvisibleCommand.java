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

public class InvisibleCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("angelplugin.invisible")) {
                if (args.length > 0) {
                    if (args[0].equalsIgnoreCase("ON")) {
                        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                            if (!onlinePlayer.equals(player)) {
                                onlinePlayer.hidePlayer(player);
                            }
                        }
                        player.sendMessage("You are now invisible to other players.");
                        return true;
                    } else if (args[0].equalsIgnoreCase("OFF")) {
                        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                            if (!onlinePlayer.equals(player)) {
                                onlinePlayer.showPlayer(player);
                            }
                        }
                        player.sendMessage("You are now visible to other players.");
                        return true;
                    } else {
                        player.sendMessage("Invalid argument! Use /invisible ON or /invisible OFF.");
                        return false;
                    }
                } else {
                    player.sendMessage("Please specify ON or OFF.");
                    return false;
                }
            } else {
                player.sendMessage("You do not have permission to use this command.");
                return false;
            }
        } else {
            sender.sendMessage("This command can only be executed by a player.");
            return false;
        }
    }
}