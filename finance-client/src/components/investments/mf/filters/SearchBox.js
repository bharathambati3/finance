import React from 'react';
import axios from "axios";
import classes from './SearchBox.css';
import TextField from "@material-ui/core/es/TextField/TextField";

const SearchBox = (props) => {

    if (props.loadData) {
        axios.get(props.url)
            .then(response => {
                props.updateData(response.data);
            });
    }

    let list = [];

    if (props.filter !== "" && !props.choosen) {
        let m = props.data.filter(e => e.toUpperCase().includes(props.filter.toUpperCase()));
        list = m.map(e => <div onClick={() => props.selected(e)} className={classes.li} key={e}>{e}</div>)
    }

    return (
        <div className={classes.root}>

            <TextField
                required
                label={props.label}
                className={classes.textField}
                margin="normal"
                onChange={props.change}
                value={props.filter}
            />
            <div className={classes.main}>{list}</div>
        </div>
    )
};

export default SearchBox;
