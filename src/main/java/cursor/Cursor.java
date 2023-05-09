package cursor;

import board.ChessBoard;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import graphics.RenderableObject;

import java.sql.Array;
import java.util.ArrayList;

public class Cursor implements RenderableObject {
    private int x;
    private int y;
    public Cursor(int x, int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public void draw(Screen screen) {
        TextGraphics renderer = screen.newTextGraphics();
        renderer.setBackgroundColor(TextColor.ANSI.WHITE_BRIGHT);
        renderer.setForegroundColor(TextColor.ANSI.BLACK);
        char drawUnder = screen.getBackCharacter(ChessBoard.x + this.x,ChessBoard.y + this.y).getCharacter();
        renderer.setCharacter(ChessBoard.x + this.x,ChessBoard.y + this.y, drawUnder);
    }
    public boolean moveCursor(Character dir, int maxx, int maxy, int minx, int miny){
        int[] dirTuple = new int[]{0, 0};
        switch (dir){
            case 'w':
                dirTuple = new int[]{0, -1};
                break;
            case 'a':
                dirTuple = new int[]{-1, 0};
                break;
            case 's':
                dirTuple = new int[]{0,1};
                break;
            case 'd':
                dirTuple = new int[]{1, 0};
                break;
        }
        int newx = x + dirTuple[0];
        int newy = y + dirTuple[1];
        if( minx <= newx && newx < maxx && miny <= newy && newy < maxy ){
            x = newx;
            y = newy;
            return true;
        }
        return false;
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
}
