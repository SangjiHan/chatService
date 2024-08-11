console.log("hello");

function displayNameValCheck() {
    const displaynameVal = document.getElementById("displayName").value;
    console.log(displaynameVal);
    const displayNameResult = document.querySelector("[data-displayname]");
    if(displaynameVal.length <= 5 || displaynameVal.length > 20){
        displayNameResult.style.color = "red";
        displayNameResult.innerHTML = '6자 ~20자의 디스플레이 이름을 입력하세요.';
        return false;
    } else {
        displayNameResult.innerHTML = '';
        return true;
    }
}

function passwordValCheck() {
    const passwordVal = document.getElementById("passwordVal").value;
    console.log(passwordVal);

    const hasAlphabet = /[a-zA-Z]/.test(passwordVal); // 알파벳 확인
    const hasNumber = /[0-9]/.test(passwordVal); // 숫자 확인
    const hasSpecialChar = /[!@#$%^&*()\-_=+\\|[\]{};:'",.<>/?]/.test(passwordVal); // 특수문자 확인

    const passwordResult = document.querySelector("[data-password]");
    if(passwordVal.length <10 || passwordVal.length >20 || !hasAlphabet || !hasNumber || !hasSpecialChar) {
        passwordResult.style.color="red";
        passwordResult.innerHTML = '비밀번호는 10자 ~ 20자여야 하며, 알파벳, 숫자, 특수문자(!,@,#,$,% 등) 을 포함해야 합니다.';
        return false;
    } else {
        passwordResult.innerHTML = '';
        return true;
    }
}

function passwordCheck() {
    var p1 = document.getElementById("passwordVal").value;
    var p2 = document.getElementById("passwordcheck").value;
    const passwordCheckResult = document.querySelector("[data-passwordcheck]");
    if(p1 != p2) {
        passwordCheckResult.style.color="red";
        passwordCheckResult.innerHTML= "비밀번호가 일치하지 않습니다.";
        return false;
    } else {
        passwordCheckResult.innerHTML = '';
        return true;
    }
}



window.onload = function(){


    function emailValCheck(){
        document.querySelector("[data-email]").innerHTML = "";
        dupObj.email = false;
        const emailVal = emailInput.value;
        const emailSplit = emailVal.split("@");

        if(emailSplit.length != 2){
            return;
        }

        const domainSplit = emailSplit[1].split(".");

        if(domainSplit.length < 2){
            return;
        }

        const dLen = domainSplit.length - 1;
        console.log("d: " + domainSplit[dLen].length);
        if(domainSplit[dLen].length < 1){
            return;
        }

        console.log(emailSplit);
        if(emailVal.length > 35){
            return;
        }
        const url = "/joincheck/email";
        const obj = {
          method : "POST",
          body: emailVal
        };

        fetch(url, obj).then(resp => resp.text())
        .then(data => displayResult("email", data));
    }

    function ukNameValCheck(){
        document.querySelector("[data-ukname]").innerHTML = "";
        dupObj.ukname = false;
        const ukNameVal = ukNameInput.value;
        if(ukNameVal.length <= 5 || ukNameVal.length >= 20){
            document.querySelector("[data-ukname]").innerHTML = "6자 ~20자의 고유이름을 입력하세요.";
            document.querySelector("[data-ukname]").style.color = "red";
            return;
        }

        console.log(ukNameVal);
        const url = "/joincheck/ukname";
        const obj = {
            method: "POST",
            body: ukNameVal
        };

        fetch(url, obj).then(resp => resp.text())
        .then(data => displayResult("ukname", data));
    }

    const dupObj = {
        email : false, ukname: false
    };

    function displayResult(type, data){
        console.log(data);
        console.log(document.querySelector("[data-email]"));
        const dataAtt = `[data-${type}]`;
        const resultSpan = document.querySelector(dataAtt);
        const messageObj = {
            email : "이메일", ukname : "고유이름"
        };


        if(data == "true"){
            resultSpan.style.color = "red";
            resultSpan.innerHTML = `현재 ${messageObj[type]}은 이미 사용중입니다.`;
            
            return;
        }

        resultSpan.style.color = "green";
        resultSpan.innerHTML = `현재 ${messageObj[type]}은 사용 가능합니다.`;
        dupObj[type] = true;
    }

    const emailInput = document.querySelector("#email");
    emailInput.addEventListener("input", emailValCheck);

    const ukNameInput = document.querySelector("#ukName");
    ukNameInput.addEventListener("input", ukNameValCheck);
    


    const submitform = document.querySelector("#joinform");
    submitform.addEventListener("submit",submitCheck)
    // 이메일은 dupObj.email 고유  dupObj.ukanme
    function submitCheck(event) {
        const isPasswordValid = passwordValCheck();
        console.log(isPasswordValid);

        const isPasswordCheckValid = passwordCheck();
        console.log(isPasswordCheckValid);

        const isDisplayNameValid = displayNameValCheck();
        console.log(isDisplayNameValid);

        if(!isPasswordValid || !isPasswordCheckValid || !isDisplayNameValid || !dupObj.email || !dupObj.ukname) {
            event.preventDefault();
            console.log("오류");
        }
    }
}




