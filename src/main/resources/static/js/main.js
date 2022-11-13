const videoDiv = document.querySelector("#video-player");
const videoScreen = document.querySelector("#video-screen");

const queryParams = Object.fromEntries(new URLSearchParams(window.location.search));

fetch('http://localhost:8080/video/all')
    .then(result => result.json())
    .then(result => {
        const videos = document.querySelector('#video-list');

        if(result.length > 0){
            for(let vid of result){
                const li = document.createElement('LI');
                const link = document.createElement('A');
                link.innerText = vid;
                link.href = window.location.origin + window.location.pathname + '?video=' + vid;
                li.appendChild(link);
                videos.appendChild(li);
            }
        }else{
            videos.innerHTML = 'No videos found';
        }
    });

if(queryParams.video){
    videoScreen.src = `http://localhost:8080/video/${queryParams.video}`;
    videoDiv.style.display = 'block';
    document.querySelector('#now-playing')
        .innerText = 'Now playing ' + queryParams.video;
}