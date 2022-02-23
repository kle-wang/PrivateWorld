package cn.ikaile;/*
    @author kle_wang
    @2022/2/24
*/

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        Player playerName=(Player) sender;
        if(args.length>=1){
            switch (args[0]){
                case "add":
                if(args.length<2){
                    return true;
                }
                    if(playerName.hasPermission("pw.has."+playerName.getName())){
                        if(PrivateWorld.utils.addWorldPlayer(playerName.getName(),args[1])){
                            playerName.sendMessage("添加成功！");
                        }else {
                            playerName.sendMessage("添加失败，是否此玩家已经有权限。");
                        }
                        return true;
                    }else{
                        playerName.sendMessage("你还没有小世界的管理权限");
                        return true;
                    }
                case "del":
                    if(args.length<2){
                        return true;
                    }
                    if(playerName.hasPermission("pw.has."+playerName.getName())){
                    if(PrivateWorld.utils.delWorldPlayer(playerName.getName(),args[1])){
                        playerName.sendMessage("执行完毕。");
                        return true;
                    }else {
                        playerName.sendMessage("删除失败！");
                        return true;
                    }
                    } else{
                        playerName.sendMessage("你还没有小世界的管理权限");
                        return true;
                    }

                case "me":
                    if(!PrivateWorld.utils.isHaveWorld(playerName)){
                        playerName.sendMessage("您好像还没有自己的小世界，您可以通过点券商城购买后联系服主进行开通");
                    }else{
                        playerName.sendMessage("世界："+playerName.getName()+"\n有权限玩家："+PrivateWorld.utils.getWorldPlayer(playerName.getName()));
                    }
                    return true;
                default:
                    if(PrivateWorld.utils.backHome(playerName.getName(),args[0])){
                        return true;
                    } else {
                        if(PrivateWorld.utils.backHome(playerName,args[0])){
                            return true;
                        }else{
                            playerName.sendMessage("你没有这个世界的权限哦。。。");
                            return true;
                        }

                    }


            }
        }
        return true;
    }
}
