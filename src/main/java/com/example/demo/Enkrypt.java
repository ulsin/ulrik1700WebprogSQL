package com.example.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Enkrypt {

    //TODO spør om dette
    // har det her fordi vi trenger å ha samme bcrypt objekt tror jeg, vet ikke hvorfor vi ikke bare bruker metodene
    // endte opp med å ikke bruke denne i det hele tatt heheh
    BCryptPasswordEncoder cryptObj = new BCryptPasswordEncoder(5); // styrke 5 fordi oy vey

    public boolean passCheck(String passord, String hashPassord) {
        return cryptObj.matches(passord, hashPassord);
    }

    public String passEncrypt(String passord) {
        return cryptObj.encode(passord);
    }
}
