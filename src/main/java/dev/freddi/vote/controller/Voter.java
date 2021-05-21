package dev.freddi.vote.controller;

import dev.freddi.vote.model.VoteInfo;
import dev.freddi.vote.utils.Empty;
import dev.freddi.vote.utils.Failure;
import dev.freddi.vote.utils.Result;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Voter {
    private boolean isVoteStarted = false;
    private VoteInfo info;
    public Voter() {
        info = new VoteInfo();
    }

    public Result<Empty> startVote() {
        if(isVoteStarted) {
            return new Result(new Failure("投票はすでに始まっています"));
        }
        isVoteStarted = true;
        info.reset();
        return new Result(new Empty());
    }

    public Result<Empty> vote(String from, String to) {
        if(!isVoteStarted) {
            return new Result(new Failure("投票は始まっていません"));
        }

        Player toPlayer = Bukkit.getPlayer(to);
        if(toPlayer == null) {
            return new Result(new Failure("プレイヤー \"" + to + "\" は存在しません"));
        }
        return info.vote(from, toPlayer);
    }

    public Result<VoteInfo> end() {
        if(!isVoteStarted) {
            return new Result(new Failure("投票は始まっていません"));
        }
        isVoteStarted = false;
        return new Result(info);
    }
}
