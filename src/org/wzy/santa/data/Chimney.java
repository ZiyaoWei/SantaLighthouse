package org.wzy.santa.data;

public class Chimney {
    private int id;
    private int x;
    private int y;
    private int groupId;

    public Chimney(int id, int x, int y) {
        super();
        this.id = id;
        this.x = x;
        this.y = y;
        this.groupId = id;
    }

    public Chimney(int id, int x, int y, int groupId) {
        super();
        this.id = id;
        this.x = x;
        this.y = y;
        this.groupId = groupId;
    }

    public double distanceTo(Chimney that) {
        int disX = that.getX() - x;
        int disY = that.getY() - y;
        return Math.sqrt(disX * disX + disY * disY);
    }

    public static double chimneyDistance(Chimney c0, Chimney c1) {
        return c0.distanceTo(c1);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object that) {
        if (!(that instanceof Chimney)) {
            return false;
        }
        Chimney other = (Chimney) that;
        return this.id == other.getId();
    }

    @Override
    public int hashCode() {
        return new Integer(id).hashCode();
    }

}
