$(document).ready
(
	function()
	{
		var env = "prod";
		
		if(env == "dev") {
			var tagUrl = "http://localhost/opendata/php/test.php";
			var tagClickedUrl = "php/test2.php";
		} else {
			var tagUrl = "http://localhost:8080/wsopendatasarthedev/rservice/Evenements/typeEvenements";
			var tagClickedUrl = "http://localhost:8080/wsopendatasarthedev/rservice/Evenements/search/";
		}
		
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
				$("#results").show();
				
				getSelectionResultsFromTagClick(tagClickedUrl, env, tag);
			}
		);
		
		getCloud(tagUrl);
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
		
		$('#tagCloud').jQCloud(json, {
			width: 450,
			height: 550,
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
		
		$.each(json, function( key, val ) {
			
			var Index = key;
			var nameEvenement = val.nameEvenement;
			var commune = val.commune;
			
			html += "<tr>" + "<th scope=\"row\">"+(Index+1)+"<td>"+nameEvenement+"</td>"+"<td>"+commune+"</td></tr>";
			
		});
		
		$("#resultsTbody").html(html);
		
	});
}