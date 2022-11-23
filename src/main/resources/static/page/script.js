const videoPlayer = document.querySelector("#video-player");
const videoScreen = document.querySelector("#video-screen");
const videoTitle = document.querySelector("#video-title");
const videoDescription = document.querySelector("#video-description");

const queryParams = Object.fromEntries(new URLSearchParams(window.location.search));

if(queryParams.video){
    videoScreen.src = `http://localhost:8080/video/${queryParams.video}`;
    videoPlayer.style.display = "block";
    videoTitle.innerText = "Now playing " + queryParams.video;
}

fetch(`http://localhost:8080/video/description/${queryParams.video}`).then(response => {
    response.text().then(responseText => videoDescription.innerText = responseText);
});

// components
var xmlHttp = new XMLHttpRequest();
xmlHttp.open( "GET", "http://localhost:8080/components/title-bar", false);
xmlHttp.send( null );
document.getElementById("title-bar").innerHTML=xmlHttp.responseText;