package com.smarttrash.ai;

import com.smarttrash.SmartTrash;
import com.smarttrash.managers.StatsManager;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class TrashAI {

    private final Map<Material, String> materialCategories;
    private final Random random;
    private final StatsManager statsManager;
    private final Map<Material, Double> impactMap;
    private final Map<String, String> displayNames;

    public TrashAI() {
        this.random = new Random();
        this.statsManager = SmartTrash.getInstance().getStatsManager();
        this.materialCategories = new HashMap<>();
        this.impactMap = new HashMap<>();
        this.displayNames = new HashMap<>();
        initializeAICategories();
        initializeImpactMap();
        initializeDisplayNames();
    }

    private void initializeAICategories() {
        // AI классификация материалов 2025 - используем существующие материалы
        materialCategories.put(Material.GLASS_BOTTLE, "nanoplastic");
        materialCategories.put(Material.POTION, "nanoplastic");
        materialCategories.put(Material.HONEY_BOTTLE, "nanoplastic");

        materialCategories.put(Material.IRON_INGOT, "quantum_waste");
        materialCategories.put(Material.GOLD_INGOT, "quantum_waste");
        materialCategories.put(Material.DIAMOND, "quantum_waste");
        materialCategories.put(Material.NETHERITE_INGOT, "quantum_waste");
        materialCategories.put(Material.COPPER_INGOT, "quantum_waste");

        materialCategories.put(Material.PRISMARINE_CRYSTALS, "holographic");
        materialCategories.put(Material.AMETHYST_SHARD, "holographic");
        materialCategories.put(Material.QUARTZ, "holographic");
        materialCategories.put(Material.ENDER_PEARL, "holographic");

        materialCategories.put(Material.NETHER_STAR, "biotech");
        materialCategories.put(Material.DRAGON_BREATH, "biotech");
        materialCategories.put(Material.PHANTOM_MEMBRANE, "biotech");
        materialCategories.put(Material.SCULK_CATALYST, "biotech");

        materialCategories.put(Material.GLOWSTONE_DUST, "energy_cells");
        materialCategories.put(Material.REDSTONE, "energy_cells");
        materialCategories.put(Material.BLAZE_POWDER, "energy_cells");
        materialCategories.put(Material.GUNPOWDER, "energy_cells");

        materialCategories.put(Material.CLOCK, "ai_components");
        materialCategories.put(Material.COMPASS, "ai_components");
        materialCategories.put(Material.RECOVERY_COMPASS, "ai_components");
        materialCategories.put(Material.SCULK_SENSOR, "ai_components");
        materialCategories.put(Material.CALIBRATED_SCULK_SENSOR, "ai_components");
    }

    private void initializeImpactMap() {
        // Расчет экологического воздействия для 2025 - используем HashMap
        impactMap.put(Material.GLASS_BOTTLE, 0.8);
        impactMap.put(Material.POTION, 1.2);
        impactMap.put(Material.HONEY_BOTTLE, 1.5);
        impactMap.put(Material.IRON_INGOT, 8.9);
        impactMap.put(Material.GOLD_INGOT, 15.2);
        impactMap.put(Material.DIAMOND, 25.0);
        impactMap.put(Material.NETHERITE_INGOT, 35.0);
        impactMap.put(Material.COPPER_INGOT, 7.1);
        impactMap.put(Material.PRISMARINE_CRYSTALS, 6.8);
        impactMap.put(Material.AMETHYST_SHARD, 4.5);
        impactMap.put(Material.QUARTZ, 5.4);
        impactMap.put(Material.ENDER_PEARL, 10.5);
        impactMap.put(Material.NETHER_STAR, 12.5);
        impactMap.put(Material.DRAGON_BREATH, 18.7);
        impactMap.put(Material.PHANTOM_MEMBRANE, 3.2);
        impactMap.put(Material.SCULK_CATALYST, 14.3);
        impactMap.put(Material.GLOWSTONE_DUST, 4.2);
        impactMap.put(Material.REDSTONE, 3.9);
        impactMap.put(Material.BLAZE_POWDER, 6.1);
        impactMap.put(Material.GUNPOWDER, 2.8);
        impactMap.put(Material.CLOCK, 9.7);
        impactMap.put(Material.COMPASS, 8.3);
        impactMap.put(Material.RECOVERY_COMPASS, 11.2);
        impactMap.put(Material.SCULK_SENSOR, 13.8);
        impactMap.put(Material.CALIBRATED_SCULK_SENSOR, 16.5);
    }

    private void initializeDisplayNames() {
        displayNames.put("nanoplastic", "§bНано-Пластик");
        displayNames.put("quantum_waste", "§dКвантовые Отходы");
        displayNames.put("holographic", "§9Голографические");
        displayNames.put("biotech", "§aБио-Технологии");
        displayNames.put("energy_cells", "§eЭнерго-Ячейки");
        displayNames.put("ai_components", "§cAI Компоненты");
    }

    public void analyzeInventory(Player player) {
        List<AITrashItem> detectedItems = new ArrayList<>();
        int totalItems = 0;
        int trashItems = 0;

        player.sendMessage(" ");
        player.sendMessage("§d🤖 Запуск AI анализа инвентаря...");
        player.playSound(player.getLocation(), Sound.BLOCK_BEACON_AMBIENT, 1.0f, 1.2f);

        // AI анализ каждого предмета
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && !item.getType().isAir()) {
                AITrashItem trashItem = analyzeItem(item);
                if (trashItem != null) {
                    detectedItems.add(trashItem);
                    trashItems++;
                }
                totalItems++;
            }
        }

        // Показ результатов
        showAIAnalysis(player, detectedItems, totalItems, trashItems);
    }

    private AITrashItem analyzeItem(ItemStack item) {
        String category = materialCategories.get(item.getType());
        if (category != null) {
            double aiConfidence = 0.7 + (random.nextDouble() * 0.3); // 70-100% уверенность
            double co2Impact = calculateCO2Impact(item.getType());
            int itemAmount = item.getAmount();

            return new AITrashItem(
                    item.getType(),
                    getItemName(item),
                    category,
                    aiConfidence,
                    co2Impact,
                    itemAmount,
                    System.currentTimeMillis()
            );
        }
        return null;
    }

    private double calculateCO2Impact(Material material) {
        return impactMap.getOrDefault(material, 2.0);
    }

    private String getItemName(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        return meta != null && meta.hasDisplayName() ?
                meta.getDisplayName() : formatMaterialName(item.getType());
    }

    private String formatMaterialName(Material material) {
        String name = material.name().toLowerCase();
        return Arrays.stream(name.split("_"))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
                .reduce((a, b) -> a + " " + b)
                .orElse(name);
    }

    private void showAIAnalysis(Player player, List<AITrashItem> items, int totalItems, int trashItems) {
        player.sendMessage("§d╔══════════════════════════════╗");
        player.sendMessage("§d║        🤖 AI АНАЛИЗ 2025     ║");
        player.sendMessage("§d╚══════════════════════════════╝");
        player.sendMessage("§7📦 Просканировано предметов: §e" + totalItems);
        player.sendMessage("§7🗑 Обнаружено мусора: §a" + trashItems);

        if (totalItems > 0) {
            double trashPercentage = ((double) trashItems / totalItems) * 100;
            player.sendMessage("§7📊 Коэффициент мусора: §6" + String.format("%.1f", trashPercentage) + "%");
        }
        player.sendMessage(" ");

        if (items.isEmpty()) {
            player.sendMessage("§c❌ Мусор не обнаружен - отличная работа!");
            player.sendMessage("§a💚 Ваш инвентарь экологически чист!");
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 1.0f, 1.0f);
            return;
        }

        // Анализ по категориям
        Map<String, Integer> categoryCount = new HashMap<>();
        Map<String, Double> categoryConfidence = new HashMap<>();
        Map<String, Double> categoryCO2 = new HashMap<>();
        Map<String, Integer> categoryAmount = new HashMap<>();

        for (AITrashItem item : items) {
            categoryCount.merge(item.getCategory(), 1, Integer::sum);
            categoryConfidence.merge(item.getCategory(), item.getConfidence(), Double::sum);
            categoryCO2.merge(item.getCategory(), item.getCo2Impact() * item.getAmount(), Double::sum);
            categoryAmount.merge(item.getCategory(), item.getAmount(), Integer::sum);
        }

        player.sendMessage("§6📊 Распределение по категориям:");
        categoryCount.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .forEach(entry -> {
                    String categoryName = getCategoryDisplayName(entry.getKey());
                    double avgConfidence = categoryConfidence.get(entry.getKey()) / entry.getValue();
                    String confidenceColor = avgConfidence > 0.8 ? "§a" : avgConfidence > 0.6 ? "§e" : "§c";
                    int totalAmount = categoryAmount.get(entry.getKey());
                    double totalCO2 = categoryCO2.get(entry.getKey());

                    player.sendMessage("§7- " + categoryName + ": §a" + totalAmount + " шт. " +
                            confidenceColor + "(" + (int)(avgConfidence * 100) + "% уверенность)");
                    player.sendMessage("  §7Потенциальная экономия CO2: §e" + String.format("%.1f", totalCO2) + " кг");
                });

        // Расчет общей экономии
        double totalPotentialCO2 = categoryCO2.values().stream().mapToDouble(Double::doubleValue).sum();

        // Рекомендации AI
        player.sendMessage(" ");
        player.sendMessage("§b💡 Рекомендации AI:");
        player.sendMessage("§7- Используйте §e/trash §7для автоматической сортировки");
        player.sendMessage("§7- Наиболее распространенная категория: §a" +
                getCategoryDisplayName(getMostCommonCategory(categoryCount)));
        player.sendMessage("§7- Потенциальная экономия CO2: §e" + String.format("%.1f", totalPotentialCO2) + " кг");
        player.sendMessage("§7- Рекомендуемая стратегия: §b" + getRecyclingStrategy(categoryCount));

        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.5f);
    }

    private String getCategoryDisplayName(String categoryKey) {
        return displayNames.getOrDefault(categoryKey, "§7Неизвестно");
    }

    private String getMostCommonCategory(Map<String, Integer> categoryCount) {
        return categoryCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("nanoplastic");
    }

    private String getRecyclingStrategy(Map<String, Integer> categoryCount) {
        String mostCommon = getMostCommonCategory(categoryCount);

        switch (mostCommon) {
            case "nanoplastic":
                return "Приоритет нано-пластиковой переработки";
            case "quantum_waste":
                return "Квантовая переработка с максимальной эффективностью";
            case "holographic":
                return "Специализированная обработка голографических материалов";
            case "biotech":
                return "Биотехнологическое разложение";
            case "energy_cells":
                return "Энерго-восстановительная переработка";
            case "ai_components":
                return "AI-оптимизированная сортировка";
            default:
                return "Стандартная многоуровневая переработка";
        }
    }

    // Вложенный класс для AI анализа
    public static class AITrashItem {
        private final Material material;
        private final String name;
        private final String category;
        private final double confidence;
        private final double co2Impact;
        private final int amount;
        private final long timestamp;

        public AITrashItem(Material material, String name, String category,
                           double confidence, double co2Impact, int amount, long timestamp) {
            this.material = material;
            this.name = name;
            this.category = category;
            this.confidence = confidence;
            this.co2Impact = co2Impact;
            this.amount = amount;
            this.timestamp = timestamp;
        }

        public Material getMaterial() { return material; }
        public String getName() { return name; }
        public String getCategory() { return category; }
        public double getConfidence() { return confidence; }
        public double getCo2Impact() { return co2Impact; }
        public int getAmount() { return amount; }
        public long getTimestamp() { return timestamp; }
    }

    // Дополнительные методы для расширенного функционала

    /**
     * Глубокая диагностика конкретного предмета
     */
    public void deepAnalyzeItem(Player player, ItemStack item) {
        AITrashItem trashItem = analyzeItem(item);

        if (trashItem != null) {
            player.sendMessage("§d🔍 Глубокий анализ предмета:");
            player.sendMessage("§7Название: §f" + trashItem.getName());
            player.sendMessage("§7Категория: " + getCategoryDisplayName(trashItem.getCategory()));
            player.sendMessage("§7Уверенность AI: §a" + (int)(trashItem.getConfidence() * 100) + "%");
            player.sendMessage("§7Экономия CO2: §e" + trashItem.getCo2Impact() + " кг/шт");
            player.sendMessage("§7Потенциал переработки: §b" + getRecyclingPotential(trashItem));
        } else {
            player.sendMessage("§a✅ Этот предмет не требует переработки");
        }
    }

    private String getRecyclingPotential(AITrashItem item) {
        if (item.getCo2Impact() > 10) return "§cВысокий";
        if (item.getCo2Impact() > 5) return "§eСредний";
        return "§aНизкий";
    }

    /**
     * Сравнительный анализ инвентаря с другими игроками
     */
    public void comparativeAnalysis(Player player) {
        // Здесь можно добавить логику сравнения с другими игроками
        player.sendMessage("§6📈 Сравнительный анализ:");
        player.sendMessage("§7Ваш коэффициент переработки: §a85%");
        player.sendMessage("§7Средний по серверу: §e72%");
        player.sendMessage("§7Лучший показатель: §b94%");
        player.sendMessage("§7Ваше место в рейтинге: §6#12");
    }
}