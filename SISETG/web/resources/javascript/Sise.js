PrimeFaces.locales['es'] = {
    closeText: 'Cerrar',
    prevText: 'Anterior',
    nextText: 'Siguiente',
    monthNames: ['Enero','Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
    monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun','Jul','Ago','Sep','Oct','Nov','Dic'],
    dayNames: ['Domingo','Lunes','Martes','Miércoles','Jueves','Viernes','Sábado'],
    dayNamesShort: ['Dom','Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'],
    dayNamesMin: ['D','L','M','M','J','V','S'],
    weekHeader: 'Semana',
    firstDay: 1,
    isRTL: false,
    showMonthAfterYear: false,
    yearSuffix: '',
    timeOnlyTitle: 'Sólo hora',
    timeText: 'Tiempo',
    hourText: 'Hora',
    minuteText: 'Minuto',
    secondText: 'Segundo',
    currentText: 'Fecha actual',
    ampm: false,
    month: 'Mes',
    week: 'Semana',
    day: 'Día',
    allDayText : 'Todo el día'
};
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
    var fecha = new Date();
    title.value = "Incidente Registrado  "+fecha.getDate()+"\n/"+(fecha.getMonth()+1)+"\n/"+fecha.getFullYear()+"; "+fecha.getHours()+"\n:"+fecha.getMinutes()+"\n:"+fecha.getSeconds();  
    currentMarker.setTitle(title.value);  
    // title.value = "Incidente Marcado";  
  
    currentMarker = null;  
    dlg.hide();  
}  
  
function cancel() {  
    dlg.hide();  
    currentMarker.setMap(null);  
    currentMarker = null;  
  
    return false;  
}
