import board.ChessBoard;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.*;
import graphics.Menu;
import graphics.MenuItem;
import input.InputHandler;


public class GambitGame {
    public void run(String[] args){
        try {
            DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
            defaultTerminalFactory.setInitialTerminalSize(new TerminalSize(25, 12));
            //broken shit for specifying a monospaced font, for some reason the terminal area = 0 if set
//            File fontfile = new File("/System/Library/Fonts/Supplemental/Andale Mono.ttf");
//            Font font = Font.createFont(Font.MONOSPACED, fontfile);
//            SwingTerminalFontConfiguration terminalfont = new SwingTerminalFontConfiguration(true, AWTTerminalFontConfiguration.BoldMode.EVERYTHING,font);
//            defaultTerminalFactory.setTerminalEmulatorFontConfiguration(terminalfont);
            Screen screen = defaultTerminalFactory.createScreen();
            screen.startScreen();
            screen.setCursorPosition(null);
            Menu menu = new Menu();
            TextColor fgMenuItemColor = TextColor.ANSI.CYAN;
            TextColor bgMenuItemColor = TextColor.ANSI.BLACK_BRIGHT;
            MenuItem numKings = new MenuItem("Kings: ", 0, fgMenuItemColor, bgMenuItemColor);
            MenuItem numQueens = new MenuItem("Queens: ", 0, fgMenuItemColor, bgMenuItemColor);
            MenuItem numBishops = new MenuItem("Bishops: ",0, fgMenuItemColor, bgMenuItemColor);
            MenuItem numKnights = new MenuItem("kNights: ", 0, fgMenuItemColor, bgMenuItemColor);
            MenuItem numRooks = new MenuItem("Rooks: ", 0, fgMenuItemColor, bgMenuItemColor);
            MenuItem numPawns = new MenuItem("Pawns: ", 0, fgMenuItemColor, bgMenuItemColor);
            menu.add_item(numBishops);
            menu.add_item(numKings);
            menu.add_item(numKnights);
            menu.add_item(numPawns);
            menu.add_item(numQueens);
            menu.add_item(numRooks);
            ChessBoard board = new ChessBoard(2, 2);
            menu.setX(ChessBoard.x + ChessBoard.sideLength + 2);
            menu.setY(ChessBoard.y);
            menu.draw(screen);
            board.generateSpawnedPiece(screen);
            InputHandler input = new InputHandler();
            int turncount = 0;
            while(board.getPlayer().isAlive()) {
                board.actorPathfind();
                board.draw(screen);
                screen.refresh();
                boolean turnActionTaken = false;
                while(!turnActionTaken){
                    turnActionTaken = input.processInput(input.getInput(screen), board, screen);
                }
                board.actorMove();
                turncount += 1;
                if (turncount % 2 == 0){
                    board.generateSpawnedPiece(screen);
                }
                else {
                    board.spawnPiece(screen);
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        GambitGame game = new GambitGame();
        game.run(args);
    }
}
