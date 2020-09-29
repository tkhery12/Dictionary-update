import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Wordlist extends JList<String> {

    private static Wordlist List;

    public Wordlist(String[] data) {
        setListData(data);
        setBackground(Color.decode("#e3e3e3"));
        setBorder(new EmptyBorder(10,10,10,10));
        Wordlist.List = this;
    }

    public static void Setdata(String [] data) {
        Wordlist.List.setListData(data);
        Wordlist.List.repaint();
    }


}
