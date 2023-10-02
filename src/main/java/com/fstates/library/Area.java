package com.fstates.library;

public class Area {
    public int initialX;
    public int initialY;
    public int finalX;
    public int finalY;

    public Area(){}
    public Area(int initialX,
                int initialY,
                int finalX,
                int finalY)
    {
        this.initialX   = initialX;
        this.initialY   = initialY;
        this.finalX     = finalX;
        this.finalY     = finalY;
    }

    public void zero()
    {
        this.initialX   = 0;
        this.initialY   = 0;
        this.finalX     = 0;
        this.finalY     = 0;
    }

    public boolean isOverlapping(Area other) {
        // Verifique se há interseção entre os retângulos
        if (this.initialX < other.finalX && this.finalX > other.initialX &&
                this.initialY < other.finalY && this.finalY > other.initialY) {
            return true; // Os retângulos se sobrepõem
        }
        return false; // Não há interseção entre os retângulos
    }
}
