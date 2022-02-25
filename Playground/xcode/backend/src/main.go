package main

import (
	// "crypto/rand"
	"fmt"
	"log"
	"math/rand"
	"net/http"
	"strconv"
)

func main() {

	fmt.Print("asdf")
	http.HandleFunc("/runcode", runcode)
	http.ListenAndServe(":8080", nil)

}

func runcode(w http.ResponseWriter, r *http.Request) {

	b, ok := r.URL.Query()["code"]
	if !ok {
		log.Fatalln("error from code")
	}
	source := strconv.Itoa(rand.Int()) + ".py"
	des := strconv.Itoa(rand.Int()) + ".txt"
	w.Header().Set("Access-Control-Allow-Origin", "*")
	result := executecode(source, des, b[0])
	// fmt.Fprintf(w, "Result of the code\n\n")
	fmt.Fprintf(w, result)
}
