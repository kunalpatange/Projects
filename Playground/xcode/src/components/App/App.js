import Editor from '../Editor/Editor';
import Navbar from '../Navbar/Navbar';
import './App.css';

function App() {
  return (
    <div>
      <h1 style={{textAlignVertical: "center",textAlign: "center",}}>Run your code</h1>
      {/* <Navbar></Navbar> */}
      <Editor></Editor>
    </div>
  );
}

export default App;
