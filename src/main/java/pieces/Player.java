package pieces;

import board.ChessBoard;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import graphics.RenderableObject;

import java.lang.reflect.Array;

public class Player extends ChessPiece {
    private final char graphic = 'X';
    private ChessPiece currentPathingPiece;

    public enum pieceTypes {
        Bishop,
        King,
        Knight,
        Pawn,
        Rook,
        Queen;
    }

    private int[] heldPieces = new int[]{0, Integer.MAX_VALUE / 2, 0, 0, 0, 0};
    private ChessPiece[] internalPieces = {new Bishop(0, 0), new King(), new Knight(),
            new Pawn(0,0), new Rook(), new Queen()};

    private void changePathingType(pieceTypes piece) {
        if (heldPieces[piece.ordinal()] > 0) {
            currentPathingPiece = internalPieces[piece.ordinal()];
        }
    }

    @Override
    public int[][] findPaths(ChessBoard board, int x, int y) {
        return new int[0][0];
    }
}
