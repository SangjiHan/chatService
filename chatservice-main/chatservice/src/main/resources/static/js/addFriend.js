// function friendAdd() {
//     console.log("Add friend");
//     const addFriendMain = document.getElementById("addFriendMain");
//     addFriendMain.style.display = "block";
// }

function friendAdd() {
    toggleVisibility('addFriendMain', 'createServerMain');
  }

function closeFriendAdd() {
    const friendAddMain = document.getElementById("addFriendMain");
    friendAddMain.style.display = "none";
}

//친구 추가
function findFriendInput() {
    console.log("select");
    console.log(type.value);
    const friendCheck = document.getElementById("checkFriend");
    friendCheck.style.opacity = "0";

    const friendTable = document.getElementById("friendInfo");
    friendTable.innerHTML = '';

    if(type.value == "null") {
        console.log("disable");
        //preventDefault();
    
    } 

    //이메일로 찾기
    else if(type.value == "findByEmail") {
        console.log("select email");
        const addFriendVal = document.querySelector("#addFriendInput").value;
  

        fetch("/user/findbyemail?keyword="+addFriendVal, {
            method:'GET',
        }).then(resp => resp.json()).then(data => {
    
            checkFriend(data);
            const friendTable = document.getElementById("checkFriend");

            friendTable.style.opacity = "1";

            
            document.getElementById("toSend").value = data.appuserId;
            const toSendId = document.getElementById("toSend");
  
        })
        .catch(error => {
            const friendTable = document.getElementById("checkFriend");
            friendTable.style.opacity = "0";
        });

    } 
    
    //고유이름으로 찾기
    else if(type.value =="findByName") {
        
        console.log("select name");
        const addFriendVal = document.querySelector("#addFriendInput").value;


        fetch("/user/findbyukname?keyword="+addFriendVal, {
            method:'GET',
        }).then(resp => resp.json()).then(data => {

            checkFriend(data);
            const friendTable = document.getElementById("checkFriend");
            friendTable.style.opacity = "1";

            document.getElementById("toSend").value = data.appuserId;
            const toSendId = document.getElementById("toSend");
  
        })
        .catch(error => {
 
            const friendTable = document.getElementById("checkFriend");
            friendTable.style.opacity = "0";
        });

        //디스플레이 이름으로 찾기
    } else if(type.value == "findByDisplayName") {
        console.log("select displayname");
        const addFriendVal = document.querySelector("#addFriendInput").value;

    
        fetch("/user/findbydisplayname?keyword="+addFriendVal, {
            method:'GET',
        }).then(resp => resp.json()).then(data => {

    
            if(data.length === 0) {
 
                const friendTable = document.getElementById("checkFriend");
                friendTable.style.opacity = "0";
                
            } else {
                for(let i=0; i<data.length; i++){

                    checkFriend(data[i]);

    
                    const friendTable = document.getElementById("checkFriend");
                    friendTable.style.opacity = "1";

                    checkFriendElements(data[i]);
                }
            }
            checkFriendElements(data);
            
        })
        .catch(error => {
            const friendTable = document.getElementById("checkFriend");
            friendTable.style.opacity = "0";
        });
    
        }   
    }
  
//검색시 보이는 친구 목록
function checkFriend(data) {
    const friendTable = document.getElementById("friendInfo");

    const addtr = document.createElement('tr');
    addtr.id = "friendCheck"

    const tdPfp = document.createElement('td');
    const tdPfpDiv = document.createElement('div');
    const tdpfpImg = document.createElement('img');
    tdPfp.setAttribute('id', 'friendImg');

    if (data.appuserGenPfp === null) {
        tdpfpImg.src = '/media/userpfp/logo.png'; // 경로가 잘못된 경우 수정 필요
    } else {
        tdpfpImg.src = '/media/userpfp/' + data.appuserGenPfp;
    }

    tdpfpImg.style.width = "40px";
    tdpfpImg.style.height = "40px";
    tdpfpImg.style.borderRadius = "50%";
    
    tdPfpDiv.appendChild(tdpfpImg);
    tdPfp.appendChild(tdPfpDiv);
    addtr.appendChild(tdPfp);

    const tdEmail = document.createElement('td');
    tdEmail.setAttribute('id', 'addfriendEmail');
    tdEmail.textContent = data.appuserEmail;
    addtr.appendChild(tdEmail);

    const tdName = document.createElement('td');
    tdName.setAttribute('id', 'addfriendEmail');
    tdName.textContent = data.appuserUkName;
    addtr.appendChild(tdName);

    const tdDisplayName = document.createElement('td');
    tdDisplayName.setAttribute('id', 'addfriendDisplayName');
    tdDisplayName.textContent = data.appuserDisplayName;
    addtr.appendChild(tdDisplayName);

    friendTable.appendChild(addtr);
}



