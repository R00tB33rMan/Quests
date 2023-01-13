package com.leonardobishop.quests.bukkit.menu.element;

import com.leonardobishop.quests.bukkit.BukkitQuestsPlugin;
import com.leonardobishop.quests.bukkit.menu.ClickResult;
import com.leonardobishop.quests.bukkit.util.MenuUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class CustomMenuElement extends MenuElement{

    private final ItemStack itemStack;
    private final List<String> commands;
    private final String playerName;

    public CustomMenuElement(BukkitQuestsPlugin plugin, UUID owner, String name, ItemStack itemStack) {
        this(plugin, owner, name, itemStack, new ArrayList<>());
    }

    public CustomMenuElement(BukkitQuestsPlugin plugin, UUID owner, String name, ItemStack itemStack, List<String> commands) {
        this.itemStack = MenuUtils.applyPlaceholders(plugin, owner, itemStack);
        this.commands = commands;
        this.playerName = name;
    }

    @Override
    public ItemStack asItemStack() {
        return itemStack;
    }

    @Override
    public ClickResult handleClick(ClickType clickType) {
        for (String command : commands) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                    command.replace("{player}", playerName));
        }
        return ClickResult.DO_NOTHING;
    }

    public List<String> getCommands() {
        return Collections.unmodifiableList(commands);
    }
}
