const videoForm = document.querySelector("#video-form");

videoForm.addEventListener("submit", ev => {
    ev.preventDefault();
    let data = new FormData(videoForm);

    fetch("http://localhost:8080/video", {
        method: "POST",
        body: data
    }).then(response => {
        var xmlHttp = new XMLHttpRequest();

        if (response.status === 200) {
            xmlHttp.open( "GET", "http://localhost:8080/components/video-upload-success", false);
        } else {
            xmlHttp.open( "GET", "http://localhost:8080/components/video-upload-fail", false);
        }

        xmlHttp.send( null );
        document.getElementById("response-message").innerHTML=xmlHttp.responseText;
    });
});