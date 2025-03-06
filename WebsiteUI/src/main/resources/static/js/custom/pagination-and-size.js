function nextPage(page){
    const urlParams = new URLSearchParams(window.location.search);
    urlParams.set("page", page);
    window.location.search = urlParams.toString();
}

function updateSize(size) {
    const urlParams = new URLSearchParams(window.location.search);
    urlParams.set("size", size);
    if (urlParams.has('page')) {
        urlParams.set("page", "0");
    }
    window.location.search = urlParams.toString();
}