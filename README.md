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
  <version>0.7</version>
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
    implementation 'net.yoonaxes.minecraft:inventoryapi:0.7'
}
```

##### Download:
**·** [Download InventoryAPI-0.7.jar](https://github.com/yoonaxes/MinecraftInventoryAPI/releases/download/0.7/MessageAPI-0.7.jar)

**·** [Download other release](https://github.com/yoonaxes/MinecraftInventoryAPI/releases/)

## Example usage
Example usage of InventoryAPI.
```java
import net.yoonaxes.inventory.builders.ItemBuilder;
import net.yoonaxes.inventory.gui.MenuWindow;
import net.yoonaxes.inventory.gui.Rows;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ExampleMenu extends MenuWindow {

    public static void openMenu(Player player) {
        new ExampleMenu(player).open(player);
    }

    public ExampleMenu(Player player) {
        super("&8{>} &7Menu &f" + player.getName() + " &8| &7MinecraftInventoryAPI", Rows.ONE);
        this.create();
    }

    private void create() {
        this.withItem(new ItemBuilder(Material.DIAMOND_SWORD).withName("&bFighting").withLore("&7Always :)").build());
        this.withItem(new ItemBuilder(Material.WATER_BUCKET).withName("&bSwimming").withName("&7If you can, swim :D").build());
        this.withItem(new ItemBuilder(Material.IRON_PICKAXE).withName("&bMinning").withName("&7If you want you can minning :P").build());
        this.withItem(8, new ItemBuilder(Material.EMERALD)
                .withName("&aIt's awesome!")
                .withLore(
                        "&7Give star on Github.",
                        "&7Click to take a link."
                )
                .build(), (humanEntity, clickType, menuHolder) -> {
            humanEntity.sendMessage("Github: https://github.com/yoonaxes");
            humanEntity.sendMessage("Project: https://github.com/yoonaxes/MinecraftInventoryAPI");
        });
    }
}
```