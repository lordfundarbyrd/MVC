import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

abstract public class View extends JFrame implements ChangeListener {
    
    protected Model model;
    
    public View (String title, Model model) {
        super (title); //JFrame's constructor
        this.model = model;
        model.addChangeListener(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    abstract public void updateView();
    
    @Override
    public void stateChanged(ChangeEvent e) {
        updateView();
    }
}
