import React from "react";
import {withStyles} from "@material-ui/core/styles";
import InputLabel from "@material-ui/core/InputLabel";
import MenuItem from "@material-ui/core/MenuItem";
import FormControl from "@material-ui/core/FormControl";
import Select from "@material-ui/core/Select";

const styles = theme => ({
    root: {
        display: 'flex',
        flexWrap: 'wrap',
    },
    formControl: {
        margin: theme.spacing.unit,
        minWidth: 120,
    },
    selectEmpty: {
        marginTop: theme.spacing.unit * 2,
    },
});

const DropDownInput = (props) => {

    const { classes } = props;

    return (
        <FormControl className={classes.formControl}>
            <InputLabel htmlFor="ddI">{props.placeholder}</InputLabel>
            <Select
                value={props.value}
                onChange={props.handleChange}
                inputProps={{
                    name: props.name,
                    id: 'ddI',
                }}
            >
                {props.data && props.data.map(e => <MenuItem key={e.id} value={e.id}>{e.name}</MenuItem>)}
            </Select>
        </FormControl>
    );
};

export default withStyles(styles)(DropDownInput);
