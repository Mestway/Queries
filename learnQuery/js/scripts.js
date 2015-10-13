function dumpData () 
{
  var ht = $('#input').handsontable('getInstance');
  console.log('data: ',  ht.getData());
  console.log('number rows: ' + ht.countRows() + ' cols: ' + ht.countCols());
}

function sendRequest ()
{
  console.log("sending request");
  $.ajax({
    type: 'GET',
    //dataType: 'jsonp',
    dataType: 'text',
    url: "http://localhost:9999/numCols@" + $("#numCols").val() + 
          "&data@" + $("#input").handsontable("getInstance").getData(),
    timeout: 5000,
    
    success: 
      function(data, textStatus )
      {
        console.log("got response:", data);
        var result = $.parseJSON(data);
        console.log("query:", result.query);

        if (result.query != "Cannot find query")
        {
          $("#result").html("inferred query:<br><br>" + result.query + 
                          "<br><br>Running this query on the sample table will return:");
          $("#result").attr("style", "visibility:show");

          //$("#result").text("inferred query:<br>" + data); // this gets escaped

          $('#resultTable').handsontable({
            data: result.results,
            autoWrapRow: true,
            colHeaders: result.columnNames,
            contextMenu: true
          });
        }
        else
        {
          $("#result").html("inferred query:<br><br>" + result.query);        
          $("#result").attr("style", "visibility:show");
        }
      },

    error: function(xhr, textStatus, errorThrown){
       console.log('request failed: ' + xhr.responseText + ' ' + errorThrown);
    }
  });


/*
 $.get("http://localhost:8080/qun", function(data) { alert("success"); })
  .fail(function(a, b, c) { alert("failed"); console.log("a", a, " b: ", b, " c: ", c); });
 console.log("done");
*/

/*
var ajax = $.ajax({
    url: 'qun',
    //type: 'GET',
    //data: params,
    success: function (data) {
        //var html = "<h1>" + data.id + "</h1><h1>" + data.name + "</h1><h1>" + data.dateTime + "</h1>";
        //$("#result").empty().append(html);
        alert("success");
    }
});
ajax.fail(function (jqXHr, textStatus, errorThrown) {
    alert('text status: ' + textStatus + " resp text: " + jqXHr.responseText);
});
*/

}

$(document).ready(
  function () 
  {
    /*
    var data = [
      ["", "Maserati", "Mazda", "Mercedes", "Mini", "Mitsubishi"],
      ["9999", 0, 2941, 4303, 354, 5814],
      ["2010", 5, 2905, 2867, 412, 5284],
      ["2011", 4, 2517, 4822, 552, 6127],
      ["2012", 2, 2422, 5399, 776, 4151]
    ];
    */
    
    var data = 
    [
      [0, 1, 2, 1, 10],
      [0, 2, 4, 3, 20],
      [0, 3, 6, 5, 30],
      [0, 4, 8, 7, 40],
    ]

   
    $('#original').handsontable({
      data: data,
      minRows: data.length,
      minCols: data[0].length,
      minSpareRows: 0,
      autoWrapRow: true,
      colHeaders: ["c0", "c1", "c2", "c3", "c4"],
      contextMenu: true,
      width: 400,
    });

    $('#input').handsontable({
      //data: [],
      autoWrapRow: true,
      colHeaders: false,
      contextMenu: true,
      currentRowClassName: "currentRow",
      currentColClassName: "currentCol",
      startRows: 4,
      startCols: 5,
      colHeaders: ["a", "b", "c", "d", "e"],
      width: 400,
    });


    //$('.ver').text($('#example').data('handsontable').version);

    /*
    function bindDumpButton() {
      $('body').on('click', 'button[name=dump]',     
        function () {      
        var dump = $(this).data('dump');
        var $container = $(dump);

        console.log('data of ' + dump, $container.handsontable('getData'));
      });
        
    }

    bindDumpButton();
    */

  }
);


