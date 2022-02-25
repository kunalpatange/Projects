import axios from 'axios';
import React, {useState} from 'react'


    function Send(props) {

    const[response, setresponse] = useState("")


    function runcode(code){
        var response = "";
        axios.get(`http://localhost:8080/runcode`,{
            params:{
                code: code
            }
        })
        .then(res => {
            const result = res.data;
            console.log(result);
            setresponse(result)
        })
        return "Running ...";
    }
    



    return (
    <div>
<button onClick={()=> runcode(props.code)}>run code</button>
    <div style={{whiteSpace: 'pre-line'}}>{response}</div>
    </div> 
    )
    }
    
  export default Send;