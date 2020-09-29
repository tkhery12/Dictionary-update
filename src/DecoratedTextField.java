import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DecoratedTextField extends JTextField implements FocusListener, DocumentListener {
    private static String hint;

    private final Icon icon;
    private final int padding = 2;
    private final int iconPadding = 8;

    public DecoratedTextField(String icon) {
        this.icon = createImageIcon("icons/" + icon + ".png", icon);
        this.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        addFocusListener(this);
        getDocument().addDocumentListener(this);
    }

    public void setHint(int status) {
        switch (status) {
            case 1:
                hint = "Tìm kiếm";
                break;
            case 2:
                hint = "Nhập từ cần thêm";
                break;
            case 3:
                hint = "Nhập từ cần xóa";
                break;
            case 4:
                hint = "Nhập từ cần sửa";
                break;
            default:
                hint = "Đã xảy ra lỗi";
                break;
        }
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int textX = padding;

        // vẽ icon
        if (this.icon != null) {
            int iconWidth = icon.getIconWidth();
            int iconHeight = icon.getIconHeight();
            textX = 2 * iconPadding + iconWidth - padding;
            int y = (this.getHeight() - iconHeight) / 2;
            icon.paintIcon(this, g, iconPadding, y);
        }

        if (this.getText().equals("")) {
            // lưu font hiện tại và tạo font mới cho placeholder
            Font prev = g.getFont();
            Color prevColor = g.getColor();
            Font italic = prev.deriveFont(Font.ITALIC);
            g.setFont(italic);
            g.setColor(Color.decode("#8f8f8f"));

            // tìm vị trí vẽ placeholder để placeholder nằm giữa input field
            int height = this.getHeight();
            int h = g.getFontMetrics().getHeight();
            int textBottom = (height - h) / 2 + h - 2 * padding;

            // vẽ placeholder
            Graphics2D g2d = (Graphics2D) g;
            RenderingHints hints = g2d.getRenderingHints();
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2d.drawString(hint, textX, textBottom);
            g2d.setRenderingHints(hints);
            g.setFont(prev);
            g.setColor(prevColor);
        }
    }

    protected ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    @Override
    public void focusGained(FocusEvent arg0) {
        int textX = icon == null ? padding : 2 * iconPadding + icon.getIconWidth() - padding;
        this.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.decode("#ab4355"), 2),
                new EmptyBorder(padding, textX, padding, padding)));
        this.repaint();
    }

    @Override
    public void focusLost(FocusEvent arg0) {
        int textX = icon == null ? padding : 2 * iconPadding + icon.getIconWidth() - padding;
        this.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.lightGray, 2),
                new EmptyBorder(padding, textX, padding, padding)));
        this.repaint();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        DatabyTrie T = new DatabyTrie();
        String Stringdata = (T.getTrie().findAllMatch(T.getTrie().search_node(getText()),getText()));
        if(T.getTrie().search(getText())){
            Stringdata = Stringdata +getText();
        }
        String []newdata = Stringdata.split("\n");
        System.out.println(newdata.length);

        Wordlist.Setdata(newdata);
        // check lai dieu kieu neu no ko tim dc

        System.out.println(getText());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
}
