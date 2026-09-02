package com.gensmp.command;

import com.gensmp.GenSMP;
import com.gensmp.gen.GenType;
import com.gensmp.player.PlayerGenData;
import com.gensmp.util.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Main command executor for /gensmp commands
 */
public class GenSMPCommand implements CommandExecutor {
    private final GenSMP plugin;

    public GenSMPCommand(GenSMP plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Messages.PREFIX + "This command can only be used by players.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            sendHelp(player);
            return true;
        }

        String subcommand = args[0].toLowerCase();

        switch (subcommand) {
            case "help":
                sendHelp(player);
                return true;
            case "info":
                showGenInfo(player);
                return true;
            case "withdraw":
                withdrawGen(player);
                return true;
            case "reroll":
                if (!player.hasPermission("gensmp.admin.reroll")) {
                    player.sendMessage(Messages.NO_PERMISSION);
                    return true;
                }
                if (args.length < 2) {
                    player.sendMessage(Messages.PREFIX + "Usage: /gensmp reroll <player>");
                    return true;
                }
                rerollGen(player, args[1]);
                return true;
            case "give":
                if (!player.hasPermission("gensmp.admin.give")) {
                    player.sendMessage(Messages.NO_PERMISSION);
                    return true;
                }
                if (args.length < 3) {
                    player.sendMessage(Messages.PREFIX + "Usage: /gensmp give <player> <gen>");
                    return true;
                }
                giveGen(player, args[1], args[2]);
                return true;
            case "reload":
                if (!player.hasPermission("gensmp.admin.reload")) {
                    player.sendMessage(Messages.NO_PERMISSION);
                    return true;
                }
                plugin.getConfigManager().reloadConfig();
                player.sendMessage(Messages.PREFIX + "Config reloaded!");
                return true;
            default:
                player.sendMessage(Messages.INVALID_COMMAND);
                return true;
        }
    }

    private void sendHelp(Player player) {
        player.sendMessage("\n§6=== GenSMP Help ===");
        player.sendMessage("§e/gensmp info §7- View your current Gens");
        player.sendMessage("§e/gensmp withdraw §7- Withdraw your first Gen");
        if (player.hasPermission("gensmp.admin")) {
            player.sendMessage("§6Admin Commands:");
            player.sendMessage("§e/gensmp reroll <player> §7- Reroll starter Gen");
            player.sendMessage("§e/gensmp give <player> <gen> §7- Give a Gen to player");
            player.sendMessage("§e/gensmp reload §7- Reload configuration");
        }
        player.sendMessage("§6==================\n");
    }

    private void showGenInfo(Player player) {
        PlayerGenData data = plugin.getPlayerDataManager().getPlayerData(player);
        player.sendMessage("\n§6=== Your Gens ===");
        
        if (data.getSlot1() != null) {
            GenType type = data.getSlot1();
            String status = data.isSlot1Unlocked() ? "§aUnlocked" : "§cLocked";
            long remaining = data.getSlot1RemainingTime() / 1000;
            player.sendMessage("§eSlot 1: §6" + type.getDisplayName() + " Gen " + status);
            if (remaining > 0) {
                player.sendMessage("§7  Unlocks in " + remaining + "s");
            }
        } else {
            player.sendMessage("§eSlot 1: §7Empty");
        }

        if (data.getSlot2() != null) {
            GenType type = data.getSlot2();
            String status = data.isSlot2Unlocked() ? "§aUnlocked" : "§cLocked";
            long remaining = data.getSlot2RemainingTime() / 1000;
            player.sendMessage("§eSlot 2: §6" + type.getDisplayName() + " Gen " + status);
            if (remaining > 0) {
                player.sendMessage("§7  Unlocks in " + remaining + "s");
            }
        } else {
            player.sendMessage("§eSlot 2: §7Empty");
        }
        
        player.sendMessage("§6==================\n");
    }

    private void withdrawGen(Player player) {
        PlayerGenData data = plugin.getPlayerDataManager().getPlayerData(player);
        
        if (data.getSlot1() == null) {
            player.sendMessage(Messages.GEN_WITHDRAWN_EMPTY);
            return;
        }

        GenType withdrawn = data.withdraw();
        plugin.getPlayerDataManager().savePlayerData(data);
        
        player.sendMessage(String.format(Messages.GEN_WITHDRAWN, withdrawn.getDisplayName()));
    }

    private void rerollGen(Player player, String targetName) {
        Player target = Bukkit.getPlayer(targetName);
        if (target == null) {
            player.sendMessage(String.format(Messages.PLAYER_NOT_FOUND, targetName));
            return;
        }

        PlayerGenData data = plugin.getPlayerDataManager().getPlayerData(target);
        GenType randomGen = GenType.values()[(int) (Math.random() * GenType.values().length)];
        data.setSlot1(randomGen);
        data.setStarterAssigned(true);
        data.setSlot1UnlockTime(System.currentTimeMillis() + 600000); // 10 minutes
        plugin.getPlayerDataManager().savePlayerData(data);

        player.sendMessage(String.format(Messages.REROLL_SUCCESS, target.getName(), randomGen.getDisplayName()));
        target.sendMessage(String.format(Messages.GEN_OBTAINED, randomGen.getDisplayName()));
    }

    private void giveGen(Player player, String targetName, String genName) {
        Player target = Bukkit.getPlayer(targetName);
        if (target == null) {
            player.sendMessage(String.format(Messages.PLAYER_NOT_FOUND, targetName));
            return;
        }

        GenType genType = GenType.fromDisplayName(genName);
        if (genType == null) {
            genType = GenType.fromId(genName);
        }

        if (genType == null) {
            player.sendMessage(String.format(Messages.GIVE_INVALID_GEN, genName));
            return;
        }

        PlayerGenData data = plugin.getPlayerDataManager().getPlayerData(target);
        if (data.isFull()) {
            player.sendMessage(Messages.GEN_ALREADY_HAVE_TWO);
            return;
        }

        if (data.getSlot1() == null) {
            data.setSlot1(genType);
            data.setSlot1UnlockTime(System.currentTimeMillis() + 600000);
        } else {
            data.setSlot2(genType);
            data.setSlot2UnlockTime(System.currentTimeMillis() + 600000);
        }
        plugin.getPlayerDataManager().savePlayerData(data);

        player.sendMessage(String.format(Messages.GIVE_SUCCESS, genType.getDisplayName(), target.getName()));
        target.sendMessage(String.format(Messages.GEN_OBTAINED, genType.getDisplayName()));
    }
}
