//친구 프로필 자세히 보기
function showLocation(event, id, userData) {
    console.log(userData);
    console.log(id);

    const x = event.clientX;
    const y = event.clientY;
    console.log(`Clicked at coordinates: (${x}, ${y})`);

    

    const sessionId = userData;
  const params = new URLSearchParams();
  params.append('ids', sessionId); // 여기서 sessionId를 배열에 담아 전송

  fetch('/user/listbylist?' + params.toString(), {
    method: "GET"
  })
  .then(resp => resp.json())
  .then(data => {
    const friendView = document.getElementById("friendView");
    friendView.innerHTML = '';
    console.log(data);

    const pageWidth = document.documentElement.scrollWidth;
    const pageHeight = document.documentElement.scrollHeight;

    const friendViewModal = document.getElementById("friendViewModal");
    friendViewModal.style.display = "block";
    

    if (id.startsWith("friend")) {
        friendViewModal.style.left = '450px'; // x 좌표에 20을 더한 값으로 설정
        /*const chatBtn = document.createElement("button");
        chatBtn.innerHTML = "채팅";
        chatBtn.id = "friendViewChat";

        friendView.appendChild(chatBtn);*/
    
        if (y < pageHeight - 600) {
            friendViewModal.style.top = `${y}px`; // 페이지의 상단에 위치
        } else {
            friendViewModal.style.top = `${pageHeight - 600}px`; // 페이지의 하단에서 500px 위에 위치
        }
    } else if(id.startsWith("myprofileName")) {
        friendViewModal.style.left = "100px";
        friendViewModal.style.top = "320px";
        const chatBtn = document.createElement("button");
        chatBtn.innerHTML = "수정";
        chatBtn.id = "myprofilechaneBtn";

        friendView.appendChild(chatBtn);
    } else if(id.startsWith("groupchatUser")) {
        friendViewModal.style.right = '290px'; 
    
        if (y < pageHeight - 600) {
            friendViewModal.style.top = `${y}px`; 
        } else {
            friendViewModal.style.top = `${pageHeight - 600}px`;
        }
    }

    const firendPfpArea = document.createElement("div");
    const friendPfp = document.createElement("img");
    if (data[0].appuserGenPfp === null) {
        friendPfp.src = '/media/userpfp/logo.png'; // 경로가 잘못된 경우 수정 필요
    } else {
        friendPfp.src = '/media/userpfp/'+data[0].appuserGenPfp;
    }
    
    firendPfpArea.appendChild(friendPfp);
    firendPfpArea.id = "firendViewPfp";

    const friendDisplayName = document.createElement("div");
    friendDisplayName.innerHTML = data[0].appuserDisplayName;
    friendDisplayName.id = "friendviewDisplayName";

    const table = document.createElement("table");
    table.classList.add("friendInfoTable");

    const dataPairs = [
        { label: "고유이름", value: data[0].appuserUkName },
        { label: "이메일", value: data[0].appuserEmail },
        { label: "가입날짜", value: data[0].appuserRegDate }
    ];

    dataPairs.forEach(pair => {
        const row = document.createElement("tr");

        const labelCell = document.createElement("td");
        labelCell.textContent = pair.label;

        const valueCell = document.createElement("td");
        valueCell.textContent = pair.value;

        row.appendChild(labelCell);
        row.appendChild(valueCell);
        table.appendChild(row);
    });

    table.id = "friendViewTable";

    friendView.appendChild(firendPfpArea);
    friendView.appendChild(friendDisplayName);
    friendView.appendChild(table);
  });

    
}