package pieces;

import board.ChessBoard;
public class Rook extends ChessPiece {
    public Rook(int x, int y){
        this.x = x;
        this.y = y;
        this.graphic = 'R';
        this.type = pieceTypes.Rook;
    };
    @Override
    public int[][] findPaths(ChessBoard board, int x, int y){
        int[] lengths = new int[4];
        lengths[0] = searchLine(board, getX(),getY(), 1, 0);
        lengths[1] = searchLine(board, getX(),getY(), -1,0);
        lengths[2] = searchLine(board, getX(),getY(), 0, -1);
        lengths[3] = searchLine(board, getX(),getY(), 0, 1);
        int[][] openSpaces = new int[lengths[0] + lengths[1] + lengths[2] + lengths[3]][2];
        int ptr = 0;
        for(int i = 0; i < lengths[0]; i++){
            openSpaces[i] = new int[]{x + i, y};
        }
        ptr += lengths[0];
        for(int i = 0; i < lengths[1]; i++){
            openSpaces[ptr + i] = new int[]{x - i, y};
        }
        ptr += lengths[1];
        for(int i = 0; i < lengths[2]; i++){
            openSpaces[ptr + i] = new int[]{x, y - i};
        }
        ptr += lengths[2];
        for(int i = 0; i < lengths[3]; i++){
            openSpaces[ptr + i] = new int[]{x, y + i};
        }
        this.threatenedSpaces = openSpaces;
        threatenedSpaces = removeSelfFromThreatenedSpaces();
        return threatenedSpaces;
    }
}
