const videoList = document.querySelector("#video-list");

fetch("video/all")
    .then(result => result.json())
    .then(result => {
        if(result.length > 0){
            for(let video of result){
                const li = document.createElement("LI");
                const link = document.createElement("A");
                link.innerText = video;
                link.classList.add("list-element");
                link.href = window.location.origin + window.location.pathname + "page/?video=" + video;
                li.appendChild(link);
                videoList.appendChild(li);
            }
        }else{
            videoList.innerHTML = "No videos found";
        }
    });

// components
var xmlHttp = new XMLHttpRequest();
xmlHttp.open( "GET", "components/title-bar", false);
xmlHttp.send( null );
document.getElementById("title-bar").innerHTML=xmlHttp.responseText;