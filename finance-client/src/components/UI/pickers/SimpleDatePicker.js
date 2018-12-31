import React from 'react'
import { withStyles } from '@material-ui/core/styles'
import TextField from '@material-ui/core/TextField'

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

function SimpleDatePicker(props) {
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
        value={(props.value)?props.value:''}
        className={classes.textField}
        InputLabelProps={{
          shrink: true,
        }}
      />
    </form>
  );
}

export default withStyles(styles)(SimpleDatePicker);
