window.onload = function () {
    const url = 'http://localhost:8080/api/properties';

    fetch(url)
        .then(res => res.json())
        .then((out) => {
            carousels(out);
        })
        .catch(err => { throw err });
};

function carousels(out) {
    document.getElementById("carousels").innerHTML = carrousel("carousel-lg", 3, out).outerHTML + carrousel("carousel-md", 2, out).outerHTML + carrousel("carousel-sm", 1, out).outerHTML;
};

function carrousel(id, size, out) {
    const properties = [];

    const carousel = document.getElementById(id);
    const slides = carousel.getElementsByClassName("carousel-item").item(0);
    const cards = slides.getElementsByClassName("card").item(0);

    let slide;

    carousel.getElementsByClassName("carousel-inner").item(0).innerHTML = "";
    slides.getElementsByClassName("card-group").item(0).innerHTML = "";
    slides.classList.remove("active");

    out.forEach(property => properties.push(fill(cards.cloneNode(true), property)));

    properties.forEach((property, index) => {
        if (index % size == 0) {
            slide = slides.cloneNode(true);

            if (index == 0) {
                slide.classList.add("active");
            }
        };

        slide.getElementsByClassName("card-group").item(0).innerHTML += property.outerHTML;

        if (index % size == (size - 1) || index == (out.length - 1)) {
            carousel.getElementsByClassName("carousel-inner").item(0).innerHTML += slide.outerHTML;
        };
    });

    return carousel;
};

function fill(card, property) {
    if (!property.outstanding) {
        card.getElementsByClassName("card-outstanding").item(0).remove();
    };

    if (property.category.type == "SELL") {
        card.getElementsByClassName("card-category").item(1).remove();
    } else if (property.category.type == "RENT") {
        card.getElementsByClassName("card-category").item(0).remove();
    };

    card.getElementsByClassName("card-price").item(0).innerHTML = property.price;
    card.getElementsByClassName("card-title").item(0).innerHTML = property.name;
    card.getElementsByClassName("card-description").item(0).innerHTML = property.name;

    return card;
};