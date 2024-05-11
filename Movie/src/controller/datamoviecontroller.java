/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.util.List;
import DAOdatamovie.datamovieDAO;
import DAOImplement.datamovieimplement;
import javax.swing.JOptionPane;
import model.*;
import view.MainView;
/**
 *
 * @author ACER
 */
public class datamoviecontroller {
    MainView frame;
    datamovieimplement impldatamovie;
    List<datamovie> dm;

    public datamoviecontroller(MainView frame) {
        this.frame = frame;
        impldatamovie = new datamovieDAO();
        dm = impldatamovie.getAll();
    }
    public void isitabel(){
        dm = impldatamovie.getAll();
        modeltabeldatamovie mv = new modeltabeldatamovie(dm);
        frame.getTabelMovie().setModel(mv);
    }
    
    public void insert(){
        datamovie dm = new datamovie();
        dm.setJudul(frame.getJtextjudul().getText());
        dm.setAlur(Double.parseDouble(frame.getJtextalur().getText()));
        dm.setPenokohan(Double.parseDouble(frame.getJtextpenokohan().getText()));
        dm.setAkting(Double.parseDouble(frame.getJtextakting().getText()));
        dm.setNilai(dm.hitungNilai());
        impldatamovie.insert(dm);
        isitabel();
        JOptionPane.showMessageDialog(null, "Data movie berhasil ditambahkan!");
    }
    
    public void update(){
        datamovie dm = new datamovie();
        dm.setJudul(frame.getJtextjudul().getText());
        dm.setAlur(Double.parseDouble(frame.getJtextalur().getText()));
        dm.setPenokohan(Double.parseDouble(frame.getJtextpenokohan().getText()));
        dm.setAkting(Double.parseDouble(frame.getJtextakting().getText()));
        dm.setNilai(dm.hitungNilai());
        impldatamovie.update(dm);
        isitabel();
        JOptionPane.showMessageDialog(null, "Data movie berhasil diupdate!");
    }
    
    public void delete(){
        String judul = frame.getJtextjudul().getText();
        impldatamovie.delete(judul);
        isitabel();
        JOptionPane.showMessageDialog(null, "Data movie berhasil dihapus!");
    }
}