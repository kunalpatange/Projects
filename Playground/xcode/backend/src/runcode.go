package main

import (
	"fmt"
	"os"
	"os/exec"
	"time"
)

var (
	path = "../runContainer/"
)

func check(e error) {
	if e != nil {
		panic(e)
	}
}

func executecode(source string,des string, code string) string {

	f, err := os.Create(path + source)
	check(err)
	defer f.Close()

	_, err = f.WriteString(code)
	check(err)
	f.Sync()

	cmd := exec.Command("bash", "executePy.sh", source, des)
	cmd.Dir = "/home/kunal/Fun/Playground/xcode/backend/runContainer"
	out, _ := cmd.Output()
	fmt.Println(string(out))


	// fmt.Println("before sleep")
	time.Sleep(1 * time.Second)
	dat, err := os.ReadFile(path + des)
	check(err)
	// fmt.Print(string(dat))
	cmd = exec.Command("rm", source, des)
	cmd.Dir = "/home/kunal/Fun/Playground/xcode/backend/runContainer"
	out, _ = cmd.Output()
	fmt.Println(string(out))

	// fmt.Println("inside runcode=== " + string(dat))
	return string(dat)

}
