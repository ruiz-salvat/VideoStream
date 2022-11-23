const videoForm = document.querySelector("#video-form");

videoForm.addEventListener("submit", ev => {
    ev.preventDefault();
    let data = new FormData(video);

    fetch("http://localhost:8080/video", {
        method: "POST",
        body: data
    }).then(result => result.text()).then(_ => {
        window.location.reload();
    });
});