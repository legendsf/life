package gdk

import (
	"bufio"
	"crypto/md5"
	"encoding/json"
	"encoding/xml"
	"errors"
	"fmt"
	"github.com/shopspring/decimal"
	"github.com/tidwall/gjson"
	"io"
	"io/ioutil"
	"os"
	"reflect"
	"runtime"
	"sort"
	"sync"
	"testing"
	"time"
)

const json1 = `{"Name":{"first":"jane","last":"prichard"},"age":47}`

type calc func(int,int)int

/**
结构体标签
 */
type Person struct {
	Id        string `json:"id" form:"userId"`
	Name      string
	CanExport string
}

func TestMd5(test *testing.T)  {
	hmd5 :=md5.New()
	io.WriteString(hmd5,"thanks")
	io.WriteString(hmd5,"thankl")
	fmt.Printf("%x\n",hmd5.Sum(nil))
	h2 :=md5.New()
	io.WriteString(h2,"thanksthankl")
	fmt.Printf("%x\n",h2.Sum(nil))
	fmt.Printf("%x\n",md5.Sum([]byte("thanksthankl")))
}

func TestXml(test *testing.T)  {
	p :=Person{
		Id: "id",
		Name: "name",
		CanExport: "Yes",
	}
	 bytes,err:=xml.Marshal(p)
	 if err!=nil{
	 	fmt.Println(err)
	 }
	 str :=string(bytes)
	 fmt.Println(str)
	 var p1 Person
	  err1:=xml.Unmarshal([]byte(str),&p1)
	  if err1!=nil{
	  	fmt.Println(err1)
	  }
	 fmt.Println(p1)
}

func copyFile(fn1,fn2 string) (err error) {
	sFile,err1:=os.Open(fn1)
	defer sFile.Close()
	dFile,err2 := os.OpenFile(fn2,os.O_RDWR|os.O_CREATE,0666)
	defer dFile.Close()
	if err1!=nil{
		return err1
	}
	if err2!=nil{
		return  err2
	}
	var tmpSlice = make([]byte,1024)
	for{
		n1,err3 :=sFile.Read(tmpSlice)
		if err3==io.EOF{
			break
		}
		if err !=nil{
			return  err
		}
		if _,err := dFile.Write(tmpSlice[:n1]);err !=nil{
			return err
		}
	}
	return nil
}

func TestMoveFile(test *testing.T)  {
	input,err :=ioutil.ReadFile("/tmp/sf.txt")
	fmt.Println(err)
	err=ioutil.WriteFile("/tmp/sf.txt1",input,0666)
	copyFile("/tmp/sf.txt","/tmp/sf2.txt")
	sb,_ := ioutil.ReadFile("/tmp/sf2.txt")
	fmt.Println(string(sb))
	err4 :=os.Mkdir("/tmp/abc",0666)
	if err4!=nil{
		fmt.Println(err4)
	}
	err5 :=os.MkdirAll("/tmp/abc/def",0777)
	if err5!=nil{
		fmt.Println(err5)
	}
	os.Remove("./aaa")
	os.Create("aaa.txt")
	os.Remove("aaa.txt")
	os.RemoveAll("/aaa/bbb")
	os.Rename("/tmp/sf.txt1","/tmp/sf.txt4")
}

func TestWriteFile(test *testing.T)  {
	f,err :=os.OpenFile("/tmp/sf.txt",os.O_CREATE|os.O_APPEND|os.O_RDWR,0666)
	if err!=nil{
		fmt.Println("打开失败")
	}
	writer :=bufio.NewWriter(f)
	writer.WriteString("ni hao \n world")
	writer.Flush()
	s,err:=ioutil.ReadFile("/tmp/sf.txt")
	if err!=nil{
		fmt.Println("读取失败")
	}
	fmt.Println(string(s))
	f.Close()
	ioutil.WriteFile("/tmp/sf.txt",[]byte("hellosssss"),0666)

}

