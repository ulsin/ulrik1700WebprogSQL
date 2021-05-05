$(function () {
    const reg = window.location.search.substring(1);
    console.log(reg);

    $.get("/mVogn/getEnVogn?" + reg, function (vogn) {
        $("#regNr").val(vogn.regNr);
        $("#personNr").val(vogn.personNr);
        $("#navn").val(vogn.navn);
        $("#adresse").val(vogn.adresse);
        $("#merke").val(vogn.merke);
        $("#biltype").val(vogn.biltype);
    });

    $("#endre").click(function () {
        $.get("/mVogn/sjekkBruker", bool => {
            if (bool) {
                console.log("Trykket endre!");

                const bilObj = {
                    personNr: $("#personNr").val(),
                    navn: $("#navn").val(),
                    adresse: $("#adresse").val(),
                    regNr: $("#regNr").val(),
                    merke: $("#merke").val(),
                    biltype: $("#biltype").val()
                };

                $.post("/mVogn/endreEnVogn", bilObj, function () {
                    window.location.href = "index.html";
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
    });
});