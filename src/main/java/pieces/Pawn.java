package pieces;

import board.ChessBoard;

import java.util.Random;

public class Pawn extends ChessPiece {
    private final char graphic = 'P';

    int xDir = 0;
    int yDir = 0;
    public Pawn(int x, int y){
        this.x = x;
        this.y = y;
        int hor = 0;
        int neg = 1;
        Random random = new Random();
        boolean horizontal = random.nextBoolean();
        if(horizontal){
            hor = 1;
        }
        boolean negative = random.nextBoolean();
        if(negative){
            neg *= -1;
        }
        xDir = hor * neg;
        yDir = (1 - hor) * neg;
    }
    @Override
    int[][] findPaths(ChessBoard board, int x, int y) {
        int nextX = x + xDir;
        int nextY= y + yDir;
        if ( x == nextX
                && (board.hasPlayerAt(nextX-1, nextY)
                || board.hasPlayerAt(nextX+1, nextY))){
            return new int[][]{{nextX-1, nextY}, {nextX+1, nextY}};
        }
        else if ( y == nextY
                && (board.hasPlayerAt(nextX, nextY-1)
                 || board.hasPlayerAt(nextX, nextY+1))){
            return new int[][]{{nextX, nextY-1}, {nextX, nextY+1}};
        }
        else return new int[][]{{x, y}, {nextX, nextY}};
    }
}
