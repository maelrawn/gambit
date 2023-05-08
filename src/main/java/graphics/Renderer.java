package graphics;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class Renderer implements RenderableObject {
    Screen screen;
    TextGraphics renderer;

    public Renderer(Screen screen) {
        this.screen = screen;
        this.renderer = this.screen.newTextGraphics();
    }

    public void putVertString(int x, int y, String text) {
        for (int i = 0; i < text.length(); i++) {
            this.renderer.putString(x, y + i, String.valueOf(text.charAt(i)));
        }
    }

    public void draw(Screen screen) {

    }
}
