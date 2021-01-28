$(document).ready(function() {
    hideAll();
});

function hideAll() {
    $("#dark-background").hide();
    $("#dark-background-form-holder").hide();
}

function editDoctor (id, name, specialty, specification, experience) {
    $("#dark-background").show();
    document.getElementById("doctorName").innerHTML = name;
    document.getElementById("id").setAttribute('value', id);
    document.getElementById("specialty").setAttribute('value', specialty);
    document.getElementById("specification").innerHTML =  specification;
    document.getElementById("experience").setAttribute('value', experience);
    $("#dark-background-form-holder").show();
}