console.log("serverlist");

window.addEventListener("load", function(){
    //getServerList();
});

function getServerList(){
    const user = document.getElementById("sessionId").value;
    console.log(user);
    const url = `/server/listbyuser?user=${user}`;

    fetch(url, {
        method: "GET"
    }).then(function(resp){
        return resp.json();
    }).then(function(data){
        renderMyServers(data);
    });
}

function renderMyServers(serverList){
    console.log(serverList);

    const serverContainer = document.querySelector(".serverContainer");
    serverContainer.innerHTML = "";

    for(server of serverList){
        const serverTag = document.createElement("div");
        serverTag.setAttribute("class", "server");
        serverTag.setAttribute("id", server.serverId);

        console.log(server);

        const a = document.createElement("a");
        a.href = `/server/${server.serverId}`;

        const img = document.createElement("img");
        img.height = 50;
        img.width = 50;
        img.style.borderRadius = '25px';
        img.src = `/media/serverpfp/${server.serverPfp}`;

        a.appendChild(img);
        serverTag.appendChild(a);
        serverContainer.appendChild(serverTag);
    }
}