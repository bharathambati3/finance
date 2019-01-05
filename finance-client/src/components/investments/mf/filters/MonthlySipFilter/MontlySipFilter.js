import React from 'react'
import { Button } from '@material-ui/core'
import classes from './MontlySipFilter.css'
import { withStyles } from '@material-ui/core/styles'
import InputLabel from '@material-ui/core/InputLabel'
import FormControl from '@material-ui/core/FormControl'
import Select from '@material-ui/core/Select'

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
})

const MonthlySipFilter = (props) => {
  return <div className={classes.main}>
    <div className={classes.root}>
      <FormControl className={classes.formControl}>


        <InputLabel htmlFor="year">Year</InputLabel>
        <Select
            native
            value={props.year}
            onChange={props.onYearChange}
            inputProps={{
                name: 'year',
                id: 'year',
            }}
        >
          <option value={"2018"}>2018</option>
          <option value={"2019"}>2019</option>
        </Select>

      </FormControl>

      <FormControl className={classes.formControl}>

        <InputLabel htmlFor="monthly">Monthly</InputLabel>
        <Select
          native
          value={props.month}
          onChange={props.onMonthChange}
          inputProps={{
            name: 'age',
            id: 'monthly',
          }}
        >
          <option value={"JANUARY"}>January</option>
          <option value={"FEBRUARY"}>February</option>
          <option value={"MARCH"}>March</option>
          <option value={"APRIL"}>April</option>
          <option value={"MAY"}>May</option>
          <option value={"JUNE"}>June</option>
          <option value={"JULY"}>July</option>
          <option value={"AUGUST"}>August</option>
          <option value={"SEPTEMBER"}>September</option>
          <option value={"OCTOBER"}>October</option>
          <option value={"NOVEMBER"}>November</option>
          <option value={"DECEMBER"}>December</option>
        </Select>

      </FormControl>

    </div>

    <Button variant="contained"
            color="primary"
            onClick={props.getMonthlySipInfo}>
      Submit
    </Button>
  </div>
}

export default withStyles(styles)(MonthlySipFilter)