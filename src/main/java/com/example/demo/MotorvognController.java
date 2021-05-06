package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

// this is the file that all the get and post requests from the Javascript ends up in.

@RestController
@RequestMapping("mVogn")
public class MotorvognController {

    @Autowired
    MotorvognRepository repo;

    @Autowired
    HttpSession session;

    private final Logger logger = LoggerFactory.getLogger(MotorvognController.class);

    @PostMapping("/encrypt")
    public void passEncrypt() {
        repo.passEncrypt();
    }

    @PostMapping("/login")
    public void login(Bruker bruker, HttpServletResponse response) throws IOException {
        if (!repo.login(bruker)) { //her kjøres funksjonen for å teste, så trenger ikke kjøre den separat
            response.sendError(500, "Brukernavn eller passord stemte ikke");
        } else {
            session.setAttribute("innLogget", bruker);
        }
    }

    @PostMapping("/logut")
    public void logUt() {
        session.removeAttribute("innLogget");
    }

    @PostMapping("/sjekkBruker")
    public void skjekkBrukerPost(HttpServletResponse response) throws IOException {
        if (session.getAttribute("innLogget") == null) {
            response.sendError(500, "Er ikke innlogget");
        }
    }

    @GetMapping("/sjekkBruker")
    public boolean skjekkBruker() {
        return session.getAttribute("innLogget") != null;
    }

    //TODO spør om dette??
    // Vet ikke helt hvor response argumentet kommer ifra, men er vel noe Spring gjør eller noe
    // Svar: Spring gjør mye
    @PostMapping("/save")
    public void save(Motorvogn vogn, HttpServletResponse response) throws IOException {

        if (Validering.validerVogn(vogn, logger)) {
            if (!repo.save(vogn)) {
                // kunne bare skrevet 500 som første input
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i save controller");
            }
        } // trenger ikke else siden validerVogn sender error selv via logger objektet som settes inn

    }

    @PostMapping("/slettAlle")
    public void slettAlle(HttpServletResponse response) throws IOException {
        if (!repo.slettAlle()) {
            response.sendError(500, "Feil i slettAlle controller");
        }
    }

    @PostMapping("/endreEnVogn")
    public void endreEn(Motorvogn vogn, HttpServletResponse response) throws IOException {
        if (Validering.validerVogn(vogn, logger)) {
            if (!repo.updateVogn(vogn)) {
                response.sendError(500,"Feil Controller endreEnVogn");
            }
        }
    }

    @GetMapping("/slettEn")
    public void slettEn(String reg, HttpServletResponse response) throws IOException {
        if (!repo.slettEn(reg)) {
            response.sendError(500, "Feil i slettEn controller");
        }
    }

    @GetMapping("/getMotorVogner")
    public List<Motorvogn> getAlleMotorvogner(HttpServletResponse response) throws IOException {
        List<Motorvogn> vList = repo.getVogner();
        if (vList == null) {
            response.sendError(500, "Feil i getMotorVogner Controller");
        }
        return vList;
    }

    @GetMapping("getEnVogn")
    public Motorvogn getEnVogn(String reg, HttpServletResponse response) throws IOException {
        System.out.println("Er i Controller");
        System.out.println(reg);
        Motorvogn vogn = repo.getEnVogn(reg);
        if (vogn == null) {
            response.sendError(500, "Feil i Controller getEnVogn");
        }
        return vogn;
    }

    @GetMapping("/getBiler")
    public List<Bil> getBiler(HttpServletResponse response) throws IOException {
        List<Bil> bList = repo.getBiler();
        if (bList == null) {
            response.sendError(500, "Feil i controller getBiler");
        }
        return bList;
    }

}
