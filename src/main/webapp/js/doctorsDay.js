$(document).ready(function() {
     hideAll();
});

$(document).on('click', function(e) {
    if ($(e.target).closest("#dark-background").length && !$(e.target).closest("#dark-background-form-holder").length) {
        document.forms['recordForm'].submit();
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
            if (data == "true"){
                editRecord(id);
             }
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
    document.getElementById("rec_id").setAttribute('value', id);
    document.getElementById("record").innerHTML =  document.getElementById("Record"+id).innerHTML;
    $("#dark-background-form-holder").show();
}