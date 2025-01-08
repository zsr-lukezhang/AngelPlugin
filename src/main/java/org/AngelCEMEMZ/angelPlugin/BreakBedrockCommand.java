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
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;

import java.util.*;

public class BreakBedrockCommand implements CommandExecutor {

    private final Map<Player, Integer> confirmationMap = new HashMap<>();
    private final Map<Player, List<Block>> brokenBedrockMap = new HashMap<>();
    private final Random random = new Random();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Check if the sender is a player
        if (sender instanceof Player) {
            Player player = (Player) sender;

            // Inform the user about the developers, repository, and license

            if (args.length > 0 && args[0].equalsIgnoreCase("A")) {
                player.sendMessage("Command Moved to /APAbout");
                return true;
            }

            // Easter egg
            if (args.length > 0 && args[0].equalsIgnoreCase("H")) {
                player.sendMessage("Hello, World!");
                player.sendMessage("This is an easter egg, so don't tell it to others!");
                player.sendMessage("This is my first plugin, I hope you like it!");
                player.sendMessage("This easter egg is only known by Luke Zhang, not even AngelCEMEMZ!");
                player.sendMessage("I hope you have a good day!");
            }

            // Usage
            if (args.length > 0 && args[0].equalsIgnoreCase("U")) {
                player.sendMessage("Angel Plugin - Break Bedrock");
                player.sendMessage("D: Diameter of the bedrock to break");
                player.sendMessage("A: About the plugin");
                player.sendMessage("Q: Quit the confirmation process");
                player.sendMessage("R: Restore the broken bedrock");
                player.sendMessage("UNKNOWN: Easter egg");
                return true;
            }

            // Check if the command is to restore bedrock
            if (args.length > 0 && args[0].equalsIgnoreCase("r")) {
                return restoreBedrock(player);
            }

            // Check if the command is to quit the confirmation process
            if (args.length > 0 && args[0].equalsIgnoreCase("q")) {
                if (confirmationMap.containsKey(player)) {
                    confirmationMap.remove(player);
                    player.sendMessage("Confirmation process has been cancelled.");
                    return true;
                } else {
                    player.sendMessage("No confirmation process to cancel.");
                    return false;
                }
            }

            // Check if the player is already in the confirmation process
            if (confirmationMap.containsKey(player)) {
                if (args.length == 1 || (args.length == 3 && args[0].equalsIgnoreCase("D"))) {
                    try {
                        int confirmationNumber = Integer.parseInt(args[args.length - 1]);
                        // Validate the confirmation number
                        if (confirmationMap.get(player) == confirmationNumber) {
                            // Confirmation successful, proceed with breaking bedrock
                            confirmationMap.remove(player);
                            int diameter = args.length == 3 ? Math.round(Float.parseFloat(args[1])) : 1;
                            if (diameter > 5) {
                                player.sendMessage("Diameter cannot be greater than 5!");
                                return false;
                            }
                            if (diameter == 1) {
                                return breakSingleBedrock(player);
                            }
                            int radius = diameter / 2;
                            return breakBedrock(player, radius);
                        } else {
                            player.sendMessage("Wrong confirmation number!");
                            return false;
                        }
                    } catch (NumberFormatException e) {
                        player.sendMessage("Please input a valid number!");
                        return false;
                    }
                } else {
                    player.sendMessage("Please input the confirmation number!");
                    return false;
                }
            } else {
                // Generate a random confirmation number and prompt the player
                int confirmationNumber = random.nextInt(100) + 1;
                confirmationMap.put(player, confirmationNumber);
                if (args.length == 2 && args[0].equalsIgnoreCase("D")) {
                    player.sendMessage("Please type /breakbedrock D " + args[1] + " " + confirmationNumber + " to confirm.");
                } else {
                    player.sendMessage("Please type /breakbedrock " + confirmationNumber + " to confirm.");
                }
                return true;
            }
        } else {
            sender.sendMessage("This command can only be executed by a player.");
            return false;
        }
    }

    private boolean breakSingleBedrock(Player player) {
        Block targetBlock = player.getTargetBlockExact(5);
        if (targetBlock != null && targetBlock.getType() == Material.BEDROCK) {
            targetBlock.setType(Material.AIR);
            player.sendMessage("Single bedrock block has been broken!");
            return true;
        } else {
            player.sendMessage("No bedrock block found in sight!");
            return false;
        }
    }

    private boolean breakBedrock(Player player, int radius) {
        boolean bedrockFound = false;
        List<Block> brokenBlocks = new ArrayList<>();
        // Get the player's location
        int px = player.getLocation().getBlockX();
        int py = player.getLocation().getBlockY();
        int pz = player.getLocation().getBlockZ();

        // Iterate through all blocks within the specified radius
        for (int x = px - radius; x <= px + radius; x++) {
            for (int y = py - radius; y <= py + radius; y++) {
                for (int z = pz - radius; z <= pz + radius; z++) {
                    Block block = player.getWorld().getBlockAt(x, y, z);
                    // Check if the block is bedrock
                    if (block.getType() == Material.BEDROCK) {
                        // Replace bedrock with air
                        block.setType(Material.AIR);
                        bedrockFound = true;
                        // Save the broken bedrock location
                        brokenBlocks.add(block);
                    }
                }
            }
        }

        if (bedrockFound) {
            brokenBedrockMap.put(player, brokenBlocks);
            player.sendMessage("All bedrock within the diameter has been broken!");
            return true;
        } else {
            player.sendMessage("No bedrock found within the diameter!");
            return false;
        }
    }

    private boolean restoreBedrock(Player player) {
        // Get the broken bedrock blocks
        List<Block> brokenBlocks = brokenBedrockMap.get(player);
        if (brokenBlocks != null && !brokenBlocks.isEmpty()) {
            for (Block block : brokenBlocks) {
                // Restore the bedrock block
                block.setType(Material.BEDROCK);
            }
            player.sendMessage("Bedrock restored!");
            brokenBedrockMap.remove(player);
            return true;
        } else {
            player.sendMessage("No bedrock to restore!");
            return false;
        }
    }
}