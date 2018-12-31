import React, { Component } from 'react'
import moment from 'moment'
import { Button } from '@material-ui/core'
import SimpleDatePicker from '../../../../../UI/pickers/SimpleDatePicker'

export const SUBMITTED_MODE = "Info";
export const SUBMIT_MODE = "Submit";

class SingleSip extends Component {

  constructor (props) {
    super(props);
    this.state = {
      nav: props.data.purchaseNav,
      investedOn: (this.props.data.investedOn)?moment(this.props.data.investedOn).format('YYYY-MM-DD'):null,
    }

    console.log("Single sip: props nav:"+props.data.purchaseNav+"  state: "+this.getMode());
  }

  getMode = () => {
    return (this.props.data.purchaseNav) ? SUBMITTED_MODE : SUBMIT_MODE;
  }

  investedOn = () => {
    return (this.state.investedOn)?moment(this.state.investedOn).format('YYYY-MM-DD'):null;
  }

  render () {
    let {data} = this.props

    return (<tr>

        <td><span>{data.scheme.name}</span></td>
        <td><span>{data.sipDate}</span></td>

        <td>{this.getPurchaseNav(data.purchaseNav)}</td>
        <td>{this.getInvestedOn(data.investedOn)}</td>

        <td><span>{data.amount}</span></td>
        <td>{this.getButton()}</td>
      </tr>
    )
  }

  getButton = () => {

    let onBtnClick = () => {
      let data = {
        ...this.props.data,
        ...this.state,
        mode: this.getMode()
      }
      this.props.clicked(data);
    }

    return (
      <Button variant="contained"
              color="primary"
              disabled={!(this.state.nav && this.state.investedOn)}
              onClick={onBtnClick}>
        {this.getMode()}
      </Button>
    )
  }

  dateChangeHandler = (v) => {
    console.log("dateChangeHandler :"+ v);
    this.setState({investedOn: v});
  }

  getInvestedOn = (investedOn) => {

    return (this.getMode() === SUBMITTED_MODE) ?  moment(investedOn).format('DD/MM/YYYY'):
      <SimpleDatePicker value={this.investedOn()}
                  changeHandler={this.dateChangeHandler}
                  timeFormat="HH:mm"
                  timeCaption="time"/>
  }

  getPurchaseNav = (nav) => {

    return (this.getMode() === SUBMITTED_MODE)
      ? nav : <input value={(this.state.nav)?this.state.nav:''} onChange={e => this.setState({nav: e.target.value})}/>
  }
}

export default SingleSip