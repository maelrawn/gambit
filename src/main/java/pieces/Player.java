package pieces;

import cursor.Cursor;

import board.ChessBoard;

import java.util.ArrayList;

public class Player extends ChessPiece {
    private ChessPiece currentPathingPiece;
    private int[] heldPieces = new int[]{1, Integer.MAX_VALUE / 2, 1, 1, 1, 1};
    private ArrayList<ChessPiece> internalPieces = new ArrayList<ChessPiece>();
    public Player(int x, int y){
        this.x = x;
        this.y = y;
        this.graphic = 'X';
        this.type = pieceTypes.Player;
        internalPieces.add(new Bishop(0, 0));
        internalPieces.add(new King(0, 0));
        internalPieces.add(new Knight(0, 0));
        internalPieces.add(new Pawn(0, 0));
        internalPieces.add(new Rook(0, 0));
        internalPieces.add(new Queen(0, 0));
        currentPathingPiece = internalPieces.get(1);
    }
    public boolean expendPiece(pieceTypes type){
        heldPieces[type.ordinal()] -= 1;
        if(heldPieces[type.ordinal()] <= 0){
            currentPathingPiece = internalPieces.get(pieceTypes.King.ordinal());
            return true;
        }
        return false;
    }
    public boolean changePathingType(pieceTypes piece) {
        if (heldPieces[piece.ordinal()] > 0) {
            currentPathingPiece = internalPieces.get(piece.ordinal());
            return true;
        }
        return false;
    }

    public ChessPiece getCurrentPathingPiece() {
        return currentPathingPiece;
    }
    public void addPiece(pieceTypes piece){
        heldPieces[piece.ordinal()] += 1;
    }

    @Override
    public int[][] findPaths(ChessBoard board, int x, int y) {
        threatenedSpaces = this.currentPathingPiece.findPaths(board, this.x, this.y);
        return threatenedSpaces;
    }
}
