import React, {useState, useEffect} from "react";

// useState -> ver documentação React do Hooks

const App = () => {
    const [counter, setCounter] = useState(0);

    useEffect( () => {
        console.log('O contador mudou de valor')   // fazer a chamada para API
    }, [counter]);

     

  return (
    <div className="container mt-5">
      <button 
      className="btn btn-primary mr-5"
      onClick={ () => setCounter(counter + 1)}
      >
        +
      </button>
      <span>
        {counter}     {/* binding  ligação do js com o html */}
      </span>
      <button 
      className="btn btn-primary ml-5"
      onClick={ () => setCounter(counter - 1) }
      >
        -
      </button>
        {counter > 5 &&  <h1>O valor é maior que 5</h1>}
        {counter <= 5 &&  <h1>O valor é menor ou igual a 5</h1>}
    </div>
  );
}
export default App;
