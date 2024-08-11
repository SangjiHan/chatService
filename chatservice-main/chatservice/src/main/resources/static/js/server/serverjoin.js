function searchServer() {
    console.log("serverjoin");

    const serverAdd = document.getElementById("serverAdd");
    serverAdd.style.display = "block";
    serverAdd.in

    serverJoin();
    }
    
function closeserverAdd(){
    const serverAdd = document.getElementById("serverAdd");
    serverAdd.style.display = "none";
}

function serverJoin() {
    console.log("serverlist");
    const userId = document.getElementById("sessionIdVal").value;
    console.log(userId);

    const serverTotalArea = document.getElementById("serverTotalArea");
    serverTotalArea.innerHTML = '';

    fetch('/server/findallexcludejoined?appuserId=' + userId, {

    }).then(resp => resp.json())
    .then(data => {
        console.log(data);
        const serverListTotal = document.createElement("div");
        serverListTotal.id = "serverListTotal";
        serverListTotal.innerHTML = '';

        for (let i = 0; i < data.length; i++) {
            // 각 서버 요소들을 생성합니다.
            const serverList = document.createElement("div");
            serverList.id = "serverListArray";

            // 서버 프로필 이미지
            const serverPfpDiv = document.createElement("div");
            serverPfpDiv.id = "serverListPfpDiv";
            const serverPfp = document.createElement("img");
            serverPfp.src = '/media/serverpfp/' + data[i].serverPfp;
            serverPfpDiv.appendChild(serverPfp);

            // 서버 이름
            const serverName = document.createElement("div");
            serverName.innerHTML = data[i].serverName;
            serverName.id = "serverListName";

            // 서버 설명
            const serverDesc = document.createElement("div");
            serverDesc.innerHTML = data[i].serverDesc;
            serverDesc.id = "serverListDesc";

            // 서버 참가 버튼
            const serverJoinBtn = document.createElement("button");
            serverJoinBtn.innerHTML = "참가";
            serverJoinBtn.id = "serverJoinBtn";

            serverList.appendChild(serverPfpDiv);
            serverList.appendChild(serverName);
            serverList.appendChild(serverDesc);
            serverList.appendChild(serverJoinBtn);

            // 생성된 서버 요소를 serverListTotal에 추가합니다.
            serverListTotal.appendChild(serverList);

            //서버 정보 저장
            const userId = document.getElementById("sessionIdVal").value;
            const serverId = data[i].serverId;
            const serverOwner = data[i].serverOwner;

            
            serverJoinBtn.addEventListener('click', function() {
                serverMemberJoin(userId, serverId);
                serverList.style.display = "none";
                
                setTimeout(function() {
                    window.location.reload();
                }, 300); 
            });

            }
            serverTotalArea.appendChild(serverListTotal);
        })
}

function serverMemberJoin(userId, serverId) {
    const serverJoinForm = document.querySelector("#serverJoinForm");

    serverJoinForm.querySelector("#serverMemberUser").value = userId;
    serverJoinForm.querySelector("#serverMemberServer").value = serverId;

    const formData = new FormData(serverJoinForm);

    fetch('/servermember/create', {
        method: 'POST',
        body: formData
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Failed to create server member');
        }
        return response.json();
    })
    .then(data => {
        console.log('Server member created successfully:', data);
    })
}
