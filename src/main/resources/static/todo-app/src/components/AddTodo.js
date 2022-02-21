import React, {useState} from "react";
import {Button, Grid, Paper, TextField} from "@material-ui/core";

function AddTodo (props){
    const [title, setTitle] = useState("");

    const onInputChange = (e) => {
        setTitle(e.target.value);
    };
    const onClickAdd = () => {
      props.addTodo(title);
      setTitle("");
    };
    const keyEventHandlerEnter = (e) => {
        if(e.key === 'Enter') onClickAdd();
    };

    return(
        <Paper style={{margin: 16, padding: 16}}>
            <Grid container>
                <Grid xs={11} md={11} item style={{paddingRight: 16}}>
                    <TextField placeholder={"Add Todo here"} fullWidth
                        onChange={onInputChange}
                        onKeyPress={keyEventHandlerEnter}
                    />
                </Grid>
                <Grid xs={1} md={1} item>
                    <Button fullWidth color={"secondary"} variant={"outlined"}
                        onClick={onClickAdd}
                    >
                        +
                    </Button>
                </Grid>
            </Grid>
        </Paper>
    );
}

export default AddTodo;