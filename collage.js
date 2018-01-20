// JavaScript Document
$("button").click(function(){
	$("body").removeClass("default");
	$("body").addClass("activated");
	setTimeout(function(){
		$("#collage").css("left", ($(this).width()- $("#collage").width())/2);
	}, 550);
});
$( window ).resize(function() {
	$("#collage").css("left", ($(this).width()- $("#collage").width())/2);
});
