package dev.freddi.vote.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import dev.freddi.vote.utils.Empty;
import dev.freddi.vote.utils.Failure;
import dev.freddi.vote.utils.Result;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;


public class VoteInfo {
    private HashMap<String, List<String>> results = new HashMap<>();
    private List<String> alreadyVoted = new ArrayList<>();

    public VoteInfo() {}

    public void reset() {
        results = new HashMap<>();
        alreadyVoted = new ArrayList<>();
    }

    public Result<String> vote(String from, Player to) {
        if(alreadyVoted.contains(from)) {
            return new Result(new Failure("すでにあなたは投票しています"));
        }

        if(!results.containsKey(to.getDisplayName())) {
            results.put(to.getDisplayName(), new ArrayList<>());
        }

        List<String> resultList = results.get(to.getDisplayName());

        if(Arrays.asList(resultList).contains(to.getDisplayName())) {
            return new Result(new Failure(""));
        }
        resultList.add(from);
        results.put(to.getDisplayName(), resultList);
        alreadyVoted.add(from);
        return new Result(to.getDisplayName());
    }

    public void show() {
        // TODO: Log関係はResultとして渡せるようにする
        for(String key: results.keySet()) {
            int count = results.get(key).size();
            Bukkit.broadcastMessage(key + ": " + count + "票");
        }
    }
}
