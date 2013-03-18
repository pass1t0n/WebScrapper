(function ($) {
    var intervalId = 0;
    $("#action_startScrap").click(function () {
        intervalId = setInterval(function () {
            $.get("status")
                .done(function (data, textStatus, jqXHR) {
                    // if I am getting Done then we need to use clearInterval(id).
                    $("#status").text("Status: "+data);

                    if (data=="Done. Email was sent"){
                        clearInterval( intervalId );
                        $("#action_startScrap").removeClass("disabled");
                        $("#action_startScrap").addClass("btn-primary");
                    }
                })
        }, 5000);

        $.get("start/marketwatch");
        $("#action_startScrap").addClass("disabled");
    });

    $("#loginForm_Submit").click(function () {
        console.log("loginForm_Submit");
        var userName = $("#loginForm_userName").val();
        var password = $("#loginForm_password").val();
        $.get("login?userName=" + userName + "&password=" + password)
            .done(function () {
                $("#action_startScrap").removeClass("disabled");
                $("#action_startScrap").addClass("btn-primary");
            })
            .fail(function () {
                alert("wrong user name and password")
            });
    });

//    var intervalId = setInterval(function () {
//        $.get("status")
//            .done(function (data, textStatus, jqXHR) {
//                // if I am getting Done then we need to use clearInterval(id).
//                $("#status").text("Status: "+data);
//
//                if (data=="Done. Email was sent"){
//                    clearInterval( intervalId );
//                }
//            })
//    }, 15000);

})(jQuery);