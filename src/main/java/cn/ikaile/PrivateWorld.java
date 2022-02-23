package cn.ikaile;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class PrivateWorld extends JavaPlugin {
    public static Utils utils;
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new Event(),this);
        Bukkit.getPluginCommand("pworld").setExecutor(new Command());
        utils =new Utils();
        utils.setMyPlugin(this);
        this.saveDefaultConfig();
        this.saveResource("data.yml",false);
        this.getLogger().info("success!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
