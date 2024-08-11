let loadingMessage = false;

document.addEventListener("DOMContentLoaded", function(){

    const channelId = document.getElementById("currentChannelId").value;

    fetch(`/displaymessage/findlatest?channelId=${channelId}`, {
        method : "GET"
    }).then(function(resp){
        return resp.text();
    }).then(function(data){
        console.log(JSON.parse(data));
        renderLatestMessages(JSON.parse(data));
    }); 

    enableMoreOptionsClients(channelId);

    const messageBox = document.querySelector(".messageBox");
    messageBox.addEventListener("scroll", scrollUpCheck(channelId));
});

function scrollUpCheck(channelId){

    return function(){
        const scrollHeight = this.scrollHeight;
        const scrollTop = this.scrollTop;
        /*console.log("scrollHeight: " + scrollHeight);
        console.log("scrollTop: " + scrollTop);
        console.log("channelId: " + channelId);*/

        const firstMessageId = this.querySelector(".message:first-child").getAttribute("id");
        //console.log(firstMessageId);

        if(scrollTop < 10){
            console.log("bringing older ones");

            if(loadingMessage == true){
                return;
            }

            loadingMessage = true;

            fetch(`/displaymessage/findolder?channelId=${channelId}&oldestMessageId=${firstMessageId}`, {
                method : "GET"
            }).then(function(resp){
                return resp.text();
            }).then(function(data){
                const messages = JSON.parse(data);
                console.log(messages);
                renderPreviousMessages(messages);
                loadingMessage = false;
            });
        }
    }
}

