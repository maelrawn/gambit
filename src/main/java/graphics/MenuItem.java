package graphics;

import com.googlecode.lanterna.TextColor;

public class MenuItem {
    private String content;
    private TextColor fgColor;
    private TextColor bgColor;
    public MenuItem(String content, TextColor fg, TextColor bg){
        this.content = content;
        this.fgColor = fg;
        this.bgColor = bg;
    }

    public TextColor getFgColor() {
        return fgColor;
    }

    public void setFgColor(TextColor fgColor) {
        this.fgColor = fgColor;
    }

    public TextColor getBgColor() {
        return bgColor;
    }

    public void setBgColor(TextColor bgColor) {
        this.bgColor = bgColor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
