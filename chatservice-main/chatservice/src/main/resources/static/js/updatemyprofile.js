// function passwordcheck() {
//     const infochangepassword = document.getElementById("infochangepassword");
//     const infochangepasswordcheck = document.getElementById("infochangepasswordcheck");

//     if (infochangepassword.value !== infochangepasswordcheck.value) {
//         document.getElementById("passwordcheckalert").textContent = '비밀번호가 일치하지 않습니다.';
//         document.getElementById("passwordcheckalert").style.color = 'red';
//     } else {
//         document.getElementById("userpassword").value = infochangepasswordcheck.value;

//         const passwordcheckData = document.getElementById("passwordcheck");
//         const formdata = new FormData(passwordcheckData);

//         fetch('/user/passwordcheck', {
//             method: 'POST',
//             body: formdata
//         })
//         .then(resp => {
//             if (!resp.ok) {
//                 throw new Error('서버 오류 발생');
//             }
//             return resp.text();
//         })
//         .then(response => {
//             console.log(response);
//             if (response === 'not verified') {
//                 document.getElementById("passwordcheckalert").textContent = '비밀번호를 다시 입력해주세요.';
//                 document.getElementById("passwordcheckalert").style.color = 'red';
//             } else {
//                 document.getElementById("passwordcheckalert").textContent = '';
//                 updateMyProfile();
//             }
//         })
//         .catch(error => {
//             console.error('fetch 오류:', error);
//             document.getElementById("passwordcheckalert").textContent = '서버 오류 발생';
//             document.getElementById("passwordcheckalert").style.color = 'red';
//         });
//     }
// }

// function updateMyProfile() {
//     console.log("ok");
//     document.getElementById("inputPasswordTable").style.display = 'none';
//     document.getElementById("updateform").style.display = 'block';

//     const sessionId = document.getElementById("sessionId").value;
//   const params = new URLSearchParams();
//   params.append('ids', sessionId); // 여기서 sessionId를 배열에 담아 전송

//   fetch('/user/listbylist?' + params.toString(), {
//     method: "GET"
//   })
//     .then(resp => resp.json())
//   .then(data => {
//     console.log(data[0]);

//     const updateformappuserUkName = document.getElementById("updateformappuserUkName");
//     updateformappuserUkName.textContent = data[0].appuserUkName;

//     const updateformappuserEmail = document.getElementById("updateformappuserEmail");
//     updateformappuserEmail.textContent = data[0].appuserEmail;

//     const updateformPfp = document.getElementById("updateformPfp");
//     updateformPfp.src = '/media/userpfp/' + data[0].appuserGenPfp;
//     updateformPfp.style.width ="200px";
//     updateformPfp.style.height ="200px";

//   })
// }

// function updateDisplayName() {
//     const user = document.getElementById("sessionId").value;
//     document.getElementById("userDisplaynameId").value = user;
    
//     const updateDisplayNameVal = document.getElementById("updateDisplayName");
//     document.getElementById("userDisplaynameVal").value = updateDisplayNameVal.value

//     const updateDisplayNameForm = document.getElementById("updateDisplayNameForm");
//     const formdata = new FormData(updateDisplayNameForm);

//     fetch('/user/updatedisplayname', {
//         method: 'PUT',
//         body:formdata
//     })
//     .then(resp => resp.json())
//     .then(data => {
//       console.log(data);
//       document.getElementById("displayupdated").textContent = '디스플레이 이름이 수정되었습니다.';
//       document.getElementById("displayupdated").style.color = 'green'; 
//     })
// }

// function updateAbout() {
//     const user = document.getElementById("sessionId").value;
//     document.getElementById("userAboutId").value = user;

//     const updateAboutVal = document.getElementById("updateAbout");
//     document.getElementById("userAboutVal").value = updateAboutVal.value

//     const updateAboutForm = document.getElementById("updateAboutForm");
//     const formdata = new FormData(updateAboutForm);

