//Implementation of Google Maps Javascript API with autocomplete search box in New Listing form
const uluru = {lat: 53.1424, lng: -7.6921};

var map;
function initAutocomplete() {

    //Setting the map for display
    map = new google.maps.Map(document.getElementById('new-listing'), {
        center: uluru,
        zoom: 7,
        mapTypeId: 'roadmap',
    });

    // Creating the search box
    const input = document.getElementById('addressInput');
    const searchBox = new google.maps.places.SearchBox(input);
    const floatBox = document.getElementById('floating-panel');
    map.controls[google.maps.ControlPosition.TOP_LEFT].push(floatBox);

    //SearchBox results are geared in favour of the current map viewport
    map.addListener("bounds_changed", () => {
        searchBox.setBounds(map.getBounds());
    });

    let markers = [];

    // Listen for the event fired when the user selects an address and retrieve details of the place
    searchBox.addListener("places_changed", () => {
        const places = searchBox.getPlaces();

        if (places.length == 0) {
            return;
        }

        // Clear out the old markers
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