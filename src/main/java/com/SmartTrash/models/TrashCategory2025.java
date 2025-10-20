package com.smarttrash.models;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class TrashCategory2025 {
    private final String name;
    private final Material icon;
    private final ChatColor color;
    private final boolean recyclable;
    private final double co2Saving;
    private final String[] examples;
    private final String emoji;
    private final double efficiency;

    public TrashCategory2025(String name, Material icon, ChatColor color,
                             boolean recyclable, double co2Saving, String[] examples,
                             String emoji, double efficiency) {
        this.name = name;
        this.icon = icon;
        this.color = color;
        this.recyclable = recyclable;
        this.co2Saving = co2Saving;
        this.examples = examples;
        this.emoji = emoji;
        this.efficiency = efficiency;
    }

    public ItemStack getMenuIcon2025() {
        ItemStack item = new ItemStack(icon);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(color + name);
        meta.setLore(Arrays.asList(
                "§7⚡ Эффективность: §a" + (int)(efficiency * 100) + "%",
                "§7🌱 Экономия CO2: §e" + co2Saving + " кг/ед.",
                "§7🔬 Технология: §b" + getTechLevel(),
                "",
                "§6📋 Примеры:",
                "§7- " + examples[0],
                "§7- " + examples[1],
                "§7- " + examples[2],
                "",
                "§e✨ Нажмите для AI-сортировки!"
        ));

        item.setItemMeta(meta);
        return item;
    }

    private String getTechLevel() {
        if (efficiency >= 0.95) return "Квантовая";
        if (efficiency >= 0.90) return "Нано-технологии";
        if (efficiency >= 0.85) return "AI-оптимизированная";
        return "Современная";
    }

    // Геттеры
    public String getName() { return name; }
    public Material getIcon() { return icon; }
    public ChatColor getColor() { return color; }
    public boolean isRecyclable() { return recyclable; }
    public double getCo2Saving() { return co2Saving; }
    public String[] getExamples() { return examples; }
    public String getEmoji() { return emoji; }
    public double getEfficiency() { return efficiency; }
}