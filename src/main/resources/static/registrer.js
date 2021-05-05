$(() => {
    genererMerker(); //generer merker fra server, og genrerer da også typer

/*    $("#personNr").on("change", () => {
        validering.personNr();
    });*/

    //venter på registrerings knappen
    $("#registrer").click(() => {
        $.get("/mVogn/sjekkBruker", bool => {
            if (bool) {
                const bilObj = {
                    personNr: $("#personNr").val(),
                    navn: $("#navn").val(),
                    adresse: $("#adresse").val(),
                    regNr: $("#regNr").val(),
                    merke: $("#merke").val(),
                    biltype: $("#biltype").val()
                };

                if (nullfeil()) {
                    $.post("/mVogn/save", bilObj, () => {
                        window.location.href = "/"; // tar deg tilbake til index .html
                    })
                        .fail(function (jqXHR) {
                            const json = $.parseJSON(jqXHR.responseText);
                            console.log(json.message);
                            $("#feil").html(json.message);
                        });
                }
            } else {
                $("#feil").html("Er ikke logget inn");
            }
        });
    });
});

function genererMerker() {
    $.get("/mVogn/getBiler", function (biler) {
        let utMerker = ""
        utMerker += "<option>Velg merke</option>";
        let gammelMerke = "";
        let merkeArray = []

        for (const b of biler) {
            if (gammelMerke !== b.merke) {
                merkeArray.push(b.merke);
            }
            gammelMerke = b.merke;
        }

        for (const m of merkeArray) {
            utMerker += "<option>" + m + "</option>";
        }

        $("#merke").html(utMerker)

        hentTyper();
    })
        .fail(function (jqXHR) {
            const json = $.parseJSON(jqXHR.responseText);
            console.log(json.message);
            $("#feil").html(json.message);
        });
}

/*
function typeChange() {
    validerType()
    hentTyper();
}
*/

function hentTyper() {
    $.get("/mVogn/getBiler", function (biler) {
        let valgtMerke = $("#merke").val();
        let utTyper = "";
        console.log(valgtMerke);

        if (valgtMerke === "Velg merke") {
            utTyper += "<option>Velg merke for å se typer</option>";
        } else {
            utTyper += "<option>Velg type</option>";
            for (const b of biler) {
                if (valgtMerke === b.merke) {
                    utTyper += "<option>" + b.modell + "</option>";
                }
            }
        }

        $("#biltype").html(utTyper);
    })
        .fail(function (jqXHR) {
            const json = $.parseJSON(jqXHR.responseText);
            console.log(json.message);
            $("#feil").html(json.message);
        });

}