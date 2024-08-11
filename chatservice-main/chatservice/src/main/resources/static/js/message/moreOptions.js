
function openMoreOptions(messageUserId, message){
    console.log(message);
    console.log(messageUserId);

    const moreOptionsModal = message.querySelector(".moreOptionsModal");

    if(moreOptionsModal.classList.contains("noneDisplay")){ // 모달이 꺼져있는 상태면 킴
        moreOptionsModal.classList.remove("noneDisplay");
        moreOptionsModal.classList.add("blockDisplay");
    }else if(moreOptionsModal.classList.contains("blockDisplay")){ // 모달이 켜져있는 상태면 끄고 함수 종료
        moreOptionsModal.classList.remove("blockDisplay");
        moreOptionsModal.classList.add("noneDisplay");
        return;
    }
    
    const serssionId = document.getElementById("sessionId").value;

    if(serssionId == messageUserId){
        moreOptionsModal.querySelector(".messageEditButton").classList.remove("noneDisplay");
        moreOptionsModal.querySelector(".messageEditButton").classList.add("blockDisplay");
        moreOptionsModal.querySelector(".messageDeleteButton").classList.remove("noneDisplay");
        moreOptionsModal.querySelector(".messageDeleteButton").classList.add("blockDisplay");
    }
}

function closeMoreOptions(message){
    const moreOptionsModal = message.querySelector(".moreOptionsModal");
    moreOptionsModal.classList.remove("blockDisplay");
    moreOptionsModal.classList.add("noneDisplay");
}

function replyMessage(messageId){
    console.log(messageId);

    const targetMessage = document.getElementById(messageId);
    closeMoreOptions(targetMessage);
    const tartgetName = targetMessage.querySelector(".messageInfoUserName").innerText.trim();
    console.log(tartgetName);

    const replySpan = document.querySelector(".messageTextContainer > span");
    console.log(replySpan);

    replySpan.innerHTML = tartgetName + "에게 답글";

    const cancelReply = document.createElement("a");
    cancelReply.innerHTML = " (취소)";
    cancelReply.className = "cancel-reply";

    cancelReply.addEventListener("click", cancelReplyMessage);


    replySpan.appendChild(cancelReply);

    const messageReplyTo = document.getElementById("messageReplyTo");
    messageReplyTo.value = messageId;

}

function cancelReplyMessage(){

    const messageReplyTo = document.getElementById("messageReplyTo");
    messageReplyTo.value = null;

    const replySpan = document.querySelector(".messageTextContainer > span");
    replySpan.innerHTML = "";
}

function deleteMessage(messageId, id){
    console.log(messageId);
    const formData = new FormData();
    formData.append("messageId", messageId);

    fetch(`/message/delete`, {
        method : "DELETE",
        body : formData
    }).then(function(resp){
        return resp.text();
    }).then(function(data){
        console.log(data);
        sendDeleteMessage(data , id);
    });
}


// function editMessage(messageId, id){
//     console.log(messageId);

//     const message = document.getElementById(messageId);
//     const messageContent = message.querySelector(".displayMessageText");

//     const hiddenContent = message.querySelector(".hiddenContent");
//     const hiddenContentValue = hiddenContent.value;
//     const textArea = document.createElement("textarea");
//     textArea.value = hiddenContentValue;
//     messageContent.innerHTML = "";

//     const editButton = document.createElement("button");
//     const cancelButton = document.createElement("button");

//     editButton.innerHTML = "수정";
//     cancelButton.innerHTML = "취소";

//     editButton.addEventListener("click", editAction(messageId, id, textArea));
//     cancelButton.addEventListener("click", editCancelAction(messageContent, hiddenContentValue));

//     messageContent.append(textArea);
//     messageContent.append(editButton);
//     messageContent.append(cancelButton);

//     scrollAllTheWayDown();
//     closeMoreOptions(message);

// }


function editMessage(messageId, id){
    console.log(messageId);

    const message = document.getElementById(messageId);
    const messageContent = message.querySelector(".displayMessageText");

    const hiddenContent = message.querySelector(".hiddenContent");
    const hiddenContentValue = hiddenContent.value;
    const textArea = document.createElement("textarea");
    textArea.value = hiddenContentValue;
    textArea.className = "edit-textarea";
    messageContent.innerHTML = "";

    const editButton = document.createElement("button");
    const cancelButton = document.createElement("button");

    editButton.innerHTML = "수정";
    cancelButton.innerHTML = "취소";

    editButton.className = "edit-button";
    cancelButton.className = "cancel-button";

    editButton.addEventListener("click", editAction(messageId, id, textArea));
    cancelButton.addEventListener("click", editCancelAction(messageContent, hiddenContentValue));

    messageContent.append(textArea);
    messageContent.append(editButton);
    messageContent.append(cancelButton);

    scrollAllTheWayDown();
    closeMoreOptions(message);

}



function editAction(messageId, id, textArea){
    return function(){
        console.log("edit");
        console.log(textArea.value);

        const editedContent = textArea.value;
        const formData = new FormData();
        formData.append("messageId", messageId);
        formData.append("editedContent", editedContent);
        fetch("/message/update", {
            method : "PUT",
            body : formData
        }).then(function(resp){
            return resp.text();
        }).then(function(data){
            sendEditMessage(data, id);
        });
    };
}

function editCancelAction(messageContent, messageContentValue){

    return function(){
        messageContent.innerHTML = "";
        messageContent.innerHTML = messageContentValue;
    };
}