public class TrieDataStructure {
    private final TrieNode root = new TrieNode(' ');

    public void insert(String word) {
        if (!search(word)) {
            TrieNode current = this.root;
            char[] word_char_arr = word.toCharArray();

            for (char ch : word_char_arr) {
                TrieNode child = current.getChild(ch);
                if (child != null) {
                    current = child;
                } else {
                    current.childList.add(new TrieNode(ch));
                    current = current.getChild(ch);
                }
                ++current.count;
            }
            current.isEnd = true;
        }
    }

    public boolean search(String word) {
        TrieNode current = this.root;
        char[] word_char_arr = word.toCharArray();

        for (char ch : word_char_arr) {
            if (current.getChild(ch) == null) {
                return false;
            }

            current = current.getChild(ch);
        }

        return current.isEnd;
    }

    public TrieNode search_node(String word) {
        TrieNode current = this.root;
        char[] word_char_arr = word.toCharArray();

        for (char ch : word_char_arr) {
            if (current.getChild(ch) == null) {
                return null;
            }

            current = current.getChild(ch);
        }

        return current;
    }

    public void remove(String word) {
        if (!this.search(word)) {
            System.out.println(word + " does not present in trie");
        } else {
            TrieNode current = this.root;
            char[] word_char_arr = word.toCharArray();

            for (char ch : word_char_arr) {
                TrieNode child = current.getChild(ch);
                if (child.count == 1) {
                    current.childList.remove(child);
                    return;
                }

                --child.count;
                current = child;
            }

            current.isEnd = false;
        }
    }

    public String cc = " ";

    // đổi tên hàm cho phù hợp mục đích
    public String findAllMatch(TrieNode root, String s) {
        if (root == null) {
            return " ";
        }
        if (root.childList != null && root.childList.size() != 0) {

            for (TrieNode node : root.childList) {
                s = s + node.data;
                findAllMatch(node, s);
                if (node.isEnd) {
                    cc = cc + s + "\n";

                     System.out.print(s+"\n");

                }
                s = s.substring(0, s.length() - 1);
            }
        }
        return cc;
    }
}