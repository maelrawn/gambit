package graphics;

import com.googlecode.lanterna.TextColor;

public class UIElement {
    private String content;
    private TextColor fgColor;
    private TextColor bgColor;

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
