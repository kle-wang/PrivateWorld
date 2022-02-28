package cn.ikaile;/*
    @author kle_wang
    @2022/2/24
*/


import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class Utils {
    //数据文件初始化
    File dataConfigFile;
    FileConfiguration dataConfig;
    private Plugin plugin;
    //this plugin
    public void setMyPlugin(Plugin myplugin){
        this.plugin=myplugin;
        dataConfigFile = new File(myplugin.getDataFolder(), "data.yml");
        dataConfig = YamlConfiguration.loadConfiguration(dataConfigFile);
    }
    //获取是否有自己小世界
    public boolean isHaveWorld(Player p){
        return p.hasPermission("pw.has."+p.getName());
    }
    //获取世界玩家
    public String getWorldPlayer(String p){
        return (String) dataConfig.get("World."+p+".player");
    }
    //获取是否存在指定小世界
    public boolean isWorld(String p){
        return dataConfig.getStringList("Worlds").contains(p);
    }
    //添加世界玩家
    public Boolean addWorldPlayer(String p, String addP){
        if(getWorldPlayer(p).contains(addP)){
            return false;
        }
        dataConfig.set("World."+p+".player",getWorldPlayer(p)+addP+",");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"manuaddp "+addP+" Build."+p);
        try {
            dataConfig.save(dataConfigFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    //删除世界玩家
    public Boolean delWorldPlayer(String p,String delP) {
        String name = getWorldPlayer(p);
        name = name.replace(delP+",","");
        dataConfig.set("World."+p+".player",name);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"manudelp "+delP+" Build."+p);
        try {
            dataConfig.save(dataConfigFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
    public Boolean backHome(String p,String world){
        if(getWorldPlayer(world).contains(p)){
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"mv tp "+p+" "+world);
            return true;
        }else {
            return false;

        }
    }
    public Boolean backHome(Player p,String world){
        if(isHaveWorld(p) && p.getName().equals(world)){
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"mv tp "+p.getName()+" "+world);
            return true;
        }else {
            return false;
        }
    }
}
