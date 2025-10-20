package com.smarttrash.gui;

import com.smarttrash.SmartTrash;
import com.smarttrash.models.TrashCategory2025;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TrashGUI {

    private final Map<String, TrashCategory2025> categories;

    public TrashGUI() {
        this.categories = new HashMap<>();
        initialize2025Categories();
    }

    private void initialize2025Categories() {
        // Современные категории 2025 с существующими материалами
        categories.put("nanoplastic", new TrashCategory2025(
                "🌊 Нано-Пластик",
                Material.GLASS_BOTTLE,
                ChatColor.AQUA,
                true,
                4.2,
                new String[]{"Стеклянные бутылки", "Зелья", "Упаковка"},
                "⚗️",
                0.95
        ));

        categories.put("quantum_waste", new TrashCategory2025(
                "⚛ Квантовые Отходы",
                Material.NETHERITE_INGOT,
                ChatColor.LIGHT_PURPLE,
                true,
                12.5,
                new String[]{"Квантовые чипы", "Нано-схемы", "Холодный синтез"},
                "🔬",
                0.85
        ));

        categories.put("holographic", new TrashCategory2025(
                "👁 Голографические",
                Material.PRISMARINE_CRYSTALS,
                ChatColor.BLUE,
                true,
                6.8,
                new String[]{"Голо-дисплеи", "3D проекторы", "Световые панели"},
                "🌈",
                0.90
        ));

        categories.put("biotech", new TrashCategory2025(
                "🧬 Био-Технологии",
                Material.NETHER_STAR,
                ChatColor.GREEN,
                true,
                8.3,
                new String[]{"Био-чипы", "Генетические", "Органо-синтез"},
                "🔬",
                0.88
        ));

        categories.put("energy_cells", new TrashCategory2025(
                "⚡ Энерго-Ячейки",
                Material.GLOWSTONE_DUST,
                ChatColor.YELLOW,
                true,
                15.2,
                new String[]{"Плазменные батареи", "Квант-аккумуляторы", "Термоядерные"},
                "🔋",
                0.92
        ));

        categories.put("ai_components", new TrashCategory2025(
                "🤖 AI Компоненты",
                Material.CLOCK,
                ChatColor.RED,
                true,
                9.7,
                new String[]{"Нейросети", "Процессоры AI", "Машинное зрение"},
                "💻",
                0.87
        ));
    }

    public void openMainMenu(Player player) {
        Inventory gui = Bukkit.createInventory(null, 54, "🚀 Умная Мусорка 2025");

        // Футуристичный дизайн
        fillHolographicBorders(gui);
        add2025Categories(gui);
        addTechButtons(gui);

        player.openInventory(gui);
        player.playSound(player.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1.0f, 1.5f);
        player.sendMessage("🚀 §aУмная мусорка 2025 активирована! Выберите категорию для AI-сортировки.");
    }

    private void fillHolographicBorders(Inventory gui) {
        ItemStack border = createHolographicBorder();
        for (int i = 0; i < 54; i++) {
            if (i < 9 || i > 44 || i % 9 == 0 || i % 9 == 8) {
                gui.setItem(i, border);
            }
        }
    }

    private void add2025Categories(Inventory gui) {
        gui.setItem(19, categories.get("nanoplastic").getMenuIcon2025());
        gui.setItem(20, categories.get("quantum_waste").getMenuIcon2025());
        gui.setItem(21, categories.get("holographic").getMenuIcon2025());
        gui.setItem(22, categories.get("biotech").getMenuIcon2025());
        gui.setItem(23, categories.get("energy_cells").getMenuIcon2025());
        gui.setItem(24, categories.get("ai_components").getMenuIcon2025());
    }

    private void addTechButtons(Inventory gui) {
        // AI анализ
        gui.setItem(47, createAIButton());

        // Статистика
        gui.setItem(48, createStatsButton());

        // Квантовая переработка
        gui.setItem(49, createQuantumButton());

        // Авто-сортировка
        gui.setItem(50, createAutoSortButton());

        // Тестирование
        gui.setItem(51, createTestButton());

        // Информация
        gui.setItem(53, createInfoButton());
    }

    private ItemStack createHolographicBorder() {
        ItemStack border = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
        ItemMeta meta = border.getItemMeta();
        meta.setDisplayName("§b✨");
        meta.setLore(Arrays.asList("§7Голографический интерфейс 2025"));
        border.setItemMeta(meta);
        return border;
    }

    private ItemStack createAIButton() {
        ItemStack ai = new ItemStack(Material.ENDER_EYE);
        ItemMeta meta = ai.getItemMeta();
        meta.setDisplayName("§d🤖 AI Анализ");
        meta.setLore(Arrays.asList(
                "§7Запуск нейросети для",
                "§7автоматической сортировки",
                "",
                "§eНажмите для сканирования!"
        ));
        ai.setItemMeta(meta);
        return ai;
    }

    private ItemStack createStatsButton() {
        ItemStack stats = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta meta = stats.getItemMeta();
        meta.setDisplayName("§6📊 Статистика 2025");
        meta.setLore(Arrays.asList(
                "§7Показатели эффективности",
                "§7и экологического вклада",
                "",
                "§eНажмите для просмотра!"
        ));
        stats.setItemMeta(meta);
        return stats;
    }

    private ItemStack createQuantumButton() {
        ItemStack quantum = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = quantum.getItemMeta();
        meta.setDisplayName("§5⚛ Квантовая переработка");
        meta.setLore(Arrays.asList(
                "§7Использование квантовых",
                "§7технологий для 99% эффективности",
                "",
                "§c🔒 Требуются специальные права!"
        ));
        quantum.setItemMeta(meta);
        return quantum;
    }

    private ItemStack createAutoSortButton() {
        ItemStack auto = new ItemStack(Material.HOPPER);
        ItemMeta meta = auto.getItemMeta();
        meta.setDisplayName("§a🔧 Авто-сортировка");
        meta.setLore(Arrays.asList(
                "§7Автоматическая обработка",
                "§7всего инвентаря",
                "",
                "§eНажмите для запуска!"
        ));
        auto.setItemMeta(meta);
        return auto;
    }

    private ItemStack createTestButton() {
        ItemStack test = new ItemStack(Material.COMMAND_BLOCK);
        ItemMeta meta = test.getItemMeta();
        meta.setDisplayName("§c🧪 Тестирование");
        meta.setLore(Arrays.asList(
                "§7Тестовые функции и",
                "§7отладка системы",
                "",
                "§c🔒 Только для администраторов!"
        ));
        test.setItemMeta(meta);
        return test;
    }

    private ItemStack createInfoButton() {
        ItemStack info = new ItemStack(Material.KNOWLEDGE_BOOK);
        ItemMeta meta = info.getItemMeta();
        meta.setDisplayName("§bℹ О системе");
        meta.setLore(Arrays.asList(
                "§7SmartTrash v1.0",
                "§7AI + Quantum Recycling",
                "",
                "§6Разработчик: GidexPlay's Team",
                "§6Версия Minecraft: 1.21.8"
        ));
        info.setItemMeta(meta);
        return info;
    }

    public TrashCategory2025 getCategory(String key) {
        return categories.get(key);
    }

    public Map<String, TrashCategory2025> getCategories() {
        return categories;
    }

    /**
     * Открывает меню квантовой переработки
     */
    public void openQuantumRecyclingMenu(Player player) {
        Inventory gui = Bukkit.createInventory(null, 27, "⚛ Квантовая Переработка");

        fillQuantumBorders(gui);
        addQuantumRecyclingOptions(gui);

        player.openInventory(gui);
        player.playSound(player.getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, 1.0f, 1.0f);
    }

    private void fillQuantumBorders(Inventory gui) {
        ItemStack border = createQuantumBorder();
        for (int i = 0; i < 27; i++) {
            if (i < 9 || i > 17 || i % 9 == 0 || i % 9 == 8) {
                gui.setItem(i, border);
            }
        }
    }

    private void addQuantumRecyclingOptions(Inventory gui) {
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
                "§5⚡ Нажмите для активации!"
        ));
        quantum.setItemMeta(meta);
        return quantum;
    }

    private ItemStack createEfficiencyBoostButton() {
        ItemStack boost = new ItemStack(Material.GLOWSTONE_DUST);
        ItemMeta meta = boost.getItemMeta();
        meta.setDisplayName("§e🔋 Буст эффективности");
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

    /**
     * Открывает меню статистики
     */
    public void openStatsMenu(Player player) {
        Inventory gui = Bukkit.createInventory(null, 36, "📊Статистика Переработки");

        fillStatsBorders(gui);
        addStatsInfo(gui, player);

        player.openInventory(gui);
        player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1.0f, 1.0f);
    }

    private void fillStatsBorders(Inventory gui) {
        ItemStack border = createStatsBorder();
        for (int i = 0; i < 36; i++) {
            if (i < 9 || i > 26 || i % 9 == 0 || i % 9 == 8) {
                gui.setItem(i, border);
            }
        }
    }

    private void addStatsInfo(Inventory gui, Player player) {
        // Здесь можно добавить реальную статистику из StatsManager
        gui.setItem(13, createPlayerStatsItem(player));
        gui.setItem(22, createGlobalStatsItem());
        gui.setItem(31, createAchievementsItem());
    }

    private ItemStack createStatsBorder() {
        ItemStack border = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        ItemMeta meta = border.getItemMeta();
        meta.setDisplayName("§a📊");
        meta.setLore(Arrays.asList("§7Система статистики"));
        border.setItemMeta(meta);
        return border;
    }

    private ItemStack createPlayerStatsItem(Player player) {
        ItemStack stats = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta meta = stats.getItemMeta();
        meta.setDisplayName("§6👤 Ваша статистика");
        meta.setLore(Arrays.asList(
                "§7Игрок: §f" + player.getName(),
                "§7Уровень переработки: §a85%",
                "§7Сохранено CO2: §e156.7 кг",
                "§7Активность: §b3 дня",
                "",
                "§eНажмите для деталей!"
        ));
        stats.setItemMeta(meta);
        return stats;
    }

    private ItemStack createGlobalStatsItem() {
        ItemStack global = new ItemStack(Material.GLOBE_BANNER_PATTERN);
        ItemMeta meta = global.getItemMeta();
        meta.setDisplayName("§2🌍 Глобальная статистика");
        meta.setLore(Arrays.asList(
                "§7Всего переработано: §a12,458 шт",
                "§7Сохранено CO2: §e8,923 кг",
                "§7Активных игроков: §b42",
                "§7Эффективность сервера: §685%",
                "",
                "§eНажмите для просмотра!"
        ));
        global.setItemMeta(meta);
        return global;
    }

    private ItemStack createAchievementsItem() {
        ItemStack achievements = new ItemStack(Material.TARGET);
        ItemMeta meta = achievements.getItemMeta();
        meta.setDisplayName("§d🏆 Достижения");
        meta.setLore(Arrays.asList(
                "§7Эко-воин: §a✅ Завершено",
                "§7Квантовый переработчик: §6🔄 В процессе",
                "§7AI-эксперт: §c❌ Не начато",
                "§7Мастер переработки: §e⭐ 3/5",
                "",
                "§eНажмите для просмотра!"
        ));
        achievements.setItemMeta(meta);
        return achievements;
    }
}