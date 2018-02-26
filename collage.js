// JavaScript Document
var collages = [];
var activeCollage = -1;
var url = "CollageServlet?";

$(document).ready(function() {
	//fillCollagesArrayTest(10);
	$("#search").click(function(){
    	handleSearchButtonClick();
  	});

  	$( window ).resize(repositionElements);
});

function fillCollagesArrayTest(t){
	//fills with 5 different images and then 
	//t-5 of the same with title t
	addCollage({src: "https://static.pexels.com/photos/210186/pexels-photo-210186.jpeg",
				  name: "waterfall"});
	addCollage({src: "https://www.viewbug.com/media/images/contests/contest8503_banner.jpg",
				  name: "tree"});
	for(var i = 3; i<t;i++){
		addCollage({src: "http://www-bcf.usc.edu/~halfond/pics/headshot.jpg",
				  name: "prof"});
	}
	$("#prev-collage-container div").each(function(index){
		$(this).removeClass("active");
	});
	addCollage({src: "http://www.globallandscapesforum.org/wp-content/uploads/2017/11/situgunung-flipped-1600.jpg",
				  name: "lake"});
}

function repositionElements(){
	if($("body").hasClass("activated")){
  		$("#collage").css("left", ($(this).width()- $("#collage").width())/2);
		
		var currentHeight = $("#title").height()+$("#title").offset().top + 10;
  		$("#collage").css("top", currentHeight);
		
		currentHeight += $("#collage").height() + 10;
  		$("#search-bar").css("top", currentHeight);
		
		currentHeight += $("#search-bar button").height() +15;
  		$("#prev-collage-container").css("top", currentHeight);
	}
}
 
function addCollage(collageObj){
	activeCollage = collages.length;
	$("#prev-collage-container").append("<div id='collage"+activeCollage+"' class='active' title='"+collageObj.name+"')'></div>");
	$("#collage"+activeCollage).css("backgroundImage","url("+collageObj.src+")")
	$("#download-button").attr("href", collageObj.src);
    collages.push(collageObj);
}

//If any collage in the previous collage container is clicked
//change the active collage and make the current collage
//visable in the previous collage container
$("#prev-collage-container").on("click", "div", function(){
	console.log("clicking prev collage")
	activeCollage = $(this).attr('id').replace(/\D/g,'');
	$("#prev-collage-container div").each(function(index){
		$(this).removeClass("active");
	});
	
	$("#collage").css("backgroundImage", "url("+collages[activeCollage].src+")");
	$("#title").text("Collage for Topic " + collages[activeCollage].name);
	$("#download-button").attr("href", collages[activeCollage].src);
	$(this).addClass("active");
	
});

//changes the cursor on the search button to imply it cannot
//be clicked if the search terms are empty
$("input").keyup(function(){
	//if after any key is hit in the input box
	//the box has no text, no pointer
	if($(this).val()===""){
		$("button").hover(function(){
    		$(this).css("cursor", "default");
    		}, function(){
    		$(this).css("cursor", "default");
		});
	}else{
		$("button").hover(function(){
    		$(this).css("cursor", "pointer");
    		}, function(){
    		$(this).css("cursor", "default");
		});
	}
});

function handleSearchButtonClick() {
  var searchQuery = $("#input-field").val();
  if(searchQuery.length > 0 && searchQuery != undefined) {
	var collageObj = makeRequest(searchQuery);
	console.log(collageObj);
	addCollage(collageObj);
	$("#collage").css("backgroundImage","url("+collageObj.src+")")
	$("#title").text("Collage for Topic " + collageObj.name);

    $("body").removeClass("default");
    $("body").addClass("activated");
    $("#search").text("Build Another Collage");
	repositionElements();
  }
}

function makeRequest(queryString) { // Make request to Servelet
	var thisData = null;
	var obj = null;
	$.ajax({
		type: 'get',
	    url: (url + $.param({ query: queryString })),
	    async: false,
	    contentType: "application/json; charset=utf-8"
    }).done(function(data) {
    	console.log(data);
    	thisData = JSON.parse(data);
    	
	})
	obj = {
    	      "src": thisData.src,
    	      "name": queryString,
    	      "isActive": false // if it will show up in the timeline
	};
	return obj;
	
}
