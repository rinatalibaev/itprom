$(document).ready(function(){

    if ($.fn.dataTable.isDataTable( '#employeesTable' )) {
        employeesTable = $('#employeesTable').DataTable();
        employeesTable.ajax.reload();
    } else {
        employeesTable = $('#employeesTable').DataTable({
            "cursor": "pointer",
            "language": {
                "url": "dataTables.russian.lang"
            },
            columns: [
                {"data": "id"},
                {"data": "fio"},
                {"data": "profession"},
                {"data": "department"},
                {"data": "note"}
            ],
            ajax: {
                url: 'http://' + window.location.host + '/employee/getAll',
                dataType: 'json',
                contentType: 'application/json',
                type: 'POST',
                // указываем, что придет массив, а не объект
                dataSrc: ''
            },
            'createdRow': function (row, data, dataIndex, cells) {
                $(cells)[2].innerHTML = data.profession == null ? "" : data.profession.name;
                $(cells)[3].innerHTML = data.department == null ? "" : data.department.name;
                $(cells)[4].innerHTML = "<button class='btn btn-danger' onclick='deleteEmployee(" + data.id + ")'>Удалить</button>";
                $(row).on('click', function() {
                    window.location = "/employee/get?employeeId=" + data.id;
                });
            }
        });
    }

    // Обновляем сотрудника
    $('body').on('click', '#saveEditEmployee', function(){
        var employeeData = {};
        employeeData.id = $('#id').val();
        employeeData.fio = $('#fio').val();
        employeeData.profession = $('#profession').val();
        employeeData.department = $('#department').val();
        employeeData.note = $('#note').val();
        $.ajax({
            type : "POST",
            contentType: "application/json; charset=utf-8",
            url : window.location.origin + "/employee/edit",
            data : JSON.stringify(employeeData),
            success : function() {
                alert('Успешно сохранено');
                window.location = "/employee/getAll";
            },
            error: function() {
                alert('Не удалось сохранить');
            },
            complete: function() {
            }
        }).done(function() {

        });
    });

    // Сохраняем нового сотрудника
    $('body').on('click', '#createEmployee', function(){
        var employeeData = {};
        employeeData.fio = $('#fio').val();
        employeeData.profession = $('#profession').val();
        employeeData.department = $('#department').val();
        employeeData.note = $('#note').val();
        $.ajax({
            type : "POST",
            contentType: "application/json; charset=utf-8",
            url : window.location.origin + "/employee/create",
            data : JSON.stringify(employeeData),
            success : function() {
                alert('Успешно сохранено');
                window.location = "/employee/getAll";
            },
            error: function() {
                alert('Не удалось сохранить. Возможно сначала необходимо создать отдел и профессию');
            },
            complete: function() {
            }
        }).done(function() {

        });
    });

});

// Удаляем сотрудника
function deleteEmployee(employeeId) {
    $.ajax({
        type : "POST",
        contentType: "application/json; charset=utf-8",
        url : window.location.origin + "/employee/delete?employeeId=" + employeeId,
        data : {},
        success : function() {
            alert('Успешно удалено');
            window.location = "/employee/getAll";
        },
        error: function() {
            alert('Не удалось удалить');
            window.location = "/employee/getAll";
        },
        complete: function() {
        }
    }).done(function() {

    });
}