func TestReadFile1(test *testing.T)  {
	f,_ := os.Open("/Users/gaodi/data/life/code/bones/go/godemo/main.go")
	readFile2(f)
	fmt.Println("*****************************")
	//f1,_ := os.Open("../main.go")
	//readFile2(f1)
}
func readFileBuf( f *os.File){
	reader :=bufio.NewReader(f)
   s,err :=	reader.ReadString('\n')
	if err!=io.EOF{
		fmt.Println(err)
	}
	fmt.Println(s)
}
func readFileBufTotal(f *os.File){
	reader :=bufio.NewReader(f)
	var str string
	for {
		s, err := reader.ReadString('\n')
		if err == io.EOF {
			str += s
			fmt.Println("读取完毕")
			break
		}
		if (err != nil) {
			fmt.Println("读取失败")
		}
		str += s
	}
	fmt.Println(str)
}

func TestIoUtil(test *testing.T)  {
	byteStr,err :=ioutil.ReadFile("../main.go")
	if err!=nil{
		fmt.Println("读取失败")
	}
	fmt.Println(string(byteStr))
}

func TestReadFileBuf(test *testing.T)  {
	f,_:=os.Open("../main.go")
	defer f.Close()
	//readFileBuf(f)
	readFileBufTotal(f)
}

func readFile2(f *os.File) {
	var strSlice []byte
	var tmpSlice = make([]byte, 128)
	for {
		_, err := f.Read(tmpSlice)
		if err == io.EOF {
			fmt.Println("读取完毕")
			break
		}
		if err != nil {
			fmt.Println("读取失败")
		}
		strSlice = append(strSlice, tmpSlice...)
	}
	fmt.Println(string(strSlice))
}

func TestFile(test *testing.T)  {
	f,err := os.Open("/Users/gaodi/data/life/code/bones/go/godemo/main.go")
	f1,err1:=os.Open("../main.go")
	defer f.Close()
	defer f1.Close()
	if err !=nil{
		fmt.Println(err)
	}
	if err1!=nil {
		fmt.Println(err1)
	}
	fmt.Println(f)
	fmt.Println(f1)
	//读取 一次内容
	var tmpslice = make([]byte,128)
	n,err3:=f.Read(tmpslice)
	if err3 !=nil{
		fmt.Println("读取失败")
	}
	fmt.Printf("读取到%v个字节内容",n)
	fmt.Println(string(tmpslice))
	//读完整内容
	var strSliece []byte
	var tmpslice1 =make([]byte,128)
	for{
		n4,err4 :=f1.Read(tmpslice1)
		if err4== io.EOF{
			fmt.Println(" 读取完毕")
			break
		}
		if err4!=nil{
			fmt.Println("读取失败")
		}
		fmt.Printf("读取 n4 %v",n4)
		strSliece=append(strSliece,tmpslice1...)
	}
	fmt.Println(strSliece)
}

func (p Person) ToString()  {
	fmt.Println(p)
}
type Class struct {
	Titile   string
	Students []Person
}
type Usb interface {
	start()
	stop()
}
type Phone struct {
	Name string
}
var wg sync.WaitGroup
var count = 0
var mutex sync.Mutex
var rw sync.RWMutex

func ModifyStruct(x interface{})  {
	t := reflect.TypeOf(x)
	if t.Kind()!=reflect.Ptr{
		fmt.Println("传入的不是指针")
	}
	if t.Elem().Kind()!=reflect.Struct{
		fmt.Println("传入的不是结构体 类型指针")
	}
	//修改结构体指针的值
	v := reflect.ValueOf(x)
	name := v.Elem().FieldByName("Name")
	name.SetString("change your name")
	fmt.Println(v)

}
func TestModifyStruct(test *testing.T)  {
	p :=Person{
		Id: "id1",
		Name: "Name1",
		CanExport: "CAN",
	}
	ModifyStruct(&p)
	fmt.Println(p)
}

func printStructField(s interface{})  {
	t := reflect.TypeOf(s)
	if t.Kind()!=reflect.Struct && t.Elem().Kind()!=reflect.Struct{
		fmt.Println("传入的参数不是结构体")
	}
	filed0:=t.Field(0)
	fmt.Println(filed0)
	 jTag:=filed0.Tag.Get("json")
	 formTag:=filed0.Tag.Get("form")
	 fmt.Printf("jTag %v ;formTag %v\n",jTag,formTag)
	if field1,ok := t.FieldByName("Name");ok{
		fmt.Println(field1)
	}else {
		fmt.Println("not have a Name")
	}
	fmt.Println("8********")
	fmt.Println(t.NumMethod())
	if m,ok := t.MethodByName("ToString");ok{
		fmt.Println(m.Name)
		fmt.Println(m.Type)
		v :=reflect.ValueOf(s)
		v.MethodByName("ToString").Call(nil)
	}

}
func TestStructField2(test *testing.T)  {
	p :=Person{
		Id: "id1",
		Name: "name1",
		CanExport: "can",
	}
	printStructField(p)
}

