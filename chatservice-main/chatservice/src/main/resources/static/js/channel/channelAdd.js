

function openChannelModal(){
    document.getElementById("channelAddModalContainer").style.display = "block";
}

function closeChannelModal(){
    document.getElementById("channelAddModalContainer").style.display = "none";
}

function addChannel(e){
    e.preventDefault();
    const form = document.getElementById("channelAddForm");
    const formData = new FormData(form);

    fetch("/channel/create", {
        method: "POST",
        body : formData
    }).then(function(resp){
        return resp.text();
    }).then(function(data){
        console.log(data);
        handleChannelAddResult(data);
    })
}

function handleChannelAddResult(result){
    if(result == "name already taken"){
        alert("중복된 채널 명입니다.");
        return;
    }

    const channel = makeChannelTag(JSON.parse(result));
    const channelContainer = document.getElementById("channelContainer");


    channelContainer.append(channel);

    const channels = document.querySelectorAll(".chatChannel");
    channels.forEach(channel => {
        channel.addEventListener("click", function() {
            channels.forEach(ch => ch.classList.remove('selectedChannel'));
            channel.classList.add('selectedChannel');

            const contentId = channel.getAttribute("data-content");
            updateContent(contentId);
        });
    });

    closeChannelModal();
}

function makeChannelTag(channel){
    const channelTag = document.createElement("div");
    channelTag.setAttribute("class", "chatChannel");
    channelTag.setAttribute("id", channel.channelId);
    channelTag.setAttribute("onclick", "goToChannel(this)");
    channelTag.innerHTML = channel.channelName;
    return channelTag;
}

