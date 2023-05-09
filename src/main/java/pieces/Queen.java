package pieces;

import board.ChessBoard;

public class Queen extends ChessPiece {
    public Queen(int x, int y){
        this.x = x;
        this.y = y;
        this.graphic = 'Q';
        this.type = pieceTypes.Queen;
    }
    @Override
    int[][] findPaths(ChessBoard board, int x, int y){
        int[] lengths = new int[8];
        lengths[0] = searchLine(board, this.x, this.y, 1, 0);
        lengths[1] = searchLine(board, this.x, this.y, -1,0);
        lengths[2] = searchLine(board, this.x, this.y, 0, -1);
        lengths[3] = searchLine(board, this.x, this.y, 0, 1);
        lengths[4] = searchLine(board, this.x, this.y, 1, 1);
        lengths[5] = searchLine(board, this.x, this.y, -1,1);
        lengths[6] = searchLine(board, this.x, this.y, 1, -1);
        lengths[7] = searchLine(board, this.x, this.y, -1, -1);
        int totalLength = 0;
        for(int i = 0; i < lengths.length; i++){
            totalLength += lengths[i];
        }
        int[][] openSpaces = new int[totalLength][2];
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
        ptr += lengths[3];
        for(int i = 0; i < lengths[4]; i++){
            openSpaces[ptr + i] = new int[]{x + i, y + i};
        }
        ptr += lengths[4];
        for(int i = 0; i < lengths[5]; i++){
            openSpaces[ptr + i] = new int[]{x - i, y + i};
        }
        ptr += lengths[5];
        for(int i = 0; i < lengths[6]; i++){
            openSpaces[ptr + i] = new int[]{x + i, y - i};
        }
        ptr += lengths[6];
        for(int i = 0; i < lengths[7]; i++){
            openSpaces[ptr + i] = new int[]{x - i, y - i};
        }
        this.threatenedSpaces = openSpaces;
        return threatenedSpaces;
    }
}
