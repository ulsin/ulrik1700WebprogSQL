const validerPersonNr = () => {
    const regexp = /^[0-9]{11}$/;
    const test = regexp.test($("#personNr").val());
    if (!test) {
        $("#personNrError").html("Navnet må bestå av 11 tall");
        return false;
    } else {
        $("#personNrError").html("");
        return true;
    }
}

function validerNavn() {
    const regexp = /^[a-zA-ZæøåÆØÅ. \-]{2,20}$/;
    const test = regexp.test($("#navn").val());
    if (!test) {
        $("#navnError").html("Navnet må bestå av 2 - 20 bokstaver");
        return false;
    } else {
        $("#navnError").html("");
        return true;
    }
}

function validerAdresse() {
    const regexp = /^[0-9a-zA-ZæøåÆØÅ. \-]{2,30}$/;
    const test = regexp.test($("#adresse").val());
    if (!test) {
        $("#adresseError").html("Adressen må være mellom 2 og 30 Tegn");
        return false;
    } else {
        $("#adresseError").html("");
        return true;
    }
}

function validerRegNr() {
    const regexp = /^[A-Z][A-Z][0-9]{5}$/;
    const test = regexp.test($("#regNr").val());
    if (!test) {
        $("#regNrError").html("Reg må Ha 2 bokstaver og 5 tall");
        return false;
    } else {
        $("#regNrError").html("");
        return true;
    }
}

function validerMerke() {
    console.log("Validerer merke")
    const valg = $("#merke").val();
    if (valg === "Velg merke") {
        $("#merkeError").html("Velg et merke!");
        return false;
    } else {
        $("#merkeError").html("");
        return true;
    }
}

function validerType() {
    console.log("Validerer type")
    const valg = $("#biltype").val();
    if (valg === "Velg merke for å se typer" || valg === "Velg type") {
        $("#typeError").html("Velg en type!");
        return false;
    } else {
        $("#typeError").html("");
        return true;
    }
}

function validerBrukerNavn() {
    const regexp = /^[a-zA-ZæøåÆØÅ. \-]{2,20}$/;
    if (!regexp.test($("#brukerNavn").val())) {
        $("#brukerError").html("Brukernavn må være 2 - 20 bokstaver");
        return false;
    } else {
        $("#brukerError").html("");
        return true;
    }
}

function validerPassord() {
    // const regexp = /^[a-zA-ZæøåÆØÅ. \-]{2,20}$/;
    //TODO finn ut hvorfor denne ikke funker
    // gøy ny kommentar
    const regexp = /^(?=.*[A-ZÆØÅa-zøæå])(?=.*\d)[A-ZØÆÅa-zøæå\d]{8,}$/; //må ha tall!!!!! (og over 8)
    if (!regexp.test($("#passord").val())) {
        $("#passordError").html("Passord må være minst 8 tegn");
        return false;
    } else {
        $("#passordError").html("");
        return true;
    }
}

function nullfeil() {
    const test = (validerPersonNr() && validerNavn() && validerAdresse() && validerRegNr() && validerMerke() && validerType())
    console.log(test);

    if (!test) {
        $("#regError").html("Feil i input bokser")
    }
    return test;
}

function nullFeilLogin() {
    const test = (validerBrukerNavn() && validerPassord());
    console.log(test)
    if (!test) {
        $("#regError").html("Feil i input bokser")
    }
    return test;
}

function skjekkBruker() {
    console.log("Skjekker bruker");
    let holder = false;
        $.post("/mVogn/sjekkBruker", function () {
            holder = true;
        })
            .fail(function (jqXHR) {
                const json = $.parseJSON(jqXHR.responseText);
                console.log(json.message);
                $("#feil").html(json.message);
            });
    console.log(holder);
    return holder;
}