func testSet1(x interface{})  {
	v := reflect.ValueOf(x)
	if v.Elem().Kind()== reflect.Float64{
		//v.Elem().SetInt(200)//panic: reflect: call of reflect.Value.SetInt on float64 Value
		v.Elem().SetFloat(200)
	}
}

func reFn(x interface{})  {
	fmt.Println(reflect.TypeOf(x))
	fmt.Println(reflect.ValueOf(x))
	v :=reflect.TypeOf(x)
	fmt.Println(v.Name()) //类型名称
	fmt.Println(v.Kind())//类型种类
	switch v.Kind() {
	case reflect.Int:
		fmt.Println("I am int")
	}
	//修改的是副本
	val :=reflect.ValueOf(x)
	fmt.Println(x)
	if val.Kind()==reflect.Float64{
		val.SetInt(200)
	}
	fmt.Println(val)
	fmt.Println(x)

}

func TestReflect(test *testing.T)  {
	a:= 200.00
	testSet1(&a)
	fmt.Println(a)
	p := Person{
		Id: "id1",
		Name: "sf",
		CanExport: "Yes",
	}
	reFn(p)
	reFn(1)
}

func read1()  {
	rw.RLock()
	fmt.Println("read")
	time.Sleep(time.Second*2)
	rw.RUnlock()
	wg.Done()
}

func write1()  {
	rw.Lock()
	fmt.Println("write")
	time.Sleep(time.Second*2)
	rw.Unlock()
	wg.Done()
}

func TestReadWriteLock(test *testing.T)  {
	for i:=0;i<10;i++{
		wg.Add(1)
		go write1()
	}
	for i:=0;i<10;i++{
		wg.Add(1)
		go read1()
	}
	time.Sleep(time.Second*10)
}

func testCountErr()  {
	mutex.Lock()
	count++
	fmt.Println("the count is",count)
	time.Sleep(time.Millisecond)
	wg.Done()
	mutex.Unlock()
}
/**
	go run race 竞争检测

 */
func TestSyncError(test *testing.T)  {
	for r:=0;r<20;r++{
		wg.Add(1)
		go testCountErr()
	}
	wg.Wait()
}

func SayHello()  {
	for i:=0;i<10;i++{
		fmt.Println("hello")
	}
}
func testPanic()  {
	defer func(){
		if err :=recover();err!=nil{
			fmt.Println("testPainc() 发生错误",err)
		}
	}()
	var m map[string]string
	m["a"]="b"
}

func TestGoroutinePanic(test *testing.T)  {
	//协程本质共用同一些线程，共用内存空间所以一个崩溃会影响另一个，除非捕获异常
	go SayHello()
	time.Sleep(100)
	go testPanic()
	time.Sleep(time.Second*2)
}

func putNum(intChan chan int)  {
	for i:=2;i<100;i++{
		intChan <- i
	}
	close(intChan)
	wg.Done()
}

func primeNum(inChan chan int ,primChan chan int,exitChannel chan bool)  {
	for num :=range  inChan{
		var flag=true
		for i:=2;i<num;i++{
			if num %i==0{
				flag=false
				break
			}
		}
		if flag{
			fmt.Println(num,"是素数")
			primChan <- num
		}
	}
	//close(primChan)//如果一个 channel 关闭了就没办法 再给 channel 发送数据
	exitChannel <- true
	wg.Done()

}

func printNum(primch chan int)  {
	for v:=range primch{
		fmt.Println(v)
	}
	wg.Done()
}

func TestSelect(test *testing.T)  {
	inCh := make(chan int,10)
	strCh := make(chan string,5)
	for i:=0;i<10;i++{
		inCh<- i
	}
		for i:=0;i<5;i++{
			strCh<- "hello"+fmt.Sprintf("%d",i)
		}
		//select 多路复用 chann 不需要关闭 channel

		for{
			select {
			case v:= <- inCh:
				fmt.Println("从 inch 读取数据",v)
			case v:= <-strCh:
				fmt.Println("从 strch 读取数据",v)
			default:
				fmt.Println("数据读取完毕")
				goto lab1
			}
		}
		lab1:
}

