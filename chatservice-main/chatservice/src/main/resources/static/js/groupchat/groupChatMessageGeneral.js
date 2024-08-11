
let loadingMessage = false;

function bringLatestGCMessages(groupId){

    fetch(`/groupmessage/findlatest?groupId=${groupId}`, {
        method : "GET"
    }).then(function(resp){
        return resp.text();
    }).then(function(data){
        renderLatestMessages(JSON.parse(data));
    });

    const messageBox = document.querySelector(".messageBox");
    messageBox.addEventListener("scroll", scrollUpCheck);
}

function scrollUpCheck(){
    const scrollTop = this.scrollTop;

    console.log(this);
    const firstMessageId = this.querySelector(".message").getAttribute("id");

    console.log(scrollTop);
    console.log(firstMessageId);
    console.log(currentGroupId);

    if(scrollTop < 10){
        if(loadingMessage == true){
            return;
        }

        loadingMessage = true;

        fetch(`/groupmessage/findolder?groupId=${currentGroupId}&oldestMessageId=${firstMessageId}`,{
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