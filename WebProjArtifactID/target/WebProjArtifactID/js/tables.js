function tables_listareferti( selector,path_richiesta,path_dettaglio) {
        var table = $(selector).DataTable({
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
function tables_listaprescrizioni( selector,path_richiesta,path_dettaglio) {
    var table = $(selector).DataTable({
        "language": {
            "url": "https://cdn.datatables.net/plug-ins/1.10.20/i18n/Italian.json"
        },
        "ajax": {
            "url": path_richiesta,
            "dataSrc": "content"
        },
        "columns": [
            {"data": "datarilascio", "name": "Data Rilascio"},
            {"data": "prestazione", "name": "Prestazione"},
            {"data": "medico", "name": "Medico"},
            {"data": "dataevasione", "name": "Data Evasione"},
            {"data": "provincia", "name": "Provincia"},
            {
                "name": "Dettagli", data: null,
                "createdCell": function (td, cellData, rowData, row, col) {
                    $(td).html("<a href=\""+path_dettaglio+cellData.idprescrizione+"\"><i class=\"far fa-list-alt\" style=\"font-size: 20px;line-height: 18px;color: rgb(0,178,255);font-weight: bold;font-style: normal;\"></i>");
                }
            }
        ]
    })
}
function tables_listapagamenti( selector,path_richiesta,path_dettaglio) {
    var table = $(selector).DataTable({
        "language": {
            "url": "https://cdn.datatables.net/plug-ins/1.10.20/i18n/Italian.json"
        },
        "ajax": {
            "url": path_richiesta,
            "dataSrc": "content"
        },
        "columns": [
            {"data": "dataemissione", "name": "Data Emissione"},
            {"data": "prestazione", "name": "Prestazione"},
            {"data": "costo", "name": "Costo"},
            {"data": "datapagamento", "name": "Data Pagamento"},
            {"data": "provinciarilascio", "name": "Provincia Rilascio"},
            {
                "name": "Dettagli", data: null,
                "createdCell": function (td, cellData, rowData, row, col) {
                    $(td).html("<a href=\""+path_dettaglio+cellData.idpagamento+"\"><i class=\"far fa-list-alt\" style=\"font-size: 20px;line-height: 18px;color: rgb(0,178,255);font-weight: bold;font-style: normal;\"></i>");
                }
            }
        ]
    })
}

