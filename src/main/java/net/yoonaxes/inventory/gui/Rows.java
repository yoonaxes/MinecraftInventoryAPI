package net.yoonaxes.inventory.gui;

public enum Rows {

    ONE(9),
    TWO(18),
    THREE(27),
    FOUR(36),
    FIVE(45),
    SIX(54);

    private final int size;

    Rows(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    /**
     * Find biggest row by inventory slots size.
     * @param size Inventory Slots Size
     * @return Biggest Row for size
     */
    public static Rows getRowBySize(int size) {
        Rows row = Rows.ONE;

        for(Rows checkedRow : Rows.values())
            if(size >= checkedRow.getSize())
                row = checkedRow;

        return row;
    }
}