function login() {

}

document.addEventListener("DOMContentLoaded", function(){


    const loginAttempt = document.getElementById("loginAttempted").value;
    console.log(loginAttempt);
    if(loginAttempt == "true"){
        console.log("failed");
        alert("로그인에 실패 했습니다.");
        fetch("/user/changetofalse",{
            method : "GET"
        }).then(function(resp){
            return resp.text();
        }).then(function(data){
            console.log(data);
        })
    }
});
    /* 
    // fetch를 이용하여 서버에 POST 요청을 보냄
    fetch('/user/login', {
        method: 'POST',
        body: formData
    })
    .then(function(response) {
        if (!response.ok) {
            throw new Error('로그인 실패');
        }
        console.log('성공');
        return response.json();
    })
    .then(function(data) {
        // 성공적으로 처리된 후 추가로직을 넣을 수 있음
        console.log('서버에서 받은 데이터:', data);
    })
    .catch(function(error) {
        console.error('로그인 오류:', error.message);
        alert('이메일이나 비밀번호가 잘못되었습니다.');
    });*/


// function loginSubmit(event) {
//     event.preventDefault();

//     const emailInput = document.getElementById("loginemailVal");
//     const passwordInput = document.getElementById("loginpasswordVal");
//     const keepLoggedIn = document.getElementById("keepLoggedIn").checked;

//     const loginForm = new FormData();
//     loginForm.append("appuserEmail", emailInput.value);
//     loginForm.append("appuserPassword", passwordInput.value);
//     loginForm.append("keepLoggedIn", keepLoggedIn);

//     fetch('/user/login', {
//         method: 'POST',
//         body: loginForm,
//         credentials: 'include'
//     })
//     .then(resp => {
//         console.log('응답 상태 코드:', resp.status);
//         if (!resp.ok) {
//             return resp.text().then(text => { throw new Error(text) });
//         }
//         return resp.json();
//     })
//     .then(data => {
//         console.log('서버 응답:', data);
//         const errorMessage = document.getElementById("errorMessage");
//         if (data.success) {
//             console.log('로그인 성공:', data);
//             errorMessage.style.visibility = 'hidden';
//             // location.reload();
//             window.location.href = '/dashboard';
//         } else {

//             errorMessage.innerText = "아이디 또는 비밀번호가 틀립니다.";
//             errorMessage.style.visibility = 'visible';
//             emailInput.select();
//             passwordInput.select();
//         }
//     })
//     .catch(error => {
//         console.error('로그인 실패:', error);
//         const errorMessage = document.getElementById("errorMessage");
//         errorMessage.innerText = "아이디 또는 비밀번호가 틀립니다.";
//         errorMessage.style.visibility = 'visible';
//         emailInput.select();
//         passwordInput.select();
//     });

//     emailInput.addEventListener("input", clearErrorMessage);
//     passwordInput.addEventListener("input", clearErrorMessage);

//     function clearErrorMessage() {
//         const errorMessage = document.getElementById("errorMessage");
//         if (!emailInput.value || !passwordInput.value) {
//             errorMessage.style.visibility = 'hidden';
//         }
//     }
// }



// function loginSubmit(event) {
//     event.preventDefault();

//     const emailInput = document.getElementById("loginemailVal");
//     const passwordInput = document.getElementById("loginpasswordVal");
//     const keepLoggedIn = document.getElementById("keepLoggedIn").checked;

//     const loginForm = new FormData();
//     loginForm.append("appuserEmail", emailInput.value);
//     loginForm.append("appuserPassword", passwordInput.value);
//     loginForm.append("keepLoggedIn", keepLoggedIn);

//     fetch('/user/login', {
//         method: 'POST',
//         body: loginForm,
//         credentials: 'include'
//     })
//     .then(resp => {
//         console.log('응답 상태 코드:', resp.status);
//         if (!resp.ok) {
//             return resp.text().then(text => { throw new Error(text) });
//         }
//         return resp.json();
//     })
//     .then(data => {
//         console.log('서버 응답:', data);
//         const errorMessage = document.getElementById("errorMessage");
//         if (data.success) {
//             console.log('로그인 성공:', data);
//             errorMessage.style.visibility = 'hidden';
//             document.querySelector(".loginFormContainer").style.display = 'none';
//             document.getElementById("userInfo").style.display = 'block';
//             document.getElementById("userEmail").innerText = emailInput.value;

//             // document.cookie = "sessionId=" + data.sessionId;
//             localStorage.setItem('sessionId', data.sessionId);
//         } else {
//             errorMessage.innerText = "아이디 또는 비밀번호가 틀립니다.";
//             errorMessage.style.visibility = 'visible';
//             emailInput.select();
//             passwordInput.select();
//         }
//     })
//     .catch(error => {
//         console.error('로그인 실패:', error);
//         const errorMessage = document.getElementById("errorMessage");
//         errorMessage.innerText = "아이디 또는 비밀번호가 틀립니다.";
//         errorMessage.style.visibility = 'visible';
//         emailInput.select();
//         passwordInput.select();
//     });

//     emailInput.addEventListener("input", clearErrorMessage);
//     passwordInput.addEventListener("input", clearErrorMessage);

//     function clearErrorMessage() {
//         const errorMessage = document.getElementById("errorMessage");
//         if (emailInput.value && passwordInput.value) {
//             errorMessage.style.visibility = 'hidden';
//         }
//     }
// }