function checkFriendElements(data) {

    const friendCheckElements = document.querySelectorAll("#friendCheck");
    
    Array.from(friendCheckElements).forEach(function(element, index) {
        element.addEventListener("click", function() {
            //id값 보내기
            document.getElementById("toSend").value = data[index].appuserId;

            //클릭 스타일
            element.style.border = "2px solid rgb(140, 0, 255)";
            
            // 다른 요소의 스타일 초기화
            Array.from(friendCheckElements).forEach(function(otherElement) {
                if (otherElement !== element) {
                    otherElement.style.border = "";
                }
            });

            return index;

        });
    });
}


function addFriendSubmit(e) {

    //e.preventDefault();

    const form = document.getElementById("addFriendForm");
    const formData = new FormData(form);


 
    fetch("/friendreq/add", {
         method: 'POST',
         body: formData
    }).then(resp => resp.text()).then(response => {
         console.log(response);
    }).catch(error => {
         console.error('Error adding friend:', error);
    });
 }

//친구 요청
function requestion() {
    //다른 창 닫기
    const friendAllList = document.getElementById("friendAllList");
    friendAllList.style.display = "none";

    //화면 보이기 
    const friendWaitingList = document.getElementById("friendWaitingList");
    friendWaitingList.style.display = "block";
    
    // 아이디 가져오기
    var sessionId = document.querySelector("#sessionIdVal").value;


    // 받은 친구 요청
    fetch("/friendreq/findbyreceiver?friendRequestReceiver=" + sessionId, {
        method: 'GET',
    })
    .then(resp => resp.json())
    .then(data => {

        receive(data);
    })
    .catch(error => {
        console.error('Error fetching data:', error);
    });

    // 보낸 친구 요청
    fetch("/friendreq/findbysender?friendRequestSender=" + sessionId, {
        method: 'GET',
    })
    .then(resp => resp.json())
    .then(data => {

        sendList(data);
    })
    .catch(error => {
        console.error('Error fetching data:', error);
    });

    
}

//받은 친구 목록 추가
function receive(data) {
    const noreceive = document.getElementById("friendreceiveNo");

    if(data.length === 0) {

        
        noreceive.innerHTML = "받은 요청이 없습니다";

        const friendreceiveIndex = document.getElementById("friendreceiveIndex");
        friendreceiveIndex.style.display = "none";

        const friendreceiveList = document.getElementById("friendreceiveList");
        friendreceiveList.style.display = "none";

        
    } else {
        noreceive.innerHTML = "";
        const friendreceive = document.getElementById("friendreceiveList");

            friendreceive.innerHTML = '';
        for(let i=0; i<data.length; i++){
            const addtr = document.createElement('tr');
            addtr.id = "receiveListTr" +[i];

            const tdPfp = document.createElement('td');
            tdPfp.setAttribute('id', 'receiveListpfp');

            const pfpImg = document.createElement("img");

            if (data[i].appuserGenPfp === null) {
                pfpImg.src = '/media/userpfp/logo.png'; // 경로가 잘못된 경우 수정 필요
            } else {
                pfpImg.src = '/media/userpfp/' + data[i].appuserGenPfp;
            }

            pfpImg.style.width = "50px";
            pfpImg.style.height = "50px";
            pfpImg.style.borderRadius = "50%";
            tdPfp.appendChild(pfpImg);
            addtr.appendChild(tdPfp);

            const tdEmail = document.createElement('td');
            tdEmail.setAttribute('id', 'receiveListEmail');
            tdEmail.textContent = data[i].appuserEmail;
            addtr.appendChild(tdEmail);

            const tdName = document.createElement('td');
            tdName.setAttribute('id', 'receiveListName');
            tdName.textContent = data[i].appuserUkName;
            addtr.appendChild(tdName);

            const tdDisplayName = document.createElement('td');
            tdDisplayName.setAttribute('id', 'receiveListDisplayName');
            tdDisplayName.textContent = data[i].appuserDisplayName;
            addtr.appendChild(tdDisplayName);

            const tdBtnArea = document.createElement('td');
            const tdBtn = document.createElement('div');
            tdBtn.innerHTML = '수락';
            tdBtn.setAttribute('id', 'receiveListBtn');

            const receiveVal = data[i].appuserId;

            tdBtn.addEventListener('click', function() {
                acceptRequest(receiveVal,i);
            });
            tdBtnArea.appendChild(tdBtn);

            const deleteBtn = document.createElement('div');
            deleteBtn.innerHTML = '거절';
            deleteBtn.setAttribute('id', 'deleteBtn');
            tdBtnArea.appendChild(deleteBtn);
            addtr.appendChild(tdBtnArea);

            deleteBtn.addEventListener('click', function(){
                refuseRequest(receiveVal,i);
            });

            friendreceive.appendChild(addtr);

        }
    }
}

