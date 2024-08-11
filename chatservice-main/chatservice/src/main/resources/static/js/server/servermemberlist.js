window.onload = function() {
    serverMemberList();
};

function serverMemberList() {

    const serverId = document.getElementById("serverId").value;

    fetch('/servermember/listbyserver?serverId=' + serverId, {
        method: "GET"
    })
    .then(resp => resp.json())
    .then(data => {
        
        const ListArea = document.getElementById("servermemberList");
        ListArea.innerHTML = '';

        for (let i = 0; i < data.length; i++) {
            const servermemberDiv = document.createElement('div');
            servermemberDiv.id= "servermemberDiv";
            const servermemberPfpDiv = document.createElement('div');
            const servermemberPfp = document.createElement('img');
            if (data[i].serverMemberPfp === null) {
                servermemberPfp.src = '/media/userpfp/logo.png'; // 경로가 잘못된 경우 수정 필요
            } else {
                servermemberPfp.src = '/media/userpfp/' + data[i].serverMemberPfp;
            }
            servermemberPfpDiv.id = "servermemberPfpDiv";
            servermemberPfpDiv.appendChild(servermemberPfp);

            const servermemberName = document.createElement('div');
            servermemberName.innerHTML = data[i].serverMemberUserName;
            servermemberName.id = "servermemberName";

            servermemberDiv.appendChild(servermemberPfpDiv);
            servermemberDiv.appendChild(servermemberName);

            ListArea.appendChild(servermemberDiv);

            (function(index) {
                servermemberDiv.addEventListener('click', function() {
                    console.log('click');
                    var id = "groupChatList" + index;
                    var userData = data[index].appuserId;
                    
                    showLocation(event, id, userData);
                });
            })(i); 
        }
    })
    .catch(error => {
        console.error('Error fetching server members:', error);
        // 오류 발생 시 적절히 처리할 수 있습니다.
    });
}