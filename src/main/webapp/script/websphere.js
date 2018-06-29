$(document).ready(function(){
	$("#formResource").submit(function(event){
		event.preventDefault();				
		$.ajax({
			type: "POST",
			url: resourceURL,
			data:{modo : 1}
		}).done(function( data ) {
			try{
				var json = $.parseJSON( data);
				for (var item in json){
					$("input[name='" + item + "']").val(json[item]);
				} 
			}catch(err){}
		}).fail(function( jqxhr, textStatus, error ) {			
			console.log(textStatus + ", " + error);										    
		});
	});
	
	$("#formResourceFile").submit(function(event){
		event.preventDefault();				
		$.ajax({
			url: resourceURL,
			type: "POST",			
			data: new FormData( this ),
	        processData: false,
	        contentType: false
		}).done(function( data ) {
			try{
				var json = $.parseJSON( data);
				for (var item in json){
					$("input[name='" + item + "']").val(json[item]);
				} 
			}catch(err){}
		}).fail(function( jqxhr, textStatus, error ) {			
			console.log(textStatus + ", " + error);										    
		});
	});
});