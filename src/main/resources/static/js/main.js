fetch("http://localhost:8080/video/all")
    .then(result => result.json())
    .then(result => {
        const videos = document.querySelector("#video-list");

        if(result.length > 0){
            for(let vid of result){
                const li = document.createElement("LI");
                const link = document.createElement("A");
                link.innerText = vid;
                link.href = window.location.origin + window.location.pathname + "page/?video=" + vid;
                li.appendChild(link);
                videos.appendChild(li);
            }
        }else{
            videos.innerHTML = "No videos found";
        }
    });