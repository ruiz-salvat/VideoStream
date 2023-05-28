var xmlHttp = new XMLHttpRequest();
const videoForm = document.querySelector("#video-form");
const categoryForm = document.querySelector("#category-form");
const videoListTable = document.querySelector("#video-list-table");
const categoryListTable = document.querySelector("#category-list-table");
const categorySelector = document.querySelector("#category");
const planSelector = document.querySelector("#plan");

let categoryOptions = [];
let planOptions = [];

function addCategoryOption(category) {
    categoryOptions.push(category);
    const option = document.createElement("OPTION");
    option.innerText = category.name;
    option.value = category.id;
    option.id = "option_" + category.id;
    categorySelector.appendChild(option);
}

function addPlanOption(plan) {
    planOptions.push(plan);
    const option = document.createElement("OPTION");
    option.innerText = plan.id; // TODO: set a name
    option.value = plan.id;
    option.id = "option_" + plan.id;
    planSelector.appendChild(option);
}

function removeCategoryOption(id) {
    let index = -1;
    for (let i = 0; i < categoryOptions.length; i++) {
        if (categoryOptions[i].id === id) {
            index = i;
            break;
        }
    }
    if (index >= 0)
      categoryOptions.splice(index, 1);

    const optionElement = document.getElementById("option_" + id);
    categorySelector.removeChild(optionElement);
}

function deleteVideo(slug) {
    fetch("private-video/" + slug, {
            method: "DELETE"
        }).then(response => {
            if (response.status === 200) {
                let rowToRemove = document.getElementById(slug);
                videoListTable.removeChild(rowToRemove);
                xmlHttp.open( "GET", "components/video-delete-success", false);
            } else {
                xmlHttp.open( "GET", "components/video-delete-fail", false);
            }

            xmlHttp.send( null );
            document.getElementById("response-message").innerHTML=xmlHttp.responseText;
        });
}

function deleteCategory(id) {
    fetch("private-category/" + id, {
            method: "DELETE"
        }).then(response => {
            if (response.status === 200) {
                let rowToRemove = document.getElementById(id);
                categoryListTable.removeChild(rowToRemove);
                removeCategoryOption(id);
                xmlHttp.open( "GET", "components/category-delete-success", false);
            } else {
                xmlHttp.open( "GET", "components/category-delete-fail", false);
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
    const tdCategory = document.createElement("TD");
    const tdPlan = document.createElement("TD");
    const tdActions = document.createElement("TD");

    tdSlug.innerHTML = `<div class='cell-container'>${video.slug}</div>`;
    tdTitle.innerHTML = `<div class='cell-container'>${video.title}</div>`;
    tdSynopsis.innerHTML = `<div class='cell-container synopsis'>${video.synopsis}</div>`;
    tdDescription.innerHTML = `<div class='cell-container'>${video.description}</div>`;

    let category = categoryOptions.find(category => category.id === video.category);
    tdCategory.innerHTML = `<div class='cell-container'>${category.name}</div>`;

    let plan = planOptions.find(plan => plan.id === video.plan);
    tdPlan.innerHTML = `<div class='cell-container'>${plan.id}</div>`;

    const deleteButton = document.createElement("BUTTON");
    deleteButton.innerText = "delete";
    deleteButton.addEventListener("click", function() {
        if (confirm("Are you sure that you want to delete this video?"))
            deleteVideo(video.slug);
    }, false);
    tdActions.appendChild(deleteButton);

    tr.appendChild(tdSlug);
    tr.appendChild(tdTitle);
    tr.appendChild(tdSynopsis);
    tr.appendChild(tdDescription);
    tr.appendChild(tdCategory);
    tr.appendChild(tdPlan);
    tr.appendChild(tdActions);

    videoListTable.appendChild(tr);
}

function addCategoryToList(category) {
    const tr = document.createElement("TR");
    tr.id = category.id;

    const tdName = document.createElement("TD");
    const tdDescription = document.createElement("TD");
    const tdActions = document.createElement("TD");

    tdName.innerHTML = `<div class='cell-container'>${category.name}</div>`;
    tdDescription.innerHTML = `<div class='cell-container'>${category.description}</div>`;

    const deleteButton = document.createElement("BUTTON");
    deleteButton.innerText = "delete";
    deleteButton.addEventListener("click", function() {
        if (confirm("Are you sure that you want to delete this category?"))
            deleteCategory(category.id);
    }, false);
    tdActions.appendChild(deleteButton);

    tr.appendChild(tdName);
    tr.appendChild(tdDescription);
    tr.appendChild(tdActions);

    categoryListTable.appendChild(tr);
}

fetch("category/all")
    .then(response => response.json())
    .then(response => {
        if (response.length > 0) {
            for (let category of response) {
                addCategoryToList(category);
                addCategoryOption(category);
            }
        }
    }).then(() => {
        fetch("private-plan/all")
            .then(response => response.json())
            .then(response => {
                if (response.length > 0) {
                    for (let plan of response) {
                        addPlanOption(plan);
                    }
                }
        }).then(() => {
            fetch("video/all")
                .then(response => response.json())
                .then(response => {
                    if(response.length > 0) {
                        for (let video of response) {
                            addVideoToList(video);
                        }
                    }
                });
        });
    });

videoForm.addEventListener("submit", ev => {
    ev.preventDefault();
    let data = new FormData(videoForm);
    fetch("private-video", {
        method: "POST",
        body: data
    }).then(r =>  r.json().then(json => ({status: r.status, data: json})))
    .then(response => {
        if (response.status === 200) {
            addVideoToList(response.data);
            xmlHttp.open( "GET", "components/video-upload-success", false);
            videoForm.reset();
        } else {
            xmlHttp.open( "GET", "components/video-upload-fail", false);
        }

        xmlHttp.send( null );
        document.getElementById("response-message").innerHTML=xmlHttp.responseText;
    });
});

categoryForm.addEventListener("submit", ev => {
    ev.preventDefault();
    let data = new FormData(categoryForm);

    fetch("private-category", {
        method: "POST",
        body: data
    }).then(r =>  r.json().then(json => ({status: r.status, data: json})))
    .then(response => {
        if (response.status === 200) {
            addCategoryToList(response.data);
            addCategoryOption(response.data);
            xmlHttp.open( "GET", "components/category-upload-success", false);
            categoryForm.reset();
        } else {
            xmlHttp.open( "GET", "components/category-upload-fail", false);
        }

        xmlHttp.send( null );
        document.getElementById("response-message").innerHTML=xmlHttp.responseText;
    });
});