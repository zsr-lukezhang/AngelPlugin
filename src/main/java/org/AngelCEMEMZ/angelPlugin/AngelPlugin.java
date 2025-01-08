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
import java.util.Random;

public final class AngelPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // 插件启动逻辑
        say("Angel Plugin Started");
        // 彩蛋：1%的概率在启动时抛出异常，并要求玩家尝试 “MINCERAFT”
        Random random = new Random();
        if (random.nextInt(100) == 0) {
            String crashMessage = "You should also try our sister game, Mopjang Minceraft!";
            say(crashMessage); // 在控制台输出假的崩溃信息
            throw new RuntimeException(crashMessage); // 抛出异常，使服务器崩溃
        }
        // 创建一个 VisibilityManager 实例
        VisibilityManager visibilityManager = new VisibilityManager();
        // 注册 breakbedrock 命令
        this.getCommand("breakbedrock").setExecutor(new BreakBedrockCommand());
        // 注册 APAbout （关于）命令
        this.getCommand("apabout").setExecutor(new APAboutCommand());
        // 注册 invisible 命令，但是由于bug，删除此命令，仅在源代码钟保留
        if (false) { //这个条件永远不会成立，相当于删除了这个命令
            this.getCommand("invisible").setExecutor(new InvisibleCommand());
        }
        this.getCommand("invisible").setExecutor(new InvisibleCommand());
        // 注册 setvisibility 命令
        this.getCommand("setvisibility").setExecutor(new SetVisibilityCommand(visibilityManager));
        // 注册玩家加入的监听器
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(visibilityManager), this);
        // 注册玩家命令预处理的监听器
        getServer().getPluginManager().registerEvents(new PlayerCommandPreprocessListener(visibilityManager), this);
        getServer().getPluginManager().registerEvents(new PlayerTeleportListener(visibilityManager), this);
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

}