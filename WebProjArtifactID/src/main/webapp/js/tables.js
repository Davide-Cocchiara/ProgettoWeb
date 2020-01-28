function tables_listareferti( selector,path_richiesta,path_dettaglio) {
        var table = $(selector).DataTable({
            "lengthMenu": [[10, 25, 50,100, -1], [10, 25, 50,100, "All"]],
            "language": {
                "url": "https://cdn.datatables.net/plug-ins/1.10.20/i18n/Italian.json"
            },
            "ajax": {
                "url": path_richiesta,
                "dataSrc": "content"
            },
            "columns": [
                {"data": "data", "title": "Data"},
                {"data": "prestazione", "title": "Prestazione"},
                {"data": "medico", "title": "Rilasciato da"},
                {
                    "title": "Dettagli", data: null,
                    "createdCell": function (td, cellData, rowData, row, col) {
                        $(td).html("<a href=\""+path_dettaglio+cellData.idreferto+"\"><i class=\"far fa-list-alt\" style=\"font-size: 20px;line-height: 18px;color: rgb(0,178,255);font-weight: bold;font-style: normal;\"></i>");
                    }
                }
            ]
        })
}
function tables_listaprescrizioni( selector,path_richiesta,path_dettaglio) {
    var table = $(selector).DataTable({
        "lengthMenu": [[10, 25, 50,100, -1], [10, 25, 50,100, "All"]],
        "language": {
            "url": "https://cdn.datatables.net/plug-ins/1.10.20/i18n/Italian.json"
        },
        "ajax": {
            "url": path_richiesta,
            "dataSrc": "content"
        },
        "columns": [
            {"data": "datarilascio", "title": "Data Rilascio"},
            {"data": "prestazione", "title": "Prestazione"},
            {"data": "medico", "title": "Medico"},
            {"data": "dataevasione", "title": "Data Evasione","sDefaultContent": "<div style=\"color:red\">Non evasa</div>"},
            {"data": "provincia", "title": "Provincia"},
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
        "lengthMenu": [[10, 25, 50,100, -1], [10, 25, 50,100, "All"]],
        "language": {
            "url": "https://cdn.datatables.net/plug-ins/1.10.20/i18n/Italian.json"
        },
        "ajax": {
            "url": path_richiesta,
            "dataSrc": "content"
        },
        "columns": [
            {"data": "dataemissione", "title": "Data Emissione"},
            {"data": "prestazione", "title": "Prestazione"},
            {"data": "costo", "title": "Costo"},
            {"data": "datapagamento", "title": "Data Pagamento" ,"sDefaultContent": "<div style=\"color:red\">Non pagato!</div>"},
            {"data": "provinciarilascio", "title": "Provincia Rilascio"},
            {
                "title": "Dettagli", data: null,
                "createdCell": function (td, cellData, rowData, row, col) {
                    $(td).html("<a href=\""+path_dettaglio+cellData.idpagamento+"\"><i class=\"far fa-list-alt\" style=\"font-size: 20px;line-height: 18px;color: rgb(0,178,255);font-weight: bold;font-style: normal;\"></i>");
                }
            }
        ]
    })
}

function tables_listapazienti( selector,path_richiesta,path_dettaglio) {
    var table = $(selector).DataTable({
        "lengthMenu": [[10, 25, 50,100, -1], [10, 25, 50,100, "All"]],
        "language": {
            "url": "https://cdn.datatables.net/plug-ins/1.10.20/i18n/Italian.json"
        },
        "ajax": {
            "url": path_richiesta,
            "dataSrc": "content"
        },
        "columns": [
            {"data": "codicefiscale", "title": "Codice Fiscale"},
            {"data": "nome", "title": "Nome"},
            {"data": "cognome", "title": "Cognome"},
            {"data": "datanascita", "title": "Data Nascita" },
            {
                "title": "Dettagli", data: null,
                "createdCell": function (td, cellData, rowData, row, col) {
                    $(td).html("<a href=\""+path_dettaglio+cellData.codicefiscale+"\"><i class=\"far fa-list-alt\" style=\"font-size: 20px;line-height: 18px;color: rgb(0,178,255);font-weight: bold;font-style: normal;\"></i>");
                }
            }
        ]
    })
}


function tables_listaprescrizionierogabili( selector,path_richiesta,path_dettaglio) {
    var table = $(selector).DataTable({
        "lengthMenu": [[10, 25, 50,100, -1], [10, 25, 50,100, "All"]],
        "language": {
            "url": "https://cdn.datatables.net/plug-ins/1.10.20/i18n/Italian.json"
        },
        "ajax": {
            "url": path_richiesta,
            "dataSrc": "content"
        },
        "columns": [
            {"data": "datarilascio", "title": "Data Rilascio"},
            {"data": "prestazione", "title": "Prestazione"},
            {"data": "medico", "title": "Medico"},
            {"data": "dataevasione", "title": "Data Evasione","sDefaultContent": "<div style=\"color:red\">Non evasa</div>"},
            {"data": "provincia", "title": "Provincia"},
            {
                "name": "Dettagli", data: null,
                "createdCell": function (td, cellData, rowData, row, col) {
                    $(td).html("<a href=\""+path_dettaglio+cellData.idprescrizione+"\"><i class=\"far fa-list-alt\" style=\"font-size: 20px;line-height: 18px;color: rgb(0,178,255);font-weight: bold;font-style: normal;\"></i>");
                }
            }
        ]
    })
}

function tables_listareportesamierogati(selector, path_richiesta) {
    var table = $(selector).DataTable({
        "lengthMenu": [[10, 25, 50,100, -1], [10, 25, 50,100, "All"]],
        "language": {
            "url": "https://cdn.datatables.net/plug-ins/1.10.20/i18n/Italian.json"
        },
        "ajax": {
            "url": path_richiesta,
            "dataSrc": "content"
        },
        "columns": [
            {"data": "value.idpagamento", "title": "ID"},
            {"data": "value.datapagamento", "title": "Pagato"},
            {"data": "value.dataemissione", "title": "Emesso"},
            {"data": "value.prestazione", "title": "Prestazione"},
            {"data": "key", "title": "Paziente"},
            {"data": "value.costo", "title": "Costo"}
        ]
    })
}

function tables_listadisponibiliesami( selector,path_richiesta) {
    // TODO BRO
    var table = $(selector).DataTable({
        "lengthMenu": [[10, 25, 50,100, -1], [10, 25, 50,100, "All"]],
        "language": {
            "url": "https://cdn.datatables.net/plug-ins/1.10.20/i18n/Italian.json"
        },
        "ajax": {
            "url": path_richiesta,
            "dataSrc": "content"
        },
        "columns": [
            {"data": "key", "title": "ID"},
            {"data": "value.key", "title": "Tipo"},
            {"data": "value.value", "title": "Costo"}
        ]
    })
}
