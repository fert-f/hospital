function getSearchMenu(){
    $.ajax({
        type: 'POST',
        url:"/admin/getSearchMenu",
        data: {doctorId:$("#doctorId").val()},
        success: function (data) {
             $('#result').html(data);
        ;},
        error: function() {
            alert("error");
        }
    });
}

function getPatientHistory() {
    $.ajax({
        type: 'POST',
        url:"/doctor/getPatientHistory",
        data: {patientId:$("#patientId").val()},
        success: function (data) {
             $('#result').html(data);
        ;},
        error: function() {
            alert("error");
        }
    });
}




