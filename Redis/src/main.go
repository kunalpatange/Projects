// to run this enable redis using docker. run redis-up.sh in home dir
package main

import (
	"context"
	"fmt"
	"io/ioutil"
	"log"
	"net/http"
	"time"

	"github.com/go-redis/redis/v8"
)

func redisConnect(name string) string {

	var ctx = context.Background()
	rdb := redis.NewClient(&redis.Options{
		Addr:     "localhost:6379",
		Password: "", // no password set
		DB:       0,  // use default DB
	})

	val, err := rdb.Get(ctx, "name:"+name).Result()
	if err != nil {
		val = getName(name)
	} else {
		fmt.Println("##########From the Redis cache##########")
		return val
	}

	err = rdb.Set(ctx, "name:"+name, string(val), 1*time.Hour).Err()
	if err != nil {
		panic(err)
	}
	fmt.Println("##########From the internet##########")
	return val
}

func getName(name string) string {

	resp, err := http.Get("https://api.nationalize.io/?name=" + name)
	if err != nil {
		log.Fatalln(err)
	}
	//We Read the response body on the line below.
	body, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		log.Fatalln(err)
	}
	//Convert the body to type string
	sb := string(body)
	return sb
}

func main() {
	// rahul tom angel tharun promod dashrath
	name := "tom"

	t1 := time.Now()
	res := redisConnect(name)
	t := time.Now()
	fmt.Println(res)
	fmt.Println(t.Sub(t1))
}
