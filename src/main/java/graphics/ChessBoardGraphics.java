package graphics;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class ChessBoardGraphics implements RenderableObject{
    TextColor dark;
    TextColor bright;
    final public int sideLength = 8;
    int x = 0;
    int y = 0;
    public ChessBoardGraphics(int x, int y, TextColor dark, TextColor bright){
        this.dark = dark;
        this.bright = bright;
        this.x = x;
        this.y = y;
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
    @Override
    public void draw(Screen screen) {
        TextGraphics darkRender = screen.newTextGraphics();
        TextGraphics brightRender = screen.newTextGraphics();
        darkRender.setBackgroundColor(dark);
        brightRender.setBackgroundColor(bright);
        String topLine = "12345678";
        String leftLine = "ABCDEFGH";
        darkRender.putString(x, y-2, topLine);
        for (int i = 0; i < leftLine.length(); i++ ){
            darkRender.putString(x-2, y + i, String.valueOf(leftLine.charAt(i)));
        }
        for(int i = 0; i < sideLength; i++){
            for(int j = 0; j < sideLength; j++){
                if( (i % 2 == 0 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0) ){
                    darkRender.putString(x+i, y+j, " ");
                }
                else
                    brightRender.putString(x + i, y + j, " ");
            }
        }
    }
}
