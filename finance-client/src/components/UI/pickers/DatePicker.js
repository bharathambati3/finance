import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';

const styles = theme => ({
    container: {
        display: 'flex',
        flexWrap: 'wrap',
    },
    textField: {
        marginLeft: theme.spacing.unit,
        marginRight: theme.spacing.unit,
        width: 200,
    },
});

function DateAndTimePickers(props) {
    const { classes } = props;
    let onChangeHandler = (e) => {
        console.log(e.target.value);
        props.changeHandler(e.target.value);
    }

    let type = (props.type)? props.type: "date";


    return (
        <form className={classes.container} noValidate>
            <TextField
                id="datetime-local"
                label={props.label}
                type={type}
                onChange={onChangeHandler}
                defaultValue={props.defaultValue}
                className={classes.textField}
                InputLabelProps={{
                    shrink: true,
                }}
            />
        </form>
    );
}

DateAndTimePickers.propTypes = {
    classes: PropTypes.object.isRequired,
};

DateAndTimePickers.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(DateAndTimePickers);
