package com.smarttrash.managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class QuantumRecycler {

    public void openQuantumMenu(Player player) {
        Inventory gui = Bukkit.createInventory(null, 27, "⚛️ Квантовая Переработка");

        // Заполнение интерфейса
        fillQuantumBorders(gui);
        addQuantumButtons(gui);

        player.openInventory(gui);
        player.playSound(player.getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, 1.0f, 1.0f);
        player.sendMessage("⚛ §5Активирована квантовая переработка!");
    }

    private void fillQuantumBorders(Inventory gui) {
        ItemStack border = createQuantumBorder();
        for (int i = 0; i < 27; i++) {
            if (i < 9 || i > 17 || i % 9 == 0 || i % 9 == 8) {
                gui.setItem(i, border);
            }
        }
    }

    private void addQuantumButtons(Inventory gui) {
        gui.setItem(11, createQuantumRecycleButton());
        gui.setItem(13, createEfficiencyBoostButton());
        gui.setItem(15, createMatterConverterButton());
    }

    private ItemStack createQuantumBorder() {
        ItemStack border = new ItemStack(Material.PURPLE_STAINED_GLASS_PANE);
        ItemMeta meta = border.getItemMeta();
        meta.setDisplayName("§5⚛");
        meta.setLore(Arrays.asList("§7Квантовое поле стабилизации"));
        border.setItemMeta(meta);
        return border;
    }

    private ItemStack createQuantumRecycleButton() {
        ItemStack quantum = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = quantum.getItemMeta();
        meta.setDisplayName("§d✨ Квантовая переработка");
        meta.setLore(Arrays.asList(
                "§7Эффективность: §a99%",
                "§7Скорость: §eМгновенная",
                "§7Технология: §bКвантовый распад",
                "",
                "§5⚡Нажмите для активации!"
        ));
        quantum.setItemMeta(meta);
        return quantum;
    }

    private ItemStack createEfficiencyBoostButton() {
        ItemStack boost = new ItemStack(Material.GLOWSTONE_DUST);
        ItemMeta meta = boost.getItemMeta();
        meta.setDisplayName("§e🔋Буст эффективности");
        meta.setLore(Arrays.asList(
                "§7Увеличивает эффективность",
                "§7переработки на 50%",
                "",
                "§6⏱️ Длительность: 10 минут",
                "",
                "§e⚡ Нажмите для активации!"
        ));
        boost.setItemMeta(meta);
        return boost;
    }

    private ItemStack createMatterConverterButton() {
        ItemStack converter = new ItemStack(Material.ENDER_PEARL);
        ItemMeta meta = converter.getItemMeta();
        meta.setDisplayName("§b🔄 Конвертер материи");
        meta.setLore(Arrays.asList(
                "§7Превращает отходы в",
                "§7полезные ресурсы",
                "",
                "§3🎯 Коэффициент: 1:0.8",
                "",
                "§b⚡ Нажмите для активации!"
        ));
        converter.setItemMeta(meta);
        return converter;
    }
}