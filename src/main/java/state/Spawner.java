package state;

import board.ChessBoard;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import graphics.RenderableObject;
import org.w3c.dom.Text;
import pieces.*;

import java.util.Random;

public class Spawner implements RenderableObject {
    int[] entryPoint;
    ChessPiece spawningPiece;
    public Spawner(){
        spawnPiece();
    };
    public void erasePieceGraphic(Screen screen){
        TextGraphics render = screen.newTextGraphics();
        render.setCharacter(spawningPiece.getX() + ChessBoard.x, spawningPiece.getY() + ChessBoard.y, ' ');
    }
    public void spawnPiece() {
        spawningPiece = ChessPiece.generateRandomPiece();
        Random rand = new Random();
        boolean hor = rand.nextBoolean(); //1 = horizontal, 0 = vertical
        if (hor) {
            int xval = rand.nextInt(0, 7);
            int yval = rand.nextBoolean() ? -1 : 8;
            spawningPiece.setX(xval);
            spawningPiece.setY(yval);
            entryPoint = (yval == -1) ? new int[]{xval, 0} : new int[]{xval, 7};

        } else  {
            int xval = rand.nextBoolean() ? -1 : 8;
            int yval = rand.nextInt(0, 7);
            spawningPiece.setX(xval);
            spawningPiece.setY(yval);
            entryPoint = (xval == -1) ? new int[]{0, yval} : new int[]{7, yval};
        }
    }

    public int[] getEntryPoint() {
        return entryPoint;
    }

    public ChessPiece getSpawningPiece() {
        return spawningPiece;
    }

    @Override
    public void draw(Screen screen) {
        int absX = spawningPiece.getX() + ChessBoard.x;
        int absY = spawningPiece.getY() + ChessBoard.y;
        TextGraphics render = screen.newTextGraphics();
        render.setCharacter(absX, absY, spawningPiece.getGraphic());
        render.setForegroundColor(TextColor.ANSI.WHITE_BRIGHT);
        render.setBackgroundColor(TextColor.ANSI.RED_BRIGHT);
        char renderChar = screen.getBackCharacter(ChessBoard.x + entryPoint[0], ChessBoard.y + entryPoint[1]).getCharacter();
        render.setCharacter(ChessBoard.x + entryPoint[0], ChessBoard.y + entryPoint[1], renderChar);
    }
}
