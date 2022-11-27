let map;
function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: parseFloat(personLocation.lat), lng: parseFloat(personLocation.lng)},
        zoom: 15,
        scrollwheel: false
    });
}
