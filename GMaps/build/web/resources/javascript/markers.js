/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var currentMarker = null;

function mover(lat,lng){
    var centros = new google.maps.LatLng(lat,lng);
    map.setCenter(centros);
    map.setZoom(10);
}
	
function handlePointClick(event) {
    if(currentMarker == null) {
        document.getElementById('lat').value = event.latLng.lat();
        document.getElementById('lng').value = event.latLng.lng();

        currentMarker = new google.maps.Marker({
            position:new google.maps.LatLng(event.latLng.lat(), event.latLng.lng())
        });
							
        map.addOverlay(currentMarker);

        dlg.show();
    }	
}

function markerAddComplete() {
    var title = document.getElementById('title');
    currentMarker.setTitle(title.value);
    title.value = "";

    currentMarker = null;
    dlg.hide();
    //window.location.reload();
    
}

function cancel() {
    dlg.hide();
    currentMarker.setMap(null);
    currentMarker = null;

    return false;
}