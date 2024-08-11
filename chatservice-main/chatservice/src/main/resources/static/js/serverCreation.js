console.log("hello");

// function createServer() {
//     console.log("Server created");
//     const modal1 = document.getElementById("createServerMain");
//     const modal2 = document.getElementById("createServerDetail");
//     modal1.style.display = "block";
//     modal2.style.display = "none";
// }

function toggleVisibility(showId, hideId) {
    var showElement = document.getElementById(showId);
    var hideElement = document.getElementById(hideId);
    if (showElement.style.display === "none" || showElement.style.display === "") {
      showElement.style.display = "block";
      hideElement.style.display = "none";
    } else {
      showElement.style.display = "none";
    }
  }
  
  function toggleVisibility(showId, hideId) {
    var showElement = document.getElementById(showId);
    var hideElement = document.getElementById(hideId);
    if (showElement.style.display === "none" || showElement.style.display === "") {
      showElement.style.display = "block";
      hideElement.style.display = "none";
    } else {
      showElement.style.display = "none";
    }
  }

function createServer() {
    toggleVisibility('createServerMain', 'addFriendMain');
    const modal2 = document.getElementById("createServerDetail");
    modal2.style.display = "none";
  }

function moveSecondPage() {
    const modal1 = document.getElementById("createServerMain");
    const modal2 = document.getElementById("createServerDetail");
    modal1.style.display = "none";
    modal2.style.display = "block";
}

function closeModal() {
    const modal1 = document.getElementById("createServerMain");
    const modal2 = document.getElementById("createServerDetail");
    modal1.style.display = "none";
    modal2.style.display = "none";

    clearForm();

    const uploadPicture = document.getElementById("uploadPicture");
    uploadPicture.innerHTML = "UPLOAD";
}

function clearForm() {
    const fileUpload = document.querySelector("[data-file-upload]");
    console.log(fileUpload);
    fileUpload.value = '';

    const uploadImg = document.querySelector("#uploadedImage");
    console.log(uploadImg);
    if(uploadImg != null){
        uploadImg.src = '';
    }

    const inputName = document.querySelector("#serverNameInput");
    console.log(inputName);
    inputName.value = '';

    const inputInfo = document.querySelector("#serverInfoInput");
    console.log(inputInfo);
    inputInfo.value = '';
}

window.addEventListener("load", function () {

    const formEl = document.getElementById("serverCreateForm");

    console.log("before onchange");
    // 파일 선택될 때 실행
    document.querySelector("[data-file-upload]").onchange = function (e) {
        const [file] = this.files;
        console.log(file);

        if (file) {
            const uploadPicture = document.getElementById("uploadPicture");

            const previewImage = document.createElement("img");
            previewImage.src = URL.createObjectURL(file);
            previewImage.id = "uploadedImage";

            uploadPicture.innerHTML = "";
            uploadPicture.appendChild(previewImage);

        }
    }

    const myprofile = document.getElementById("myProfile");
    console.log(myprofile);

    const sessionId = document.getElementById("sessionId").value;
    const params = new URLSearchParams();
    params.append('ids', sessionId); // 여기서 sessionId를 배열에 담아 전송

    fetch('/user/listbylist?' + params.toString(), {
    method: "GET"
    })
    .then(resp => resp.json())
    .then(data => {
        console.log(data[0]);

    const myprofileImage = this.document.createElement('img');

    if (data[0].appuserGenPfp === null) {
        myprofileImage.src = '/media/userpfp/logo.png'; // 경로가 잘못된 경우 수정 필요
    } else {
        myprofileImage.src = '/media/userpfp/' + data[0].appuserGenPfp;
    }
    myprofileImage.style.width ="50px";
    myprofileImage.style.height ="50px";
    myprofile.appendChild(myprofileImage);

    const myprofileName = this.document.createElement('div');
    myprofileName.textContent = data[0].appuserDisplayName;
    myprofileName.id ="myprofileName";
    myprofile.appendChild(myprofileName);
    
    (function(id, userData) {
        myprofileImage.addEventListener('click', function(event) {
            showLocation(event, id, userData);
        });
    })(myprofileName.id, data[0].appuserId);
    });
});

function submitServerCreation(e) {
    e.preventDefault();
    console.log("submit");
    const formData = new FormData(this);

    console.log(formData.get("serverDesc"));
}

function uploadFile(e) { // 프사 업로드
    e.preventDefault();
    const fileUploadForm = document.querySelector("#dataFileUpload");
    console.log(fileUploadForm);
    const formData = new FormData(fileUploadForm);
    //const data = new URLSearchParams(formData);
    console.log(formData.get("file"));

    fetch("/server/serverpfp", {
        method: 'POST',
        body: formData
    }).then(resp => resp.text()).then(data => putImageValue(data));
}

function putImageValue(data) { // 서버에서 작성된 이름을 가져옴
    console.log(data);

    document.querySelector("[data-server-pfp]").value = data;

    serverSubmit();
}



function serverSubmit() {
    const createServerForm = document.querySelector("#serverCreateForm");
    const serverData = new FormData(createServerForm);

    fetch("/server/create", {
        method: 'POST',
        body: serverData
    }).then(resp => resp.json()).then(data => {
        console.log(data);
        severMemberSubmit(data);
    });

    clearForm();

    const uploadPicture = document.getElementById("uploadPicture");
    uploadPicture.innerHTML = "UPLOAD";

    const modal1 = document.getElementById("createServerMain");
    const modal2 = document.getElementById("createServerDetail");
    console.log(modal1);
    modal1.style.display = "none";
    modal2.style.display = "none";

    let newServer = document.createElement('div');
    newServer.classList.add('server');
    // newServer.textContent = 'New Server';
    newServer.onclick = function() {
        selectServer(this);
    };

    let serversContainer = document.querySelector('.serverContainer');
    serversContainer.appendChild(newServer);
}


function severMemberSubmit(data) {
    console.log("server member submit:");
    console.log(data);

    document.getElementById("serverMemberServer").value = data.serverMemberServer;
    document.getElementById("serverMemberUser").value = data.serverMemberUser;
    const serverMemberVal = document.getElementById("severMemberForm");
    const serverMemberData = new FormData();

    serverMemberData.append("serverMemberServer" , data.serverMemberServer);
    serverMemberData.append("serverMemberUser" , data.serverMemberUser);

    fetch("/servermember/create", {
        method: 'POST',
        body: serverMemberData
    }).then(resp => resp.text()).then(data => {
        console.log(data);
        channelSubmit();
    });
}

function channelSubmit() {
    document.getElementById("channelName").value = '일반';
    document.getElementById("channelServer").value = document.getElementById("serverMemberServer").value;
    const serverId = document.getElementById("serverMemberServer").value;
    const channelVal = document.querySelector("#channelForm");
    const channelData = new FormData(channelVal);

    fetch("/channel/create", {
        method: 'POST',
        body: channelData
    }).then(resp => resp.text()).then(data => {
        console.log(data);
        addDefaultLastVisited(JSON.parse(data), serverId);
        getServerList();
    });
}

function addDefaultLastVisited(channel, serverId){
    const appuserId = document.getElementById("sessionId").value;
    console.log(appuserId);
    console.log(serverId);
    const formData = new FormData();

    formData.append("appuserId", appuserId);
    formData.append("serverId", serverId);
    formData.append("channelId", channel.channelId);

    fetch("/servermember/updatelastvisited", {
        method : "PUT",
        body : formData 
    });

    
}




