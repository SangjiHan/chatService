let currentGroupId = null;

function directMessage() {
    console.log("dm");
    const dmModal = document.getElementById("dmModal");

    dmModal.style.display = "block";

    const groupchat = document.getElementById("groupChatScreen");
    groupchat.style.display = "none";

    const user = document.getElementById("sessionId").value;

    fetch('/friend/list?requestUser='+user, {
        method: "GET"
    }).then(resp => resp.json())
    .then(data => {
        console.log(data);
        const friendlist = [];

        for(let i=0; i<data.length; i++){
            friendlist.push(data[i].friend.friend2);
        }
        dmFriendList(friendlist);
    })
}

function dmFriendList(friendlist) {
    const queryParams = friendlist.map(id => `ids=${encodeURIComponent(id)}`).join('&');
    const url = `/user/listbylist?${queryParams}`;

    fetch(url, {
        method: "GET"
    })
    .then(resp => resp.json())
    .then(data => {
        console.log(data);

        const dmModal = document.getElementById("dmModalList");
        dmModal.innerHTML = '';


        for (let i = 0; i < data.length; i++) {
            // dmFriendAllList 및 dmFriendList 생성
            const dmFriendAllList = document.createElement('div');
            dmFriendAllList.id = `dmFriendAllList-${i}`; 
            dmFriendAllList.className = "dmFriendAllList";
            
            const dmFriendList = document.createElement('div');
            dmFriendList.id = `dmFriendList-${i}`;
            dmFriendAllList.className = "dmFriendList";

            // 프로필 사진 영역 생성
            const dmFriendPfpArea = document.createElement('div');
            dmFriendPfpArea.id = `dmFriendPfpArea-${i}`;
            dmFriendPfpArea.className = "dmFriendPfpArea";
            const dmFriendPfp = document.createElement('img');
            if (data[i].appuserGenPfp === null) {
                dmFriendPfp.src = '/media/userpfp/logo.png';
            } else {
                dmFriendPfp.src = '/media/userpfp/' + data[i].appuserGenPfp;
            }
            dmFriendPfp.style.width = "50px";
            dmFriendPfp.id = `dmFriendPfp-${i}`;
            dmFriendPfp.className = "dmFriendPfp";
            dmFriendPfpArea.appendChild(dmFriendPfp);

            // dmFriendList에 프로필 사진 영역 추가
            dmFriendList.appendChild(dmFriendPfpArea);

            // 표시 이름 추가
            const dmFriendDisplayName = document.createElement('div');
            dmFriendDisplayName.innerHTML = data[i].appuserDisplayName;
            dmFriendDisplayName.id = `dmFriendDisplayName-${i}`;
            dmFriendDisplayName.className = "dmFriendDisplayName";
            dmFriendList.appendChild(dmFriendDisplayName);

            // 체크박스 추가
            const checkbox = document.createElement('input');
            checkbox.type = 'checkbox';
            checkbox.value = data[i].appuserId;
            checkbox.id = `dmFriendListCheckbox-${i}`; 
            checkbox.className = "dmFriendListCheckbox";
            dmFriendList.appendChild(checkbox);

            // dmFriendAllList에 dmFriendList 추가
            dmFriendAllList.appendChild(dmFriendList);

            // dmModal에 dmFriendAllList 추가
            dmModal.appendChild(dmFriendAllList);

            checkbox.addEventListener('change', function() {
                if (checkbox.checked) {
                    // 선택된 경우 displayName 가져오기
                    const selectedDisplayName = data[i].appuserDisplayName;
                    console.log("Selected displayName:", selectedDisplayName);
                    // 여기서 원하는 동작 수행
                }
            });
        }

        

        const dmSubmitListBtn = document.createElement("button");
        dmSubmitListBtn.innerHTML = '다이렉트 메세지';
        dmSubmitListBtn.id = "dmSubmitListBtn";

        dmModal.appendChild(dmSubmitListBtn);

        dmSubmitListBtn.addEventListener('click', function() {
            groupchat();
            window.location.reload();
        });
    });
}

function groupchat() {
    const groupchat = document.getElementById("groupChatScreen");
    groupchat.style.display = "block";
    groupchat.innerHTML = '';

    const dmModal = document.getElementById("dmModal");
    dmModal.style.display = "none";

    const dmFriendAllLists = document.querySelectorAll('[id^="dmFriendAllList-"]');
    const selectedUserIds = [];

    dmFriendAllLists.forEach(dmFriendAllList => {
        const checkboxes = dmFriendAllList.querySelectorAll('input[type="checkbox"]:checked');
        checkboxes.forEach(checkbox => {
            selectedUserIds.push(checkbox.value);
        });
    });

    console.log(selectedUserIds);

    const user = document.getElementById("sessionId").value;
    document.getElementById("dminviter").value = user;
    document.getElementById("dminvitees").value = selectedUserIds;

    const formData = new FormData();
    formData.append('inviter', user); 
    selectedUserIds.forEach(id => {
        formData.append('invitees', id); 
    });


    fetch('/groupchat/create', {
        method: 'POST',
        body: formData,
    })
    .then(response => response.json())
    .then(data => {
        console.log('Group chat created:', data);
    })
    .catch(error => {
        console.error('Error creating group chat:', error);
    });
}

