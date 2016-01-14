<!DOCTYPE html>
<html>
  <head>
    <title>Remove Markers</title>
    <style>
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
      #map {
        height: 100%;
      }
#floating-panel {
    position: absolute;
    top: 10px;
    left: 25%;
    z-index: 5;
    background-color: #fff;
    padding: 5px;
    border: 1px solid #999;
    text-align: center;
    font-family: 'Roboto','sans-serif';
    line-height: 30px;
    padding-left: 10px;
}
    </style>
  </head>
  <body>
    <div id="floating-panel">
      <input onclick = "deleteMarkers();" type = button value="Delete Markers">
      <input onclick = "saveRoute();" type = button value = "Save route">
    </div>
    <div id="map"></div>
    <p>Click on the map to add markers.</p>
    <script>
var map;
var markers = [];
var allPoints = [];
var url = "";
function Point() {
    var latitude;
    var longitude;
    this.setLatitude = function (lat) {
        this.latitude = lat;
    }
    this.setLongitude = function (long) {
         this.longitude = long;
    }
}
Point.prototype.toJSON = function() {
    var end = '\n';
    return "latitude: " + this.latitude +" " + end + " longitude: " + this.longitude + end;
}


function initMap() {
    var position = navigator.geolocation.getCurrentPosition(function(position) {
        var latitude = position.coords.latitude;
        var longitude = position.coords.longitude
        map = new google.maps.Map(document.getElementById('map'), {
            center: {lat: latitude, lng: longitude},
            zoom: 12
        });
        map.addListener('click', function(event) {
            addMarker(event.latLng);
        });
    });
}

function addMarker(location) {
    var marker = new google.maps.Marker({
        position: location,
        map: map
    });
    markers.push(marker);
    var p = new Point();
    p.setLatitude(location.lat());
    p.setLongitude(location.lng());
    allPoints.push(p);
}

function deleteMarkers() {
    if (markers.length > 0) {
        var last = markers.pop();
        last.setMap(null);
        allPoints.pop();
    }
}

function saveRoute() {
    if (allPoints.length > 0) {
        var json = JSON.stringify(allPoints);
        alert(json);
        //jQuery.post(url, json, resultSave, "json");
    }
}

function resultSave (data, textStatus, jqXHR) {
    alert(textStatus);
}

    </script>
    <script async defer
         src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB2Xr6t-W21QxCKzhDJL1Zwz19pNGnc78I&signed_in=true&callback=initMap"></script>
  </body>
</html>