console.log("message ws");
const messageClient = new StompJs.Client({
    brokerURL : "/message"
});

document.addEventListener("DOMContentLoaded", function(){
    messageClient.activate();
});

messageClient.onConnect = function(frame){
    console.log(frame);
    const currentChannelId = document.getElementById("currentChannelId").value;

    //메세지 받았을 때
    messageClient.subscribe(`/messagedest/${currentChannelId}`, function (message){

        const messageObj = JSON.parse(message.body);
        console.log(messageObj);
        appendMessage(messageObj);
    });
}

function sendMessage(messageId){

    const currentChannelId = document.getElementById("currentChannelId").value;

    // 메세지 보낼 때
    messageClient.publish({
        destination : `/messagesrc/${currentChannelId}/${messageId}`
    });
}