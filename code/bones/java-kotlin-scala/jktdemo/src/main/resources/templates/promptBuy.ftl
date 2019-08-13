<html>

<head>
    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
    <script>
        $(document).ready(function () {
            $('#action-btn').click(function () {
                console.log("in act-btn")
                $.ajax({
                    type: 'POST',
                    url: '/admin/saveOrder?token='+$('#token').val(),
                    contentType: "application/json;charset=utf-8",
                    // data: JSON.stringify($('#form').serialize()),// text=text 这种不是json 格式 form格式和json 格式不一样
                    data:JSON.stringify({
                        'text':$('#text').val()
                    }),
                    success: function (data) {
                        console.log("success:", data)
                        $('html').html(data)
                    },
                    error: function (data) {
                        console.log("error:", data)
                        console.log(data)
                    }
                })
            });
        })
    </script>
</head>
<body>
<form id="form" action="/admin/saveOrder" name="form" method="post">
    <input type="text" id="text" name="text" value="text">
    <br>
    <input type="text" id="id" name="id" value="id">
    <br>
    <input type="button" id="action-btn" value="提交">
</form>
<input type="hidden" name="token" id="token" value="${token}">
</body>

</html>


