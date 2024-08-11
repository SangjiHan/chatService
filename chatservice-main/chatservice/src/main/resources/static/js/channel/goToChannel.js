
console.log("gotochannel");

function goToChannel(channel){
    console.log(channel);
    const channelName = channel.textContent;
    console.log(channelName);

    const chatChannelName = document.querySelector(".chatChannelName");
    chatChannelName.textContent = channelName;

    const serverId = document.getElementById("serverId").value;
    const channelId = channel.getAttribute("id");
    const href = `/server/${serverId}/channel/${channelId}`;

    console.log(serverId);

    location.href = href;

}
