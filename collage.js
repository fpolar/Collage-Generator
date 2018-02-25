// JavaScript Document
var collages = [];
var activeCollage = -1;
var url = "http::localhost:8080/CollageServlet/?";

$(document).ready(function() {
  $("button").click(function(){
    handleButtonClick();
  });
  positionCollage();
});


function positionCollage() {
  $( window ).resize(function() {
  	$("#collage").css("left", ($(this).width()- $("#collage").width())/2);
  });
}
 
function addCollage(collageObj){
	$("#prev-collage-container").append("<div title='"+collageObj.name+"'style='display: none'><img src='"+collageObj.src+"'></div>");
    collages.push(collageObj);
	activeCollage = collages.length-1;
}

$("#prev-collage-container div")

function handleButtonClick() {
  var searchQuery = $("#input-field").val();
  if(searchQuery.length > 0 && searchQuery != undefined) {
    var collageObj = makeRequest(searchQuery);
	addCollage(collageObj);
	$("#collage").backgroundImage("url("+collageObj.src+")")

    $("body").removeClass("default");
    $("body").addClass("activated");
    setTimeout(function(){
      $("#collage").css("left", ($(this).width()- $("#collage").width())/2);
    }, 550);
  }
}

function makeRequest(queryString) { // Make request to Servelet

  $.get(url + $.param({ query: queryString }))
  .done(function(data) {
    return {
      src: data.src,
      name: queryString,
      isActive: false, // if it will show up in the timeline
    };
  })
  .fail(function() {
    console.log("Request to " + url + $.param({ query: queryString }) + " failed");
  });
}
