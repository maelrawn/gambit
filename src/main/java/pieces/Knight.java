package pieces;

import board.ChessBoard;

public class Knight extends ChessPiece {
    private final char graphic = 'N';
    @Override
    int[][] findPaths(ChessBoard board, int x, int y){
        return new int[0][0];
    }
}
