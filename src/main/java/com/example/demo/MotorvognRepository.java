package com.example.demo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(MotorvognRepository.class);

    public boolean save(Motorvogn vogn) {
        String sql = "insert into Motorvogner(personNr, navn, adresse, regNr, merke, biltype) " + "values(?, ?, ?, ?, ?, ?)";
        try {
            db.update(sql, vogn.getPersonNr(), vogn.getNavn(), vogn.getAdresse(), vogn.getRegNr(), vogn.getMerke(), vogn.getBiltype());
            return true;
        } catch (Exception e) {
            logger.error("Feil i repo lagring" + e);
            return false;
        }
    }

    public boolean slettAlle() {
        String sql = "delete from Motorvogner";
//        String sql = "delete from Motorvognerrrrr"; // for error
        try {
            db.update(sql);
            return true;
        } catch (Exception e) {
            logger.error("Feil i repo slett alle" + e);
            return false;
        }
    }

    public boolean slettEn(String reg) {
        String sql = "delete from Motorvogner where regNr = ?";
        //TODO bytt til if statesments basert på om objektet finnes eller ikke, akkurat nå catcher den ikke feil riktig
        try {
            db.update(sql, reg);
            return true;
        } catch (Exception e) {
            logger.error("Feil i repo slett en" + e);
            return false;
        }
    }

    public List<Motorvogn> getVogner() {
        String sql = "select * from Motorvogner";
        try {
            return db.query(sql, new BeanPropertyRowMapper<>(Motorvogn.class));
        } catch (Exception e) {
            logger.error("Feil i repo getVogner" + e);
            return null;
        }
    }

    public Motorvogn getEnVogn(String reg) {
        System.out.println("Er i Repo");
        System.out.println(reg);
        String sql = "select * from Motorvogner where regNr = ?";

        try {
            return db.queryForObject(sql, BeanPropertyRowMapper.newInstance(Motorvogn.class), reg);

        } catch (Exception e) {
            logger.error("Feil i getEnVogn" + e);
            return null;
        }

    }

    public boolean updateVogn(Motorvogn vogn) {
        String sql = "update Motorvogner set personNr=?, navn=?, adresse=?, merke=?, biltype=? where regNr = ?";
        try {
            db.update(sql, vogn.getPersonNr(), vogn.getNavn(), vogn.getAdresse(), vogn.getMerke(), vogn.getBiltype(), vogn.getRegNr());
            return true;
        } catch (Exception e) {
            logger.error("Feil i repo updateVogn" + e);
            return false;
        }
    }

    // Biler tabellen
    public List<Bil> getBiler() {
        String sql = "select * from Biler order by merke, modell";
//        String sql = "select * from Biler";
        try {
            return db.query(sql, new BeanPropertyRowMapper<>(Bil.class));

        } catch (Exception e) {
            logger.error("Feil i repo getBiler" + e);
            return null;
        }
    }

//    public List<Bil> getMerker() {
//        String sql = "select merke from Biler";
//        List<Bil> vognList = db.query(sql, new BeanPropertyRowMapper<>(Bil.class));
//        return vognList;
//    }

}
