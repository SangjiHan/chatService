document.addEventListener("DOMContentLoaded", function() {
    const navItems = document.querySelectorAll(".directmessageList");
    const mainContent = document.querySelector(".friendList");

    navItems.forEach(item => {
      item.addEventListener("click", function() {
        const contentId = item.getAttribute("data-content");
        updateContent(contentId);
      });
    });

    function updateContent(contentId) {
      let content;
      switch(contentId) {
        case "message1":
          content = "<h1>메세지 1</h1><p>메세지 1의 내용</p>";
          break;
        case "message2":
          content = "<<h1>메세지 2</h1><p>메세지 2의 내용</p>";
          break;
        case "message3":
          content = "<h1>메세지 3</h1><p>메세지 3의 내용</p>";
          break;
        case "message4":
          content = "<h1>메세지 4</h1><p>메세지 4의 내용</p>";
          break;
        default:
          content = "<h1>친구 목록</h1><p>친구 목록을 여기에 표시합니다.</p>";
      }
      mainContent.innerHTML = content;
    }
});

function selectServer(element) {
    let servers = document.querySelectorAll('.server');
    servers.forEach(server => {
        server.classList.remove('selectedServer');
    });

    element.classList.add('selectedServer');
}


function changeTextStyle(element) {
  resetTextStyles();
  element.classList.add('active');
}

function resetTextStyles() {
  var elements = document.querySelectorAll('.friendNavigation .fn');
  elements.forEach(function(el) {
    el.classList.remove('active');
  });
}

// function changeTextStyle(element) {
//   var buttons = document.querySelectorAll('.fn');
//   buttons.forEach(function(button) {
//     button.classList.remove('selected');
//   });
//   element.classList.add('selected');
// }

function toggleAllModals() {
  document.getElementById('addFriendMain').style.display = 'none';
  document.getElementById('createServerMain').style.display = 'none';
}