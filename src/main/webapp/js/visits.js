function dropRecord () {
    $.ajax({
        type: 'POST',
        url:"/patient/dropAppointment",
        data: {recId:id},
        success: function (data) {
             window.location.href = data;
        ;},
        error: function() {
            alert("error");
        }
    });
}