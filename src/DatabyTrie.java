import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// đang test //
public class DatabyTrie {
    private TrieDataStructure T = new TrieDataStructure();
    public void insert() {
        int dem=0;
        Connection connection = null;
        try {
            // tạo connection với database
            String jdbc;
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\HTCOM\\Downloads\\DictionarySSSS\\Dictionary\\src\\data.db");
            Statement statement = connection.createStatement();

            // lệnh insert vào database.
            //statement.executeUpdate("insert into av values(1, 'leo')");

            // lấy kết quả ra từ database và loop rồi in
            ResultSet rs = statement.executeQuery("select * from av");

            while (rs.next()) {
                // read the result set
                //System.out.println(dem);
                dem=dem+1;
                T.insert(rs.getString("word"));
                //System.out.println("word = " + rs.getString("word"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }
    public  String [] getdata (String word) {
        String ddd = T.findAllMatch(T.search_node(word),word) ;
        if(T.search(word)){
            ddd=word+"\n"+ddd;
        }
        String []a = ddd.split("\n");
        for(int k =0;k<a.length;k++)
            System.out.println(a[k]);
        return ddd.split("\n") ;
    }
public  TrieDataStructure getTrie() {
        this.insert();
        return T;
}
}
