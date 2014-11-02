// Génère le tableau de résultats à partir du JSON

$(document).ready(function() {
	// Stop la soumisssion de requête
	$("#searchForm").submit(function(e) {
	       e.preventDefault();
	});
	// Vérifie le clic sur le bouton
	$("#searchtrain").click(function(event) {
		// Récupère les données du formulaire et les sérialise
	    dataString = $("#searchForm").serialize();
	    var depart = $("input#depart").val();
	    var arrivee = $("input#arrivee").val();
	    dataString = 'depart=' + depart + '&arrivee=' + arrivee;
	    alert(dataString);
	    $.ajax({
	    	url : "searchTrain",
	        type : "POST",
	        data : dataString,
	        dataType : "json",
	        // Réception d'une réponse provenant du serveur
	        success : function(responseJson) {
	        	$.get('searchTrain', function(responseJson) {
	    			if(responseJson != null) {
	    				$("#trains").find("tr:gt(0)").remove();
	    				var table = $("#trains");
	    				$.each(responseJson, function(key, value) { 
	    					var rowNew = $("<tr><td></td><td></td><td></td><td></td><td></td></tr>");
	    					rowNew.children().eq(0).text(value['num']); 
	    					rowNew.children().eq(1).text(value['date']); 
	    					rowNew.children().eq(2).text(value['miss']); 
	    					rowNew.children().eq(3).text(value['term']); 
	    					rowNew.children().eq(4).text(value['etat']); 
	    					rowNew.appendTo(table);
	    				});
	    			}
	    		});
	        },
	        // En l'absence de réponse du serveur
	        error : function(data, status, error) {
	             console.log("error : " + data + " status : " + status + " er : " + error);
	        },
	        // Capture de la requête avant l'envoi au serveur
	        beforeSend : function(data, settings) {
	            // Désactive le bouton jusqu'à la réception de la réponse 
	            $('#searchtrain').attr("disabled", true);
	        },
	        complete : function(data, status){
	            // Active le bouton
	            $('#searchtrain').attr("disabled", false);
	        }
	    });
	});
});

/*
$(document).ready(function() {
	$("#resultat").hide();
	$("#searchtrain").click(function(event) {
		$.get('searchTrain', function(responseJson) {
			if(responseJson != null) {
				$("#trains").find("tr:gt(0)").remove();
				var table = $("#trains");
				$.each(responseJson, function(key, value) { 
					var rowNew = $("<tr><td></td><td></td><td></td><td></td><td></td></tr>");
					rowNew.children().eq(0).text(value['num']); 
					rowNew.children().eq(1).text(value['date']); 
					rowNew.children().eq(2).text(value['miss']); 
					rowNew.children().eq(3).text(value['term']); 
					rowNew.children().eq(4).text(value['etat']); 
					rowNew.appendTo(table);
				});
			}
		});
		$("#resultat").show();          
	});
});
*/