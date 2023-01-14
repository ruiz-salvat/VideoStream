var xmlHttp = new XMLHttpRequest();
const videoForm = document.querySelector("#video-form");
const videoListTable = document.querySelector("#video-list-table");

function deleteVideo(slug) {
    fetch("private-video/" + slug, {
            method: "DELETE"
        }).then(response => {
            let rowToRemove = document.getElementById(slug);
            videoListTable.removeChild(rowToRemove);

            if (response.status === 200) {
                xmlHttp.open( "GET", "components/video-delete-success", false);
            } else {
                xmlHttp.open( "GET", "components/video-delete-fail", false);
            }

            xmlHttp.send( null );
            document.getElementById("response-message").innerHTML=xmlHttp.responseText;
        });
}

function addVideoToList(video) {
    const tr = document.createElement("TR");
    tr.id = video.slug;

    const tdSlug = document.createElement("TD");
    const tdTitle = document.createElement("TD");
    const tdSynopsis = document.createElement("TD");
    const tdDescription = document.createElement("TD");
    const tdActions = document.createElement("TD");

    tdSlug.innerText = video.slug;
    tdTitle.innerText = video.title;
    tdSynopsis.innerText = video.synopsis;
    tdDescription.innerText = video.description;

    const deleteButton = document.createElement("BUTTON");
    deleteButton.innerText = "delete";
    deleteButton.addEventListener("click", function() {
        deleteVideo(video.slug);
    }, false);
    tdActions.appendChild(deleteButton);

    tr.appendChild(tdSlug);
    tr.appendChild(tdTitle);
    tr.appendChild(tdSynopsis);
    tr.appendChild(tdDescription);
    tr.appendChild(tdActions);

    videoListTable.appendChild(tr);
}

fetch("video/all")
    .then(response => response.json())
    .then(response => {
        if(response.length > 0){
            for(let video of response){
                addVideoToList(video);
            }
        }
    });

videoForm.addEventListener("submit", ev => {
    ev.preventDefault();
    let data = new FormData(videoForm);

    fetch("private-video", {
        method: "POST",
        body: data
    }).then(response => {
        let video = {
            slug: data.get("slug"),
            title: data.get("title"),
            synopsis: data.get("synopsis"),
            description: data.get("description")
        }
        addVideoToList(video);

        videoForm.reset();

        if (response.status === 200) {
            xmlHttp.open( "GET", "components/video-upload-success", false);
        } else {
            xmlHttp.open( "GET", "components/video-upload-fail", false);
        }

        xmlHttp.send( null );
        document.getElementById("response-message").innerHTML=xmlHttp.responseText;
    });
});