function friendDM(friendDisplayName,friendId) {
    console.log(friendDisplayName.textContent,friendId);

    const DMList = document.getElementById('groupchatList');

    for (var i = 0; i < DMList.children.length; i++) {
        var childDiv = DMList.children[i];
        
        if (childDiv.textContent.trim() === friendDisplayName.textContent) {
          
            console.log("일치하는 텍스트를 찾았습니다:", childDiv);
            console.log(childDiv.value);
            groupchatScreen(childDiv.value, childDiv.textContent);
            break;
        } else {
            console.log('채팅방 없음');
        }
    }
}