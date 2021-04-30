$(() => {
    genererMerker(); //generer merker fra server, og genrerer da også typer

    //venter på registrerings knappen
    $("#registrer").click(() => {
        const bilObj = {
            personNr: $("#personNr").val(),
            navn: $("#navn").val(),
            adresse: $("#adresse").val(),
            regNr: $("#regNr").val(),
            merke: $("#merke").val(),
            biltype: $("#biltype").val()
        }

        $.post("/mVogn/save", bilObj, () => {
            window.location.href = "/"; // tar deg tilbake til index .html
        });
    });
});

function genererMerker() {
    $.get("/mVogn/getBiler", function (biler) {
        let utMerker = ""
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
    });
}

function hentTyper() {
    $.get("/mVogn/getBiler", function (biler) {
        let valgtMerke = $("#merke").val();
        let utTyper = "";
        console.log(valgtMerke);

        for (const b of biler) {
            if (valgtMerke === b.merke) {
                utTyper += "<option>" + b.modell + "</option>";

            }
        }

        $("#biltype").html(utTyper);
    });
}