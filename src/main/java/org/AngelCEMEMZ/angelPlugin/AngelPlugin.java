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