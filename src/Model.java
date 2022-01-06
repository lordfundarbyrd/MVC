import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.event.ChangeListener;

public class Model implements Serializable {
    
    public static final char RED = 'R'; // player 1
    public static final char GREEN = 'G'; // player 2
    public static final char BLUE = 'B'; // player 2
    private static char turn; // L or D for whose turn it is
   
    private char[][] board; // matrix for each space of the board, and each index will contain L, D, or . to show occupation.
    
    private Collection<ChangeListener> views; // Collection of ChangeListeners subscribed to the model so they get updates when it changes.
    
    public void addChangeListener (ChangeListener view) { // subscribes ChangeListener, adds them to views.
        views.add(view);
    }
    
    public void removeChangeListener (ChangeListener view) { // unsubscribes ChangeListener, removes them from views.
        views.remove(view);
    }
    
    public Model (int size) { // creates board with specified size, must be at least 4 and an even number. reset() specifies how the board spaces should be occupied.
        board = new char[size][size];
        
        if (size < 5) {
            throw new IllegalArgumentException();
        }
        if (size % 3 != 0) {
            throw new IllegalArgumentException();
        }
        
        views = new ArrayList();
        
        reset();
    }
    
    
    public String toString() { // returns a String that represents the board, showing D for DARK player, L for LIGHT player, and . for unoccupied
        String model = "";
        for (int column = 0; column < this.getSize(); column++) {
            for (int row = 0; row < this.getSize(); row++) {
                model += board[row][column] + " ";
            }
        model += '\n';
        }
        return model;
    }
    
    public void reset() {
        turn = RED; // returns game to initial configuration with two spaces owned by D and two owned by L in the middle of the board.

        occupy(((getSize()-1)/2)-1,((getSize()-1)/2)-1,RED);
        occupy(((getSize()-1)/2),((getSize()-1)/2),BLUE);
        occupy(((getSize()-1)/2)+1,((getSize()-1)/2)+1,GREEN);
        occupy(((getSize()-1)/2)-1,((getSize()-1)/2)+1,BLUE);
        occupy(((getSize()-1)/2),((getSize()-1)/2)-1,GREEN);
        occupy(((getSize()-1)/2)+1,((getSize()-1)/2),RED);
        occupy(((getSize()-1)/2)-1,((getSize()-1)/2),GREEN);
        occupy(((getSize()-1)/2),((getSize()-1)/2)+1,RED);
        occupy(((getSize()-1)/2)+1,((getSize()-1)/2)-1,BLUE);
        
        for (int column = 0; column < this.getSize(); column++) {
            for (int row = 0; row < this.getSize(); row++) {
                if (board[row][column] != RED && board[row][column] != BLUE && board[row][column] != GREEN) {
                    board[row][column] = '.';
                }
            }
        }
    }
    
    public int getSize() { // returns how many rows/columns the board has
        return board.length;
    }
     
    public int getCount (char player) { // counts how many spaces a specified player possesses
        int count = 0;
        for (int column = 0; column < this.getSize(); column++) {
            for (int row = 0; row < this.getSize(); row++) {
                if (board[row][column] == player) {
                    count++;
                }
            }
        }
    return count;
    }
    
    /***  invoked by the controller at the end of a player's turn, takeTurn will
     *  change the player and inform all registered views to update.*/
    
    public void takeTurn() { // changes turn, notifies ChangeListeners (which is commented out because it causes a null pointer exception when method is used due to there being no ChangeListeners in Collection)
        if (turn == BLUE) {
            turn = RED;
        }
        
        else {
            if (turn == RED) {
            turn = GREEN;
            }
            else {
                if (turn == GREEN) {
                turn = BLUE;
                }
            }
        }
        
        for (ChangeListener view : views) {
            view.stateChanged(null);
        }
    }
    
    
    public char getTurn() { // returns whose turn it is
        return turn;
    }
   
    
    public boolean isOccupied (int row, int col) { // checks specified place to see if a player occupies it
        if (board[row][col] != '.') {
            return true;
        }
        return false;
    }
    
    public boolean isOccupiedBy (int row, int col, char player) { // checks specified place to see if a specified player occupies it
        if (board[row][col] == player) {
            return true;
        }
        return false;
    }
    
    
    public void occupy (int row, int col, char player) { // changes specified place to be possessed by the specified player
        board[row][col] = player;
    }
}    

// make 3-person Othello