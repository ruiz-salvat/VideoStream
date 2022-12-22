const videoPlayer = document.querySelector("#video-player");
const videoScreen = document.querySelector("#video-screen");
const videoTitle = document.querySelector("#video-title");
const videoDescription = document.querySelector("#video-description");

const queryParams = Object.fromEntries(new URLSearchParams(window.location.search));

if(queryParams.video){
    videoScreen.src = `../video/${queryParams.video}`;
    videoPlayer.style.display = "block";
    videoTitle.innerText = "Now playing " + queryParams.video;
}

fetch(`../video/details/${queryParams.video}`)
    .then(response => response.json())
    .then(response => {
        videoTitle.innerText = response.title;
        videoDescription.innerText = response.description;
    });

// components
var xmlHttp = new XMLHttpRequest();
xmlHttp.open( "GET", "../components/title-bar", false);
xmlHttp.send( null );
document.getElementById("title-bar").innerHTML=xmlHttp.responseText;