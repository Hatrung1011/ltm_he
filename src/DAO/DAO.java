package DAO;

import Model.Stationery;
import Model.StationeryType;

import java.sql.*;
import java.util.ArrayList;

public class DAO {

    private Connection connection;
    private Statement statement;

    public DAO() {
        try {
            String url = "jdbc:postgresql://dpg-cj4d8htgkuvsl08em5pg-a.singapore-postgres.render.com/stationery";
            String user = "admin";
            String password = "5dilfUG6cYHT9qNznJaI3KcaczSeW5vd";
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url, user, password);
            this.statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addStationeryType(StationeryType type) {
        StringBuilder sql = new StringBuilder(" INSERT INTO stationery_type VALUES( ");
        sql.append(type.getId()).append(",");
        sql.append("'" + type.getName() + "', ");
        sql.append("'" + type.getDescription() + "')");
        try {
            this.statement.execute(String.valueOf(sql));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<StationeryType> searchType(String type) {
        StringBuilder sql = new StringBuilder("SELECT * FROM stationery_type");
        sql.append(" WHERE stationery_type.name LIKE '%");
        sql.append(type);
        sql.append("%'");
        ResultSet resultSet;
        try {
            resultSet = this.statement.executeQuery(String.valueOf(sql));
            ArrayList<StationeryType> stationeryTypes = new ArrayList<StationeryType>();

            while (resultSet.next()) {
                StationeryType stationeryType = new StationeryType(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"));
                stationeryTypes.add(stationeryType);
            }

            return stationeryTypes;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateType(StationeryType type) {
        StringBuilder sql = new StringBuilder("UPDATE stationery_type SET ");
        sql.append(" name = '" + type.getName() + "',");
        sql.append(" description = '" + type.getDescription() + "'");
        sql.append(" WHERE id = " + type.getId());
        try {
            this.statement.execute(String.valueOf(sql));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteType(int id) {
        StringBuilder sql = new StringBuilder("DELETE FROM stationery_type WHERE id = '");
        sql.append(id);
        sql.append("'");
        try {
            this.statement.execute(String.valueOf(sql));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Stationery> getAllStationery() {
        StringBuilder sql = new StringBuilder("SELECT * FROM stationery");
        ResultSet result;
        try {
            result = this.statement.executeQuery(String.valueOf(sql));
            ArrayList<Stationery> lst = new ArrayList<>();

            while (result.next()) {
                Stationery stationery = new Stationery(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("type"),
                        result.getInt("age"),
                        result.getString("img"),
                        result.getString("price")
                );
                lst.add(stationery);
            }

            return lst;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Stationery> getStationeryByName(String name) {
        StringBuilder sql = new StringBuilder("SELECT * FROM stationery WHERE");
        sql.append(" name LIKE '%");
        sql.append(name);
        sql.append("%'");
        ResultSet result;
        try {
            result = this.statement.executeQuery(String.valueOf(sql));
            ArrayList<Stationery> lst = new ArrayList<>();

            while (result.next()) {
                Stationery stationery = new Stationery(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("type"),
                        result.getInt("age"),
                        result.getString("img"),
                        result.getString("price")
                );
                lst.add(stationery);
            }

            return lst;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Stationery> getStationeryByType(String type) {
        StringBuilder sql = new StringBuilder("SELECT * FROM stationery WHERE");
        sql.append(" type LIKE '%");
        sql.append(type);
        sql.append("%'");
        ResultSet result;
        try {
            result = this.statement.executeQuery(String.valueOf(sql));
            ArrayList<Stationery> lst = new ArrayList<>();

            while (result.next()) {
                Stationery stationery = new Stationery(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("type"),
                        result.getInt("age"),
                        result.getString("img"),
                        result.getString("price")
                );
                lst.add(stationery);
            }

            return lst;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Stationery> getStationeryByPrice(String price) {
        StringBuilder sql = new StringBuilder("SELECT * FROM stationery WHERE");
        sql.append(" price = '");
        sql.append(price);
        sql.append("'");
        ResultSet result;
        try {
            result = this.statement.executeQuery(String.valueOf(sql));
            ArrayList<Stationery> lst = new ArrayList<>();

            while (result.next()) {
                Stationery stationery = new Stationery(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("type"),
                        result.getInt("age"),
                        result.getString("img"),
                        result.getString("price")
                );
                lst.add(stationery);
            }

            return lst;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateStationery(Stationery stationery) {
        StringBuilder sql = new StringBuilder("UPDATE stationery SET ");
        sql.append(" name = '" + stationery.getName() + "',");
        sql.append(" type = '" + stationery.getType() + "',");
        sql.append(" age = " + stationery.getAge() + ",");
        sql.append(" img = '" + stationery.getImg() + "',");
        sql.append(" price = " + stationery.getPrice());
        sql.append(" WHERE id = " + stationery.getId());
        try {
            this.statement.execute(String.valueOf(sql));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStationery(int id) {
        StringBuilder sql = new StringBuilder("DELETE FROM stationery WHERE id = '");
        sql.append(id);
        sql.append("'");
        try {
            this.statement.execute(String.valueOf(sql));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (Stationery a : new DAO().getAllStationery()) {
            System.out.println(a);
        }
        ;
        for (StationeryType a : new DAO().getAllType()) {
            System.out.println(a);
        }
        ;
    }

    public ArrayList<StationeryType> getAllType() {
        StringBuilder sql = new StringBuilder("SELECT * FROM stationery_type");
        ResultSet result;
        try {
            result = this.statement.executeQuery(String.valueOf(sql));
            ArrayList<StationeryType> lst = new ArrayList<>();

            while (result.next()) {
                StationeryType type = new StationeryType(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("description")
                );
                lst.add(type);
            }

            return lst;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addStationery(Stationery stationery) {
        StringBuilder sql = new StringBuilder("INSERT INTO stationery VALUES( ");
        sql.append(stationery.getId()).append(",");
        sql.append("'" + stationery.getName() + "', ");
        sql.append("'" + stationery.getType() + "', ");
        sql.append(stationery.getAge() + ", ");
        sql.append("'" + stationery.getImg() + "', ");
        sql.append(stationery.getPrice() + ")");
        System.out.println(sql);
        try {
            this.statement.executeUpdate(String.valueOf(sql));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
