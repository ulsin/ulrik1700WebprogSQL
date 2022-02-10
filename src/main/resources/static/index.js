$(() => {
    // console.log($.cookie('JSESIONID'));

    visData() // henter data fra "server" når siden er lastet

    $.get("/mVogn/sjekkBruker", (bool) => {
        if (!bool) {
            $("#logut").hide();
        } else {
            $("#login").hide();
        }
    })

    // venter på slett knappen
    $("#slettAlle").click(() => {
        $.get("/mVogn/sjekkBruker", (bool) => {
            if (bool) {
                $.post("/mVogn/slettAlle", () => {
                    visData();
                }) // ikke semikolon, og ikke dollartegn
                    .fail(function (jqXHR) {
                        $("#feil").html($.parseJSON(jqXHR.responseText).message);
                    });
            } else {
                $("#feil").html("Er ikke logget inn");
            }
        });
    });

    // bra feil eksemel
    $("#registrer").click(() => {
        $.post("mVogn/sjekkBruker", () => {
            window.location.href = "/registrer.html";
        })
            .fail(jqXHR => {
                $("#feil").html($.parseJSON(jqXHR.responseText).message);
            });
    });

    $("#logut").click(() => {
        $.get("/mVogn/sjekkBruker", bool => {
            if (bool) {
                $.post("mVogn/logut");
            } else {
                $("#feil").html("Er ikke logget inn");
            }
        });
    });

    // auto encrypt
    $.post("/mVogn/encrypt");

    // $("#encrypt").click(() => {
    //     // do stuff
    // });

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
                    "<td><input type='button' onclick='endreEn( \"" + d.regNr + "\")' class='btn btn-primary' value='Endre'></td>" +
                    // "<td><a type='button' href='endre.html?reg=" + d.regNr + "' class='btn btn-primary'>Endre</a></td>" +
                    // the string magic within slettEn() makes it work, do not touch, ask Ana (think it has to be turned into a string upon input)
                    "<td><input type='button' onclick='slettEn( \"" + d.regNr + "\")' class='btn btn-danger' value='Slett'></td>" +
                "</tr>";
        }
        ut += "</table>";
    }

    $("#utDiv").html(ut);
}

function slettEn(reg) {
    $.get("/mVogn/sjekkBruker", bool => {
        if (bool) {
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
        } else {
            $("#feil").html("Er ikke logget inn");
        }
    });

}

function endreEn(reg) {
    $.get("/mVogn/sjekkBruker", bool => {
        if (bool) {
            window.location.href = "/endre.html?reg=" + reg;
        } else {
            $("#feil").html("Er ikke logget inn");
        }
    });
}

