package pieces;

import board.ChessBoard;
import com.googlecode.lanterna.screen.Screen;
import graphics.RenderableObject;

public class Queen extends ChessPiece {
    private final char graphic = 'Q';
    @Override
    int[][] findPaths(ChessBoard board, int x, int y){
        return new int[0][0];
    }
}
