// JavaScript Document
var collages = [];
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

function handleButtonClick() {
  var searchQuery = $("#input-field").val();
  if(searchQuery.length > 0 && searchQuery != undefined) {
    var collageObj = makeRequest(searchQuery);
    collages.push(collageObj);

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
