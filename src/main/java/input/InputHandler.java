package input;

import board.ChessBoard;
import com.googlecode.lanterna.screen.Screen;
import cursor.Cursor;
import pieces.ChessPiece;
import pieces.Player;

public class InputHandler {
    public static Character getInput(Screen screen){
        Character result = null;
        try {
            result = screen.readInput().getCharacter();
        } catch ( Exception e ){
            System.out.println(e);
        }
        return result;
    }
    public static boolean processInput( Character input, ChessBoard board, Screen screen){
        switch (Character.toLowerCase(input)){
            case 'w':
            case 'a':
            case 's':
            case 'd':
                board.getCursor().moveCursor(input, 8, 8, 0, 0);
                board.draw(screen);
                break;
            case 'b':
                if(board.getPlayer().changePathingType(ChessPiece.pieceTypes.Bishop)){
                    board.getPlayer().updateThreatenedSpaces(board);
                    board.draw(screen);
                }
                break;
            case 'k':
                if(board.getPlayer().changePathingType(ChessPiece.pieceTypes.King)){
                    board.getPlayer().updateThreatenedSpaces(board);
                    board.draw(screen);
                }
                break;
            case 'n':
                if(board.getPlayer().changePathingType(ChessPiece.pieceTypes.Knight)) {
                    board.getPlayer().updateThreatenedSpaces(board);
                    board.draw(screen);
                }
                break;
            //case 'p':
                //return player.switchpiece(pawn)
                //break;
            case 'r':
                if(board.getPlayer().changePathingType(ChessPiece.pieceTypes.Rook)){
                    board.getPlayer().updateThreatenedSpaces(board);
                    board.draw(screen);
                }
                break;
            case 'q':
                if(board.getPlayer().changePathingType(ChessPiece.pieceTypes.Queen)){
                    board.getPlayer().updateThreatenedSpaces(board);
                    board.draw(screen);
                }
                break;
            case '\n':
                Player player = board.getPlayer();
                Cursor cursor = board.getCursor();
                int[] target = new int[]{cursor.getX(), cursor.getY()};
                for (int[] space:player.findPaths(board, player.getX(), player.getY())) {
                    if (space[0] == cursor.getX() && space[1] == cursor.getY()) {
                        player.move(board, target);
                        return true;
                    }
                }
                break;
        }
        try {
            screen.refresh();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
