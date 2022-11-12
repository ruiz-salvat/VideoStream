const form = document.querySelector("#video-form");
const videPlayer = document.querySelector("#video-player");
const videoScreen = document.querySelector("#video-screen");

// const queryParams = Objetc.fromEntries(new URLSearchParams(window.location.search));

fetch('http://localhost:8080/video/all')
    .then(result => result.json())
    .then(result => {
        
        console.log("RASULT", result);

        const myVids = document.querySelector('#your-videos');

        if(result.length > 0){
            for(let vid of result){
                const li = document.createElement('LI');
                const link = document.createElement('A');
                link.innerText = vid;
                link.href = window.location.origin + window.location.pathname + '?video=' + vid;
                li.appendChild(link);
                myVids.appendChild(li);
            }
        }else{
            myVids.innerHTML = 'No videos found';
        }

    });