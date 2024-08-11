function selectServer(element) {
    let servers = document.querySelectorAll('.server');
    servers.forEach(server => {
        server.classList.remove('selectedServer');
    });

    element.classList.add('selectedServer');
}

 /*function selectChatChannel(element){
     let channelName = channel.getAttribute('data-channel');
     let content = '';
     switch (channelName) {
         case '일반':
             content = '일반 채널 내용을 여기에 표시합니다.';
             break;
         case '일반2':
             content = '일반2 채널 내용을 여기에 표시합니다.';
             break;
         case '일반3':
             content = '일반3 채널 내용을 여기에 표시합니다.';
             break;
         default:
             content = '다른 채널 내용을 여기에 표시합니다.';
             break;
     }

     let chatContentElement = document.querySelector('.chatContent');
     chatContentElement.textContent = content;
 }*/ 

document.addEventListener("DOMContentLoaded", function() {
    const channels = document.querySelectorAll(".chatChannel");
    const chatContent = document.querySelector(".chatContent");

    channels.forEach(channel => {
        channel.addEventListener("click", function() {
            channels.forEach(ch => ch.classList.remove('selectedChannel'));
            channel.classList.add('selectedChannel');

            const contentId = channel.getAttribute("data-content");
            updateContent(contentId);
        });
    });

    function updateContent(contentId) {
       console.log(contentId);
    }
});



// document.addEventListener("DOMContentLoaded", function() {
//     console.log("DOMContentLoaded event fired");
//     initializeProfile();
// });

// function initializeProfile() {
//     const sessionIdElement = document.getElementById("sessionId");
//     console.log("sessionIdElement:", sessionIdElement);
    
//     if (!sessionIdElement) {
//         console.error("sessionId 요소를 찾을 수 없습니다.");
//         return;
//     }

//     const sessionId = sessionIdElement.value;
//     console.log("sessionId 값:", sessionId);

//     // 필요한 작업 수행 (예: 프로필 이미지 설정)
//     fetchProfileData(sessionId);
// }

// function fetchProfileData(sessionId) {
//     const params = new URLSearchParams();
//     params.append('ids', sessionId); // sessionId를 배열에 담아 전송

//     fetch('/user/listbylist?' + params.toString(), {
//         method: "GET"
//     })
//     .then(resp => resp.json())
//     .then(data => {
//         const myProfile = document.getElementById("myProfile");
//         console.log("myProfile 요소:", myProfile);

//         if (myProfile) {
//             const profileImage = document.createElement("img");
//             let imageUrl;

//             if (data[0].appuserGenPfp === null) {
//                 imageUrl = '/media/userpfp/logo.png';
//             } else {
//                 imageUrl = '/media/userpfp/' + data[0].appuserGenPfp;
//             }

//             console.log("이미지 URL:", imageUrl);

//             // 이미지를 로드할 수 있는지 테스트
//             fetch(imageUrl)
//                 .then(response => {
//                     if (!response.ok) {
//                         throw new Error('Network response was not ok ' + response.statusText);
//                     }
//                     return response.blob();
//                 })
//                 .then(blob => {
//                     const url = URL.createObjectURL(blob);
//                     profileImage.src = url;
//                     profileImage.style.width = "40px"; // Adjust size as needed
//                     profileImage.style.height = "40px"; // Adjust size as needed
//                     myProfile.innerHTML = ''; // Clear any existing content
//                     myProfile.appendChild(profileImage);
//                 })
//                 .catch(error => {
//                     console.error('Error fetching image:', error);
//                     profileImage.src = '/media/userpfp/logo.png'; // fallback 이미지 경로
//                     profileImage.style.width = "40px"; // Adjust size as needed
//                     profileImage.style.height = "40px"; // Adjust size as needed
//                     myProfile.innerHTML = ''; // Clear any existing content
//                     myProfile.appendChild(profileImage);
//                 });
//         }
//     })
//     .catch(error => {
//         console.error('Error fetching user data:', error);
//     });
// }
