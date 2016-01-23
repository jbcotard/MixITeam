function getSelectionCriterias(url) {
	
	$.post(url, function(result) {
		
		var nbSelectionCriterias = parseInt(result.split(";")[0]);
		var selectionCriterias = result.split(";")[1];
		
		var html = "Nombre de critères: " + nbSelectionCriterias + "<br /><br />";
		
		for(var i = 1; i<nbSelectionCriterias+1; i++) {
			html += "Critère" + i + ": " + selectionCriterias.split(",")[i-1] + "<br />";
		}
	
		$("#selectionCriterias").html(html);
	})
}

function getJSONData() {
	$.getJSON( "data/opendata_1.json", function( data ) {
		var items = [];
		$.each( data.value, function( key, val ) {
			
			var resultLi = "<li id='" + key + "'>" + key + ":" + val.SyndicObjectName + "-->" + val.Type;
			
			if(val.Acces) {
				var nbAccess = val.Acces.split("||").length;
				var AccesHTML = "";
				for (var i = 0; i < nbAccess; i++) {
					var imgFull = val.Acces.split("||")[i];
					var imgStart = imgFull.indexOf('<');
					var imgEnd = imgFull.indexOf('>');
					
					AccesHTML += imgFull.substr(imgStart, imgEnd+1) + "&nbsp;&nbsp;&nbsp;";
					
				}
				resultLi += AccesHTML;
			}
			
			if(val.LanguesParleesAccueil) {
				var nbLanguesParleesAccueil = val.LanguesParleesAccueil.split("##").length;
				var LanguesParleesAccueilHTML = "";
				
				for (var i = 0; i < nbLanguesParleesAccueil; i++) {
					var imgFull = val.LanguesParleesAccueil.split("##")[i];
					var imgStart = imgFull.indexOf('<');
					var imgEnd = imgFull.indexOf('>');
					LanguesParleesAccueilHTML += imgFull.substr(imgStart, imgEnd+1) + "&nbsp;&nbsp;&nbsp;";
				}
				resultLi += LanguesParleesAccueilHTML;
			}

			
			if(val.ModePaiement) {
				var nbModePaiement = val.ModePaiement.split("##").length;
				var ModePaiementHTML = "";
				
				for (var i = 0; i < nbModePaiement; i++) {
					var imgFull = val.ModePaiement.split("##")[i];
					var imgStart = imgFull.indexOf('<');
					var imgEnd = imgFull.indexOf('>');
					ModePaiementHTML += imgFull.substr(imgStart, imgEnd+1) + "&nbsp;&nbsp;&nbsp;";
				}
				resultLi += ModePaiementHTML;
			}
			
			if(val.plateformeURL) {
				var nbplateformeURL = val.plateformeURL.split("##").length;
				var plateformeURL = "";
				
				for (var i = 0; i < nbplateformeURL; i++) {
					var imgFull = val.plateformeURL.split("##")[i];
					var imgStart = imgFull.indexOf('<');
					var imgEnd = imgFull.indexOf('>');
					plateformeURL += imgFull.substr(imgStart, imgEnd+1) + "&nbsp;&nbsp;&nbsp;";
				}
				resultLi += plateformeURL;
			}
			
			if(val.Equipements) {
				var nbEquipements = val.Equipements.split("##").length;
				var Equipements = "";
				
				for (var i = 0; i < nbEquipements; i++) {
					var imgFull = val.Equipements.split("##")[i];
					var imgStart = imgFull.indexOf('<');
					var imgEnd = imgFull.indexOf('>');
					Equipements += imgFull.substr(imgStart, imgEnd+1) + "&nbsp;&nbsp;&nbsp;";
				}
				resultLi += Equipements;
			}
			
			if(val.Service) {
				var nbService = val.Service.split("##").length;
				var Service = "";
				
				for (var i = 0; i < nbService; i++) {
					var imgFull = val.Service.split("##")[i];
					var imgStart = imgFull.indexOf('<');
					var imgEnd = imgFull.indexOf('>');
					Service += imgFull.substr(imgStart, imgEnd+1) + "&nbsp;&nbsp;&nbsp;";
				}
				resultLi += Service;
			}
			
			if(val.VisiteLanguesPanneaux) {
				var nbVisiteLanguesPanneaux = val.VisiteLanguesPanneaux.split("##").length;
				var VisiteLanguesPanneaux = "";
				
				for (var i = 0; i < nbService; i++) {
					var imgFull = val.VisiteLanguesPanneaux.split("##")[i];
					var imgStart = imgFull.indexOf('<');
					var imgEnd = imgFull.indexOf('>');
					VisiteLanguesPanneaux += imgFull.substr(imgStart, imgEnd+1) + "&nbsp;&nbsp;&nbsp;";
				}
				resultLi += VisiteLanguesPanneaux;
			}
			
			if(val.Labels) {
				var nbLabels = val.Labels.split("##").length;
				var Labels = "";
				
				for (var i = 0; i < nbService; i++) {
					var imgFull = val.Labels.split("##")[i];
					var imgStart = imgFull.indexOf('<');
					var imgEnd = imgFull.indexOf('>');
					Labels += imgFull.substr(imgStart, imgEnd+1) + "&nbsp;&nbsp;&nbsp;";
				}
				resultLi += Labels;
			}
			
			items.push(resultLi + "</li>");
			
			
			
			
		});
 
		$( "<ul/>", {
			"class": "my-new-list",
			html: items.join( "" )
		}).appendTo( "#jsonTest" );
});