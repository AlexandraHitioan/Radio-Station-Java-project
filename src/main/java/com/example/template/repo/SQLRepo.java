package com.example.template.repo;

import com.example.template.domain.Melodie;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;

public class SQLRepo {
    private ArrayList<Melodie> data;
    private String dbLocation;
    private Connection conn = null;

    public SQLRepo(String dbLocation) {
        data = new ArrayList<Melodie>();
        this.dbLocation = "jdbc:sqlite:" + dbLocation;
        initDbConnection();
        createSchema();
    }

    private void initDbConnection() {
        try {
            // with DataSource
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(dbLocation);
            if (conn == null || conn.isClosed())
                conn = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void createSchema() {
        try {
            try (final Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS melodii(id int, formatie varchar(50), titlu varchar(50), gen varchar(50), durata varchar(50));");
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS playlist(id int, formatie varchar(50), titlu varchar(50), gen varchar(50), durata varchar(50));");
               // System.out.println("o facut tabelu");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Melodie> getAll() {
        ArrayList<Melodie> products = new ArrayList<>();
        try {
            try (PreparedStatement statement = conn.prepareStatement("SELECT * from melodii"); ResultSet rs = statement.executeQuery();) {
                while (rs.next()) {
                    Melodie m  = new Melodie(rs.getInt("id"), rs.getString("formatie"), rs.getString("titlu"), rs.getString("gen"), rs.getString("durata")); //atributes of Cake class
                    products.add(m);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return products;
    }

    //functia asta ne insereaza O LISTA DE CHESTII in tabel
//    private void insertData(ArrayList<Melodie> newData) throws SQLException {
//        // Assuming T is Cake, adjust this part based on your actual T type
//        try (PreparedStatement statement = conn.prepareStatement("INSERT INTO products VALUES (?, ?,?,?,?)")) {
//            for (Product item : newData) {
//                if (item instanceof Product) {
//                    statement.setInt(1, item.getId());
//                    statement.setString(2, item.getMarca());
//                    statement.setString(3, item.getNume());
//                    statement.setInt(4, item.getPret());
//                    statement.setInt(5, item.getCantitate());
//                    statement.executeUpdate();
//                }
//            }
//        }
//    }

    //asta ne insereaza numa un obiect at a time in tabel
    public void add(Melodie m) {
        try {

            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO melodii VALUES (?, ?,?,?,?)")) {
                statement.setInt(1, m.getId());
                statement.setString(2, m.getFormatie());
                statement.setString(3, m.getTitlu());
                statement.setString(4, m.getGen());
                statement.setString(5, m.getDurata());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


//    public int findById(int id) {
//        try {
//            try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM products WHERE id = ?")) {
//                statement.setInt(1, id);
//                try (ResultSet resultSet = statement.executeQuery()) {
//                    if (resultSet.next()) {
//                        return resultSet.getInt("id");
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return -1; // Return null if the cake with the specified ID is not found
//    }
}


