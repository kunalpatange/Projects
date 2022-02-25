package main

import (
	"fmt"
	"net/http"
)

func main() {
	fmt.Println("hello")
	http.Handle("/", http.FileServer(http.Dir("../client")))
	http.HandleFunc("/users", getuser)
	http.ListenAndServe("localhost:8080", nil)
}

var counter int

func getuser(w http.ResponseWriter, r *http.Request) {

	w.Header().Set("Access-Control-Allow-Origin", "*")
	w.Header().Set("Access-Control-Allow-Headers", "Content-Type")
	w.Header().Set("Content-Type", "text/event-stream")
	w.Header().Set("Cache-Control", "no-cache")
	w.Header().Set("Connection", "keep-alive")
        for{
 	counter++
	fmt.Fprintf(w, "data: %v\n\n", counter)
	if f, ok := w.(http.Flusher); ok {
		f.Flush()
	}
}
	fmt.Println(counter)
}
