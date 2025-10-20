package com.smarttrash;

import com.smarttrash.ai.TrashAI;
import com.smarttrash.gui.TrashGUI;
import com.smarttrash.managers.QuantumRecycler;
import com.smarttrash.managers.StatsManager;
import com.smarttrash.listeners.GUIListener;
import com.smarttrash.testing.TrashTester;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class SmartTrash extends JavaPlugin {

    private static SmartTrash instance;
    private TrashGUI guiManager;
    private StatsManager statsManager;
    private TrashAI trashAI;
    private QuantumRecycler quantumRecycler;
    private TrashTester trashTester;
    private Logger logger;

    @Override
    public void onEnable() {
        instance = this;
        this.logger = getLogger();

        // Инициализация менеджеров 2025
        this.guiManager = new TrashGUI();
        this.statsManager = new StatsManager();
        this.trashAI = new TrashAI();
        this.quantumRecycler = new QuantumRecycler();
        this.trashTester = new TrashTester();

        // Регистрация слушателей
        Bukkit.getPluginManager().registerEvents(new GUIListener(), this);

        // Сохранение конфига
        saveDefaultConfig();

        // Запуск AI задач
        startAITasks();

        logger.info("╔══════════════════════════════════════╗");
        logger.info("║         SMART TRASH 2025 v1.0        ║");
        logger.info("║      AI + Quantum Recycling          ║");
        logger.info("║      Minecraft 1.21.8 Ready          ║");
        logger.info("╚══════════════════════════════════════╝");
        logger.info("🔧 Команды: /trash, /trashai, /trashstats, /trashquantum, /trashtest");
    }

    @Override
    public void onDisable() {
        statsManager.saveAllStats();
        logger.info("🔴 SmartTrash 2025 отключен");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("🔴 Только игроки могут использовать эту команду!");
            return true;
        }

        String cmd = command.getName().toLowerCase();

        switch (cmd) {
            case "trash":
                guiManager.openMainMenu(player);
                return true;

            case "trashai":
                trashAI.analyzeInventory(player);
                return true;

            case "trashstats":
                statsManager.showAdvancedStats(player);
                return true;

            case "trashquantum":
                if (player.hasPermission("smarttrash.quantum")) {
                    quantumRecycler.openQuantumMenu(player);
                } else {
                    player.sendMessage("🔒 Недостаточно прав для квантовой переработки!");
                }
                return true;

            case "trashtest":
                if (player.hasPermission("smarttrash.test")) {
                    trashTester.runTests(player);
                } else {
                    player.sendMessage("🔒 Недостаточно прав для тестирования!");
                }
                return true;
        }

        return false;
    }

    private void startAITasks() {
        // Авто-анализ каждые 5 минут
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            logger.info("🤖 AI: Запуск фонового анализа данных...");
            statsManager.cleanOldData();
        }, 6000L, 6000L);
    }

    public static SmartTrash getInstance() {
        return instance;
    }

    public TrashGUI getGuiManager() {
        return guiManager;
    }

    public StatsManager getStatsManager() {
        return statsManager;
    }

    public TrashAI getTrashAI() {
        return trashAI;
    }

    public QuantumRecycler getQuantumRecycler() {
        return quantumRecycler;
    }

    public TrashTester getTrashTester() {
        return trashTester;
    }
}