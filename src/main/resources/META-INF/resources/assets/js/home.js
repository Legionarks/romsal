window.onload = function () {
    const url = 'http://localhost:8080/api/properties';

    fetch(url)
        .then(res => res.json())
        .then((out) => {
            carrousels(out);
        })
        .catch(err => { throw err });
};

function carrousels(out) {
    carrousel("carousel-md", 2, out);
};

function carrousel(id, size, out) {
    const carousel = document.getElementById("carousel-md");
    const slides = carousel.getElementsByClassName("carousel-item").item(0);
    const cards = slides.getElementsByClassName("card").item(0);

    carousel.getElementsByClassName("carousel-inner").item(0).innerHTML = "";
    slides.getElementsByClassName("card-group").item(0).innerHTML = "";

    let slide, card;

    out.forEach((element, index) => {
        if (index % 2 == 0) {
            slide = slides.cloneNode(true);

            if (index != 0) {
                slide.classList.remove("active");
            }
        };

        card = cards.cloneNode(true);
        slide.getElementsByClassName("card-group").item(0).innerHTML += card.outerHTML;

        if (index % 2 == (2 - 1) || index == (out.length - 1)) {
            carousel.getElementsByClassName("carousel-inner").item(0).innerHTML += slide.outerHTML;
        };
    });

    return carousel;
};
