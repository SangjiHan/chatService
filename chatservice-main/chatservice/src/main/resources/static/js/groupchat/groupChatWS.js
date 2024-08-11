console.log("gc ws");

const groupChatClient = new StompJs.Client({
    brokerURL : "/message"
});

groupChatClient.onConnect = function(frame){
    console.log(frame);
    console.log(currentGroupId);
    const groupId = currentGroupId;
    //메세지 받았을때
    groupChatClient.subscribe(`/messagedest/gcmessagedest/${groupId}`, function(message){
        console.log("sub");
        console.log(JSON.parse(message.body));
        const messageObj = JSON.parse(message.body);
        appendMessage(messageObj);
    });
}

function sendGCMessage(messageId){
    console.log("pub");
    console.log(messageId);
    console.log(currentGroupId);
    const groupId = currentGroupId;
    groupChatClient.publish({
        destination : `/messagesrc/gcmessagesrc/${groupId}/${messageId}`
    });
}