func TestSelectSuspend(test *testing.T)  {
	var ch1 = make(chan int,3)
	var ch2 = make(chan int,3)
	for{
		//发生阻塞
		data,ok := <- ch1
		data,ok = <- ch2
		fmt.Println(data,ok)
	}
}

func TestRWchannel(test *testing.T)  {
	ch1 :=make(chan<- int,2)
	ch1<- 1
	ch1<-2
	var a int
	//a = <- ch1 // 只写
	fmt.Println(a)
	ch2 :=make(<-chan int,2)
	//ch2 <- 1//只读 channel
	fmt.Println(ch2)
}

func TestGoroutineChannel2(test *testing.T)  {
	inch := make(chan int,100)
	primch := make(chan int,100)
	exitCh := make(chan bool,16)
	wg.Add(1)
	go putNum(inch)
	//并行执行统计
	for i:=0;i<16;i++{
		wg.Add(1)
		go primeNum(inch,primch,exitCh)
	}
	wg.Add(1)
	go printNum(primch)
	wg.Add(1)
	go func(){
		for i:=0;i<16;i++{
			<- exitCh
		}
		close(primch)
		wg.Done()
	}()
	wg.Wait()
}

func write(ch chan int)  {
	for i:=1;i<10;i++{
		ch <- i
		time.Sleep(time.Millisecond*500)
	}
	close(ch)
	wg.Done()
}
func read(ch chan int)  {
	for v:=range ch{
		fmt.Println(v)
		time.Sleep(time.Millisecond*10)
	}
	wg.Done()
}
func TestGoroutineChannel(test *testing.T)  {
	var ch = make(chan int,10)
	wg.Add(1)
	go write(ch)
	wg.Add(1)
	go read(ch)
	wg.Wait()

}

func TestChannel2(test *testing.T)  {
	ch1 := make(chan int,5)
	for i:=0;i<5;i++{
		ch1 <- i+1
	}
	close(ch1)//fatal error: all goroutines are asleep - deadlock!
	for val := range ch1{
		fmt.Println(val)
	}
	//close(ch1) // close closed channel
}

func TestChannel(test *testing.T)  {
	ch := make(chan int,3)
	ch <- 10
	ch <-20
	ch <-30
	//ch <- 40
	a:= <-ch
	fmt.Println(a)
	a= <-ch
	fmt.Println(a)
	a = <-ch
	fmt.Println(a)

	ch <-2
	ch <-3
	for v:=range ch{
		fmt.Println(v)
	}
	close(ch)
}

func TestLock(test *testing.T)  {
	for i:=0;i<10;i++{
		wg.Add(1)
		go testAdd3()
	}
	wg.Wait()
	fmt.Println("zhixing wanbi ")
}

func testAdd3()  {
	time.Sleep(1000)
	wg.Done()
}

func (p Phone) start()  {
	fmt.Println("phone start")
}

func (p Phone) stop()  {
	fmt.Println("phone stop")
}

func work(u Usb)  {
	u.start()
	u.stop()
}

func Print( x interface{})  {
	if _,ok :=x.(string);ok{
		fmt.Println("string")
	}else if _,ok := x.(int); ok{
		fmt.Println("int")
	}
	switch v:= x.(type) {
	case float64:
		fmt.Println("float64 ",v)
	}
}

func TestInterface(testing *testing.T)  {
	fmt.Println(runtime.NumCPU())
	Print("aaaaaa")
	Print(float64(0.12))
	Print(float32(0.12))
	var p Phone
	p.start()
	p.stop()
	var d Usb
	d=p
	d.start()
	d.stop()
	work(p)
}

