console.log("grabLatest.js");

function getMessageDOM(message){

    const messageTemplate = document.getElementById("messageTemplate");

    const messageDOM = messageTemplate.cloneNode(true);
    messageDOM.removeAttribute("id");
    const messageId = message.displayMessageEntity.displayMessageId;
    messageDOM.setAttribute("id", messageId);
    messageDOM.style.display = "block";
    messageDOM.setAttribute("class", "message");

    const generalId = message.displayMessageEntity.displayMessageChannel != null ?
    message.displayMessageEntity.displayMessageChannel : message.displayMessageEntity.displayMessageGroupChat;

    console.log("generalId: " + generalId);

    const messageReplyToInfo = messageDOM.querySelector(".messageReplyToInfo");
    messageReplyToInfo.innerHTML = getMessageReplyToInfo(message.displayMessageEntity);

    const userPfp = messageDOM.querySelector(".messageInfoUserPfp");
    userPfp.src = `/media/userpfp/${message.displayMessageEntity.displayMessagePfp}`;

    const userName = messageDOM.querySelector(".messageInfoUserName");
    userName.innerHTML = '&nbsp;&nbsp;' + message.displayMessageEntity.displayMessageUserName;

    const regDate = messageDOM.querySelector(".messageInfoRegDate");

    const utcDate = new Date(message.displayMessageEntity.displayMessageRegDate);
    const koreanDate = new Date(utcDate.getTime() + (utcDate.getTimezoneOffset() * 60000) + (9 * 3600000));
    const formattedDate =
    ('00' + koreanDate.getFullYear()).slice(-2) + '/' +
    ('0' + (koreanDate.getMonth() + 1)).slice(-2) + '/' +
    ('0' + koreanDate.getDate()).slice(-2) + ' ' +
    ('0' + koreanDate.getHours()).slice(-2) + ':' +
    ('0' + koreanDate.getMinutes()).slice(-2);

    regDate.innerHTML = '&nbsp;&nbsp;' + formattedDate;

    const displayMessageText = messageDOM.querySelector(".displayMessageText");
    displayMessageText.innerHTML = getMessageContent(message.displayMessageEntity);

    const displayMessageMedia = messageDOM.querySelector(".displayMessageMedia");

    for(messageMedium of message.messageMediaEntities){
        let mediumDom = null;
        if(messageMedium.messageMediaType.includes("video")){ //영상일 때
            mediumDom = document.createElement("video");
            mediumDom.setAttribute("controls", "controls");
            mediumDom.addEventListener("click", videoExpand); // 영상 확대 기능 추가
        }else if(messageMedium.messageMediaType.includes("image")){ //이미지일 때
            mediumDom = document.createElement("img");
            mediumDom.addEventListener("click", imageExpand); // 이미지 확대 기능 추가
        }

        mediumDom.src = `/media/message/${messageMedium.messageMediaFileName}`;
        mediumDom.height = 150;

        displayMessageMedia.appendChild(mediumDom);
    }

    const messageUserId = message.displayMessageEntity.displayMessageUser;

    const messageUser = document.createElement("input");
    messageUser.setAttribute("type", "hidden");
    messageUser.setAttribute("class", "messageUserId");
    messageUser.value = messageUserId;
    messageDOM.appendChild(messageUser);

    // 더보기 버튼 기능
    const moreOptions = messageDOM.querySelector(".moreOptions");
    moreOptions.addEventListener("click", function(){
        openMoreOptions(messageUserId, messageDOM);
    });

    const moreOptionsModal = messageDOM.querySelector(".moreOptionsModal");

    const replyButton = document.createElement("div");
    replyButton.setAttribute("class", "messageReplyButton");
    replyButton.innerHTML = "답글 달기";
    replyButton.addEventListener("click", function(){
        replyMessage(messageId);
    });
    moreOptionsModal.appendChild(replyButton);


    const deleteButton = document.createElement("div");
    deleteButton.setAttribute("class", "messageDeleteButton noneDisplay");
    deleteButton.innerHTML = "삭제하기";
    deleteButton.addEventListener("click", function(){
        deleteMessage(messageId, generalId);
    });
    moreOptionsModal.appendChild(deleteButton);

    const editButton = document.createElement("div");
    editButton.setAttribute("class", "messageEditButton noneDisplay");
    editButton.innerHTML = "수정하기";
    editButton.addEventListener("click", function(){
        editMessage(messageId, generalId);
    });
    moreOptionsModal.appendChild(editButton);

    const hiddenContent = document.createElement("input");
    hiddenContent.setAttribute("type", "hidden");
    hiddenContent.setAttribute("class", "hiddenContent");
    hiddenContent.value = message.displayMessageEntity.displayMessageContent;
    messageDOM.appendChild(hiddenContent);

    return messageDOM;
}

