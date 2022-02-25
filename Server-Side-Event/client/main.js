function onloaded(){
    var source = new EventSource("http://localhost:8080/users");
    source.onmessage = function(event){

        document.getElementById("counter").innerHTML = event.data
    }
}