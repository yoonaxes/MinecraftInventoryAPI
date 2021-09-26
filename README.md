<div align=center>

<hr />

# MinecraftInventoryAPI
An open source Bukkit util for Inventory API. This API is used to facilitate the creation of interactive inventory.

<hr />

</div>

## Initial Setup
Initial setup to start using InventoryAPI.

##### Maven:
```xml
<repository>
    <id>yoonaxes-maven</id>
    <name>yoonaxes repository</name>
    <url>https://maven.yoonaxes.pl/</url>
</repository>

<dependency>
  <groupId>net.yoonaxes.minecraft</groupId>
  <artifactId>inventoryapi</artifactId>
  <version>0.8</version>
</dependency>
```

##### Gradle:
```groovy
repositories {
    ...
    maven {
        name = 'yoonaxes-maven'
        url = 'https://maven.yoonaxes.pl/'
    }
}

dependencies {
    implementation 'net.yoonaxes.minecraft:inventoryapi:0.8'
}
```

##### Download:
**·** [Download InventoryAPI-0.8.jar](https://github.com/yoonaxes/MinecraftInventoryAPI/releases/download/0.8/MessageAPI-0.8.jar)

**·** [Download other release](https://github.com/yoonaxes/MinecraftInventoryAPI/releases/)

## Example usage
Example usage of InventoryAPI.
```java
import net.yoonaxes.inventory.builders.ItemBuilder;
import net.yoonaxes.inventory.callback.WindowCloseCallback;
import net.yoonaxes.inventory.callback.WindowOpenCallback;
import net.yoonaxes.inventory.gui.MenuWindow;
import net.yoonaxes.inventory.gui.Rows;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ExampleMenu extends MenuWindow {

    public static void openMenu(Player player) {
        // It's simple!
        new ExampleMenu(player).open(player);
    }

    public ExampleMenu(Player player) {
        super("&8{>} &7Menu &f" + player.getName() + " &8| &7MinecraftInventoryAPI", Rows.ONE);
        
        /* Window callbacks */
        this.withWindowCallback((WindowOpenCallback) (humanEntity, windowAction) -> {
            humanEntity.sendMessage("Menu opened.");
        });
        
        this.withWindowCallback((WindowCloseCallback) (humanEntity, windowAction) -> {
            humanEntity.sendMessage("Menu closed.");
        });
        
        /* You can fill empty slots with glass, add more Window Callbacks, add more items etc...
         * Example to fill empty slots with glass:
         * - this.fillEmptySlots(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).withName("&8#").build()); */
        
        this.createItems(); /* Make create items method */
    }

    private void createItems() {
        /* Example for static items
         * For example you can use too:
         * this.withItem(slot, ItemStack); */
        this.withItem(new ItemBuilder(Material.DIAMOND_SWORD).withName("&cFighting").withLore("&7Always :)").build());
        this.withItem(new ItemBuilder(Material.WATER_BUCKET).withName("&bSwimming").withLore("&7If you can, swim :D").build());
        this.withItem(new ItemBuilder(Material.IRON_PICKAXE).withName("&eMinning").withLore("&7If you want you can minning :P").build());

        /* Example for item with action
         * For example you can use too:
         * - this.withItem(ItemStack, ItemClickCallback);
         * - this.withItem(slot, ItemStack, ItemClickCallback);
         * But here I'm using a separate method to add an action for the item */
        this.withItem(8, new ItemBuilder(Material.EMERALD)
                .withName("&aIt's awesome!")
                .withLore(
                        "&7Give star on Github.",
                        "&7Click to take a link."
                )
                .build());
        
        /* Add action to item on 8 slot in inventory */
        this.withItemAction(8, (humanEntity, itemClickAction) -> {
            humanEntity.sendMessage("Github: https://github.com/yoonaxes");
            humanEntity.sendMessage("Project: https://github.com/yoonaxes/MinecraftInventoryAPI");
        });
    }
}
```

##### Result:
![](https://i.imgur.com/dqC25rZ.png "Example Screenshot")

<hr />