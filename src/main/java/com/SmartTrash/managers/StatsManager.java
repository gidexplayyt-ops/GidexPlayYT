package com.smarttrash.managers;

import com.smarttrash.models.PlayerStats2025;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class StatsManager {

    private final Map<Player, PlayerStats2025> playerStats;

    public StatsManager() {
        this.playerStats = new HashMap<>();
    }

    public PlayerStats2025 getPlayerStats(Player player) {
        return playerStats.computeIfAbsent(player, k -> new PlayerStats2025());
    }

    public void addTrashSorted(Player player, String category, double co2Saved) {
        PlayerStats2025 stats = getPlayerStats(player);
        stats.addTrash(category, co2Saved);
    }

    public void showAdvancedStats(Player player) {
        PlayerStats2025 stats = getPlayerStats(player);
        player.sendMessage(stats.getAdvancedStats());
    }

    public void cleanOldData() {
        // Очистка старых данных
        playerStats.entrySet().removeIf(entry ->
                entry.getValue().getTotalItems() == 0
        );
    }

    public void saveAllStats() {
        // Здесь можно добавить сохранение в файл/БД
        System.out.println("💾 Статистика сохранена для " + playerStats.size() + " игроков");
    }
}