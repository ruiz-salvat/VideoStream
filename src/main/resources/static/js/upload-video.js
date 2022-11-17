const form = document.querySelector("#video-form");

form.addEventListener("submit", ev => {
    ev.preventDefault();
    let data = new FormData(form);

    fetch("http://localhost:8080/video", {
        method: "POST",
        body: data
    }).then(result => result.text()).then(_ => {
        window.location.reload();
    });
});