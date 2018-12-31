import React, { Component } from 'react'
import axios from 'axios'
import Summary from './summary'
import * as Config from '../../../../config/Config'
import moment from 'moment'
import DropDownInput from '../../../UI/Input/DropDownInput'


const TODAY = "today";
const TOMORROW = "tomorrow";
const WEEK = "week";
const ALL = "all";

class UpComingSips extends Component {
  constructor () {
    super()
    this.limitList = [{id: 1, name: '1'}, {id: 5, name: '5'}, {id: 10, name: '10'}, {id: -1, name: 'All'}]
    this.daysList = [{id: TODAY, name: 'Today'}, {id: TOMORROW, name: 'Tomorrow'}, {id: WEEK, name: 'Week'},  {id: ALL, name: 'All'}]
    this.state = {
      investments: [],
      limit: -1,
      days: TOMORROW,
    }
  }

  componentDidMount () {
    this.doNetworkCall()
  }

  doNetworkCall = (lmt, d) => {

    let limit = (lmt) ? lmt : this.state.limit;
    let days = (d) ? d : this.state.days;

    let data = {
      userId: 'a',
      upComingSip: limit,
      range: this.convertToDateRange(days),
    }
    axios.post(`${Config.ROOT_URL}${Config.GET_UPCOMING_SIP_INFO}`, data)
      .then(response => {

        let arr = response.data.map(e => {
          let data = {}
          data.paymentDate = e.paymentDate
          data.id = e.info.id
          data.startDate = e.info.startDate
          data.endDate = e.info.endDate
          data.scheme = e.info.scheme.name
          data.amount = e.info.amount
          return data
        })

        this.setState({investments: arr, limit: limit, days: days});
      })
  }

  convertToDateRange = (id) => {
    let start = moment().utc().startOf('day')
    let end = moment().utc().endOf('day');
    switch (id) {
      case TODAY:
        return {startDate: start, endDate: end}
      case TOMORROW:
        start = moment().add(1, 'day').utc().endOf('day');
        end = moment().add(2, 'day').utc().endOf('day');
        return {startDate: start, endDate: end}
      case WEEK:
        end = moment().add(7, 'day').utc().endOf('day');
        return {startDate: start, endDate: end}
      default:
          return null;
    }
  }

  onCountChange = (e) => {
    this.doNetworkCall(e.target.value);
  }

  onDaysChange = (e) => {
    this.doNetworkCall(null, e.target.value);
  }

  render () {

    let dContent = [
      {id: 1, field: 'id', name: 'Id', isKey: true, summary: '0'},
      {id: 2, field: 'scheme', name: 'scheme', summary: 'Total'},
      {id: 3, field: 'paymentDate', name: 'Next payment', summary: '-'},
      {id: 4, field: 'startDate', name: 'Started On', summary: {operation: 'min'}},
      {id: 5, field: 'endDate', name: 'Ends On', summary: {operation: 'max'}},
      {id: 6, field: 'amount', name: 'amount', summary: {operation: 'sum', params: {fixed: 0}}},
    ]

    return (
      <div>
        <div>
          <DropDownInput placeholder="Days"
                         value={this.state.days}
                         handleChange={this.onDaysChange}
                         data={this.daysList}
          />
          <DropDownInput placeholder="Limit"
                         value={this.state.limit}
                         handleChange={this.onCountChange}
                         data={this.limitList}
          />

        </div>
        <Summary data={this.state.investments} content={dContent}/>
      </div>
    )
  }
}

export default UpComingSips
