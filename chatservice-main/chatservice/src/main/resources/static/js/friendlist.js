function getFriendListId() {
    //화면 가리거나 보이게
    const friendAllList = document.getElementById("friendAllList");
    friendAllList.style.display = "block";

    const friendWaitingList = document.getElementById("friendWaitingList");
    friendWaitingList.style.display = "none";

    //테이블 초기화
    const friendList = document.getElementById("friendTotalList");
    friendList.innerHTML = '';

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
        getFriendList(friendlist);
    })
}

//친구 리스트
function getFriendList(friendlist) {
    const queryParams = friendlist.map(id => `ids=${encodeURIComponent(id)}`).join('&');
    const url = `/user/listbylist?${queryParams}`;

    fetch(url, {
        method: "GET"
    })
    .then(resp => resp.json())
    .then(data => {
        data.sort((a, b) => {
            if (a.appuserDisplayName < b.appuserDisplayName) {
              return -1; // a를 b보다 앞으로 정렬
            }
            if (a.appuserDisplayName > b.appuserDisplayName) {
              return 1; // a를 b보다 뒤로 정렬
            }
            return 0; // 순서를 변경하지 않음
          });

          
        console.log(data);


        const friendListContainer = document.getElementById("friendTotalList");

        data.forEach((friend, index) => {
            const friendDiv = document.createElement("div");
            friendDiv.className = "friendDiv";
            friendDiv.id = "friend" + index;

            const friendPfp = document.createElement("img");
            friendPfp.setAttribute('id', 'friendPfp');

            if (friend.appuserGenPfp === null) {
                friendPfp.src = '/media/userpfp/logo.png'; // 경로가 잘못된 경우 수정 필요
            } else {
                friendPfp.src = '/media/userpfp/' + friend.appuserGenPfp;
            }
            friendPfp.style.width = "50px";
            friendPfp.style.height = "50px";
            friendPfp.style.borderRadius = "50%";

            friendDiv.appendChild(friendPfp);

            const friendDisplayName = document.createElement("span");
            friendDisplayName.innerHTML = friend.appuserDisplayName;
            friendDisplayName.setAttribute('id', 'friendDisplayName');

            const friendUkName = document.createElement("span");
            friendUkName.innerHTML = friend.appuserUkName;
            friendUkName.setAttribute('id', 'friendUkName');

            friendDiv.appendChild(friendDisplayName);
            friendDiv.appendChild(friendUkName);

            const friendChat = document.createElement("div");
            friendChat.setAttribute('id', 'friendchatBtnDiv');
            const tdBtn = document.createElement('div');
            tdBtn.innerHTML = '채팅';
            tdBtn.setAttribute('id', 'friendchatBtn');
            friendChat.appendChild(tdBtn);

            tdBtn.addEventListener('click',function(){
                const friendId = friend.appuserId;
                friendDM(friendDisplayName,friendId);
            })

            friendDiv.appendChild(friendChat);

            friendListContainer.appendChild(friendDiv);

            // 클릭 이벤트 리스너 등록
            friendPfp.addEventListener('click', function() {
                console.log('click');
                // 클릭 이벤트 처리 코드 추가
                var id = "friend" + index;
                var userData = data[index].appuserId; 
            
                showLocation(event, id, userData);
            });

        });
    })
    .catch(error => {
        console.error('Error fetching data:', error);
    });
}


//창닫기
function closeFriendView() {
    const friendViewModal = document.getElementById("friendViewModal");
    friendViewModal.style.display = "none";
}
