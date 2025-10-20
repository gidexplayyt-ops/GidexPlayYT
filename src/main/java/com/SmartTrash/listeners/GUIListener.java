package com.smarttrash.listeners;

import com.smarttrash.SmartTrash;
import com.smarttrash.gui.TrashGUI;
import com.smarttrash.managers.StatsManager;
import com.smarttrash.models.TrashCategory2025;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GUIListener implements Listener {

    private final TrashGUI guiManager;
    private final StatsManager statsManager;

    public GUIListener() {
        this.guiManager = SmartTrash.getInstance().getGuiManager();
        this.statsManager = SmartTrash.getInstance().getStatsManager();
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;

        String title = event.getView().getTitle();

        // Обработка основного меню
        if (title.equals("🚀 Умная Мусорка 2025")) {
            event.setCancelled(true);
            handleMainMenuClick(player, event.getCurrentItem());
        }

        // Обработка квантового меню
        else if (title.equals("⚛  ️Квантовая Переработка")) {
            event.setCancelled(true);
            handleQuantumMenuClick(player, event.getCurrentItem());
        }
    }

    private void handleMainMenuClick(Player player, ItemStack clicked) {
        if (clicked == null) return;

        // Обработка клика по категории мусора
        if (isCategoryItem(clicked.getType())) {
            handleCategoryClick(player, clicked);
        }

        // Обработка функциональных кнопок
        else if (clicked.getType() == Material.ENDER_EYE) {
            SmartTrash.getInstance().getTrashAI().analyzeInventory(player);
            player.closeInventory();
        }
        else if (clicked.getType() == Material.ENCHANTED_BOOK) {
            statsManager.showAdvancedStats(player);
            player.closeInventory();
        }
        else if (clicked.getType() == Material.NETHER_STAR) {
            if (player.hasPermission("smarttrash.quantum")) {
                SmartTrash.getInstance().getQuantumRecycler().openQuantumMenu(player);
            } else {
                player.sendMessage("🔒 Недостаточно прав для квантовой переработки!");
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
            }
        }
        else if (clicked.getType() == Material.HOPPER) {
            player.sendMessage("🔧 §aАвто-сортировка запущена!");
            // Здесь можно добавить логику авто-сортировки
            player.closeInventory();
        }
        else if (clicked.getType() == Material.COMMAND_BLOCK) {
            if (player.hasPermission("smarttrash.test")) {
                SmartTrash.getInstance().getTrashTester().runTests(player);
            } else {
                player.sendMessage("🔒 Недостаточно прав для тестирования!");
            }
            player.closeInventory();
        }
    }

    private void handleQuantumMenuClick(Player player, ItemStack clicked) {
        if (clicked == null) return;

        if (clicked.getType() == Material.NETHER_STAR) {
            player.sendMessage("✨ §dЗапущена квантовая переработка!");
            player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 1.0f, 1.5f);
        }
        else if (clicked.getType() == Material.GLOWSTONE_DUST) {
            player.sendMessage("🔋§eБуст эффективности активирован!");
            player.playSound(player.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1.0f, 1.2f);
        }
        else if (clicked.getType() == Material.ENDER_PEARL) {
            player.sendMessage("🔄 §bКонвертер материи запущен!");
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 1.0f);
        }
    }

    private boolean isCategoryItem(Material material) {
        return material == Material.CYAN_STAINED_GLASS ||
                material == Material.AMETHYST_SHARD ||
                material == Material.PRISMARINE_CRYSTALS ||
                material == Material.NETHER_STAR ||
                material == Material.GLOWSTONE_DUST ||
                material == Material.CLOCK;
    }

    private void handleCategoryClick(Player player, ItemStack item) {
        String itemName = ChatColor.stripColor(item.getItemMeta().getDisplayName());

        // Ищем соответствующую категорию
        for (String key : guiManager.getCategories().keySet()) {
            TrashCategory2025 category = guiManager.getCategory(key);
            if (category.getName().equals(itemName)) {
                processTrashSorting(player, category);
                return;
            }
        }
    }

    private void processTrashSorting(Player player, TrashCategory2025 category) {
        // Проверяем есть ли мусор в инвентаре
        boolean hasTrash = false;
        int trashCount = 0;

        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && !item.getType().isAir()) {
                hasTrash = true;
                trashCount++;
            }
        }

        if (!hasTrash) {
            player.sendMessage("🔴 §cВ вашем инвентаре нет мусора для сортировки!");
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
            return;
        }

        // Симулируем сортировку (1-8 случайных предметов)
        int itemsSorted = Math.min((int) (Math.random() * 8) + 1, trashCount);
        double co2Saved = category.getCo2Saving() * itemsSorted * category.getEfficiency();

        // Обновляем статистику
        statsManager.addTrashSorted(player, category.getName().toLowerCase().replace(" ", "_"), co2Saved);

        // Отправляем сообщение игроку
        player.sendMessage("✅ §aУспешно отсортировано §e" + itemsSorted + " §aпредметов!");
        player.sendMessage("♻️ §bКатегория: " + category.getColor() + category.getName());
        player.sendMessage("🌱 §6Сохранено CO2: §e" + String.format("%.2f", co2Saved) + " кг");
        player.sendMessage("⚡ §3Эффективность: §b" + (int)(category.getEfficiency() * 100) + "%");

        player.playSound(player.getLocation(), Sound.BLOCK_COMPOSTER_FILL_SUCCESS, 1.0f, 1.2f);
        player.closeInventory();
    }
}