const videoList = document.querySelector("#video-list");

fetch("video/all")
    .then(response => response.json())
    .then(response => {
        if(response.length > 0){
            for(let video of response){
                const li = document.createElement("LI");
                const link = document.createElement("A");
                link.innerText = video.title;
                link.classList.add("list-element");
                link.href = window.location.origin + window.location.pathname + "?video=" + video.slug;
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