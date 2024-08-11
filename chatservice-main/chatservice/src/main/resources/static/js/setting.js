/*document.addEventListener("DOMContentLoaded", function() {
    const navItems = document.querySelectorAll(".settingCategory");
    const mainContent = document.querySelector(".setTitle");

    navItems.forEach(item => {
      item.addEventListener("click", function() {
        const contentId = item.getAttribute("data[0]-content");
        updateContent(contentId);
      });
    });

    function updateContent(contentId) {
      let content;
      switch(contentId) {
        case "myAccount":
          content = "<h1>내 계정</h1><p>이것은 내 계정의 내용입니다.</p>";
          break;
        case "profile":
          content = "<h1>프로필</h1><p>이것은 프로필의 내용입니다.</p>";
          break;
        case "logout":
          content = "<h1>로그아웃</h1><p>이것은 로그아웃의 내용입니다.</p>";
          break;
        default:
          content = "<h1>기본 콘텐츠</h1><p>기본 내용을 여기에 표시합니다.</p>";
      }
      mainContent.innerHTML = content;
    }
});*/


function myprofile() {
  document.getElementById("updateform").style.display = 'none';

  const myprofileArea = document.getElementById("myprofileArea");
    myprofileArea.style.display = "block";

  const sessionId = document.getElementById("sessionId").value;
  const params = new URLSearchParams();
  params.append('ids', sessionId); // 여기서 sessionId를 배열에 담아 전송

  fetch('/user/listbylist?' + params.toString(), {
    method: "GET"
  })
  .then(resp => resp.json())
  .then(data => {
    console.log(data[0]);
    
    const myprofile = document.getElementById("myprofile");
    myprofile.innerHTML = '';
    const infochange = document.getElementById("infochangeArea");
    infochange.style.display = "none";

    const mypfptitle = document.createElement("div");
    mypfptitle.innerHTML = "내 계정";
    mypfptitle.id = "mypfptitle";

    myprofile.appendChild(mypfptitle);

    const mypfpArea = document.createElement("div");
    const mypfp = document.createElement("img");

    if (data[0].appuserGenPfp === null) {
      mypfp.src = '/media/userpfp/logo.png'; // 경로가 잘못된 경우 수정 필요
  } else {
    mypfp.src = '/media/userpfp/' + data[0].appuserGenPfp;
  }
    mypfp.id ="mypfpImage";
    mypfpArea.appendChild(mypfp);
    mypfpArea.id = "mypfpArea";

    const table = document.createElement("table");
    table.id = "myprofileTable";

    // Create rows and cells for each user detail
    const rows = [
      { label: "고유이름", value: data[0].appuserUkName },
      { label: "이메일", value: data[0].appuserEmail },
      { label: "디스플레이 이름", value: data[0].appuserDisplayName },
      { label: "가입일", value: data[0].appuserRegDate }
      // Add more rows as needed
    ];

    rows.forEach(rowData => {
      const row = document.createElement("tr");
      const labelCell = document.createElement("td");
      labelCell.textContent = rowData.label;
      const valueCell = document.createElement("td");
      valueCell.textContent = rowData.value;
      row.appendChild(labelCell);
      row.appendChild(valueCell);
      table.appendChild(row);
    });

    myprofile.appendChild(mypfpArea);
    myprofile.appendChild(table);

  })
  .catch(error => {
    console.error('데이터 가져오는 중 오류 발생:', error);
  });
}

function infochange() {
  console.log("passwordcheck");
  const myprofileArea = document.getElementById("myprofileArea");
    myprofileArea.style.display = "none";

    const infochange = document.getElementById("infochangeArea");
    infochange.style.display = "block";

    document.getElementById("updateform").style.display = 'none';

    document.getElementById("inputPasswordTable").style.display = 'block';

    document.getElementById("infochangepassword").value = "";
    document.getElementById("infochangepasswordcheck").value = "";
}



window.onload = function() {
  // 초기화면 설정
  myprofile();
  document.getElementById("updatePfpbtn").addEventListener("click", function() {
    document.getElementById("fileInput").click();
  });

  document.getElementById("updateform").style.display = 'none';
}



function updatePfp(event) {
  event.preventDefault();

    const formData = new FormData(document.getElementById("updataPfpform"));

    fetch('user/updatepfp', {
      method: 'PUT',
      body: formData
    })
    .then(resp => resp.json())
    .then(data => {
      console.log(data);
      const mypfpImage = document.getElementById("mypfpImage");
      console.log(mypfpImage);
      mypfpImage.src = '/media/userpfp/' + data.appuserGenPfp;
      
    })
    .catch(error => {
      console.error('Error:', error);
    });
}

function goBack() {
  location.href = document.referrer;
}

document.addEventListener('keydown', function(event) {
  if (event.key === "Escape" || event.key === "Esc") {
      goBack();
  }
});

function setDefaultCategory() {
  const defaultButton = document.getElementById("myAccount");
  if (defaultButton) {
    defaultButton.classList.add('selectedCategory');
    myprofile();
  }
}

function selectCategory(selectedButton) {
  const buttons = document.querySelectorAll('.settingCategory');
  buttons.forEach(button => button.classList.remove('selectedCategory'));

  selectedButton.classList.add('selectedCategory');
}