package net.yoonaxes.inventory.callback;

public @interface WindowCallback {

    Type type();

    enum Type {
        OPEN,
        CLOSE
    }
}
