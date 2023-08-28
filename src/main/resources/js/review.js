function getReviewForm() {
    $.ajax({
        type: 'POST',
        url:"/patient/reviewForm",
        data: {recId:$("#rec_id").val()},
        success: function (data) {
             $('#result').html(data);
        ;},
        error: function() {
            alert("error");
        }
    });
}

function getReviews(){
    $.ajax({
        type: 'POST',
        url:"/admin/getReviews",
        data: {doctorId:$("#doctorId").val()},
        success: function (data) {
             $('#result').html(data);
        ;},
        error: function() {
            alert("error");
        }
    });
}