//     fetch('/user/updateabout', {
//         method: 'PUT',
//         body:formdata
//     })
//     .then(resp => resp.json())
//     .then(data => {
//       console.log(data);
//       document.getElementById("aboutupdated").textContent = '소개글이 수정되었습니다.';
//       document.getElementById("displayupdated").style.color = 'green'; 
//     })
// }

// function updatePassword() {
//     const user = document.getElementById("sessionId").value;
//     document.getElementById("updatePassowrdId").value = user;

//     const updatePassword = document.getElementById("updatepassword");
//     document.getElementById("updatePassowrdVal").value = updatePassword.value

//     const updatePassowrdForm = document.getElementById("updatePassowrdForm");
//     const formdata = new FormData(updatePassowrdForm);

//     fetch('/user/updatepassword', {
//         method: 'PUT',
//         body:formdata
//     })
//     .then(resp => resp.json())
//     .then(data => {
//       console.log(data);
//       document.getElementById("passwordupdatedcheck").textContent = '비밀번호가 수정되었습니다.';
//       document.getElementById("passwordupdatedcheck").style.color = 'green';   
//     })
// }




function passwordcheck() {
    const infochangepassword = document.getElementById("infochangepassword");

    document.getElementById("userpassword").value = infochangepassword.value;

    const passwordcheckData = document.getElementById("passwordcheck");
    const formdata = new FormData(passwordcheckData);

    fetch('/user/passwordcheck', {
        method: 'POST',
        body: formdata
    })
    .then(resp => {
        if (!resp.ok) {
            throw new Error('서버 오류 발생');
        }
        return resp.text();
    })
    .then(response => {
        console.log(response);
        if (response === 'not verified') {
            document.getElementById("passwordcheckalert").textContent = '비밀번호를 다시 입력해주세요.';
            document.getElementById("passwordcheckalert").style.color = 'red';
        } else {
            document.getElementById("passwordcheckalert").textContent = '';
            updateMyProfile();
        }
    })
    .catch(error => {
        console.error('fetch 오류:', error);
        document.getElementById("passwordcheckalert").textContent = '서버 오류 발생';
        document.getElementById("passwordcheckalert").style.color = 'red';
    });
}

function updateMyProfile() {
    console.log("ok");
    document.getElementById("inputPasswordTable").style.display = 'none';
    document.getElementById("updateform").style.display = 'block';

    const sessionId = document.getElementById("sessionId").value;
    const params = new URLSearchParams();
    params.append('ids', sessionId);

    fetch('/user/listbylist?' + params.toString(), {
        method: "GET"
    })
    .then(resp => resp.json())
    .then(data => {
        console.log(data[0]);

        const updateformappuserUkName = document.getElementById("updateformappuserUkName");
        updateformappuserUkName.textContent = data[0].appuserUkName;

        const updateformappuserEmail = document.getElementById("updateformappuserEmail");
        updateformappuserEmail.textContent = data[0].appuserEmail;

        const updateformPfp = document.getElementById("updateformPfp");
        updateformPfp.src = '/media/userpfp/' + data[0].appuserGenPfp;
        updateformPfp.style.width ="200px";
        updateformPfp.style.height ="200px";
    });
}

function updateDisplayName() {
    const user = document.getElementById("sessionId").value;
    document.getElementById("userDisplaynameId").value = user;
    
    const updateDisplayNameVal = document.getElementById("updateDisplayName");
    document.getElementById("userDisplaynameVal").value = updateDisplayNameVal.value;

    const updateDisplayNameForm = document.getElementById("updateDisplayNameForm");
    const formdata = new FormData(updateDisplayNameForm);

    fetch('/user/updatedisplayname', {
        method: 'PUT',
        body: formdata
    })
    .then(resp => resp.json())
    .then(data => {
        console.log(data);
        document.getElementById("displayupdated").textContent = '디스플레이 이름이 수정되었습니다.';
        document.getElementById("displayupdated").style.color = 'green';
    });
}

