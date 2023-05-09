import board.ChessBoard;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.*;
import graphics.Menu;
import graphics.MenuItem;
import input.InputHandler;
import pieces.*;

public class GambitGame {
    public void run(String[] args){
        try {
            DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
            //broken shit for specifying a monospaced font, for some reason the terminal area = 0 if set
//            File fontfile = new File("/System/Library/Fonts/Supplemental/Andale Mono.ttf");
//            Font font = Font.createFont(Font.MONOSPACED, fontfile);
//            SwingTerminalFontConfiguration terminalfont = new SwingTerminalFontConfiguration(true, AWTTerminalFontConfiguration.BoldMode.EVERYTHING,font);
//            defaultTerminalFactory.setTerminalEmulatorFontConfiguration(terminalfont);
            Screen screen = defaultTerminalFactory.createScreen();
            screen.startScreen();
            screen.setCursorPosition(null);
            Menu menu = new Menu();
            MenuItem numKings = new MenuItem("Kings: ∞",
                                                TextColor.ANSI.CYAN,
                                                TextColor.ANSI.BLACK_BRIGHT);
            MenuItem numQueens = new MenuItem("Queens: 0",
                                                TextColor.ANSI.CYAN,
                                                TextColor.ANSI.BLACK_BRIGHT);
            MenuItem numBishops = new MenuItem("Bishops: 0",
                                                TextColor.ANSI.CYAN,
                                                TextColor.ANSI.BLACK_BRIGHT);
            MenuItem numKnights = new MenuItem("kNights: 0",
                                                TextColor.ANSI.CYAN,
                                                TextColor.ANSI.BLACK_BRIGHT);
            MenuItem numRooks = new MenuItem("Rooks: 0",
                                                TextColor.ANSI.CYAN,
                                                TextColor.ANSI.BLACK_BRIGHT);
//            MenuItem numPawns = new MenuItem("Pawns: 0",
//                                                TextColor.ANSI.CYAN,
//                                                TextColor.ANSI.BLACK_BRIGHT);
            menu.add_item(numKings);
            menu.add_item(numQueens);
            menu.add_item(numKnights);
            menu.add_item(numRooks);
            menu.add_item(numBishops);
            ChessBoard board = new ChessBoard(20, 10);
            menu.setX(ChessBoard.x + ChessBoard.sideLength + 2);
            menu.setY(ChessBoard.y);
            menu.draw(screen);
            board.addActor(new Queen(0,0));
            InputHandler input = new InputHandler();
            while(board.getPlayer().isAlive()) {
                board.actorPathfind();
                board.draw(screen);
                screen.refresh();
                while(!input.processInput(input.getInput(screen), board, screen)){}
                board.actorMove();
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
