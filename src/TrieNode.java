import java.util.*;

class TrieNode {
    char data;
    boolean isEnd;// check xem node cuoi cua word chua
    int count;
    LinkedList<TrieNode> childList;

    /* Constructor */
    public TrieNode(char c) {
        childList = new LinkedList<TrieNode>();
        isEnd = false;
        data = c;
        count = 0;
    }

    // lay data node c
    public TrieNode getChild(char c) {
        if (childList != null)
            for (TrieNode eachChild : childList)
                if (eachChild.data == c)
                    return eachChild;
        return null;
    }
}