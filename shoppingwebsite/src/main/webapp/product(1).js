//const btn = document.querySelector('button');

function getData(e){
	let dt={};
	dt.pid=e.id
	//console.log("q"+e.id);
	dt.qty=document.getElementById("q"+e.id).value;
	sendData(dt);
}
function sendData( data ) {
  console.log( 'Sending data' );

  const XHR = new XMLHttpRequest();

  let urlEncodedData = "",
      urlEncodedDataPairs = [],
      name;

  // Turn the data object into an array of URL-encoded key/value pairs.
  for( name in data ) {
    urlEncodedDataPairs.push( encodeURIComponent( name ) + '=' + encodeURIComponent( data[name] ) );
  }

  // Combine the pairs into a single string and replace all %-encoded spaces to
  // the '+' character; matches the behavior of browser form submissions.
  urlEncodedData = urlEncodedDataPairs.join( '&' ).replace( /%20/g, '+' );

  // Define what happens on successful data submission
  XHR.addEventListener( 'load', function(event) {
    alert( 'Yeah! Added to cart. If required you can change the quantity and press add to cart to change the number of items' );
  } );

  // Define what happens in case of error
  XHR.addEventListener( 'error', function(event) {
    alert( 'Oops! Something went wrong.' );
  } );

  // Set up our request
  XHR.open( 'POST', 'http://localhost:8080/homepage/add' );

  // Add the required HTTP header for form data POST requests
  XHR.setRequestHeader( 'Content-Type', 'application/x-www-form-urlencoded' );

  // Finally, send our data.
  XHR.send( urlEncodedData );
}

//btn.addEventListener( 'click', function() {
 // sendData( {test:'ok'} );
//} )
