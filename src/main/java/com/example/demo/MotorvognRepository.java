package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

// klasse som holder metoder som kan snakke med database, og som kan kalles på i controlleren
@Repository
public class MotorvognRepository {

    @Autowired
    private JdbcTemplate db;

    public void save(Motorvogn vogn) {
        String sql = "insert into Motorvogner(personNr, navn, adresse, regNr, merke, biltype) " +
                "values(?, ?, ?, ?, ?, ?)";
        db.update(sql, vogn.getPersonNr(), vogn.getNavn(), vogn.getAdresse(), vogn.getRegNr(), vogn.getMerke(), vogn.getBiltype());
    }

    public void slettAlle(Motorvogn vogn) {
        String sql = "delete from Motorvogner";
        db.update(sql);
    }

    public void slettEn(String reg) {
        String sql = "delete from Motorvogner where regNr = ?";
        db.update(sql, reg);
    }

    public List<Motorvogn> getVogner() {
        String sql = "select * from Motorvogner";
        return db.query(sql, new BeanPropertyRowMapper<>(Motorvogn.class));
    }

    public Motorvogn getEnVogn(String reg) {
        System.out.println("Er i Repo");
        System.out.println(reg);
        String sql = "select * from Motorvogner where regNr = ?";
        return db.queryForObject(sql, BeanPropertyRowMapper.newInstance(Motorvogn.class), reg);
    }

    public void updateVogn(Motorvogn vogn) {
        String sql = "update Motorvogner set personNr=?, navn=?, adresse=?, merke=?, biltype=? where regNr = ?";
        db.update(sql, vogn.getPersonNr(), vogn.getNavn(), vogn.getAdresse(), vogn.getMerke(), vogn.getBiltype(), vogn.getRegNr());
    }

    // Biler tabellen
    public List<Bil> getBiler() {
        String sql = "select * from Biler order by merke, modell";
//        String sql = "select * from Biler";
        return db.query(sql, new BeanPropertyRowMapper<>(Bil.class));
    }

//    public List<Bil> getMerker() {
//        String sql = "select merke from Biler";
//        List<Bil> vognList = db.query(sql, new BeanPropertyRowMapper<>(Bil.class));
//        return vognList;
//    }

}
