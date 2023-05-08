package pieces;

import board.ChessBoard;
public class Rook extends ChessPiece {
    private final char graphic = 'R';
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
        return openSpaces;
    }
}
