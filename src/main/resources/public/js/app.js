var gameModel;

$( document ).ready(function() {
  // Handler for .ready() called.
  $.getJSON("model", function( json ) {
  gameModel = json;
    console.log( "JSON Data: " + json );
   });
   //var tile = getElementsByTagName("TD");
function blink() {
   var f = document.getElementById('blink');
    setInterval(function() {
        f.style.display = (f.style.display == 'none' ? 'inline' : 'none');
    }, 1000);
}
});


function placeShip() {
   console.log($( "#shipSelec" ).val());
   console.log($( "#rowSelec" ).val());
   console.log($( "#colSelec" ).val());
   console.log($( "#orientationSelec" ).val());

   //var menuId = $( "ul.nav" ).first().attr( "id" );
   var request = $.ajax({
     url: "/placeShip/"+$( "#shipSelec" ).val()+"/"+$( "#rowSelec" ).val()+"/"+$( "#colSelec" ).val()+"/"+$( "#orientationSelec" ).val(),
     method: "post",
     data: JSON.stringify(gameModel),
     contentType: "application/json; charset=utf-8",
     dataType: "json"
   });

   request.done(function( currModel ) {
     displayShipState(currModel);
     gameModel = currModel;

   });

   request.fail(function( jqXHR, textStatus ) {
     alert( "Request failed: " + textStatus );
   });
}


function fire(){
 console.log($( "#rowFire" ).val());
 console.log($( "#colFire" ).val());
//var menuId = $( "ul.nav" ).first().attr( "id" );
	var request = $.ajax({
		url: "/fire/"+$( "#rowFire" ).val()+"/"+$( "#colFire" ).val(),
		method: "post",
		data: JSON.stringify(gameModel),
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	});

	request.done(function( currModel ) {
		displayFireState(currModel);
		gameModel = currModel;
	});

	request.fail(function( jqXHR, textStatus ) {
		alert( "Request failed: " + textStatus );
	});

}

function scan(){
    console.log($("#colFire").val());
    console.log($("#rowFire").val());
    var request = $.ajax({
    url: "/scan/"+$("#colFire").val()+"/"+$("#rowFire").val(),
    method: "post",
    data: JSON.stringify(gameModel),
	contentType: "application/json; charset=utf-8",
    dataType: "json"
    });

   request.done(function( currModel ){
        displayScanState(currModel);
        gameModel = currModel;
    });

    request.fail(function( jqXHR, textStatus ) {
     alert( "Request failed: " + textStatus );
    });

}


function log(logContents){
    console.log(logContents);
}

function displayShipState(gameModel){
	displayShip(gameModel.aircraftCarrier);
	displayShip(gameModel.battleship);
	displayShip(gameModel.cruiser);
	displayShip(gameModel.destroyer);
	displayShip(gameModel.submarine);
}
function displayFireState(gameModel){
	//$( '#MyBoard td'  ).css("background-color", "blue");
	//$( '#TheirBoard td'  ).css("background-color", "green");

	for (var i = 0; i < gameModel.computerMisses.length; i++) {
	   $( '#TheirBoard #' + gameModel.computerMisses[i].Across + '_' + gameModel.computerMisses[i].Down ).css("background-color", "black");
	}
	for (var i = 0; i < gameModel.computerHits.length; i++) {
	   $( '#TheirBoard #' + gameModel.computerHits[i].Across + '_' + gameModel.computerHits[i].Down ).css("background-color", "red");
	}

	for (var i = 0; i < gameModel.playerMisses.length; i++) {
	   $( '#MyBoard #' + gameModel.playerMisses[i].Across + '_' + gameModel.playerMisses[i].Down ).css("background-color", "green");
	}
	for (var i = 0; i < gameModel.playerHits.length; i++) {
	   $( '#MyBoard #' + gameModel.playerHits[i].Across + '_' + gameModel.playerHits[i].Down ).css("background-color", "red");
	}
}
function displayScanState(gameModel){
	if(gameModel.scanResult){
		alert("Scan found at least one Ship")
	}
	else{
		alert("Scan found no Ships")
	}
	displayFireState(gameModel);
}

function displayShip(ship){
 startCoordAcross = ship.start.Across;
 startCoordDown = ship.start.Down;
 endCoordAcross = ship.end.Across;
 endCoordDown = ship.end.Down;
 console.log(startCoordAcross);
 if(startCoordAcross > 0){
    if(startCoordAcross == endCoordAcross){
        for (i = startCoordDown; i <= endCoordDown; i++) {
            $( '#MyBoard #'+startCoordAcross+'_'+i  ).css("background-color", "yellow");
        }
    } else {
        for (i = startCoordAcross; i <= endCoordAcross; i++) {
            $( '#MyBoard #'+i+'_'+startCoordDown  ).css("background-color", "yellow");
        }
    }
 }
}


/*/testing to add coordinates clicked to fire selection
document.addEventListener('click', whereClick);

function whereClick(event){
    console.log(event.target);
}

function whereClick(){
    var tile = document.getElementsByTagName("TD");
    document.getElementById("demo").innerHTML = tile[1].innerHTML;
}*/