func TestJson(test *testing.T)  {
	p :=Person{
		Id:        "songfei",
		Name:      "songfeiName",
		CanExport: "I am Public",
	}
	fmt.Println(p)
	jsonBytes,err :=json.Marshal(p)
	if err !=nil{
		fmt.Println(err)
	}
	fmt.Println(string(jsonBytes))
	p1 := Person{}
	err1 := json.Unmarshal(jsonBytes,&p1)
	if err !=nil{
		fmt.Println(err1)
	}
	fmt.Println(p1)
	for i:=0;i<10;i++{
		s :=Person{
			Id: fmt.Sprintf("%v",i),
			Name: fmt.Sprintf("stu_%v",i),
		}
		fmt.Println(s)
	}
	c :=Class{
		Titile: "001班级",
		Students : make([]Person,0),
	}
	for j:=0;j<10;j++{
		s :=Person{
			Id: fmt.Sprintf("%v",j),
			Name: fmt.Sprintf("stu_%v",j),
			CanExport: fmt.Sprintf("YES"),
		}
		c.Students=append(c.Students,s)
	}
	fmt.Println(c)
}

func (p Person) printinfo()  {
	fmt.Printf("%v 888 %v\n",p.Id,p.Name)
}
func (p Person) setInfo(id ,name string)  {
	//是个副本
	p.Id =id
	p.Name =name
}

func (p *Person)setInfo2(id,name string)  {
	p.Id =id
	(*p).Name =name
}
type mint int

func (m mint) SayHello()  {
	fmt.Println(m)
}
func TestStruct2( t *testing.T)  {
	var m1 mint
	m1.SayHello()
	m1 = 100
	m1.SayHello()
}

func TestStruct(t *testing.T)  {
	p := Person{"11","songfei","YES"}
	fmt.Println(p)
	var p2 = new(Person)
	p2.Id ="ss"
	p2.Name ="ss"
	var p3= &Person{}
	p3.Id ="p3"
	p3.Name ="p3Name"
	fmt.Printf("%v %T\n",p3,p3)
	fmt.Printf("%#v %T\n",p3,p3)
	var p4= &Person{
		Id:   "p4",
		Name: "p4aname",
	}
	fmt.Println(p4.Name)
	fmt.Printf("%T %T",p,p4)
	println("******")
	p4.printinfo()
	p4.setInfo("5","N5")
	p4.printinfo()
	p4.setInfo2("5","N5")
	p4.printinfo()
}


func TestTicket(t *testing.T)  {
	ticker := time.NewTicker(time.Second)
	n :=0
	for i:= range ticker.C{
		fmt.Println(i)
		n++
		if n>5{
			ticker.Stop()
			return
		}
	}
}

func TestTime(t *testing.T)  {
	timeobj :=time.Now()
	timeobj.Add(time.Hour)
	var str=timeobj.Format("2006-01-02 03:04:05")
	fmt.Println(timeobj.Year(),timeobj.Month(),timeobj.Weekday(),timeobj.Minute(),timeobj.Second())
	fmt.Println(str)
	unixtime := timeobj.Unix() //毫秒
	unixNtime :=timeobj.UnixNano() //纳秒
	fmt.Println(unixtime)
	fmt.Println(unixNtime)
	tobj2 :=time.Unix(unixtime,0)
	fmt.Println(tobj2.Format("2006-01-02 03:04:05"))
	var str2="2020-08-01 05:21:27"
	var tmp="2006-01-02 03:04:05"
	tobj3,_ :=time.ParseInLocation(tmp,str2,time.Local)
	fmt.Println(tobj3)
}

func TestMyfun(t *testing.T)  {
	mfun()
	fmt.Println("继续执行")
}

func readFile(n string) error  {
	if n == "main.go"{
		return nil
	}else {
		return errors.New("读取失败")
	}
}
func mfun()  {
	defer func() {
		e := recover()
		if e !=nil{
			fmt.Println("给管理员发邮件")
		}
	}()
	err := readFile("xxx.go")
	if err !=nil{
		panic(err)
	}
}

func fn2(a,b int) int  {
	defer func() {
		err := recover()
		if err != nil{
			fmt.Println("err",err)
		}
	}()
	return a/b
}

func logErr()  {

}

func fn1(){
	defer func() {
		err:= recover()
		if err != nil{
			fmt.Println("err",err)
		}
	}()
	panic("抛出一个异常")
}

func TestRecover(t *testing.T) {
	fn1()
	fn2(1,0)
}

func f3()int  {
	var a int
	defer func(){
		a++
	}()
	return a
}
/**
命名返回值
 */
func f4() (a int)  {
	fmt.Println(a)
	defer func() {
		a++
		fmt.Println("defera ",a)
	}()
	fmt.Println(a)
	return a
}
/**
	执行顺序：
		X=赋值
		defer 语句
		RET 指令
	如果 defer 语句不修改具名返回值，那么具名返回值就等于 X
 */
