function getContent(fragmentId, callback) {

    var pages = {
        about:   "<h2>This is the about page.</h2>",
        login: "<h1>You have logged in.</h1>"
    };

    callback(pages[fragmentId]);
}

function loadContent() {
    var contentDiv = document.getElementById("app");
    var fragmentId = location.hash.substr(1);

    getContent(fragmentId, function(content) {
        contentDiv.innerHTML = content;
    });
}

if (!location.hash) {
    location.hash = "#home";
}

loadContent();

window.addEventListener("hashchange", loadContent)

