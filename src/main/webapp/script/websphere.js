$(document).ready(function(){
	$("#formResource").submit(function(event){
		event.preventDefault();
		$.ajax({
			type: "POST",
			url: actionURL,
			data:{}
		}).done(function( data ) {
			try{
				var json = $.parseJSON( data);
			}catch(err){}
		}).fail(function( jqxhr, textStatus, error ) {			
			console.log(textStatus + ", " + error);										    
		});
	});
});