import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionEvent;

public class BoardView extends View {
    
    private JButton[][] btns;
    protected static Controller cntrl;
    
    public BoardView(Model model, Controller cntrl) {
        super("Bored", model); // View's constructor
        this.cntrl = cntrl;
        int size = model.getSize();
        setSize(size * 50, size * 50);
        setLayout(new GridLayout(size,size));
        
        btns = new JButton[size][size];
        
        for (int row = 0; row < size; row++) {
            for (int col =0; col < size; col++) {
                JButton btn = new JButton (String.format ("%d, %d", row, col));
                btn.setBorder(new EtchedBorder (EtchedBorder.RAISED));
                btn.addActionListener(cntrl);
                add(btn);
                btns[row][col] = btn;
            }
        } 
        
        updateView();
        setVisible(true);
    }

    @Override
    public void updateView() {
        int size = model.getSize();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (model.isOccupied(row, col)) {
                    btns[row][col].setOpaque(true);
                    if (model.isOccupiedBy(row, col, Model.RED)) 
                        btns[row][col].setBackground(Color.RED);
                    if (model.isOccupiedBy(row, col, Model.BLUE))
                        btns [row][col].setBackground(Color.BLUE);
                    if (model.isOccupiedBy(row, col, Model.GREEN))
                        btns [row][col].setBackground(Color.GREEN);
                }
                else {
                    btns [row][col].setBackground(Color.WHITE);
                }
            }
        }
        for (int r = 0; r < btns[0].length; r++) {
            for (int c = 0; c < btns.length; c++) {
                if (model.isOccupiedBy(r,c,model.getTurn())) {
                    for (int i = 1; i < (btns[0].length - 1) - c; i++) {
                        for (int j = 1; j < (btns.length - 1) - r; j++) {
                                if (cntrl.validate(r, c, i-r, j-c) == true) {
                                    btns[2*r-i][2*c-j].setForeground(Color.ORANGE);
                                }
                        }
                    }
                }
                else {
                    btns[r][c].setForeground(Color.BLACK);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Model m = new Model(21);
        Controller ctrl = new Controller(m);
        new BoardView(m, ctrl);
        new ScoreView("Score and Reset", m);
    }
}

