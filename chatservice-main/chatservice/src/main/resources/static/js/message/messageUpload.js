
document.addEventListener("DOMContentLoaded", function(){
    console.log("oh load")
    document.getElementById("fileInput")
    .addEventListener("input", displaySelectedMedia); // 미디어 골를 때마다 실행

});



function openMediaSelection(){
    console.log("open media");
    document.getElementById("fileInput").click();
}

function displaySelectedMedia(){
    console.log("mediaselectionInput");

    const files = document.getElementById("fileInput").files;
    console.log(files);

    const messageMediaDisplayBox = document.querySelector(".messageMediaDisplayBox");
    messageMediaDisplayBox.innerHTML = "";

    let index = 0;
    for(file of files){
        
        console.log(file);
        const mediaDiv = document.createElement("div");

        const cancelButton = document.createElement("a");
        cancelButton.innerHTML = "X";
        cancelButton.setAttribute("class", "mediaCancelButton");
        cancelButton.addEventListener("click", removeMedia(index));
        cancelButton.style.fontSize = '10px';

        console.log("file.type: " + file.type);

        let mediaObj = null;
        if(file.type.includes("image")){
            mediaObj = document.createElement("img");
        }

        if(file.type.includes("video")){
            mediaObj = document.createElement("video");
        }
        mediaObj.src = URL.createObjectURL(file);
        mediaObj.style.height = "100px";

        mediaDiv.appendChild(cancelButton);
        mediaDiv.appendChild(mediaObj);
        
        messageMediaDisplayBox.appendChild(mediaDiv);

        index++;
    }
}

function removeMedia(index){
    return function(){
        console.log("remove media");
        console.log(index);

        const dt = new DataTransfer();
        const input = document.getElementById("fileInput");
        const files = input.files;

        console.log(input);
        console.log(files);

        for(let i = 0; i < files.length; i++){
            const file = files[i];

            if(index !== i){
                dt.items.add(file);
            }
        }

        input.files = dt.files;

        displaySelectedMedia();
    }
}

function submitMessage(){ // 메세지 서브미트

    const form = document.getElementById("messageForm");
    const formData = new FormData(form);

    const messageText = document.querySelector(".messageText");
    const messageMediaDisplayBox = document.querySelector(".messageMediaDisplayBox");

    messageText.value = "";
    messageMediaDisplayBox.innerHTML = "";

    const dt = new DataTransfer();
    document.getElementById("fileInput").files = dt.files;

    console.log(formData.get("messageChannel"));
    
    //체널이 아니면 구룹 아이디 값을 
    if(formData.get("messageChannel") == null){
        formData.append("messageGroupChat", currentGroupId);
    }

    
    fetch("/message/create", {
        method : "POST",
        body : formData
    }).then(function(resp){
        return resp.text();
    }).then(function(text){
        console.log(JSON.parse(text));
        const message = JSON.parse(text);
        const messageId = message.messageEntity.messageId
        console.log(messageId);
        if(message.messageEntity.messageGroupChat != null){
            console.log('gc');
            sendGCMessage(messageId);
        }

        if(message.messageEntity.messageChannel != null){
            console.log("ch");
            sendMessage(messageId)
        }
    });

    cancelReplyMessage();
}

