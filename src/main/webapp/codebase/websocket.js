/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var wsUri = "ws://" + document.location.host +"/colabdario"+ "/colaborativeclassendpoint";
var websocket = new WebSocket(wsUri);

websocket.onerror = function(evt){ 
    onError(evt); 
    };

function onError(evt) {
    writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
    }

// For testing purposes
var output = document.getElementById("output");
websocket.onopen = function(evt){ 
    onOpen(evt);
    };

function writeToScreen(message) {
    output.innerHTML += message + "<br>";
    }

function onOpen() {
    writeToScreen("Connected to " + wsUri);
    }

websocket.onmessage = function(evt) { 
    onMessage(evt);
    };

function sendText(text) {
    var toSend = JSON.stringify(text);
    console.log("sending text: " + toSend);
    websocket.send(toSend);
    }
                
function onMessage(evt) {
    console.log("received: " + evt.data);
    var jsonDelta = JSON.parse(evt.data);
    if(nativeChange === true){
        return;
    }
    advancedEditor.updateContents(jsonDelta);
    }