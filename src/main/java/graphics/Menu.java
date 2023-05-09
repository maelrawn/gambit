package graphics;
import java.util.ArrayList;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class Menu implements RenderableObject{
    private ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
    private int x;
    private int y;
    private String title;

    public void updateItemValue(int newValue, int idx){
        menuItems.get(idx).setContent(newValue);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public void add_item(MenuItem item){
        this.menuItems.add(item);
    }
    public void draw(Screen screen){
        int maxlength = 0;
        int rows = menuItems.size();
        for (MenuItem element : menuItems) {
            int length = element.getText().length();
            if (length > maxlength)
                maxlength = length;
        }
        final TextGraphics renderer = screen.newTextGraphics();
        for (int i = 0; i < rows; i++) {
            try {
                String s = menuItems.get(i).getText();
                s = String.format("%-" + (maxlength) + "s", s);
                renderer.setForegroundColor(menuItems.get(i).getFgColor());
                renderer.setBackgroundColor(menuItems.get(i).getBgColor());
                renderer.putString(this.x, this.y + i, s, SGR.BOLD);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

}
