package MultiplayerJogador2;

import java.awt.Color;
import java.awt.Polygon;

public class Casa {

    private int player;
    private int posX;
    private int posY;
    private Boolean draw;
    private Color color;
    private Polygon polygon;
    

    public Casa() {
        this.player = 0;
        this.draw = false;
        this.color = Color.GRAY;
    }

    public Boolean Contains(int x, int y) {
        if (this.getPolygon().contains(x, y)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return the player
     */
    public int getPlayer() {
        return player;
    }

    /**
     * @param player The player who owns the piece
     */
    public void setPlayer(int player) {
        this.player = player;
        this.Color();
    }

    /**
     * @return the posX
     */
    public int getX() {
        return posX;
    }

    /**
     * @return the posY
     */
    public int getY() {
        return posY;
    }

    /**
     * @return the draw
     */
    public Boolean getDraw() {
        return draw;
    }

    /**
     * @param draw the draw to set
     */
    public void setDraw(Boolean draw) {
        this.draw = draw;
    }

    /**
     * @return the color of the cell
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return the polygon
     */
    public Polygon getPolygon() {
        return polygon;
    }

    /**
     * @param polygon the polygon to set
     */
    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }

    private void Color() {
        if (this.player == 1) {
            this.color = Color.WHITE;
        } else if(this.player==2) {
            this.color = Color.BLACK;
        }
        else {
            this.color = Color.GRAY;
        }
    }

    /**
     * @param posX the posX to set
     */
    public void setX(int x) {
        this.posX = x;
    }

    /**
     * @param posY the posY to set
     */
    public void setY(int y) {
        this.posY = y;
    }
}
