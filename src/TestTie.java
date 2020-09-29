public class TestTie {
    public static void main(String[] args) {
        int dem = 0;
        DatabyTrie T = new DatabyTrie();
        T.insert();
        String word = "a";
        TrieNode start = T.getTrie().search_node(word) ;
        String []getdata = {};
        String data = T.getTrie().findAllMatch(start,word);
        //for (int i =0 ;i<data.length;i++)
            //System.out.println(data[i]);
        //System.out.println(data);
        String []inputdata = T.getdata(word);
        for (int i =0 ;i<inputdata.length;i++)
        System.out.println(inputdata[i]);
    }
}
