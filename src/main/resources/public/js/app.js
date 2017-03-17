var gameModel;
var currentChosenRow = 0;
var currentChosenCol = 0;
var scanFlag = 0;

$( document ).ready(function() {
	$.getJSON("model", function( json ) {
		gameModel = json;
		console.log( "JSON Data: " + json );
	});
});


function placeShip() {
	console.log($( "#shipSelec" ).val());
	console.log($( "#rowSelec" ).val());
	console.log($( "#colSelec" ).val());
	console.log($( "#orientationSelec" ).val());
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


function difficulty(){
    var diff = 'easy';
    var request = $.ajax({
    url: "/difficulty/"+ diff,
    method: "post",
    data: JSON.stringify(gameModel),
	contentType: "application/json; charset=utf-8",
    dataType: "json"
    });

    //document.getElementById(difficultyButton).disabled = true;

   request.done(function( currModel ){
        displayScanState(currModel);
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
	displayShip(gameModel.clipper);
	displayShip(gameModel.dinghy);
	displayShip(gameModel.submarine);
}
function displayFireState(gameModel){

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
	console.log(ship);
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
function myCoord(x, y){
	document.getElementById('rowSelec').selectedIndex = (x - 1);
	document.getElementById('colSelec').selectedIndex = (y - 1);
	//console.log("x: " + x + ", y: " + y);
}
function theirCoord(x, y){
	//var rowFire = document.getElementById('rowFire');
	//var colFire = document.getElementById('colFire');
	document.getElementById('rowFire').selectedIndex = (x - 1);
	document.getElementById('colFire').selectedIndex = (y - 1);
	//$('#rowFire').eq(x).prop('selected', true).trigger('change');
	//$('#colFire').eq(y).prop('selected', true).trigger('change');
	console.log("x: " + x + ", y: " + y);
}

