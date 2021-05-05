package com.example.demo;

import org.slf4j.Logger;

public class Validering {
    public static boolean validerVogn(Motorvogn vogn, Logger logger) {
        String regexPersonNr = "[0-9]{11}";
        String regexNavn = "[a-zA-ZæøåÆØÅ .\\-]{2,20}";
        String regexAdresse = "[0-9a-zA-ZæøåÆØÅ ,.\\-]{2,30}";
        String regexKjennetegn = "[A-Z][A-Z][0-9]{5}";
        String regexMerke = "[a-zA-ZæøåÆØÅ.\\-]{2,10}";
        String regexType = "[0-9a-zA-ZæøåÆØÅ.\\-]{2,10}";

        boolean personNrMatch = vogn.getPersonNr().matches(regexPersonNr);
        boolean navnMatch = vogn.getNavn().matches(regexNavn);
        boolean adresseMatch = vogn.getAdresse().matches(regexAdresse);
        boolean kjennetegnMatch = vogn.getRegNr().matches(regexKjennetegn);
        boolean merkeMatch = vogn.getMerke().matches(regexMerke);
        boolean typeMatch = vogn.getBiltype().matches(regexType);

        if (personNrMatch && navnMatch && adresseMatch && kjennetegnMatch && merkeMatch && typeMatch) {
            return true;
        }else {
            logger.error("Valideringsfeil i controller");
            return false;
        }
    }
}
