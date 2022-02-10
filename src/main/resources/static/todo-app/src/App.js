import './App.css';
import Todo from './components/Todo'
import {useState} from "react";
import {Paper, List} from "@material-ui/core";

function App(props) {
    const myTodoItems = [
        {id: 0, title: "Hello world 1", done: true},
        {id: 1, title: "Hello world 2", done: false}
    ]
    const [items, setItems] = useState(myTodoItems);
    return (
        <div className="App">
            <Paper style={{margin:16}}>
                <List>
                    {items.map((item, i) => {return <Todo key={item.id} item={item}/>;})}
                </List>
            </Paper>
        </div>
    );
}

export default App;
