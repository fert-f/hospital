function getDayHistory(){
    $.ajax({
        type: 'POST',
        url:"/admin/getDayHistory",
        data: {date:$("#date").val(),doctorId:$("#doctorId").val()},
        success: function (data) {
             $('#result2').html(data);
        ;},
        error: function() {
            alert("error");
        }
    });
}

function getPatientHistoryForAdmin() {
    $.ajax({
        type: 'POST',
        url:"/admin/getPatientHistory",
        data: {patientId:$("#patientId").val(), doctorId:$("#doctorId").val()},
        success: function (data) {
             $('#result2').html(data);
        ;},
        error: function() {
            alert("error");
        }
    });
}


let enabledDays2 = ['2021-2-1', '2021-02-02', '2021-1-31', '2020-12-31']

function enableAllTheseDays(date) {
   var cd = date.getFullYear() + '-' +(date.getMonth() + 1) + '-' + date.getDate();
   return [ $.inArray(cd, enabledDays) != -1 ]
}

$("#date").datepicker({
    dateFormat: 'yy-mm-dd',
    beforeShowDay: enableAllTheseDays
  });


