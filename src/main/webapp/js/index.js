$('#submit').click(function () {
    var btn = $(this);
    btn.prop('disabled', true);
    var data = JSON.stringify({
        'fragment': $('#fragment').val(),
        'maxResponseSize': 10
    });
    $.ajax({
        url: '/api/search',
        type: 'post',
        dataType: 'json',
        contentType: 'application/json;charset=UTF-8',
        data: data,
        success: function (results) {
            var html = '<div>' + results.length + ' result(s) found</div>';
            $.each(results, function (i, res) {
                var str = '<h3>' + res.project + '</h3>'
                        + '<div>Path: ' + res.path + '</div>'
                        + '<div>Start: ' + formatPos(res.begin)
                        + ', End: ' + formatPos(res.end)
                        + '</div>'
                        + '<div>Method start: ' + formatPos(res.methodBegin)
                        + ', Method end: ' + formatPos(res.methodEnd)
                        + '</div>'
                        + '<textarea>' + escape(res.method) + '</textarea>';
                html += str;
            });
            $('#results').html(html);
            btn.prop('disabled', false);
        },
        error: function () {
            alert('Operation failed');
        }
    });
});

function formatPos(pos) {
    return '(line: ' + pos.line + ', column: ' + pos.column + ')';
}

function escape(text) {
    if (!text) {
        return '';
    }
    return text.replace(/&/g, '&amp;')
            .replace(/</g, '&lt;')
            .replace(/>/g, '&gt;')
            .replace(/"/g, '&quot;')
            .replace(/'/g, '&apos;');
}
