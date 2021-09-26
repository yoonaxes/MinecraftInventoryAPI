package net.yoonaxes.inventory.actions;

import net.yoonaxes.inventory.gui.MenuHolder;

public class WindowAction {

    private final MenuHolder menuHolder;
    private final WindowActionType windowActionType;

    public WindowAction(MenuHolder menuHolder, WindowActionType windowActionType) {
        this.menuHolder = menuHolder;
        this.windowActionType = windowActionType;
    }

    public MenuHolder getMenuHolder() {
        return menuHolder;
    }

    public WindowActionType getWindowActionType() {
        return windowActionType;
    }

    public enum WindowActionType {
        OPEN,
        CLOSE;
    }
}
