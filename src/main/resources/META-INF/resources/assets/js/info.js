Array.from(document.getElementsByClassName("thumbnail")).forEach(function (element, index) {
  element.addEventListener("click", function () {
    show(index);
  });
});

function show(index) {
  bootstrap.Carousel.getInstance(document.getElementById('carousel-1')).to(index);
};

// Initialize and add the map
function initMap() {
  let params = new URL(document.location).searchParams;
  let id = params.get("id");
  const url = '/api/property/location?id=' + id;

  // The location of x
  const dominican = { lat: 18.900, lng: -70.500 };

  // The map, centered at x
  const map = new google.maps.Map(document.getElementById("map"), {
    zoom: 8,
    center: dominican,
  });

  // The marker, positioned at x
  fetch(url)
    .then(res => res.json())
    .then((out) => {
      markers(out, map);
    })
    .catch(err => { throw err });
};

function markers(location, map) {
    new google.maps.Marker({
      position: { lat: location.latitude, lng: location.longitude },
      map: map,
    });
};