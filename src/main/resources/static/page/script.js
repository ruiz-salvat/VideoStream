const videoDiv = document.querySelector("#video-player");
const videoScreen = document.querySelector("#video-screen");

const queryParams = Object.fromEntries(new URLSearchParams(window.location.search));

if(queryParams.video){
    videoScreen.src = `http://localhost:8080/video/${queryParams.video}`;
    videoDiv.style.display = "block";
    document.querySelector("#now-playing").innerText = "Now playing " + queryParams.video;
}

// components
var xmlHttp = new XMLHttpRequest();
xmlHttp.open( "GET", "http://localhost:8080/components/title-bar", false);
xmlHttp.send( null );
document.getElementById("title-bar").innerHTML=xmlHttp.responseText;
