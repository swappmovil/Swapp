// Funciones del GPS

	function GET_SWAPP_GPS() {
		
		if( SWAPP.GPS() ){ }else{
			
			alert("El GPS esta desactivado.");
			
		}
		
	}


// Funciones HTTP
	
	function GET_SWAPP_HTTP(url) {
		
		SWAPP.HTTP(url);
		
	}
	
	function SWAPP_HTTP_INTERNA() {
		
		var result = SWAPP.getJSON_HTTP();
		
		if ( result != "Sin Internet" ){ 
			
			SWAPP_HTTP(result, true);
			
		}else{
			
			SWAPP_HTTP(result, false);
			
			alert("El Internet esta desactivado.");
		}
		
	}
