$(document).ready(function(){

    if ($.fn.dataTable.isDataTable( '#departmentsTable' )) {
        departmentsTable = $('#departmentsTable').DataTable();
        departmentsTable.ajax.reload();
    } else {
        departmentsTable = $('#departmentsTable').DataTable({
            "cursor": "pointer",
            "language": {
                "url": "dataTables.russian.lang"
            },
            columns: [
                {"data": "id"},
                {"data": "name"},
                {"data": "note"},
                {"data": "parentDepartment"},
                {"data": "parentDepartment"}
            ],
            ajax: {
                url: 'http://' + window.location.host + '/department/getAll',
                dataType: 'json',
                contentType: 'application/json',
                type: 'POST',
                // указываем, что придет массив, а не объект
                dataSrc: ''
            },
            'createdRow': function (row, data, dataIndex, cells) {
                $(cells)[3].innerHTML = data.parentDepartment == null ? "" : data.parentDepartment.name;
                $(cells)[4].innerHTML = "<button class='btn btn-danger' onclick='deleteDepartment(" + data.id + ")'>Удалить</button>";
                $(row).on('click', function() {
                    window.location = "/department/get?departmentId=" + data.id;
                });
            }
        });
    }

    // Сохраняем изменения в отделе
    $('body').on('click', '#saveEditDepartment', function(){
        var departmentData = {};
        departmentData.id = $('#id').val();
        departmentData.name = $('#name').val();
        departmentData.note = $('#note').val();
        departmentData.parentDepartment = $('#parentDepartment').val() === "" ? null : {"id":$('#parentDepartment').val()};
        $.ajax({
            type : "POST",
            contentType: "application/json; charset=utf-8",
            url : window.location.origin + "/department/edit",
            data : JSON.stringify(departmentData),
            success : function() {
                alert('Успешно сохранено');
                window.location = "/department/getAll";
            },
            error: function() {
                alert('Не удалось сохранить');
            },
            complete: function() {
            }
        }).done(function() {

        });
    });

    // Сохраняем новый отдел
    $('body').on('click', '#createDepartment', function(){
        var departmentData = {};
        departmentData.name = $('#name').val();
        departmentData.note = $('#note').val();
        departmentData.parentDepartment = $('#parentDepartment').val() == "" ? null : {"id":$('#parentDepartment').val()};
        $.ajax({
            type : "POST",
            contentType: "application/json; charset=utf-8",
            url : window.location.origin + "/department/create",
            data : JSON.stringify(departmentData),
            success : function() {
                alert('Успешно сохранено');
                window.location = "/department/getAll";
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

// Удаляем отдел
function deleteDepartment(departmentId) {
    $.ajax({
        type : "POST",
        contentType: "application/json; charset=utf-8",
        url : window.location.origin + "/department/delete?departmentId=" + departmentId,
        data : {},
        success : function() {
            alert('Успешно удалено');
            window.location = "/department/getAll";
        },
        error: function() {
            alert('Не удалось удалить. Возможно у этого отдела есть подчиненные отделы или в этом отделе числятся сотрудники');
            window.location = "/department/getAll";
        },
        complete: function() {
        }
    }).done(function() {

    });
}

