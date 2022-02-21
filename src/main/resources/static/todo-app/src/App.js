import './App.css';
import Todo from './components/Todo'
import {useState} from "react";
import {Paper, List} from "@material-ui/core";
import AddTodo from "./components/AddTodo";

function App() {
    const myTodos = [
        {id: "0", title: "Hello world 1", done: true},
        {id: "1", title: "Hello world 2", done: false}
    ]

    const [todos, setTodos] = useState(myTodos);

    const addTodo = (title) => {
        let newTodo = {
            id: "ID-" + todos.length,
            title: title,
            done: false
        };
        console.log(newTodo);
        setTodos(todos.concat(newTodo));
    };

    return (
        <div className="App">
        <AddTodo addTodo={addTodo}/>
            {(myTodos.length > 0) ? (
                <Paper style={{margin: 16}}>
                    <List>
                        {todos.map((item) => {
                            return <Todo key={item.id} item={item}/>;
                        })}
                    </List>
                </Paper>
            ) : (<></>)}
        </div>
    );
}

export default App;
