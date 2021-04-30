package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

// this is like the file that all the get and post requests from the Javascript ends up in.

@RestController
@RequestMapping("mVogn")
public class MotorvognController {

    @Autowired
    MotorvognRepository repo;

//    private final List<Motorvogn> alleMotorvogner = new ArrayList<>();
//    private final List<Bil> biler = new ArrayList<>();

    @PostMapping("/save")
    public void save(Motorvogn vogn) {
        repo.save(vogn);
    }

    @PostMapping("/slettAlle")
    public void slettAlle(Motorvogn vogn) {
        repo.slettAlle(vogn);
    }

    @GetMapping("/slettEn")
    public void slettEn(String reg) {
        repo.slettEn(reg);
    }

    @GetMapping("/getMotorVogner")
    public List<Motorvogn> getAlleMotorvogner() {
        return repo.getVogner();
    }

    @GetMapping("getEnVogn")
    public Motorvogn getEnVogn(String reg) {
        System.out.println("Er i Controller");
        System.out.println(reg);
        return repo.getEnVogn(reg);
    }

    @PostMapping("/endreEnVogn")
    public void endreEn(Motorvogn vogn) {
        repo.updateVogn(vogn);
    }

    @GetMapping("/getBiler")
    public List<Bil> getBiler() {
        return repo.getBiler();
    }

}
