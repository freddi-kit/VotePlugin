package dev.freddi.vote;

import dev.freddi.vote.controller.Voter;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class Vote extends JavaPlugin {

    Voter voter = new Voter();
    @Override
    public void onEnable() {
        // Plugin startup logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(cmd.getName().equalsIgnoreCase("vs")) {
            voter.startVote()
                .handle(success -> {
                    Bukkit.broadcastMessage(Consts.LogPrefix + "投票開始！");
                }, failure -> {
                    sender.sendMessage(failure.message);
                });
        } else if(cmd.getName().equalsIgnoreCase("v")) {
            if(args.length != 1) {
                sender.sendMessage(Consts.LogPrefix + "投票先を正しく指定していません");
                return false;
            }
            voter.vote(sender.getName(), args[0])
                .handle(userName -> {
                    sender.sendMessage(Consts.LogPrefix + userName + "に投票しました");
                }, failure -> {
                    sender.sendMessage(Consts.LogPrefix + failure.message);
                });
        } else if(cmd.getName().equalsIgnoreCase("vsend")) {
            voter.end()
                .handle(info -> {
                    Bukkit.broadcastMessage(Consts.LogPrefix + "投票先終了・開示！");
                    info.show();
                }, failure -> {
                    sender.sendMessage(Consts.LogPrefix + failure.message);
                });
        }
        return false;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
