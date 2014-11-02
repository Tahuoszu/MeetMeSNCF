//Autocomplétion pour la recherche de gares de départ et d'arrivée

var depart;
$("#arrivee").prop("disabled",true);

$(document).ready(function() {
    
	$(function() {
		$("#depart").autocomplete({
			minLength : 2,
			source : function(request, response) {
				$.ajax({
					url : "searchGare",
					type : "POST",
					data : {
						depart : request.term
					},
					dataType : "json",
					success : function(data) {
						response(data);
					}
				});
			},
			// Quand on promene la souris sur un choix propose par lautocompletion
			focus : function(event, ui) {
		        $("#depart").val(ui.item.value);
		        return false;
		    },
		    // Quand on clique sur un choix propose par lautocompletion
			select : function(event, ui) {
				$('#depart').val(ui.item.value);
			    depart = ui.item.value;
			    $("#arrivee").prop("disabled",false);
			    return false;
	        },
	        // Quand apres avoir fait un choix, on clique en dehors de linput 
	        // ou des choix dautocompletion
		    change : function(event, ui) {
		        $('#depart').val(ui.item.value);
		        return false;
		    }
		});
	});
});

$(document).ready(function() {
	$(function() {
		$("#arrivee").autocomplete({
			minLength : 2,
			source : function(request, response) {
				$.ajax({
					url : "searchGare",
					type : "POST",
					data : {
					    departChoosen : depart,
						arrivee : request.term
					},
					dataType : "json",
					success : function(data) {
						response(data);
					}
				});
			},
			focus : function(event, ui) {
		        $("#arrivee").val(ui.item.value );
		        return false;
		    },
			select : function(event, ui) {
				$('#arrivee').val(ui.item.value);
                return false; 
	        },
		    change : function(event, ui) {
		    	$('#arrivee').val(ui.item.value);
                return false; 
		    }
		});
	});
});