$(() => {
    $("#login").click(() => {
        if (nullFeilLogin()) {
            const bruker = {
                navn: $("#brukerNavn").val(),
                passord: $("#passord").val()
            };
            $.post("/mVogn/login", bruker, function () {
                window.location.href = "/index.html";
            })
                .fail(function (jqXHR) {
                    const json = $.parseJSON(jqXHR.responseText);
                    console.log(json.message);
                    $("#logError").html(json.message);
                });
        }
    });
});