// window.addEventListener('load', () => {
//     const sessionId = localStorage.getItem('sessionId');
//     if (sessionId) {
//         fetch('/user/status', {
//             method: 'GET',
//             credentials: 'include'
//         })
//         .then(resp => resp.json())
//         .then(data => {
//             if (data.loggedIn) {
//                 document.querySelector(".loginFormContainer").style.display = 'none';
//                 document.getElementById("userInfo").style.display = 'block';
//                 document.getElementById("userEmail").innerText = sessionId;
//             }
//         });
//     }
// });



// function loginSubmit(event) {
//     event.preventDefault();

//     const emailInput = document.getElementById("loginemailVal");
//     const passwordInput = document.getElementById("loginpasswordVal");
//     const keepLoggedIn = document.getElementById("keepLoggedIn").checked;

//     const loginForm = new FormData();
//     loginForm.append("appuserEmail", emailInput.value);
//     loginForm.append("appuserPassword", passwordInput.value);
//     loginForm.append("keepLoggedIn", keepLoggedIn);

//     fetch('/user/login', {
//         method: 'POST',
//         body: loginForm,
//         credentials: 'include'
//     })
//     .then(resp => {
//         console.log('응답 상태 코드:', resp.status);
//         if (!resp.ok) {
//             return resp.text().then(text => { throw new Error(text) });
//         }
//         return resp.text();
//     })
//     .then(text => {
//         try {
//             const data = JSON.parse(text);
//             console.log('서버 응답:', data);
//             const errorMessage = document.getElementById("errorMessage");
//             if (data.success) {
//                 console.log('로그인 성공:', data);
//                 errorMessage.style.visibility = 'hidden';
//                 window.location.href = '/dashboard'; 
//             } else {
//                 errorMessage.innerText = "아이디 또는 비밀번호가 틀립니다.";
//                 errorMessage.style.visibility = 'visible';
//                 emailInput.select();
//                 passwordInput.select();
//             }
//         } catch (e) {
//             console.error('JSON 파싱 실패:', e);
//             console.error('응답 텍스트:', text);
//             const errorMessage = document.getElementById("errorMessage");
//             errorMessage.innerText = "서버 응답을 처리하는 중 오류가 발생했습니다.";
//             errorMessage.style.visibility = 'visible';
//         }
//     })
//     .catch(error => {
//         console.error('로그인 실패:', error);
//         const errorMessage = document.getElementById("errorMessage");
//         errorMessage.innerText = "아이디 또는 비밀번호가 틀립니다.";
//         errorMessage.style.visibility = 'visible';
//         emailInput.select();
//         passwordInput.select();
//     });

//     emailInput.addEventListener("input", clearErrorMessage);
//     passwordInput.addEventListener("input", clearErrorMessage);

//     function clearErrorMessage() {
//         const errorMessage = document.getElementById("errorMessage");
//         if (!emailInput.value || !passwordInput.value) {
//             errorMessage.style.visibility = 'hidden';
//         }
//     }
// }

// const express = require('express');
// const app = express();

// app.use(express.json());
// app.use(express.urlencoded({ extended: true }));

// app.post('/user/login', (req, res) => {
//     const { appuserEmail, appuserPassword } = req.body;

//     const user = authenticateUser(appuserEmail, appuserPassword);

//     if (user) {
//         res.json({ success: true, message: '로그인 성공', user });
//     } else {
//         res.status(401).json({ success: false, message: '아이디 또는 비밀번호가 틀립니다.' });
//     }
// });

// app.listen(8082, () => {
//     console.log('서버가 포트 8082에서 실행 중입니다.');
// });

// function loginSubmit(event) {
//     event.preventDefault();

//     const emailInput = document.getElementById("loginemailVal");
//     const passwordInput = document.getElementById("loginpasswordVal");
//     const keepLoggedIn = document.getElementById("keepLoggedIn").checked;

//     const loginForm = new FormData();
//     loginForm.append("appuserEmail", emailInput.value);
//     loginForm.append("appuserPassword", passwordInput.value);
//     loginForm.append("keepLoggedIn", keepLoggedIn);

//     fetch('/user/login', {
//         method: 'POST',
//         body: loginForm,
//         credentials: 'include',
//         headers: {
//             'Accept': 'application/json'
//         }
//     })
//     .then(resp => {
//         console.log('응답 상태 코드:', resp.status);
//         return resp.json();
//     })
//     .then(data => {
//         console.log('서버 응답:', data);
//         const errorMessage = document.getElementById("errorMessage");
//         if (data.success) {
//             console.log('로그인 성공:', data);
//             errorMessage.style.visibility = 'hidden';
//             window.location.href = '/dashboard'; 
//         } else {
//             errorMessage.innerText = data.message || "아이디 또는 비밀번호가 틀립니다.";
//             errorMessage.style.visibility = 'visible';
//             emailInput.select();
//             passwordInput.select();
//         }
//     })
//     .catch(error => {
//         console.error('로그인 실패:', error);
//         const errorMessage = document.getElementById("errorMessage");
//         errorMessage.innerText = "서버 응답을 처리하는 중 오류가 발생했습니다.";
//         errorMessage.style.visibility = 'visible';
//         emailInput.select();
//         passwordInput.select();
//     });

//     emailInput.addEventListener("input", clearErrorMessage);
//     passwordInput.addEventListener("input", clearErrorMessage);

//     function clearErrorMessage() {
//         const errorMessage = document.getElementById("errorMessage");
//         if (!emailInput.value || !passwordInput.value) {
//             errorMessage.style.visibility = 'hidden';
//         }
//     }
// }