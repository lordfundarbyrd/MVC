import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Controller implements ActionListener {
    private Model model;
    
    public Controller (Model model) {
        this.model = model; 
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton)e.getSource();
        String coords = btn.getText();
        int row = Integer.parseInt(coords.substring(0, coords.indexOf(",")));
        int col = Integer.parseInt(coords.substring(coords.indexOf(",") + 2));
        System.out.format("%d, %d\n", row, col);
        
        if (!model.isOccupied(row, col)) {
            if (validate(row, col, -1, 0) | 
                validate(row, col, -1, 1) | 
                validate(row, col, 0, 1) | 
                validate(row, col, 1, 1) | 
                validate(row, col, 1, 0) | 
                validate(row, col, 1, -1) | 
                validate(row, col, 0, -1) | 
                validate(row, col, -1, -1)) 
            {
                model.occupy(row, col, model.getTurn());
                model.takeTurn();
            }
        }
    }    
    boolean validate (int upStreamRow, int upStreamCol, int dRow, int dCol) {
        int row = upStreamRow + dRow;
        int col = upStreamCol + dCol;

        // on the board?
        if (row < 0 || row == model.getSize() || col < 0 || col == model.getSize()) {
            // off the board...
            return false;
        }
        
        //occupied?
        if (!model.isOccupied(row, col)) {
            // nope...
            return false;
        }
        
        if (model.isOccupiedBy(row, col, model.getTurn())) {
            return model.isOccupied(upStreamRow, upStreamCol);
        }
        // on board, occupied, opposite color...
        
        if (validate(row, col, dRow, dCol)) {
            model.occupy (row, col, model.getTurn());
            return true;
        }
        return false;
    }
}

