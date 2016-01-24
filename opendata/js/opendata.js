$(document).ready
(
	function()
	{
		var env = "dev";
		
		if(env == "dev") {
			var tagUrl = "http://localhost/opendata/php/test.php";
			var tagClickedUrl = "php/test2.php";
			var selectionDetailUrl  = "php/test3.php";
		} else {
			var tagUrl = "http://localhost:8080/wsopendatasarthedev/rservice/Evenements/typeEvenements";
			var tagClickedUrl = "http://localhost:8080/wsopendatasarthedev/rservice/Evenements/search/";
			var selectionNoteUrl = "http://localhost:8080/wsopendatasarthedev/rservice/Evenements/note/";
			var selectionDetailUrl = "http://localhost:8080/wsopendatasarthedev/rservice/Evenements/";
		}
		
		getCloud(tagUrl);
		
		$(document).on
		(
			"click",
			"#selectionCriterias",
			function()
			{
				getSelectionCriterias(url, env);
			}
		);
		
		$(document).on
		(
			"click",
			"#jsonTest",
			function()
			{
				getJSONData();
			}
		);
		
		$(document).on
		(
			"mouseenter",
			".tag",
			function(){
				var tag = $(this).html();
				$(".tag").not(".selectedTag").css({"background-color":"white"});
				if(!$(this).hasClass("selectedTag")){
					$(this).css({"background-color":"yellow"});
				}
				getTagWeight(tagUrl, tag);
			}
		)
		
		$(document).on
		(
			"mouseleave",
			".tag",
			function(){
				$(".tag").not(".selectedTag").css({"background-color":"white"});
			}
		);
		
		$(document).on
		(
			"click",
			".tag",
			function()
			{
				var tag = $(this).html();
				$(".tag").css({"background-color":"white"});
				$(".tag").removeClass("selectedTag");
				$(this).css({"background-color":"#aaffcc"});
				$(this).addClass("selectedTag");
				$("#tagCloud").css({"margin-left":"18px"});
				$("#selectedTagTitle").html(tag);
				$("#tagCloud").css({"float":"left"});
				$("#results").css({"float":"left"});
				var windowWidth = $(window).width();
				windowWidth = parseInt(windowWidth) - 550;
				$("#resultsTable").css({"width":windowWidth});
				$("#results").show();
				
				getSelectionResultsFromTagClick(tagClickedUrl, env, tag);
			}
		);
		
		$(document).on
		(
			"mouseenter",
			".selectionStar",
			function() {
				var id = $(this).attr("app-starId");
				var position = parseInt($(this).attr("app-starPosition"));
				$("img[app-starId="+id+"]").attr("src","img/star_off.png");
				
				for(var i=1; i<position+1; i++) {
					$("img[app-starId="+id+"][app-starPosition="+i+"]").attr("src","img/star_on.png");
				}
			}
		);
		
		$(document).on
		(
			"mouseleave",
			".selectionStar",
			function() {
				var id = $(this).attr("app-starId");
				var notation = parseInt($(this).attr("app-starNotation"));
				$("img[app-starId="+id+"]").attr("src","img/star_off.png");
				for(var i=1; i<notation+1; i++) {
					$("img[app-starId="+id+"][app-starPosition="+i+"]").attr("src","img/star_on.png");
				}
			}
		);
		
		$(document).on
		(
			"click",
			".selectionStar",
			function() {
				var id = $(this).attr("app-starId");
				var position = parseInt($(this).attr("app-starPosition"));
				$("img[app-starId="+id+"]").attr("app-starNotation",position);
				$("img[app-starId="+id+"]").attr("src","img/star_off.png");
				for(var i=1; i<position+1; i++) {
					$("img[app-starId="+id+"][app-starPosition="+i+"]").attr("src","img/star_on.png");
				}
				setNotation(selectionNoteUrl, id, position);
			}
		);
		
		$(document).on
		(
			"click",
			".selectionEvenement",
			function() {
				
				var id = $(this).attr("app-id");
				var state = $(this).attr("app-state");
				if(state == "closed") {
					$(".trInfo[app-id='"+id+"']").show();
					$(this).attr("app-state","opened");
				} else {
					$(".trInfo[app-id='"+id+"']").hide();
					$(this).attr("app-state","closed");
				}
				
				getDetailedSelection(selectionDetailUrl, env, id);
			}
		);
	}
);

function getTagWeight(url, tag) {
	$.get(url, function(json) {
		$.each(json, function(key, val ) {
			if(val.text == tag) {
				$("#tagWeight").html(val.weight);
				$("#tagName").html(val.text);
				$("#tagWeightContainer").show();
			}
		});
	});
}

function getCloud(url, env) {
	
	$.get(url, function(json) {
		
		var windowHeight = $(window).height();
		windowHeight = parseInt(windowHeight) - 170;
		
		$('#tagCloud').jQCloud(json, {
			width: 450,
			height: windowHeight,
			shape:'elliptic',
			afterCloudRender:addTagTrigger,
			autoResize:true
		});
	});
}

