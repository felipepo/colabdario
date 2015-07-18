/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//var eventId = scheduler.addEvent({
//    start_date: "16-07-2015 09:00",
//    end_date:   "16-07-2015 12:00",
//    text:   "Meeting",
//    holder: "John", //userdata
//    room:   "5"     //userdata
//});

function populateEvents(){
    console.log("=== Populating Calendar");
    var id= {"userId" : 1};
    var data = JSON.stringify(id)
    console.log(data);
    var ajaxRequest = new XMLHttpRequest();
    ajaxRequest.open("POST", "calendarServlet");
    ajaxRequest.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    ajaxRequest.onreadystatechange =
            function () {
                if (ajaxRequest.readyState === 4 && ajaxRequest.status === 200) {
                        var respostaJSON = JSON.parse(ajaxRequest.responseText);
                        for(var i = 0; i < respostaJSON.counters.lenght;i++){
                            var lesson = respostaJSON.counters[i];
                            scheduler.addEvent(lesson);
                        }
                }
            };
    ajaxRequest.send(data);
}