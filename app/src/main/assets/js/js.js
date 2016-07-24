// Funciones de peticion personalizadas
	
	function GET_HTTP(url) {
		
		document.getElementById("http").innerHTML = "Esperando respuesta del servidor...";
		GET_SWAPP_HTTP(url);
	}
	
	function GET_GPS() {
		
		document.getElementById("coordenadas").innerHTML = "Esperando coordenadas del dispositivo...";
		GET_SWAPP_GPS();
		
	}
	
// Funciones de recepción

	function SWAPP_HTTP(result, status) {
		
		if( status ){
			
			var datos = eval('(' + result + ')');
			document.getElementById("http").innerHTML = SWAPP.getJSON_HTTP();
			
		}else{
			
			document.getElementById("http").innerHTML = "Error, verifique que tiene acceso a internet.";
			
		}
	}

	function SWAPP_GPS(coordenadas) {
		
		if( coordenadas != null ){
			
			var datos = eval('(' + coordenadas + ')');
			document.getElementById("coordenadas").innerHTML = "X: "+datos.x+", Y: "+datos.y;
			
		}else{
			
			document.getElementById("coordenadas").innerHTML = "Error, verifique que tiene el GPS activado.";
			
		}
		
	}
