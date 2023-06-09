package graphics;

import com.googlecode.lanterna.TextColor;

public class MenuItem {
    private String label;
    private int content;
    private TextColor fgColor;
    private TextColor bgColor;
    public MenuItem(String label, int content, TextColor fg, TextColor bg){
        this.label = label;
        this.content = content;
        this.fgColor = fg;
        this.bgColor = bg;
    }
    public TextColor getFgColor() {
        return fgColor;
    }
    public TextColor getBgColor() {
        return bgColor;
    }
    public String getText(){ return label + " " + String.valueOf(content); }
    public void setContent(int content) {
        this.content = content;
    }

}