function addTagTrigger() {
	$("#tagCloud > span").addClass("tag");
	$("#tagCloud > span").css({"cursor":"pointer"});
	$("#tagCloud").css({"padding":"50px"});
}

function getSelectionResultsFromTagClick(url, env, tag) {
	
	if(env == "prod") {
		url += tag;
	}
	
	$.get(url, function(json) {
		
		var html = "";
		var prop = "commune";
		var asc = true;
		
		json = json.sort(function(a, b) {
			if (asc) return (a[prop] > b[prop]) ? 1 : ((a[prop] < b[prop]) ? -1 : 0);
			else return (b[prop] > a[prop]) ? 1 : ((b[prop] < a[prop]) ? -1 : 0);
		});
		
		$.each(json, function( key, val ) {
			
			var id = val.id;
			var Index = key;
			var nameEvenement = val.nameEvenement;
			var commune = val.commune;
			var longitude = val.longitude;
			var lattitude = val.lattitude;
			var tel = val.tel;
			var mail = val.mail;
			var site = val.site;
			var reseauSociaux = val.reseauSociaux;
			var codePostal = val.codPostal;
			var tarif = val.tarif;
			var entreprise = val.entreprise;
			var adresse = val.adresse;
			var equipement = val.equipement;
			var services = val.services;
			var modePaiement = val.modePaiement;
			var tarifGratuit = val.tarifGratuit;
			var ouverture = val.ouverture;
			var acces = val.acces;
			var starOn = 2;
			var starOff = 5 - starOn;
			var notation = "";
			var trColor = "white";
			var trBorderLeft = "#b3b3b3";
			
			if(key % 2 == 0) {
				trColor = "#cccccc";
				trBorderLeft = "#808080";
			}
			
			for(var i = 0; i< starOn; i++) {
				var position = i+1;
				notation += '<img class="selectionStar" src="img/star_on.png" style="cursor:pointer;" app-starNotation='+starOn+' app-starPosition='+position+' app-starId="'+id+'" />&nbsp;';
			}
			
			for(var i = 0; i<starOff; i++) {
				var position = i+1+starOn;
				notation += '<img class="selectionStar" src="img/star_off.png" style="cursor:pointer;" app-starNotation='+starOn+' app-starPosition='+position+' app-starId="'+id+'" />&nbsp;';
			}
			
			html += "<tr style='background-color:"+trColor+";'>" + "<td style='border-top:1px solid #666666;border-left:4px solid "+trBorderLeft+"' scope=\"row\">"+(Index+1)+"<td style='border-top:1px solid #666666;'><span class='selectionEvenement' style='cursor:pointer' app-id='"+id+"' app-state='closed'>"+nameEvenement+"</span></td>"+"<td style='border-top:1px solid #666666;'>"+commune+"</td><td style='border-top:1px solid #666666;'>"+notation+"</td></tr>";
			html += "<tr style='display:none;' app-id='"+id+"' class='trInfo'></tr>";
		});
		
		$("#resultsTbody").html(html);
		
	});
}

function getDetailedSelection(url, env, id) {
	
	if(env == "prod") {
		url += id;
	}
	
	$.get(url, function(json) {
			var html = "<td colspan='4' style='padding:0px;'>\
				<blockquote style='margin:0px;font-size:13px;height:460px;'>\
					<div style='float:left;width:400px;'>\
						<span><b>id:</b>"+json.id+"</span><br />\
						<span><b>nameEvenement:</b>"+json.nameEvenement+"</span><br />\
						<span><b>tel:</b>"+json.tel+"</span><br />\
						<span><b>mail:</b>"+json.mail+"</span><br />\
						<span><b>site:</b>"+json.site+"</span><br />\
						<span><b>reseauSociaux:</b>"+json.reseauSociaux+"</span><br />\
						<span><b>ouverture:</b>"+json.ouverture+"</span><br />\
						<span><b>equipement:</b>"+json.equipement+"</span><br />\
						<span><b>services:</b>"+json.services+"</span><br />\
						<span><b>entreprise:</b>"+json.entreprise+"</span><br />\
						<span><b>adresse:</b>"+json.adresse+"</span><br />\
						<span><b>commune:</b>"+json.commune+"</span><br />\
						<span><b>codepostal:</b>"+json.codePostal+"</span><br />\
						<span><b>acces:</b>"+json.acces+"</span><br />\
						<span><b>longitude:</b>"+json.longitude+"</span><br />\
						<span><b>lattitude:</b>"+json.lattitude+"</span><br />\
						<span><b>tarif:</b>"+json.tarif+"</span><br />\
						<span><b>tarifGratuit:</b>"+json.tarifGratuit+"</span><br />\
						<span><b>modePaiement:</b>"+json.modePaiement+"</span><br />\
					</div>\
					<div style='height:440px;margin-left:32px;float:left;border-left:4px solid #808080;padding-left:32px;'>\
					"+json.cartOsm+"\
					</div>\
				</blockquote>\
			</td>";

		$(".trInfo[app-id="+id+"]").html(html);
	});
}

function setNotation(url, id, notation) {
	var urlNote = url+"/"+id+"/"+notation;
	$.get(url, function() {});
}