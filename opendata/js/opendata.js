$(document).ready
(
	function()
	{
		var tagUrl = "";
		
		$(document).on
		(
			"click",
			"#selectionCriterias",
			function()
			{
				getSelectionCriterias(url);
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
				$(".tag").not(".selectedTag").css({"background-color":"white"});
				if(!$(this).hasClass("selectedTag")){
					$(this).css({"background-color":"yellow"});
				}
			}
		)
		
		$(document).on
		(
			"mouseleave",
			".tag",
			function(){
				$(".tag").not(".selectedTag").css({"background-color":"white"});
			}
		)
		
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
				$("#tagCloud").css({"margin-left":"0px", "border-right":"2px solid #e6e6e6"});
			}
		);
		
		getCloud();
	}
);

function getCloud() {
	
	$.get("php/test.php", function(json) {
	
		var tags = jQuery.parseJSON(json);
		
		$('#tagCloud').jQCloud(tags, {
			width: 500,
			height: 350,
			shape:'elliptic',
			afterCloudRender:addTagTrigger
		});
	});
}

function addTagTrigger() {
	$("#tagCloud > span").addClass("tag");
	$("#tagCloud > span").css({"cursor":"pointer"});
}