function updateAbout() {
    const user = document.getElementById("sessionId").value;
    document.getElementById("userAboutId").value = user;

    const updateAboutVal = document.getElementById("updateAbout");
    document.getElementById("userAboutVal").value = updateAboutVal.value;

    const updateAboutForm = document.getElementById("updateAboutForm");
    const formdata = new FormData(updateAboutForm);

    fetch('/user/updateabout', {
        method: 'PUT',
        body: formdata
    })
    .then(resp => resp.json())
    .then(data => {
        console.log(data);
        document.getElementById("aboutupdated").textContent = '소개글이 수정되었습니다.';
        document.getElementById("aboutupdated").style.color = 'green';
    });
}

function updatePassword() {
    const user = document.getElementById("sessionId").value;
    document.getElementById("updatePassowrdId").value = user;

    const updatePassword = document.getElementById("updatepassword");
    document.getElementById("updatePassowrdVal").value = updatePassword.value;

    const updatePassowrdForm = document.getElementById("updatePassowrdForm");
    const formdata = new FormData(updatePassowrdForm);

    fetch('/user/updatepassword', {
        method: 'PUT',
        body: formdata
    })
    .then(resp => resp.json())
    .then(data => {
        console.log(data);
        document.getElementById("passwordupdatedcheck").textContent = '비밀번호가 수정되었습니다.';
        document.getElementById("passwordupdatedcheck").style.color = 'green';    
    });
}


 function displaynameVal() {
     const displayNameVal = document.getElementById("updateDisplayName").value.trim(); // 입력값 앞뒤 공백 제거
     console.log(displayNameVal);

     const displayUpdated = document.getElementById("displayupdated");
     const updateBtn = document.querySelector("#displayNameupdateBtn");

     if (displayNameVal.length === 0) {
         displayUpdated.textContent = '';
         updateBtn.disabled = true; // 수정 버튼 비활성화
        } else if (displayNameVal.length <= 5 || displayNameVal.length > 20) {
         displayUpdated.textContent = '6자에서 20자 사이의 디스플레이 이름을 입력하세요.';
         displayUpdated.style.color = 'red';
         updateBtn.disabled = true; // 수정 버튼 비활성화
     } else {
         displayUpdated.textContent = '';
         updateBtn.disabled = false; // 수정 버튼 활성화
     }
 }

 function passwordVal() {
     const updatepassword = document.getElementById("updatepassword").value.trim(); // 입력값 앞뒤 공백 제거

     const hasAlphabet = /[a-zA-Z]/.test(updatepassword); // 알파벳 확인
     const hasNumber = /[0-9]/.test(updatepassword); // 숫자 확인
     const hasSpecialChar = /[!@#$%^&*()\-_=+\\|[\]{};:'",.<>/?]/.test(updatepassword); // 특수문자 확인

     const passwordResult = document.getElementById("passwordupdated");
     const updateBtn = document.querySelector("#passwordupdateBtn");
     if (updatepassword.length === 0) {
         passwordResult.textContent = '';
         updateBtn.disabled = true;
     } else if (updatepassword.length < 10 || updatepassword.length > 20 || !hasAlphabet || !hasNumber || !hasSpecialChar) {
         passwordResult.style.color = "red";
         passwordResult.textContent = '비밀번호는 10자에서 20자 사이이어야 하며, 알파벳, 숫자, 특수문자(!,@,#,$,% 등)를 포함해야 합니다.';
         updateBtn.disabled = true; 
     } else {
         passwordResult.textContent = '';
         updateBtn.disabled = false;
     }
 }

 function passwordcheckVal() {
     var p1 = document.getElementById("updatepassword").value;
     var p2 = document.getElementById("updatepasswordcheck").value;
     const passwordCheckResult = document.getElementById("passwordupdatedcheck");
     const updateBtn = document.querySelector("#passwordupdateBtn");
     console.log(updateBtn);
     if(p1 != p2) {
         passwordCheckResult.style.color="red";
         passwordCheckResult.textContent= "비밀번호가 일치하지 않습니다.";
         updateBtn.disabled = true;
        return false;
     } else {
         passwordCheckResult.textContent = '';
         updateBtn.disabled = false;
         return true;
     }
 }
