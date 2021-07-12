window.onload = function () {
    const url = '/api/properties';

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
    const cards = slides.getElementsByClassName("card-property").item(0);
    const empty = slides.getElementsByClassName("card-comming").item(0);

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

    for (var index = 1; index < Math.ceil(out.length / size) * size; index++) {
        carousel.getElementsByClassName("card-group").item(0).innerHTML += empty.cloneNode(true).outerHTML;
    };

    return carousel;
};

function fill(card, property) {
    if (!property.outstanding) {
        card.getElementsByClassName("card-outstanding").item(0).remove();
    };

    if (property.category.type == "SELL") {
        card.getElementsByClassName("card-category").item(0).textContent = "En venta";
    } else if (property.category.type == "RENT") {
        card.getElementsByClassName("card-category").item(0).textContent = "En alquiler";
    };

    card.getElementsByClassName("card-price").item(0).textContent = property.price;
    console.log(card.getElementsByClassName("card-title").item(0).children.item(0));
    card.getElementsByClassName("card-title").item(0).innerHTML = card.getElementsByClassName("card-title").item(0).children.item(0).outerHTML + property.name;
    card.getElementsByClassName("card-description").item(0).textContent = property.description;
    card.getElementsByClassName("card-link").item(0).href = "/property/info?id=" + property.id;

    return card;
};