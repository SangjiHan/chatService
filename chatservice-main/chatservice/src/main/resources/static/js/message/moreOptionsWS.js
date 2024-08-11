
const messageDeleteClient = new StompJs.Client({
    brokerURL : "/message"
});

const messageEditClient = new StompJs.Client({
    brokerURL : "/message"
});


function enableMoreOptionsClients(id){


    messageDeleteClient.onConnect = function(frame){
        console.log(frame);

        messageDeleteClient.subscribe(`/messagedest/delete/${id}`, function(result){
            console.log(result.body);

            const messageId = result.body;

            const targetMessage = document.getElementById(messageId);
            console.log(targetMessage);
            targetMessage.innerHTML = "";
        });
    }
    messageDeleteClient.activate();

    messageEditClient.onConnect = function(frame){
        console.log(frame);

        messageEditClient.subscribe(`/messagedest/edit/${id}`, function(result){
            const message = JSON.parse(result.body);
            console.log(message);

            const targetMessage = document.getElementById(message.displayMessageId);
            const hiddenContent = targetMessage.querySelector(".hiddenContent");
            hiddenContent.value = message.displayMessageContent;

            const targetMessageText = targetMessage.querySelector(".displayMessageText");
            targetMessageText.innerHTML = "";
            targetMessageText.innerHTML = getMessageContent(message);

            const moreOptionsModal = targetMessage.querySelector(".moreOptionsModal");
            moreOptionsModal.classList.remove("blockDisplay");
            moreOptionsModal.classList.add("noneDisplay");

            scrollAllTheWayDown();
        });
    }

    messageEditClient.activate();

}

function disableMoreOptionsClients(){
    messageDeleteClient.deactivate();
    messageEditClient.deactivate();
}

function sendDeleteMessage(messageId, id){
    console.log(messageId);

    messageDeleteClient.publish({
        destination : `/messagesrc/delete/${id}/${messageId}`
    });
}

function sendEditMessage(messageId, id){
    console.log("sendEdit");
    console.log(id);
    messageEditClient.publish({
        destination : `/messagesrc/edit/${id}/${messageId}`
    });
}