package pieces;

import board.ChessBoard;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import graphics.RenderableObject;

import java.util.Random;

import static java.lang.Math.*;

public abstract class ChessPiece implements RenderableObject {
    protected int x = 0;
    protected int y = 0;
    protected boolean isAlive = true;
    protected char graphic = 'C';
    protected pieceTypes type;
    public enum pieceTypes {
        Bishop,
        King,
        Knight,
        Pawn,
        Rook,
        Queen,
        Player;
    }
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
        double currdist;
        for ( int[] coord: available ) {
            currdist = sqrt(pow(Double.valueOf(coord[0] - target[0]),2)
                            + pow(Double.valueOf(coord[1] - target[1]), 2));
            if( currdist < mindist ){
                mindist = currdist;
                bestcoord = coord;
            }
        }
        return bestcoord;
    }
    public boolean move(ChessBoard board, int[] target){
        threatenedSpaces = findPaths(board, this.x, this.y);
        int[] space = selectSpace(target, threatenedSpaces);
        if(board.hasPlayerAt(space[0], space[1])){
            board.getPlayer().setAlive(false);
            this.x = space[0];
            this.y = space[1];
            return false;
        }
        else if(board.isOpenSpace(this, space[0], space[1])) {
            if(this.type == pieceTypes.Player){
                pieceTypes type = board.killPieceAt(space[0], space[1]);
                board.getPlayer().addPiece(type);
            }
            this.x = space[0];
            this.y = space[1];
        }
        return true;
    }
    public void updateThreatenedSpaces(ChessBoard board){
        threatenedSpaces = findPaths(board, x, y);
    }
    public pieceTypes getType(){
        return this.type;
    }
    public void setAlive(boolean alive){
        this.isAlive = alive;
    }
    public boolean isAlive(){
        return this.isAlive;
    }
    public void draw(Screen screen) {
        System.out.println(this);
        int absX = this.x + ChessBoard.x;
        int absY = this.y + ChessBoard.y;
        TextGraphics render = screen.newTextGraphics();
        render.setCharacter(absX, absY, this.getGraphic());
        render.setForegroundColor(TextColor.ANSI.WHITE_BRIGHT);
        render.setBackgroundColor(TextColor.ANSI.RED_BRIGHT);
        if(this.graphic == 'X'){
            render.setBackgroundColor(TextColor.ANSI.CYAN);
        }
        for (int[] space : threatenedSpaces) {
//            System.out.println(String.valueOf(ChessBoard.x + space[0]) + " " + String.valueOf(ChessBoard.y + space[1]));
            char renderChar = screen.getBackCharacter(ChessBoard.x + space[0], ChessBoard.y + space[1]).getCharacter();
            render.setCharacter(ChessBoard.x + space[0], ChessBoard.y + space[1], renderChar);
        }
    }
    public static ChessPiece generateRandomPiece(){
        Random rand = new Random();
        int num = rand.nextInt(0, pieceTypes.values().length - 2); //we are omitting .Pawn and .Player
        switch(num){
            case 0:
                return new Bishop(0, 0);
            case 1:
                return new King(0, 0);
            case 2:
                return new Knight(0, 0);
            case 4:
                return new Queen(0, 0);
            case 5:
                return new Rook(0, 0);
        }
        return new Pawn(0,0);
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