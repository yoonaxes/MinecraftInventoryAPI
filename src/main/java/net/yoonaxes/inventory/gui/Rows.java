package net.yoonaxes.inventory.gui;

/**
 * Simplify Inventory Chest Row System.
 * @author yoonaxes
 */
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
     * @param slots Inventory Slots Size
     * @return Biggest Row for size
     */
    public static Rows getRowBySlots(int slots) {
        Rows row = Rows.ONE;

        for(Rows checkedRow : Rows.values())
            if(slots >= checkedRow.getSize())
                row = checkedRow;

        return row;
    }

    /**
     * Find row by number.
     * @param number Rows number
     * @return Finded Row
     */
    public static Rows getRow(int number) {
        switch(number) {
            case 1: {
                return Rows.ONE;
            }
            case 2: {
                return Rows.TWO;
            }
            case 3: {
                return Rows.THREE;
            }
            case 4: {
                return Rows.FOUR;
            }
            case 5: {
                return Rows.FIVE;
            }
            default: {
                return Rows.SIX;
            }
        }
    }
}