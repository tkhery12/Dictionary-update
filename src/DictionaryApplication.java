import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DictionaryApplication {
    public void runApplication() {
        JFrame frame = new JFrame("Dictionary");
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel left = leftContainer();

        JPanel right = rightContainer();

        frame.setSize(600, 550);
        frame.setPreferredSize(new Dimension(600, 550));

        frame.getContentPane().add(left, BorderLayout.WEST);
        frame.getContentPane().add(right, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JPanel rightContainer() {
        JPanel right = new JPanel();
        right.setLayout(new BorderLayout());

        JPanel header = definitionHeader();

        JPanel definition = new JPanel();
        definition.setBackground(Color.white);

        right.add(header, BorderLayout.NORTH);
        return right;
    }

    private JPanel definitionHeader() {
        JPanel header = new JPanel();
        header.setPreferredSize(new Dimension(250, 54));
        header.setBackground(Color.decode("#0a4a82"));
        header.setLayout(new BorderLayout());
        header.setBorder(new EmptyBorder(0, 10, 5, 0));

        JLabel headerContent = new JLabel("Định nghĩa");
        headerContent.setIcon(new ImageIcon(getClass().getResource("icons/list.png")));

        headerContent.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        headerContent.setForeground(Color.decode("#d4d4d4"));

        header.add(headerContent, BorderLayout.PAGE_END);

        return header;
    }

    private JPanel leftContainer() {
        JPanel left = new JPanel();
        left.setPreferredSize(new Dimension(250, 550));
        left.setLayout(new BorderLayout());

        // text field chính
        DecoratedTextField mainTextField = new DecoratedTextField("search");
        mainTextField.setPreferredSize(new Dimension(230, 30));

        JPanel tool_box = toolBox(mainTextField);
        JPanel search_box = searchBox(mainTextField);
        JScrollPane word_list = wordList();

        left.add(tool_box, BorderLayout.NORTH);
        left.add(search_box, BorderLayout.CENTER);
        left.add(word_list, BorderLayout.SOUTH);

        return left;
    }

    private JPanel header() {
        JPanel header = new JPanel();
        header.setPreferredSize(new Dimension(250, 25));
        header.setBackground(Color.decode("#053259"));

        // tạo tên app từ điển
        JLabel dictionary = new JLabel("Từ điển");
        dictionary.setForeground(Color.decode("#b5b5b5"));

        JLabel name = new JLabel("Anh - Việt");
        name.setForeground(Color.white);

        header.add(dictionary);
        header.add(name);

        return header;
    }

    private JPanel allTabs(DecoratedTextField mainTextField) {
        JPanel allTabs = new JPanel();
        allTabs.setBackground(Color.decode("#053259"));
        Tab.setMainTextField(mainTextField);

        // tạo các tab điều khiển
        Tab searchTab = new Tab("Tìm kiếm", "search_right", 1);
        searchTab.setToolTipText("Tìm từ");
        allTabs.add(searchTab);

        Tab addTab = new Tab("Thêm", "add", 2);
        addTab.setToolTipText("Thêm từ");
        allTabs.add(addTab);

        Tab removeTab = new Tab("Xóa", "trash", 3);
        removeTab.setToolTipText("Xóa từ");
        allTabs.add(removeTab);

        Tab editTab = new Tab("Chỉnh sửa", "edit", 4);
        editTab.setToolTipText("Sửa từ");
        allTabs.add(editTab);

        return allTabs;
    }

    private JPanel toolBox(DecoratedTextField mainTextField) {
        // tạo tool box và kích thước tool box
        JPanel tool_box = new JPanel();
        tool_box.setPreferredSize(new Dimension(250, 54));
        tool_box.setLayout(new BorderLayout());

        // thêm tên và các tab điều khiển vào tool box
        tool_box.add(header(), BorderLayout.NORTH);
        tool_box.add(allTabs(mainTextField), BorderLayout.CENTER);

        return tool_box;
    }

    private JPanel searchBox(DecoratedTextField mainTextField) {
        JPanel search_box = new JPanel();
        search_box.setLayout(new GridBagLayout());
        search_box.setBackground(Color.decode("#e3e3e3"));

        search_box.add(mainTextField);

        return search_box;
    }

    private JScrollPane wordList() {
            DatabyTrie T = new DatabyTrie();
            T.insert();
            String week[]= T.getdata("a");
            Wordlist list = new Wordlist(week);


        CustomScrollPane scrollPane = new CustomScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(250, 424));
        scrollPane.setBorder(null);



        //scrollPane.setVerticalScrollBar();

            //return word_list;
            return scrollPane;
    }

    public static void main(String[] args) {
        DictionaryApplication app = new DictionaryApplication();
        app.runApplication();
    }
}
