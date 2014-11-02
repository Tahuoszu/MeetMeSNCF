// Génère le tableau de résultats à partir du JSON

var depart;

$(document).ready(function() {
	// Stop la soumisssion de requête
	$("#searchForm").submit(function(e) {
	       e.preventDefault();
	});
	// Vérifie le clic sur le bouton

	$("#searchtrain").click(function(event) {
		// Récupère les données du formulaire et les sérialise
	    //dataString = $("#searchForm").serialize();
	    depart = $('#depart').val();
	    var arrivee = $('#arrivee').val();
	    //dataString = 'depart=' + depart + '&arrivee=' + arrivee;
	    //alert(dataString);
	    $.ajax({
	    	url : "searchTrain",
	        type : "POST",
	        data : {
                depart : depart,
                arrivee : arrivee
            },
	        dataType : "json",
	        // Réception d'une réponse provenant du serveur
	        success : function(responseJson) {
	            if(responseJson != null) {
	                $("#trains").find("tr:gt(0)").remove();
	                var table = $("#trains");
	                $.each(responseJson, function(key, value) { 
	                    var rowNew = $("<tr><td></td><td></td><td></td><td></td></tr>");
	                    rowNew.children().eq(0).text(value['num']); 
	                    rowNew.children().eq(1).text(value['date']); 
	                    rowNew.children().eq(2).text(value['miss']);
	                    var code = '<button type="button" id="' + value['num'] + '" class="btn btn-primary btn-xs train">Choisir</button>';
	                    rowNew.children().eq(3).append(code);
	                    //rowNew.children().eq(3).text(value['term']); 
	                    //rowNew.children().eq(4).text(value['etat']); 
	                    rowNew.appendTo(table);
	                });
	            }
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


$(".train").click(function(event) {
    var login = $("#chats").find("input").val();
    var num = $(this).val();
    $.ajax({
        url : "takeTrain",
        type : "POST",
        data : {
            login : login,
            depart : depart,
            num : num
        },
        dataType : "json",
        // Réception d'une réponse provenant du serveur
        success : function(responseJson) {
            if(responseJson != null) {
                // recup la liste des gens qui vont prendre meme train
                
                /*
                var table = $("#users");
                $("#users").find("tr:gt(0)").remove();
                $.each(responseJson, function(key, value) {
                    var rowNew = $("<tr><td></td><td></td><td></td></tr>");
                    rowNew.children().eq(0).text(value['login']); 
                    rowNew.children().eq(1).text(value['presentation']);
                    
                    var code = '<div class="chooseReceiver">'+
                        '<button type="button" class="btn btn-primary btn-xs talk">Chatter</button>'+
                        '<input type="hidden" class="destinataire" value="' + login + '" />'+
                      '</div>';
                    rowNew.children().eq(3).append(code);
                    
                    rowNew.appendTo(table);
                });
                */
            }
        }
    });
});
