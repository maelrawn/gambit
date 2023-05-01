package graphics;
import graphics.UIElement;
import java.util.ArrayList;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.*;
public class Menu{
    private ArrayList<UIElement> elements;
    private int x;
    private int y;
    public void add_item(UIElement element){
        this.elements.add(element);
    }
    public void draw(){
        int maxlength = 0;
        int rows = elements.size();
        for (UIElement element : elements) {
            int length = element.getContent().length();
            if (length > maxlength)
                maxlength = length;
        }
        for (int i = 0; i < rows; i++) {
            TextGraphics renderer = new TextGraphics();
            renderer.putString(
                    this.x,
                    this.y + i,
                    String.format("%-" + maxlength + "s", elements[i].getContent())
            );
        }
    }

}
