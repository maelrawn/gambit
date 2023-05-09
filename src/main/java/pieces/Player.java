package pieces;

import cursor.Cursor;

import board.ChessBoard;

import java.util.ArrayList;

public class Player extends ChessPiece {
    private ChessPiece currentPathingPiece;
    private int[] heldPieces = new int[]{1, 999, 1, 1, 1, 1};
    private ArrayList<ChessPiece> internalPieces = new ArrayList<ChessPiece>();
    public Player(int x, int y){
        this.x = x;
        this.y = y;
        this.graphic = 'X';
        this.type = pieceTypes.Player;
        internalPieces.add(new Bishop(x, y));
        internalPieces.add(new King(x, y));
        internalPieces.add(new Knight(x, y));
        internalPieces.add(new Pawn(x, y));
        internalPieces.add(new Queen(x, y));
        internalPieces.add(new Rook(x, y));
        currentPathingPiece = internalPieces.get(1);
    }
    public int[] getHeldPieces(){
        return heldPieces;
    }
    public boolean expendPiece(){
        heldPieces[currentPathingPiece.getType().ordinal()] -= 1;
        if(heldPieces[currentPathingPiece.getType().ordinal()] <= 0){
            currentPathingPiece = internalPieces.get(pieceTypes.King.ordinal());
            return true;
        }
        return false;
    }
    public boolean playerMove(ChessBoard board, int[] target) {
        if (board.hasPieceAt(target[0], target[1])) {
            pieceTypes type = board.killPieceAt(target[0], target[1]);
            board.getPlayer().addPiece(type);
        }
        this.x = target[0];
        this.y = target[1];
        for (ChessPiece element:this.internalPieces) {
            element.setX(this.x);
            element.setY(this.y);
        }
        expendPiece();
        return true;
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
        threatenedSpaces = removeSelfFromThreatenedSpaces();
        return threatenedSpaces;
    }
}
