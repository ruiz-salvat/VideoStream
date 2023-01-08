const videoForm = document.querySelector("#video-form");
const videoListTable = document.querySelector("#video-list-table");

function deleteVideo(slug) {
    fetch("private-video/" + slug, {
            method: "DELETE"
        }).then(response => {
            console.log(response);
        });
}

fetch("video/all")
    .then(response => response.json())
    .then(response => {
        if(response.length > 0){
            for(let video of response){
                const tr = document.createElement("TR");

                const tdSlug = document.createElement("TD");
                const tdTitle = document.createElement("TD");
                const tdDescription = document.createElement("TD");
                const tdActions = document.createElement("TD");

                tdSlug.innerText = video.slug;
                tdTitle.innerText = video.title;
                tdDescription.innerText = video.description;

                const deleteButton = document.createElement("BUTTON");
                deleteButton.innerText = "delete";
                deleteButton.addEventListener("click", function() {
                    deleteVideo(video.slug);
                }, false);
                tdActions.appendChild(deleteButton);

                tr.appendChild(tdSlug);
                tr.appendChild(tdTitle);
                tr.appendChild(tdDescription);
                tr.appendChild(tdActions);

                videoListTable.appendChild(tr);
            }
        }else{
            // TODO: no videos found message
        }
    });

videoForm.addEventListener("submit", ev => {
    ev.preventDefault();
    let data = new FormData(videoForm);

    fetch("private-video", {
        method: "POST",
        body: data
    }).then(response => {
        var xmlHttp = new XMLHttpRequest();

        if (response.status === 200) {
            xmlHttp.open( "GET", "components/video-upload-success", false);
        } else {
            xmlHttp.open( "GET", "components/video-upload-fail", false);
        }

        xmlHttp.send( null );
        document.getElementById("response-message").innerHTML=xmlHttp.responseText;
    });
});