import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class ScoreView extends View {

    protected Object[][] view;
    protected static JButton reset;
            
    public ScoreView(String title, Model model) {
        super(title, model);
        reset = new JButton("Reset");
        view = new Object[5][1];
        reset.setSize(model.getSize(), model.getSize());
        setLayout(new GridLayout(model.getSize(), model.getSize()));
        view[0][0] = new JLabel("Blue: " + model.getCount('B'));
        view[1][0] = new JLabel("Red: " + model.getCount('R'));
        view[2][0] = new JLabel("Green: " + model.getCount('G'));
        view[3][0] = new JLabel("Turn: " + model.getTurn());
        view[4][0] = reset;
        JLabel one = (JLabel)view[0][0];
        add(one);
        one.setHorizontalAlignment(SwingConstants.CENTER);
        one.setVerticalAlignment(SwingConstants.CENTER);
        JLabel two = (JLabel)view[1][0];
        add(two);
        two.setHorizontalAlignment(SwingConstants.CENTER);
        two.setVerticalAlignment(SwingConstants.CENTER);
        JLabel three = (JLabel)view[2][0];
        add(three);
        three.setHorizontalAlignment(SwingConstants.CENTER);
        three.setVerticalAlignment(SwingConstants.CENTER);
        JLabel four = (JLabel)view[3][0];
        add(four);
        four.setHorizontalAlignment(SwingConstants.CENTER);
        four.setVerticalAlignment(SwingConstants.CENTER);
        reset.setHorizontalAlignment(SwingConstants.CENTER);
        reset.setVerticalAlignment(SwingConstants.CENTER);
        reset.addActionListener(BoardView.cntrl);
        add(reset);
        updateView();
        setVisible(true);
        this.pack();    
    }
    @Override
    public void updateView() {
        JLabel one = (JLabel) view[0][0];
        one.setText("Blue: " + model.getCount('B'));
        JLabel two = (JLabel) view[1][0];
        two.setText("Red: " + model.getCount('R')); 
        JLabel three = (JLabel) view[2][0];
        three.setText("Green: " + model.getCount('R'));   
        JLabel four = (JLabel) view[3][0];
        four.setText("Turn: " + model.getTurn()); 
    }
}