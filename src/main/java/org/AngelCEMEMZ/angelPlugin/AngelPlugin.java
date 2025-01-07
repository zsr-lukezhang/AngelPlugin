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
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class AngelPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // 插件启动逻辑
        say("Angel Plugin Started");
        // 注册 breakbedrock 命令
        this.getCommand("breakbedrock").setExecutor(new BreakBedrockCommand());
        // 注册 invisible 命令，但是由于bug，删除此命令，仅在源代码钟保留
        // this.getCommand("invisible").setExecutor(new InvisibleCommand());
        // 注册 setvisibility 命令
        this.getCommand("setvisibility").setExecutor(new SetVisibilityCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        say("Angel Plugin Stopped");
    }

    public void say(String s) {
        CommandSender sender = Bukkit.getConsoleSender();
        sender.sendMessage(s);
    }

    // 加一个破基岩方法，去除玩家看着的基岩，当触发命令 /breakbedrock 时发生

}