func f5() (a int)  {
	x:=5
	defer func() {
		x++ //没有修改具名返回值
	}()
	return x
}
func f7() (a int)  {
	x:=5
	defer func() {
		a=x+1//最后修改了具名返回值
	}()
	return x
}
func TestDefer2(t *testing.T) {
	fmt.Println(f6())
	fmt.Println(f7())
}
func f6() (a int)  {
	defer func(a int) {
		a++
	}(a)
	return 5;
}

func TestDefer(t *testing.T) {
	fmt.Println(f3())
	fmt.Println(f4())
	fmt.Println(f3())
	fmt.Println("************")
	defer fmt.Println("1")
	defer fmt.Println("2")
	defer fmt.Println("3")
	fmt.Println("kaishi")
	defer func(){
		fmt.Println("aaa")
		fmt.Println("bbb")
	}()
	fmt.Println("jieshu")
}

func add(x,y int)int  {
	return x+y
}

var a = 12
func TestClouser(t *testing.T) {
		var a = adder()
		fmt.Println(a())
		fmt.Println(a())
		fmt.Println(a())
		var a2=adder2()
		fmt.Println(a2(1))
		fmt.Println(a2(1))
		fmt.Println(a2(1))
}

/**
闭包，返回一个函数
函数的入参 int 返回值 int
 */
func adder2() func(y int)int  {
	var i=10
	return func(y int) int {
		i += y
		return i
	}
}

func adder() func() int {
	var i = 10
	return func() int {
		return i+1;
	}
}
func TestMap(t *testing.T) {
	c := add
	fmt.Println(c(1,2))
	fmt.Printf("%T",c)
	var userinfo = make(map[string]string)
	userinfo["k"]="v1"
	userinfo["age"]="20"
	fmt.Println(userinfo)
	fmt.Println(userinfo["age"])
	var uinfo2=map[string]int{"k1":1,"k2":2}
	fmt.Println(uinfo2)
	var uinfo3= make([]map[string]string,3,3)
	fmt.Println(uinfo3[0])
	uinfo3[0] = map[string]string{"k1":"v1"}
	fmt.Println(uinfo3)
	fmt.Println(uinfo3[0])
}

func TestSort(t *testing.T) {
	intList := []int{2,7,4,5,8,6}
	sort.Ints(intList)
	for index,value := range  intList{
		fmt.Println(index,value)
	}
	strList := []string{"2","8","7","a"}
	sort.Sort(sort.Reverse(sort.StringSlice(strList)))
	fmt.Println(strList)
}

func TestLabel(t *testing.T) {
	out:
	for i:=0;i<10;i++{
		for j:=0;j<10;j++{
			if j==9{
				break out
			}else{
				if j%2==0{
					continue out
				}else {
					goto out
				}
			}
		}
	}
}

func say(s string)  {
	for i:=0;i<5;i++{
		time.Sleep(100*time.Millisecond)
		fmt.Println(s)
	}
}

func TestGoroutine(t *testing.T) {
	go say("world")
	say("hello")
}

func TestAdd(t *testing.T) {
	fmt.Println(Add(1.0,2.0))
}
func TestGjson(t *testing.T) {
	value := gjson.Get(json1,"Name.last");
	println(value.String())
}

func TestDecimal(t *testing.T) {
	price, err := decimal.NewFromString("136.02")
	if err != nil {
		panic(err)
	}

	quantity := decimal.NewFromInt(3)

	fee, _ := decimal.NewFromString(".035")
	taxRate, _ := decimal.NewFromString(".08875")

	subtotal := price.Mul(quantity)

	preTax := subtotal.Mul(fee.Add(decimal.NewFromFloat(1)))

	total := preTax.Mul(taxRate.Add(decimal.NewFromFloat(1)))

	fmt.Println("Subtotal:", subtotal)                      // Subtotal: 408.06
	fmt.Println("Pre-tax:", preTax)                         // Pre-tax: 422.3421
	fmt.Println("Taxes:", total.Sub(preTax))                // Taxes: 37.482861375
	fmt.Println("Total:", total)                            // Total: 459.824961375
	fmt.Println("Tax rate:", total.Sub(preTax).Div(preTax)) // Tax rate: 0.08875)
}