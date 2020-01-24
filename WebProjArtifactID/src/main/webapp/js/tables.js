function tables_listareferti( id,path_richiesta,path_dettaglio) {
        var table = $(id).DataTable({
            "language": {
                "url": "https://cdn.datatables.net/plug-ins/1.10.20/i18n/Italian.json"
            },
            "ajax": {
                "url": path_richiesta,
                "dataSrc": "content"
            },
            "columns": [
                {"data": "data", "name": "Data"},
                {"data": "prestazione", "name": "Prestazione"},
                {"data": "medico", "name": "Medico"},
                {
                    "name": "Dettagli", data: null,
                    "createdCell": function (td, cellData, rowData, row, col) {
                        $(td).html("<a href=\""+path_dettaglio+cellData.idreferto+"\"><i class=\"far fa-list-alt\" style=\"font-size: 20px;line-height: 18px;color: rgb(0,178,255);font-weight: bold;font-style: normal;\"></i>");
                    }
                }
            ]
        })
}

