import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class Tab extends JLabel implements MouseListener {
    private static final Tab[] allTabs = new Tab[4];
    private static int number_of_tabs = 0;
    private static DecoratedTextField mainTextField;

    private final String content;
    private final URL imgURL;
    private final URL imgInactiveURL;
    private final int status_code;

    public Tab(String content, String icon_name, int status_code) {
        this.content = content;
        this.imgURL = getClass().getResource("icons/" + icon_name + ".png");
        this.imgInactiveURL = getClass().getResource("icons/" + icon_name + "_inactive.png");
        this.status_code = status_code;

        // Khởi tạo không có chữ chỉ có icon
        setText(null);
        setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));

        // load icon vào
        setIcon(new ImageIcon(imgInactiveURL));

        setHorizontalAlignment(SwingConstants.CENTER);
        setForeground(Color.white);

        // thêm event listener
        addMouseListener(this);
        allTabs[number_of_tabs++] = this;
        if (number_of_tabs == 1) {
            allTabs[0].setText(content);
            allTabs[0].setIcon(new ImageIcon(imgURL));
            mainTextField.setHint(1);
        }
    }

    public static void setMainTextField(DecoratedTextField main) {
        mainTextField = main;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // xóa chữ của tất cả các tab
        // và chuyển ảnh về màu xám tượng trưng cho không active
        for (Tab tab : allTabs) {
            tab.setText(null);
            tab.setIcon(new ImageIcon(tab.imgInactiveURL));
        }
        // set lại màu và chữ cho tab đang active
        setIcon(new ImageIcon(imgURL));
        setText(content);
        mainTextField.setHint(status_code);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    // đổi chuột thành hình bàn tay
    @Override
    public void mouseEntered(MouseEvent e) {
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    // đổi chuột về lại hình ban đầu
    @Override
    public void mouseExited(MouseEvent e) {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
}
