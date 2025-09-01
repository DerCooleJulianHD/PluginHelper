
public class PlayerManager {
    
    static final BedWars plugin = BedWars.getInstance();
    static final List<Player> spectators = new ArrayList<>();

    public static void readyPlayer(Player player) {
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        player.setHealth(20D);
        player.setHealthScale(20D);
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        if (!player.isOp()) player.setAllowFlight(false);
        player.setFlying(false);
        player.setExp(0F);
        player.setLevel(0);
        player.setGameMode(GameMode.SURVIVAL);
        player.setPlayerListName(player.getName());
        player.setSaturation(20F);
        player.setFoodLevel(20);
        player.getActivePotionEffects().forEach(potionEffect -> player.removePotionEffect(potionEffect.getType()));
        player.setFireTicks(0);
    }

    public static void setTeam(Player player) {
        final TeamList list = plugin.getGameData().getTeams();
        if (list == null) return;
        if (list.isEmpty()) return;
        final Team team = list.getTeamJoinable();
        if (team == null) return;
        team.addPlayer(player);
    }

    public static void removeTeam(Player player) {
        final Team team = TeamList.getPlayerTeam(player);
        if (team == null) return;
        team.removePlayer(player);
    }

    public static List<Player> getSpectators() {
        return spectators;
    }

    public boolean isSpectator(Player player) {
        return spectators.contains(player);
    }

    public static void setSpectator(Player player, boolean b) {
        if (b) {
            Bukkit.getOnlinePlayers().remove(player);
            spectators.add(player);
            PlayerManager.setSpectorTeam(player);
        } else {
            spectators.remove(player);
            org.bukkit.scoreboard.Team team = player.getScoreboard().getEntryTeam(player.getName());
            if (team == null) return;
            team.removeEntry(player.getName());
        }
    }

    static void setSpectorTeam(Player player) {
        player.setGameMode(GameMode.ADVENTURE);

        final Scoreboard scoreboard = player.getScoreboard();
        org.bukkit.scoreboard.Team team = scoreboard.getTeam("spectator");
        if (team == null) {
            team = scoreboard.registerNewTeam("spectator");
            team.setPrefix(ChatColor.GRAY.toString());
            team.setSuffix(ChatColor.DARK_GRAY + " [" + ChatColor.DARK_RED + "X" + ChatColor.DARK_GRAY + "]");
            team.setAllowFriendlyFire(true);
            team.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
        }

        player.getActivePotionEffects().forEach(potionEffect -> player.removePotionEffect(potionEffect.getType()));
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 255, true, false));

        for (Player target : Bukkit.getOnlinePlayers()) {
            if (team.hasEntry(target.getName())) continue;
            target.hidePlayer(player);
        }
    }
}


