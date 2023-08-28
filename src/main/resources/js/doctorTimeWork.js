function getDateWorkTime (id) {
    $.ajax({
        type: 'POST',
        url:"/admin/doctorTimeWork/"+id+"/getDate",
        data: {date:$("#date_app").val()},
        success: function (data) {
             $('#result').html(data);
        ;},
        error: function() {
            alert("error");
        }
    });
}