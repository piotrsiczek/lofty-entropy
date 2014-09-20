/**
 * Created by piotr on 2014-04-26.
 */


function onOpened() {
    alert("Channel opened!!!!!!!");
}

function connectChannel(token) {

    alert("channel function");
    channel = new goog.appengine.Channel(token);
    alert("channel function");
    socket = channel.open();
    alert("channel function");
    socket.onopen = function() { alert("****************");onOpen();}
    alert("channel function");
    socket.onmessage = test;
        //socket.onmessage = function(message) { alert("get message" + message.data);}
    //socket.onmessage = function() { alert("get message"); }
    socket.onerror = function(error ) { onError(error.description, error.code); }
    socket.onclose = function() { onClose();}
    alert("asdf");
}

function test(message) {
    onMessage(message.data);
    alert("get message" + message.data);
}
