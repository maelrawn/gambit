package board;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;

import graphics.ChessBoardGraphics;
import pieces.ChessPiece;
import pieces.Player;
import cursor.Cursor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class ChessBoard {
    public static int x;
    public static int y;
    public static final int sideLength = 8;
    private Cursor cursor;
    public ChessBoard(int x, int y){
        ChessBoard.x = x;
        ChessBoard.y = y;
        boardGraphics = new ChessBoardGraphics(x, y, TextColor.ANSI.BLACK_BRIGHT, TextColor.ANSI.WHITE);
        enemyPieces = new ArrayList<ChessPiece>();
        player = new Player(3, 4);
        cursor = new Cursor(3, 4);
    }
    ChessBoardGraphics boardGraphics;
    ArrayList<ChessPiece> enemyPieces;
    Player player = new Player(3, 4);
    public void addActor(ChessPiece actor){
        enemyPieces.add(actor);
    }
    public Player getPlayer(){
        return player;
    }

    public Cursor getCursor() {
        return cursor;
    }

    public boolean hasPlayerAt(int x, int y){
        if (player.getX() == x && player.getY() == y){
            return true;
        }
        return false;
    }
    public ChessPiece.pieceTypes killPieceAt(int x, int y){
        ChessPiece.pieceTypes type = ChessPiece.pieceTypes.Pawn;
        for (ChessPiece piece:enemyPieces) {
            if(piece.getX() == x && piece.getY() == y){
                piece.setAlive(false);
                return piece.getType();
            }
        }
        return type;
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
    public boolean isOpenSpace(ChessPiece self, int x, int y){
        if(!isInBounds(x, y)){
            return false;
        }
        System.out.println(player.getCurrentPathingPiece());
        System.out.println(self);
        if(self == player.getCurrentPathingPiece()){ //ordering like this allows player piece to land on enemies
            return true;
        }
        for (ChessPiece piece:enemyPieces) {
            if (    piece != self
                    && piece.isAlive()
                    && piece.getX() == x
                    && piece.getY() == y){
                return false;
            }
        }
        return true;
    }
    public void actorPathfind(){
        for(ChessPiece actor: enemyPieces){
            if (actor.isAlive()){
                actor.updateThreatenedSpaces(this);
            }
        }
        player.updateThreatenedSpaces(this);
    }
    public void draw(Screen screen){
        this.boardGraphics.draw(screen);
        for( ChessPiece actor: enemyPieces){
            if (actor.isAlive()){
                actor.draw(screen);
            }
        }
        player.draw(screen);
        cursor.draw(screen);
    }
    public void actorMove(){
        for(ChessPiece actor: enemyPieces){
            if (actor.isAlive()){
                actor.updateThreatenedSpaces(this);
                actor.move(this, new int[]{player.getX(), player.getY()});
            }
        }
    }
}