//보낸 친구 목록
function sendList(data) {
    const nosend = document.getElementById("friendsendNo");
    if(data.length === 0) {
        nosend.innerHTML = "보낸 요청이 없습니다";

        const friendsendIndex = document.getElementById("friendsendIndex");
        friendsendIndex.style.display = "none";

        const friendsendList = document.getElementById("friendsendList");
        friendsendList.style.display = "none";

    } else {
        nosend.innerHTML = "";
            
        const friendsendList = document.getElementById("friendsendList");
        friendsendList.innerHTML = '';

        for(let i=0; i<data.length; i++){
            const addtr = document.createElement('tr');
            addtr.id = "sendListTr"

            const tdPfp = document.createElement('td');
            tdPfp.setAttribute('id', 'receiveListpfp');

            const pfpImg = document.createElement("img");
            if (data[i].appuserGenPfp === null) {
                pfpImg.src = '/media/userpfp/logo.png'; // 경로가 잘못된 경우 수정 필요
            } else {
                pfpImg.src = '/media/userpfp/' + data[i].appuserGenPfp;
            }
            pfpImg.style.width = "50px";
            pfpImg.style.height = "50px";
            pfpImg.style.borderRadius = "50%";
            tdPfp.appendChild(pfpImg);
            addtr.appendChild(tdPfp);

            const tdEmail = document.createElement('td');
            tdEmail.setAttribute('id', 'sendListEmail');
            tdEmail.textContent = data[i].appuserEmail;
            addtr.appendChild(tdEmail);

            const tdName = document.createElement('td');
            tdName.setAttribute('id', 'sendListName');
            tdName.textContent = data[i].appuserUkName;
            addtr.appendChild(tdName);

            const tdDisplayName = document.createElement('td');
            tdDisplayName.setAttribute('id', 'sendListDisplayName');
            tdDisplayName.textContent = data[i].appuserDisplayName;
            addtr.appendChild(tdDisplayName);

            const tdBtnArea = document.createElement('td');
            const tdBtn = document.createElement('div');
            tdBtn.innerHTML = '대기중';
            tdBtn.setAttribute('id', 'sendListBtn');
            tdBtnArea.appendChild(tdBtn);
            addtr.appendChild(tdBtnArea);

            friendsendList.appendChild(addtr);

        }
    }
}

function acceptRequest(receiveVal,i) {
    // sessionId 가져오기
    var sessionId = document.querySelector("#sessionIdVal").value;
    
    // 숨겨진 입력 필드에 값 설정
    document.getElementById("receiveFirend").value = sessionId;
    document.getElementById("tosendFriend").value = receiveVal;

    // 폼 요소 가져오기
    const acceptformval = document.querySelector("#acceptrequestForm");

    // FormData 객체 생성
    const acceptRequestData = new FormData(acceptformval);

    // fetch 요청 보내기
    fetch("/friend/accept", {
        method: 'POST',
        body: acceptRequestData
   }).then(resp => resp.text()).then(response => {
        console.log(response);
        
        //수락 버튼 글릭시 목록에서 삭제
        const clickIndex = document.getElementById("receiveListTr" +[i]);
        clickIndex.remove();

        const rows = document.querySelectorAll("#friendreceiveList tr");
        const noreceive = document.getElementById("friendreceiveNo");
            if(rows.length === 0) {     
                noreceive.innerHTML = "받은 요청이 없습니다";

                const friendreceiveIndex = document.getElementById("friendreceiveIndex");
                friendreceiveIndex.style.display = "none";

                const friendreceiveList = document.getElementById("friendreceiveList");
                friendreceiveList.style.display = "none";
            }
        
   }).catch(error => {
        console.error('Error adding friend:', error);
   });

}

function refuseRequest(receiveVal, i) {
    document.querySelector("#refuseSend").value = receiveVal;
    
    const refuseVal = document.querySelector("#refuseForm");
    console.log(refuseVal);
    const formData = new FormData(refuseVal);

    fetch('/friendreq/delete', {
        method: 'DELETE',
        body: formData
    }).then(resp => resp.text()).then(response => {
        console.log(response);
        const clickIndex = document.getElementById("receiveListTr" +[i]);
        clickIndex.remove();

        const rows = document.querySelectorAll("#friendreceiveList tr");
        console.log(rows)
        const noreceive = document.getElementById("friendreceiveNo");
            if(rows.length === 0) {
                noreceive.innerHTML = "받은 요청이 없습니다";

                const friendreceiveIndex = document.getElementById("friendreceiveIndex");
                friendreceiveIndex.style.display = "none";

                const friendreceiveList = document.getElementById("friendreceiveList");
                friendreceiveList.style.display = "none";
            }     
    }).catch(error => {
        console.error('Error deleting friend request:', error);
    });
}