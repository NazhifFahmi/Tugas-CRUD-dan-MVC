/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOdatamovie;
import java.sql.*;
import java.util.*;
import koneksi.connector;
import model.*;
import DAOImplement.datamovieimplement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ACER
 */
public class datamovieDAO implements datamovieimplement{
    Connection connection;
    
    final String select = "SELECT * FROM movie";
    final String insert = "INSERT INTO `movie`(`judul`, `alur`, `penokohan`, `akting`, `nilai`) VALUES (?, ?, ?, ?, ?)";
    final String update = "update movie set alur=?, penokohan=?, akting=?, nilai=? where judul=?";
    final String delete = "delete from movie where judul=?";
    public datamovieDAO() {
        connection = connector.connection();
    } 

    @Override
    public void insert(datamovie m) {
       PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, m.getJudul());
            statement.setDouble(2, m.getAlur());
            statement.setDouble(3, m.getPenokohan());
            statement.setDouble(4, m.getAkting());
            statement.setDouble(5, m.hitungNilai());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while(rs.next()){
                m.setJudul(rs.getString(1));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void update(datamovie m) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(update);
            statement.setDouble(1, m.getAlur());
            statement.setDouble(2, m.getPenokohan());
            statement.setDouble(3, m.getAkting());
            statement.setDouble(4, m.hitungNilai());
            statement.setString(5, m.getJudul());
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String judul) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(delete);
            statement.setString(1, judul);
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<datamovie> getAll() {
        List<datamovie> dm = null;
        try{
            dm = new ArrayList<datamovie>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()){
                datamovie mv = new datamovie();
                mv.setJudul(rs.getString("Judul"));
                mv.setAlur(rs.getDouble("Alur"));
                mv.setPenokohan(rs.getDouble("Penokohan"));
                mv.setAkting(rs.getDouble("Akting"));
                mv.setNilai(rs.getDouble("Nilai"));
                dm.add(mv);
            }
        }catch(SQLException ex){
            Logger.getLogger(datamovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dm;
    }
    
}
