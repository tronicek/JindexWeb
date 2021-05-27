$('#submit').click(function () {
    var btn = $(this);
    btn.prop('disabled', true);
    var data = JSON.stringify({
        'project': $('#repository').val()
    });
    $.ajax({
        url: '/api/initialize',
        type: 'post',
        dataType: 'json',
        contentType: 'application/json;charset=UTF-8',
        data: data,
        success: function (response) {
            $('#result').html('The index was created: ' + response.project);
            btn.prop('disabled', false);
        },
        error: function () {
            alert('Operation failed');
        }
    });
});
