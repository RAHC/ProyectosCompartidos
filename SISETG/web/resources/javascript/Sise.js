/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var currentMarker = null;
	
function handlePointClick(event) {
    //cambios para elevacion
    var elevator;
    elevator = new google.maps.ElevationService();
    var locations = [];
    if(currentMarker == null) {
        document.getElementById('f2:lat').value = event.latLng.lat();
        document.getElementById('f2:lng').value = event.latLng.lng();
        var clickedLocation = event.latLng;
        locations.push(clickedLocation);
        var positionalRequest = {
            'locations': locations
        }
        elevator.getElevationForLocations(positionalRequest, function(results, status) {
            if (status == google.maps.ElevationStatus.OK) {

                // Retrieve the first result
                if (results[0]) {
                    var altura=results[0].elevation;
                    // Open an info window indicating the elevation at the clicked position
                    document.getElementById('f2:altu').value = altura.toFixed(2);
              
                } else {
                    alert('No results found');
                }
            } else {
                alert('Elevation service failed due to: ' + status);
            }
        });

        currentMarker = new google.maps.Marker({
            position:new google.maps.LatLng(event.latLng.lat(), event.latLng.lng())
        });
							
        map.addOverlay(currentMarker);

        dlg.show();
    }	
}

function markerAddComplete() {
    var title = "Incidente";
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