window.onload = function() {
    console.log('grouplist');
    const user = document.getElementById("sessionId").value;

    fetch('/groupchat/listbyuser?appuserId=' + user, {
        method: 'GET',
    })
    .then(response => response.json())
    .then(data => {
        data.sort((a, b) => {
            if (a.groupChatName < b.groupChatName) {
                return -1;
            }
            if (a.groupChatName > b.groupChatName) {
                return 1;
            }
            return 0;
        });
        
        const groupchatList = document.getElementById("groupchatList");

        for(let i = 0; i < data.length; i++) {
            const groupchatIndex = document.createElement("div");
            groupchatIndex.className = "groupchatIndex";
            groupchatIndex.id = "groupchatIndex" + i;

            groupchatIndex.value = data[i].groupChatId;

            if (data[i].groupChatName.length>16) {
                groupchatIndex.innerHTML = data[i].groupChatName.slice(0,16) + ' ...';
            } else {
                groupchatIndex.innerHTML = data[i].groupChatName;
            }

            
            groupchatList.appendChild(groupchatIndex);

            (function(groupId, groupChatName) {
                groupchatIndex.addEventListener('click', function() {
                    groupchatScreen(groupId, groupChatName);
                });
            })(data[i].groupChatId, data[i].groupChatName);

            groupchatIndex.addEventListener('click', function() {
                selectDmlist(this); 
            });

        }
    })
    .catch(error => {
        console.error('Error fetching group chat list:', error);
    });
}

function groupchatScreen(groupId, groupChatName) {
    console.log(groupId);
    console.log(groupChatName);
    currentGroupId = groupId;

    groupChatClient.deactivate();
    groupChatClient.activate();

    disableMoreOptionsClients();
    enableMoreOptionsClients(groupId);

    //메세지 박스 초기화
    const messageTemplate = document.getElementById("messageTemplate");
    console.log(messageTemplate);
    const messageBox = document.querySelector(".messageBox");
    messageBox.innerHTML = "";
    messageBox.appendChild(messageTemplate);

    const groupChating = document.getElementById("groupChatScreen");
    groupChating.style.display = "block";

    const groupchatId = document.getElementById("groupchatId");
    groupchatId.value = groupId;

    // 최근 메세지 가져오기
    bringLatestGCMessages(groupId);

    fetch('/groupchatuser/userlistbygroupid?groupId='+ groupId, {
        method: 'GET'
    })
    .then(response => response.json())
    .then(data => {
        console.log(data);

        const user = document.getElementById("sessionId").value;

        const groupchatFriendList = document.getElementById("groupchatFriendList");
        groupchatFriendList.innerHTML = '';

        const groupChatHead = document.getElementById("groupChatHead");
        groupChatHead.textContent = groupChatName;


        for (let i = 0; i < data.length; i++) {
            if (data[i].appuserId !== user) {
                const groupchatUser = document.createElement("div");
                groupchatUser.id = "groupchatUser";

                //프로필 사진
                const groupchatUserPfpArea = document.createElement("div");
                groupchatUserPfpArea.id = "groupchatUserPfpArea";

                const groupchatUserPfp = document.createElement("img");
                groupchatUserPfp.id = "groupchatUserPfp";
                
                if(data[i].appuserGenPfp === null) {
                    groupchatUserPfp.src = '/media/userpfp/logo.png';
                } else {
                    groupchatUserPfp.src = '/media/userpfp/' + data[i].appuserGenPfp;
                }

                groupchatUserPfp.style.width = '50px';

                groupchatUserPfpArea.appendChild(groupchatUserPfp);

                //디스플레이 이름
                const groupchatUserDisplayName = document.createElement('div');
                groupchatUserDisplayName.id = "groupchatUserDisplayName";

                groupchatUserDisplayName.innerHTML = data[i].appuserDisplayName;

                //고유 이름
                const groupchatUserUkName = document.createElement('div');
                groupchatUserUkName.id = "groupchatUserUkName";

                groupchatUserUkName.innerHTML = data[i].appuserUkName;

                groupchatUser.appendChild(groupchatUserPfpArea);
                groupchatUser.appendChild(groupchatUserDisplayName);
                groupchatUser.appendChild(groupchatUserUkName);

                groupchatFriendList.appendChild(groupchatUser);

                (function(index) {
                    groupchatUser.addEventListener('click', function() {
                        console.log('click');
                        var id = "groupchatUser" + index;
                        var userData = data[index].appuserId;
                        
                        showLocation(event, id, userData);
                    });
                })(i); 
                
                }
            };

            
        }

        
    
    )
}

function mainFriend() {
    const groupChating = document.getElementById("groupChatScreen");
    groupChating.style.display = "none";
}

function closeDmModal() {
    const dmModal = document.getElementById("dmModal");
    dmModal.style.display = "none";
}

//dm리스트 클릭
//selectDmlist(this); 

function selectDmlist(element) {
    let servers = document.querySelectorAll('.groupchatIndex');
    servers.forEach(server => {
        server.classList.remove('selectedDmlist');
    });

    element.classList.add('selectedDmlist'); 

    console.log(servers);
}