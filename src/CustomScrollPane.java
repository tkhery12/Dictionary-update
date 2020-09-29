import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class CustomScrollPane extends JScrollPane {
    public CustomScrollPane(JList<String> list) {
        super(list);
        // đặt scrollbar lên trên viewport
        setComponentZOrder(getVerticalScrollBar(), 0);
        setComponentZOrder(getViewport(), 1);

        // không vẽ nền của scrollbar
        getVerticalScrollBar().setOpaque(false);

        //
        setLayout(new ScrollPaneLayout() {
            @Override
            public void layoutContainer(Container parent) {
                JScrollPane scrollPane = (JScrollPane) parent;

                Rectangle availR = scrollPane.getBounds();
                availR.x = availR.y = 0;

                // đặt viewport dựa theo padding của scroll pane
                Insets parentInsets = parent.getInsets();
                availR.x = parentInsets.left;
                availR.y = parentInsets.top;
                availR.width -= parentInsets.left + parentInsets.right;
                availR.height -= parentInsets.top + parentInsets.bottom;

                // xác định chỗ vẽ scrollbar
                Rectangle vsbR = new Rectangle();
                vsbR.width = 8;
                vsbR.height = availR.height;
                vsbR.x = availR.x + availR.width - vsbR.width;
                vsbR.y = availR.y;

                // set viewport
                if (viewport != null) {
                    viewport.setBounds(availR);
                }
                // set scroll bar
                if (vsb != null) {
                    vsb.setVisible(true);
                    vsb.setBounds(vsbR);
                }
            }
        });

        // thêm custom ui
        getVerticalScrollBar().setUI(new MyScrollBarUI());
    }

    private static class MyScrollBarUI extends BasicScrollBarUI {
        private final Dimension d = new Dimension();

        // bỏ 2 nút bấm ở 2 đầu
        @Override
        protected JButton createDecreaseButton(int orientation) {
            return new JButton() {
                @Override
                public Dimension getPreferredSize() {
                    return d;
                }
            };
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            return new JButton() {
                @Override
                public Dimension getPreferredSize() {
                    return d;
                }
            };
        }

        // không vẽ track
        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
        }

        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            Color color;
            JScrollBar sb = (JScrollBar) c;
            if (!sb.isEnabled() || r.width > r.height) {
                return;
            } else if (isDragging) {
                color = Color.DARK_GRAY;
            } else if (isThumbRollover()) {
                color = Color.LIGHT_GRAY;
            } else {
                color = Color.GRAY;
            }
            g2.setPaint(color);
            g2.fillRoundRect(r.x, r.y, r.width , r.height*15, 40, 40);
            g2.dispose();
        }

        @Override
        protected void setThumbBounds(int x, int y, int width, int height) {
            super.setThumbBounds(x, y, width, height);
            scrollbar.repaint();
        }
    }
}
