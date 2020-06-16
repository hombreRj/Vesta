package gg.scenarios.vesta.utils;
import gg.scenarios.vesta.Vesta;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.function.Consumer;

public class GuiBuilder implements Listener {

    private String name;
    private int rows;
    private HashMap<Integer, ItemStack> items;
    private HashMap<Integer, Consumer<InventoryClickEvent>> runnableHashMap;
    private int slot;


    public GuiBuilder() {
        Bukkit.getPluginManager().registerEvents(this, Vesta.getPlugin(Vesta.class));
        this.name = "Inventory";
        this.rows = 1;
        this.items = new HashMap<>();
        this.runnableHashMap = new HashMap<>();
    }

    public GuiBuilder rows(int newRows) {
        this.rows = newRows;
        return this;
    }

    public GuiBuilder name(String newName) {
        this.name = ChatColor.translateAlternateColorCodes('&', newName);
        return this;
    }

    public GuiBuilder item(int slot, ItemStack item) {
        items.put(slot, item);
        this.slot = slot;
        return this;
    }

    public GuiBuilder item(int slot, ItemStack item, Consumer<InventoryClickEvent> consumer) {
        items.put(slot, item);
        this.slot = slot;
        runnableHashMap.put(slot, consumer);

        return this;
    }


    public void onClick(Consumer<InventoryClickEvent> runnable) {
        runnableHashMap.put(slot, runnable);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        if (e.getCurrentItem() != null) {
            if (e.getCurrentItem().getType() != Material.AIR) {
                if (ChatColor.stripColor(e.getInventory().getName()).equalsIgnoreCase(ChatColor.stripColor(name))) {
                    int slot = e.getSlot();
                    if (runnableHashMap.get(slot) != null) {
                        runnableHashMap.get(slot).accept(e);

                    }
                }
            }
        }
    }


    public Inventory make() {

        if (rows * 9 > 54) {
            throw new IllegalArgumentException("Too many rows in the created inventory!");
        }

        Inventory inv = Bukkit.createInventory(null, rows * 9, name);
        for (int f : items.keySet()) {
            inv.setItem(f, items.get(f));
        }
        return inv;
    }
}