function s2ab(s) {
    var buf = new ArrayBuffer(s.length); //convert s to arrayBuffer
    var view = new Uint8Array(buf);  //create uint8array as viewer
    for (var i = 0; i < s.length; i++) view[i] = s.charCodeAt(i) & 0xFF; //convert to octet
    return buf;
}

function downloadXLS(selector_id,filename,sheetname) {
    /* make the worksheet */
   // $.getJSON( request_url, function( data ) {
       // var ws = XLSX.utils.json_to_sheet((data));
        ws =  XLSX.utils.table_to_sheet(document.getElementById(selector_id));

        /* add to workbook */
        var wb = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(wb, ws, sheetname);



        XLSX.writeFile(wb, filename)
    //});
}