const uluru = {lat: 53.1424, lng: -7.6921};

var map;
//Google Maps autocomplete search box in New Listing form
function initAutocomplete() {

    map = new google.maps.Map(document.getElementById('new-listing'), {
        center: uluru,
        zoom: 7,
        mapTypeId: 'roadmap',
    });

    // Create the search box and link it to the UI element.
    const input = document.getElementById('addressInput');
    const searchBox = new google.maps.places.SearchBox(input);
    const floatBox = document.getElementById('floating-panel');
    map.controls[google.maps.ControlPosition.TOP_LEFT].push(floatBox);

    // Bias the SearchBox results towards current map's viewport.
    map.addListener("bounds_changed", () => {
        searchBox.setBounds(map.getBounds());
    });

    let markers = [];

    // Listen for the event fired when the user selects a prediction and retrieve
    // more details for that place.
    searchBox.addListener("places_changed", () => {
        const places = searchBox.getPlaces();

        if (places.length == 0) {
            return;
        }

        // Clear out the old markers.
        markers.forEach((marker) => {
            marker.setMap(null);
        });
        markers = [];

        // For each place, get the icon, name and location.
        const bounds = new google.maps.LatLngBounds();

        places.forEach((place) => {
            if (!place.geometry || !place.geometry.location) {
                console.log("Returned place contains no geometry");
                return;
            }

            const icon = {
                url: place.icon,
                size: new google.maps.Size(71, 71),
                origin: new google.maps.Point(0, 0),
                anchor: new google.maps.Point(17, 34),
                scaledSize: new google.maps.Size(25, 25),
            };

            // Create a marker for each place.
            markers.push(new google.maps.Marker({
                map, icon, title: place.name, position: place.geometry.location,
            }));
            if (place.geometry.viewport) {
                // Only geocodes have viewport.
                bounds.union(place.geometry.viewport);
            } else {
                bounds.extend(place.geometry.location);
            }
        });
        map.fitBounds(bounds);
    });
}

window.initAutocomplete = initAutocomplete;
//
// const image =
//     "https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png";
//
// function initMap() {
//     const map = new google.maps.Map(document.getElementById("listings-map"), {
//         zoom: 7,
//         center: uluru,
//     });
//
//
//     $.getJSON('http://localhost:7777/api/listings',
//         function (listings) {
//             if (listings.length > 0) {
//                 var markers = [];
//                 for (var i = 0; i < listings.length; i++) {
//                     var latLng =
//                         new google.maps.LatLng(listings[i].lat, listings[i].lng);
//
//
//                     const marker = new google.maps.Marker({
//                         position: latLng,
//                         icon: image
//                     });
//
//                     let listingDescription = getListingDescription(listings[i].title, listings[i].rent);
//
//                     const infoWindow = new google.maps.InfoWindow({
//                         content: listingDescription,
//                     });
//
//                     (function (m, infoWindow, idx) {
//                         google.maps.event.addListener(m, 'click', function (evt) {
//                             infoWindow.setContent(listingDescription);
//                             infoWindow.open(map, m);
//                         })
//                     })(marker, infoWindow, i);
//
//                     markers.push(marker);
//                 }
//                 var markerCluster = new MarkerClusterer(map, markers, {imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});
//             }
//         });
//
// }
// window.initMap = initMap;
//
// function getListingDescription(title, rent) {
//     return '<div style="align-content: center; align-items: center; text-align: center;" id="content">' +
//         '<h4>' + title + '</h4>' +
//         '<div id="bodyContent">' +
//         "<p>" + rent + "</p>" +
//         "</div>" + "</div>";
// }

// $(document).ready(function() {
//     $('.js-example-basic-multiple').select2();
// });
