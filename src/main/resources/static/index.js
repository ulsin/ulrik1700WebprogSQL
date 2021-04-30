//TODO fix input validation??
$(() => {
    visData() // henter data fra "server" når siden er lastet

    // venter på slett knappen
    $("#slettAlle").click(() => {
        $.post("/mVogn/slettAlle", () => {
            visData();
        }) // ikke semikolon, og ikke dollartegn
        .fail(function (jqXHR) {
            const json = $.parseJSON(jqXHR.responseText);
            console.log(json.message);
            $("#feil").html(json.message);
        });
    });

});

// funksjoner som brukes mer under her
function visData() {
    $.get("/mVogn/getMotorVogner", function (data) {
        formaterData(data);
    });
}

function formaterData(data) {
    let ut = "";
    // if testen gjør at headings slettes om det ikke er noe data å hente
    if (data.length > 0) {
        ut = "<table class='table'><tr><th>PersonNr</th><th>Navn</th><th>Adresse</th><th>RegNr</th><th>Merke</th><th>Biltype</th><th></th><th></th></tr>";
        for (const d of data) {
            ut +=
                "<tr>" +
                    "<td>" + d.personNr + "</td><td>" + d.navn + "</td>" +
                    "<td>" + d.adresse + "</td><td>" + d.regNr + "</td>" +
                    "<td>" + d.merke + "</td><td>" + d.biltype + "</td>" +
                    // "<td><input type='button' onclick='endre( \"" + d.regNr + "\")' class='btn btn-primary' value='Endre'></td>" +
                    "<td><a type='button' href='endre.html?reg=" + d.regNr + "' class='btn btn-primary'>Endre</a></td>" +
                    // the string magic within slettEn() makes it work, do not touch, ask Ana (think it has to be turned into a string upon input)
                    "<td><input type='button' onclick='slettEn( \"" + d.regNr + "\")' class='btn btn-danger' value='Slett'></td>" +
                "</tr>";
        }
        ut += "</table>";
    }

    $("#utDiv").html(ut);
}

function slettEn(reg) {
    console.log(reg);
    let url = "/mVogn/slettEn?reg=" + reg;
    $.get(url, () => {
        visData()
    })
    .fail(function (jqXHR) {
        const json = $.parseJSON(jqXHR.responseText);
        console.log(json.message);
        $("#feil").html(json.message);
    });
}