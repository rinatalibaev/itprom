$(document).ready(function(){

    if ($.fn.dataTable.isDataTable( '#professionsTable' )) {
        professionsTable = $('#professionsTable').DataTable();
        professionsTable.ajax.reload();
    } else {
        professionsTable = $('#professionsTable').DataTable({
            "cursor": "pointer",
            "language": {
                "url": "dataTables.russian.lang"
            },
            columns: [
                {"data": "id"},
                {"data": "name"},
                {"data": "note"},
                {"data": "note"}
            ],
            ajax: {
                url: 'http://' + window.location.host + '/profession/getAll',
                dataType: 'json',
                contentType: 'application/json',
                type: 'POST',
                // указываем, что придет массив, а не объект
                dataSrc: ''
            },
            'createdRow': function (row, data, dataIndex, cells) {
                $(cells)[3].innerHTML = "<button class='btn btn-danger' onclick='deleteProfession(" + data.id + ")'>Удалить</button>";
                $(row).on('click', function() {
                    window.location = "/profession/get?professionId=" + data.id;
                });
            }
        });
    }

    // Сохраняем изменения в профессии
    $('body').on('click', '#saveEditProfession', function(){
        var professionData = {};
        professionData.id = $('#id').val();
        professionData.name = $('#name').val();
        professionData.note = $('#note').val();
        $.ajax({
            type : "POST",
            contentType: "application/json; charset=utf-8",
            url : window.location.origin + "/profession/edit",
            data : JSON.stringify(professionData),
            success : function() {
                alert('Успешно сохранено');
                window.location = "/profession/getAll";
            },
            error: function() {
                alert('Не удалось сохранить');
            },
            complete: function() {
            }
        }).done(function() {

        });
    });

    // Сохраняем новую профессию
    $('body').on('click', '#createProfession', function(){
        var professionData = {};
        professionData.name = $('#name').val();
        professionData.note = $('#note').val();
        $.ajax({
            type : "POST",
            contentType: "application/json; charset=utf-8",
            url : window.location.origin + "/profession/create",
            data : JSON.stringify(professionData),
            success : function() {
                alert('Успешно сохранено');
                window.location = "/profession/getAll";
            },
            error: function() {
                alert('Не удалось сохранить');
            },
            complete: function() {
            }
        }).done(function() {

        });
    });

});

// Удаляем профессию
function deleteProfession(professionId) {
    $.ajax({
        type : "POST",
        contentType: "application/json; charset=utf-8",
        url : window.location.origin + "/profession/delete?professionId=" + professionId,
        data : {},
        success : function() {
            alert('Успешно удалено');
            window.location = "/profession/getAll";
        },
        error: function() {
            alert('Не удалось удалить');
        },
        complete: function() {
        }
    }).done(function() {

    });
}

