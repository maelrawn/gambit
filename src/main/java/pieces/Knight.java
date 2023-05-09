package pieces;

import board.ChessBoard;

import java.util.ArrayList;

public class Knight extends ChessPiece {
    public Knight(int x, int y){
        this.x = x;
        this.y = y;
        this.graphic = 'N';
        this.type = pieceTypes.Knight;
    }
    @Override
    int[][] findPaths(ChessBoard board, int x, int y){
        ArrayList<int[]> spaces =  new ArrayList<int[]>();
        if ( board.isOpenSpace(this, x + 2, y - 1)){
            spaces.add(new int[]{x+2, y-1});
        }
        if ( board.isOpenSpace(this, x + 2, y + 1)){
            spaces.add(new int[]{x+2, y+1});
        }
        if ( board.isOpenSpace(this, x - 2, y - 1)){
            spaces.add(new int[]{x-2, y-1});
        }
        if ( board.isOpenSpace(this, x - 2, y + 1)){
            spaces.add(new int[]{x-2, y+1});
        }
        if ( board.isOpenSpace(this, x + 1, y - 2)){
            spaces.add(new int[]{x+1, y-2});
        }
        if ( board.isOpenSpace(this, x - 1, y - 2)){
            spaces.add(new int[]{x-1, y-2});
        }
        if ( board.isOpenSpace(this, x + 1, y +2)){
            spaces.add(new int[]{x+1, y+2});
        }
        if ( board.isOpenSpace(this, x - 1, y + 2)){
            spaces.add(new int[]{x-1, y+2});
        }
        int[][] openSpaces = new int[spaces.size()][2];
        for(int i = 0; i < spaces.size(); i++){
            openSpaces[i] = spaces.get(i);
        }
        return openSpaces;
    }
}
