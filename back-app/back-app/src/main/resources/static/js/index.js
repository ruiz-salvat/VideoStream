//const videoCardContainer = document.querySelector("#video-card-container");
const categoryListContainer = document.querySelector("#category-list-container");

fetch("category/all")
    .then(response => response.json())
    .then(response => {
        response.forEach(category => {
            const categoryContainer = document.createElement("DIV");
            const categoryName = document.createElement("DIV");
            const videoCardContainer = document.createElement("DIV");

            categoryName.innerText = category.name;

            categoryContainer.classList.add("category-container");
            categoryName.classList.add("category-name");
            videoCardContainer.id = category.id;

            categoryContainer.appendChild(categoryName);
            categoryContainer.appendChild(videoCardContainer);
            categoryListContainer.appendChild(categoryContainer);
        });
    }).then(() => {
        fetch("video/all")
            .then(response => response.json())
            .then(response => {
                if(response.length > 0){
                    for(let video of response){
                        const videoCardContainer = document.getElementById(video.category);
                        const videoLink = document.createElement("A");
                        const videoCard = document.createElement("DIV");
                        const videoCardImage = document.createElement("IMG");
                        const videoCardTextContainer = document.createElement("DIV");
                        const videoCardTitle = document.createElement("DIV");
                        const videoCardSynopsis = document.createElement("DIV");

                        videoCardContainer.classList.add("video-card-container");
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
                        videoCardTextContainer.appendChild(videoCardSynopsis);
                        videoCard.appendChild(videoCardTextContainer);
                        videoLink.appendChild(videoCard);
                        videoCardContainer.appendChild(videoLink);
                    }
                }
            });
    });

// components
var xmlHttp = new XMLHttpRequest();
xmlHttp.open( "GET", "components/title-bar", false);
xmlHttp.send( null );
document.getElementById("title-bar").innerHTML=xmlHttp.responseText;