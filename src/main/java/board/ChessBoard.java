package board;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import graphics.ChessBoardGraphics;
import pieces.ChessPiece;
import pieces.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ChessBoard {
    public static int x;
    public static int y;
    public static final int sideLength = 8;
    public ChessBoard(int x, int y){
        ChessBoard.x = x;
        ChessBoard.y = y;
        boardGraphics = new ChessBoardGraphics(x, y, TextColor.ANSI.BLACK_BRIGHT, TextColor.ANSI.WHITE);
        enemyPieces = new ArrayList<ChessPiece>();
        actorQueue = new LinkedList<ChessPiece>();
    }
    ChessBoardGraphics boardGraphics;
    ArrayList<ChessPiece> enemyPieces;
    Queue<ChessPiece> actorQueue;
    Player player = new Player();
    public void addActor(ChessPiece actor){
        enemyPieces.add(actor);
        actorQueue.add(actor);
    }
    public Player getPlayer(){
        return player;
    }
    public boolean hasPlayerAt(int x, int y){
        if (player.getX() == x && player.getY() == y){
            return true;
        }
        return false;
    }
    private boolean isInBounds(int x, int y){
        return (0 <= x && x < 8 && 0 <= y && y < 8);
    }
    public boolean isOpenSpace(int x, int y){
        if(!isInBounds(x, y)){
            return false;
        }
        for (ChessPiece piece:enemyPieces) {
            if (    piece.isAlive()
                    && piece.getX() == x
                    && piece.getY() == y){
                return false;
            }
        }
        return true;
    }
    public boolean isOpenSpace(ChessPiece enemy, int x, int y){
        if(!isInBounds(x, y)){
            return false;
        }
        for (ChessPiece piece:enemyPieces) {
            if (    piece != enemy
                    && piece.isAlive()
                    && piece.getX() == x
                    && piece.getY() == y){
                return false;
            }
        }
        return true;
    }
    public void pathfind(){
        int numActors = actorQueue.size();
        for(int i  = 0; i < numActors; i++){
            ChessPiece actor = actorQueue.poll();
            if (actor.isAlive()){
                actor.move(this);
                actorQueue.add(actor);
            }
        }
    }
    public void draw(Screen screen){
        this.boardGraphics.draw(screen);
        int numActors = actorQueue.size();
        for(int i  = 0; i < numActors; i++){
            ChessPiece actor = actorQueue.poll();
            if (actor.isAlive()){
                actor.draw(screen);
                actorQueue.add(actor);
            }
        }
    }
}
