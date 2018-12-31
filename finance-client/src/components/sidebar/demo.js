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
        console.log(e);
        console.log(e.target.value);
        console.log(new Date(e.target.value).getTime());
        console.log(e.timeStamp);
    };

    return (
        <form className={classes.container} noValidate>
            <TextField
                id="datetime-local"
                label="Next appointment"
                type="datetime-local"
                onChange={onChangeHandler}
                defaultValue="2017-05-24T10:30"
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

export default withStyles(styles)(DateAndTimePickers);