function imageExpand(){ // 이미지 클릭시 확대
    console.log("imgage expand");
    console.log(this.src);

    const expandedImage = document.querySelector(".expandedImage");
    expandedImage.classList.remove("noneDisplay");
    expandedImage.classList.add("blockDisplay");

    const expandedImageContent = document.querySelector(".expandedImageContent");
    expandedImageContent.src = this.src;

    const darkCover = document.querySelector(".darkCover");
    darkCover.classList.remove("noneDisplay");
    darkCover.classList.add("blockDisplay");

    const closeExpandedImage = expandedImage.querySelector(".closeExpandedImage");
    closeExpandedImage.classList.remove("noneDisplay");
    closeExpandedImage.classList.add("blockDisplay");
}

function videoExpand(){
    const expandedVideo = document.querySelector(".expandedVideo");
    expandedVideo.classList.remove("noneDisplay");
    expandedVideo.classList.add("blockDisplay");

    console.log(this);
    this.play();
    const expandedVideoContent = document.querySelector(".expandedVideoContent");
    expandedVideoContent.src = this.src;

    const darkCover = document.querySelector(".darkCover");
    darkCover.classList.remove("noneDisplay");
    darkCover.classList.add("blockDisplay");

    const closeExpandedImage = expandedVideo.querySelector(".closeExpandedImage");
    closeExpandedImage.classList.remove("noneDisplay");
    closeExpandedImage.classList.add("blockDisplay");
}

function closeExpandedImage(){

    const expandedImage = document.querySelector(".expandedImage");
    expandedImage.classList.remove("blockDisplay");
    expandedImage.classList.add("noneDisplay");

    const expandedVideo = document.querySelector(".expandedVideo");
    expandedVideo.classList.remove("blockDisplay");
    expandedVideo.classList.add("noneDisplay");

    const closeExpandedImage = document.querySelector(".closeExpandedImage");
    closeExpandedImage.classList.remove("blockDisplay");
    closeExpandedImage.classList.add("noneDisplay");

    const darkCover = document.querySelector(".darkCover");
    darkCover.classList.remove("blockDisplay");
    darkCover.classList.add("noneDisplay");

    const expandedImageContent = document.querySelector(".expandedImageContent");
    expandedImageContent.src = null;

    const expandedVideoContent = document.querySelector(".expandedVideoContent");
    expandedVideoContent.src = null;
}

function getMessageReplyToInfo(message){
    const replyTo = message.displayMessageReplyTo;

    if(replyTo == null){
        return "";
    }

    const targetMessage = document.getElementById(replyTo);

    if(targetMessage == null){
        return "메세지를 불러올 수 없었습니다";
    }

    const tartgetName = targetMessage.querySelector(".messageInfoUserName").innerText.trim();
    const tartgetContent = targetMessage.querySelector(".hiddenContent").value;

    const resultStr = `--> ${tartgetName}: ${tartgetContent}`;
    return resultStr;
}

// function getMessageContent(message){

//     let content = message.displayMessageContent;
//     if(message.displayMessageEdited == 1){
//         content += "(수정됨)";
//     }

//     return content;
// }

function getMessageContent(message) {
    let content = message.displayMessageContent;
    if (message.displayMessageEdited == 1) {
        content += " <span class='message-edited'>(수정됨)</span>";
    }
    return content;
}

function displayMessage(message) {
    const messageElement = document.getElementById(message.messageId);
    const displayMessageTextElement = messageElement.querySelector(".displayMessageText");

    displayMessageTextElement.innerHTML = getMessageContent(message);
}

function appendMessage(message){

    const messageDOM = getMessageDOM(message);
    const messageBox = document.querySelector(".messageBox");

    messageBox.appendChild(messageDOM);

    const scrollHeight = messageBox.scrollHeight; // 메세지 박스 전체 높이
    const scrollTop = messageBox.scrollTop; // 스크롤 위치
    const offsetHeight = messageBox.offsetHeight // 현재 보이는 메세지 박스 높이
    const difference = Math.abs(offsetHeight + scrollTop - scrollHeight)  // 스크롤 다 내리기 까지 남은 값

    console.log("diff: " + difference);
    if(difference < 200){ // 좀금 남았을때 스크롤 다내림
        messageBox.scrollBy(0, scrollHeight + 500);
    }
}

function scrollAllTheWayDown(){
    const messageBox = document.querySelector(".messageBox"); 
    messageBox.scrollBy(0, messageBox.scrollHeight);
}

function renderLatestMessages(messages){

    const messageBox = document.querySelector(".messageBox");

    for(message of messages){
        const messageDOM = getMessageDOM(message);
        messageBox.appendChild(messageDOM);
    }

    scrollAllTheWayDown();
}

function renderPreviousMessages(messages){
    const messageBox = document.querySelector(".messageBox");

    for(message of messages){
        const messageDOM = getMessageDOM(message);
        messageBox.prepend(messageDOM);
    }
}