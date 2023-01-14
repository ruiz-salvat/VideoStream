const videoList = document.querySelector("#video-list");
const videoCardContainer = document.querySelector("#video-card-container");

fetch("video/all")
    .then(response => response.json())
    .then(response => {
        if(response.length > 0){
            for(let video of response){
                const videoLink = document.createElement("A");
                const videoCard = document.createElement("DIV");
                const videoCardImage = document.createElement("IMG");
                const videoCardTextContainer = document.createElement("DIV");
                const videoCardTitle = document.createElement("DIV");
                const hr = document.createElement("HR");
                const videoCardSynopsis = document.createElement("DIV");

                videoCard.classList.add("video-card");
                videoCardImage.classList.add("video-card-image");
                videoCardTextContainer.classList.add("video-card-text-container");
                videoCardTitle.classList.add("video-card-title");
                videoCardSynopsis.classList.add("video-card-synopsis");

                videoLink.href = `${window.location.origin}${window.location.pathname}?video=${video.slug}`;
                videoCardImage.src = `video/image/${video.slug}`;
                videoCardTitle.innerText = video.title;
                videoCardSynopsis.innerText = video.synopsis;

                videoCard.appendChild(videoCardImage);
                videoCardTextContainer.appendChild(videoCardTitle);
                videoCardTextContainer.appendChild(hr);
                videoCardTextContainer.appendChild(videoCardSynopsis);
                videoCard.appendChild(videoCardTextContainer);
                videoLink.appendChild(videoCard);
                videoCardContainer.appendChild(videoLink);
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