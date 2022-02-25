import 'codemirror/theme/dracula.css'
import React, {useState} from 'react'
import 'codemirror/mode/javascript/javascript'
import 'codemirror/lib/codemirror.css'

import CodeMirror from '@uiw/react-codemirror';
import Send from '../Send/Send';
// const code = 'const a=0;'
function Editor() {
  

  const [code, setCode] = React.useState("");
   
    return (
      <div>
      <CodeMirror
      value="print('hello world')"
      placeholder="Enter your code here!"
      height="500px"
      mode="python"
      theme= 'dark'
      // options={{
      //   gutters: ['CodeMirror-linenumbers', 'CodeMirror-foldgutter'],
      //   mode: 'javascript',
      //   lineWrapping: true,
      //   smartIndent: true,
      //   lineNumbers: true,
      //   foldGutter: true,
      //   autoCloseTags: true,
      //   keyMap: 'sublime',
      //   matchBrackets: true,
      //   autoCloseBrackets: true,
      //   extraKeys: {
      //     'Ctrl-Space': 'autocomplete'
      //   }
      // }}
      // extensions={[javascript({ jsx: true })]}
      onChange={(value, viewUpdate) => {
        setCode(value);
      }}
    />
<Send code={code}></Send>
      </div>
    );
  }
  
  export default Editor;