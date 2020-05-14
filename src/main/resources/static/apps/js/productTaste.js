$(function () {
    var proPageSize = 10;
    var proPageNum = 1;
    var proTotalPages = 0;
    var currentData = [];
    var proId = "";
    $('#productForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            tastename: {
                message: '添加失败',
                validators: {
                    notEmpty: {
                        message: '名称不能为空'
                    }
                }
            },
        }
    });
    $("#btn_submit").click(function () {
        if (!proId) {
            var bv = $("#productForm").data('bootstrapValidator');
            bv.validate();
            if (bv.isValid()) {
                var data = JSON.stringify($("#productForm").serializeJson());
                $.ajax({
                    url: ctx + "ProductTaste/insert",
                    data: data,
                    type: "post",
                    contentType: "application/json; charset=utf-8",
                    success: function (res) {
                        if (res.code === "200") {
                            $('#con-close-modal').modal('hide');
                            window.location.reload();
                        } else {
                            alert(res.message ? res.message : "添加失败");
                        }
                    }
                });
            } else {
                $('#con-close-modal').modal('show');
            }
        } else {
            $('#productForm').data('bootstrapValidator', null);
            $('#productForm').bootstrapValidator({
                message: 'This value is not valid',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    tastename: {
                        message: '修改失败',
                        validators: {
                            notEmpty: {
                                message: '名称不能为空'
                            },
                        }
                    },
                }
            });
            var flag = $("#productForm").data('bootstrapValidator');
            flag.validate();
            if (flag.isValid()) {
                var data = JSON.stringify($("#productForm").serializeJson());
                var obj = {
                    id: proId,
                    tastename: $("#productForm").serializeJson().tastename
                };
                $.ajax({
                    url: ctx + "ProductTaste/update",
                    data: JSON.stringify(obj),
                    type: "post",
                    contentType: "application/json; charset=utf-8",
                    success: function (res) {
                        if (res.code === "200") {
                            $('#con-close-modal').modal('hide');
                            window.location.reload();
                        } else {
                            alert(res.message ? res.message : "修改失败");
                        }
                    }
                });
            } else {
                $('#con-close-modal').modal('show');
            }
        }
    });
    var init = function () {
        $("#table1").empty();
        var obj = {
            pageSize: proPageSize,
            pageNum: proPageNum, //页数
            rules: []
        };
        $.ajax({
            url: ctx + "ProductTaste/findPage",
            data: JSON.stringify(obj),
            type: "post",
            contentType: "application/json; charset=utf-8",
            success: function (res) {
                proTotalPages = res.data.totalPages;
                currentData = res.data.content;
                // $("#id_table1").html(data.data.content);
                var inf = "<thead><tr>\n" +
                    "                            <th>id</th>\n" +
                    "                            <th>名称</th>\n" +
                    "                        </tr></thead>";
                /*<![CDATA[*/
                res.data.content.forEach(function (item) {
                    /*]]*/
                    inf += "<tbody><tr><td>" + item.id + "</td><td>" + item.tastename + "</td><td>"
                        + "<button class=\"btn btn-primary\" onclick='changePro(" + JSON.stringify(item) + ")' >修改</button><button class=\"btn btn-primary\" id='delete' onclick='delPro(" + JSON.stringify(item) + ")'>删除</button>" + "</td></tr></tbody>"
                });
                $("#table1").append(inf);
                var pageCount = proTotalPages; //取到pageCount的值(把返回数据转成object类型)
                var currentPage = proPageNum; //得到urrentPage
                var options = {
                    bootstrapMajorVersion: 3, //版本
                    currentPage: currentPage, //当前页数
                    totalPages: pageCount, //总页数
                    itemTexts: function (type, page, current) {
                        switch (type) {
                            case "first":
                                return "首页";
                            case "prev":
                                return "上一页";
                            case "next":
                                return "下一页";
                            case "last":
                                return "末页";
                            case "page":
                                return page;
                        }
                    },//点击事件，用于通过Ajax来刷新整个list列表
                    onPageClicked: function (event, originalEvent, type, page) {
                        // debugger
                        var obj = {
                            pageSize: proPageSize,
                            pageNum: page, //页数
                            rules: []
                        };
                        $.ajax({
                            url: ctx + "ProductTaste/findPage",
                            data: JSON.stringify(obj),
                            type: "post",
                            contentType: "application/json; charset=utf-8",
                            success: function (res) {
                                proTotalPages = res.data.totalPages;
                                // $("#id_table1").html(data.data.content);
                                var inf = "<thead><tr>\n" +
                                    "                            <th>id</th>\n" +
                                    "                            <th>名称</th>\n" +
                                    "                        </tr></thead>";
                                /*<![CDATA[*/
                                res.data.content.forEach(function (item) {
                                    /*]]*/
                                    inf += "<tbody><tr><td>" + item.id + "</td><td>" + item.tastename + "</td><td>"
                                        + "<button class=\"btn btn-primary\" onclick='changePro(" + JSON.stringify(item) + ")' >修改</button><button class=\"btn btn-primary\" id='delete' onclick='delPro(" + JSON.stringify(item) + ")'>删除</button>" + "</td></tr></tbody>"
                                });
                                $("#table1").empty();
                                $("#table1").append(inf);
                            }
                        });
                    }
                };
                $("#pageLimit").bootstrapPaginator(options);
            }
        });
    };
    changePro = function (e) {
        proId = e.id;
        $.ajax({
            url: ctx + "ProductTaste/get?id="+proId,
            type: "get",
            contentType: "application/json; charset=utf-8",
            success: function (res) {
                $('#con-close-modal').modal('show');
                // debugger
                $("#tastename").val(res.data.tastename);
            }
        });
    };
    delPro = function (e) {
        var id = e.id;
        if (confirm('确定要删除吗？')) {
            $.ajax({
                url: ctx + "ProductTaste/delete?id="+id,
                type: "get",
                contentType: "application/json; charset=utf-8",
                success: function (res) {
                    if (res.code === "200") {
                        alert(res.message ? res.message : "删除成功");
                        init();
                    } else {
                        alert(res.message ? res.message : "删除失败");
                    }
                }
            });
        } else {
            alert('删除失败！')
        }
    };
    $("#btn_add").click(function (e) {
        $('#con-close-modal').modal('show');
    });
    $("#btn_close").click(function (e) {
        $('#con-close-modal').modal('hide');
        window.location.reload();
    });
    // $("#brandSelect").change(function (e) {
    //     var options = $("#brandSelect option:selected");
    //     // selectedId = options.val();
    //     alert(options.val());
    // });
    $("#reload").click(function (e) {
        init();
    });

    window.onload = function () {
        init();
    }

});

