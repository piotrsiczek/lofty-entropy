setInterval(function () {imageTimer()}, 4000);

var imageNumber = 0;

function imageTimer() {
    if (imageNumber == 5)
        imageNumber = 0;

    if (imageNumber > 0)
        document.body.style.backgroundImage="url('/images/wallpaper" + imageNumber + ".jpg')";
    imageNumber++;
}