<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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

function Point() {
    var lat;
    var lng;
    this.setLatitude = function (latitude) {
        this.lat = latitude;
    }
    this.setLongitude = function (long) {
         this.lng = long;
    }
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
    var myUrl = "http://localhost:8080/Meeting/save";
    var property = ${userForm.sessionId};
    //alert(property);
    if (allPoints.length > 0) {
        var json = JSON.stringify(allPoints);
        //document.write(json);
        jQuery.ajax({
                headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                url: myUrl,
                type: 'POST',
                data: json,
                dataType: 'json',
               success:function(result)//we got the response
               {
                alert('Successfully called');
               },
               error:function(exception){alert('Exeption:'+exception);}


            })
    }
}
function resultSave (data, textStatus, jqXHR) {
    alert(textStatus);
}
</script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script async defer
         src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB2Xr6t-W21QxCKzhDJL1Zwz19pNGnc78I&signed_in=true&callback=initMap"></script>
  </body>
</html>