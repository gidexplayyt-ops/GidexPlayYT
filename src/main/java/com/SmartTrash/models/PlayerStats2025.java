package com.smarttrash.models;

import java.util.HashMap;
import java.util.Map;

public class PlayerStats2025 {
    private final Map<String, Integer> trashSorted;
    private int totalItems;
    private double totalCo2Saved;
    private long firstRecycleTime;
    private int sessions;

    public PlayerStats2025() {
        this.trashSorted = new HashMap<>();
        this.totalItems = 0;
        this.totalCo2Saved = 0.0;
        this.firstRecycleTime = System.currentTimeMillis();
        this.sessions = 1;

        // Инициализация категорий 2025
        trashSorted.put("nanoplastic", 0);
        trashSorted.put("quantum_waste", 0);
        trashSorted.put("holographic", 0);
        trashSorted.put("biotech", 0);
        trashSorted.put("energy_cells", 0);
        trashSorted.put("ai_components", 0);
    }

    public void addTrash(String category, double co2Saved) {
        trashSorted.put(category, trashSorted.getOrDefault(category, 0) + 1);
        totalItems++;
        totalCo2Saved += co2Saved;
    }

    public void incrementSessions() {
        sessions++;
    }

    public int getTotalItems() { return totalItems; }
    public double getTotalCo2Saved() { return totalCo2Saved; }
    public Map<String, Integer> getTrashSorted() { return trashSorted; }
    public long getFirstRecycleTime() { return firstRecycleTime; }
    public int getSessions() { return sessions; }

    public String getAdvancedStats() {
        long timePlaying = System.currentTimeMillis() - firstRecycleTime;
        long hours = timePlaying / (1000 * 60 * 60);
        long days = hours / 24;

        double efficiency = totalItems > 0 ? (totalCo2Saved / (totalItems * 2)) * 100 : 0;

        return "§6╔══════════════════════════════╗\n" +
                "§6║     📊 СТАТИСТИКА 2025      ║\n" +
                "§6╚══════════════════════════════╝\n" +
                "§f🎯 Всего переработано: §a" + totalItems + " шт.\n" +
                "§f🌱 Сохранено CO2: §e" + String.format("%.2f", totalCo2Saved) + " кг\n" +
                "§f⚡ Эффективность: §b" + String.format("%.1f", efficiency) + "%\n" +
                "§f⏱️ Время активности: §6" + days + "д " + (hours % 24) + "ч\n" +
                "§f🔄 Сессии: §3" + sessions + "\n" +
                "§f📈 Категории:\n" +
                "§7- Нано-пластик: §b" + trashSorted.get("nanoplastic") + " шт.\n" +
                "§7- Квантовые отходы: §d" + trashSorted.get("quantum_waste") + " шт.\n" +
                "§7- Голографические: §9" + trashSorted.get("holographic") + " шт.\n" +
                "§7- Био-технологии: §a" + trashSorted.get("biotech") + " шт.\n" +
                "§7- Энерго-ячейки: §e" + trashSorted.get("energy_cells") + " шт.\n" +
                "§7- AI компоненты: §c" + trashSorted.get("ai_components") + " шт.";
    }
}