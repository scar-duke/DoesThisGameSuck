$(document).ready(function () {
    $("#usere").hide();
    $("#gendererror").hide();
    $("#passerror").hide();
    $("#emailerror").hide();
    $("#user").change(ue);
    $("#passerror").change(ps);
    $("#gendererror").change(af);
    $("#emailerror").change(caere);
});

// check on submit
$(document).on("submit", "form", function (a) {
    ue();
    ps();
    af();
    caere();
    // if no error messages, it's valid
    if ($("form").find(".form-error:visible").length != 0) { a.preventDefault(); }
});

// if there are less than three characters in the user input, error
function ue() {
    if ($("#user").val().length < 3) { $("#usere").show(); }
    else { $("#usere").hide(); }
}

function ps() {
    if ($("#pass").val().length < 8) { $("#passerror").show(); }
    else { $("#passerror").hide(); }
}

// if a there is nothing selected error
function af() {
    if ($("#gender").val() == "") { $("#gendererror").show(); }
    else { $("#gendererror").hide(); }
}



// if there is empty string, error
function caere() {
    if ($("#email").val() == "") { $("#emailerror").show(); }
    else { $("#emailerror").hide(); }
}