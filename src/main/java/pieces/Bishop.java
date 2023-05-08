package pieces;

import board.ChessBoard;

public class Bishop extends ChessPiece{
    public Bishop(int x, int y){
        this.x = x;
        this.y = y;
        this.graphic = 'B';
    }
    @Override
    int[][] findPaths(ChessBoard board, int x, int y) {
        int[] lengths = new int[4];
        lengths[0] = searchLine(board, this.x, this.y, 1, 1);
        lengths[1] = searchLine(board, this.x, this.y, -1,1);
        lengths[2] = searchLine(board, this.x, this.y, 1, -1);
        lengths[3] = searchLine(board, this.x, this.y, -1, -1);
        int[][] openSpaces = new int[lengths[0] + lengths[1] + lengths[2] + lengths[3]][2];
        int ptr = 0;
        for(int i = 0; i < lengths[0]; i++){
            openSpaces[i] = new int[]{x + i, y + i};
        }
        ptr += lengths[0];
        for(int i = 0; i < lengths[1]; i++){
            openSpaces[ptr + i] = new int[]{x - i, y + i};
        }
        ptr += lengths[1];
        for(int i = 0; i < lengths[2]; i++){
            openSpaces[ptr + i] = new int[]{x + i, y - i};
        }
        ptr += lengths[2];
        for(int i = 0; i < lengths[3]; i++){
            openSpaces[ptr + i] = new int[]{x - i, y - i};
        }
        for (int[] coord:openSpaces) {
            System.out.println(String.valueOf(coord[0]) + String.valueOf(coord[1]) );
        }
        this.threatenedSpaces = openSpaces;
        return openSpaces;
    }
}
