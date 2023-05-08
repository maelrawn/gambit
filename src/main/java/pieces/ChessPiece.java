package pieces;

import board.ChessBoard;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import graphics.RenderableObject;

import static java.lang.Math.*;

public abstract class ChessPiece implements RenderableObject {
    protected int x = 0;
    protected int y = 0;
    protected boolean isAlive = true;
    protected char graphic = 'C';
    protected int[][] threatenedSpaces;
    abstract int[][] findPaths(ChessBoard board, int x, int y);
    protected int searchLine(ChessBoard board, int x, int y, int xdir, int ydir){
        int length = 1;
        int currx = x + xdir;
        int curry = y + ydir;
        while(board.isOpenSpace(this, currx, curry) || board.hasPlayerAt(currx, curry)){
            length += 1;
            currx += xdir;
            curry += ydir;
        }
        return length;
    }
    public int[] selectSpace(int[] target, int[][] available) {
        int[] bestcoord = new int[2];
        double mindist = Double.MAX_VALUE;
        double currdist = 0;
        for ( int[] coord: available ) {
            currdist =    sqrt(pow(Double.valueOf(coord[0] - target[0]),2))
                        + sqrt(pow(Double.valueOf(coord[1] - target[1]), 2));
            if( currdist <= mindist ){
                mindist = currdist;
            }
            bestcoord = coord;
        }
        return bestcoord;
    }
    public boolean move(ChessBoard board){
        int[] target = new int[]{board.getPlayer().getX(), board.getPlayer().getY()};
        threatenedSpaces =  findPaths(board, this.x, this.y);
        int[] space = selectSpace(target, threatenedSpaces);
        setX(space[0]);
        setY(space[1]);
        if(board.hasPlayerAt(space[0], space[1])){
            board.getPlayer().setAlive(false);
            return false;
        }
        return true;
    }
    public void setAlive(boolean alive){
        this.isAlive = alive;
    }
    public boolean isAlive(){
        return this.isAlive;
    }
    public void draw(Screen screen) {
        int absX = getX() + ChessBoard.x;
        int absY = getY() + ChessBoard.y;
        TextGraphics render = screen.newTextGraphics();
        render.setCharacter(absX, absY, this.getGraphic());
        for (int[] space : threatenedSpaces) {
            char renderChar = screen.getBackCharacter(absX + space[0], absY + space[1]).getCharacter();
            render.setBackgroundColor(TextColor.ANSI.RED);
            render.setCharacter(absX + space[0], absY + space[1], renderChar);
        }
    }

    public char getGraphic(){
        return graphic;
    }
    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getY(){
        return this.y;
    }

    public int getX(){
        return this.x;
    }

}