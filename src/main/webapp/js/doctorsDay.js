$(document).ready(function() {
     hideAll();
});

$(document).on('click', function(e) {
    if ($(e.target).closest("#dark-background").length && !$(e.target).closest("#dark-background-form-holder").length) {
        hideAll();
    }
    e.stopPropagation();
});


function startRecord(id) {
    $.ajax({
        type: 'POST',
        url:"/doctor/patientCome/",
        data: {recId:id},
        success: function (data) {
             editRecord(id);
        ;},
        error: function() {
            alert("error");
        }
    });

}

function hideAll() {
    $("#dark-background").hide();
    $("#dark-background-form-holder").hide();
}

function editRecord (id) {
    $("#dark-background").show();
    document.getElementById("doctorName").innerHTML = document.getElementById("doctorName"+id).innerHTML;
    document.getElementById("id").setAttribute('value', id);
    document.getElementById("specialty").setAttribute('value', document.getElementById("specialty"+id).innerHTML);
    document.getElementById("specification").innerHTML =  document.getElementById("specification"+id).innerHTML;
    document.getElementById("experience").setAttribute('value', document.getElementById("experience"+id).innerHTML);
    $("#dark-background-form-holder").show();
}