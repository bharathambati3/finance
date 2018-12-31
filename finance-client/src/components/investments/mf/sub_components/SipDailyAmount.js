import React from 'react'
import axios from "axios";
import * as Config from '../../../../config/Config'
import Summary from './summary'

class SipDailyAmount extends React.Component {

  constructor() {
    super();
    this.state = {
      dailyInfo: [

      ]
    }
  }

  componentDidMount() {
    let data = {userId: "a"};
    axios.post(`${Config.ROOT_URL}${Config.GET_SIP_DAILY_AMOUNT}`, data)
      .then(response => {
        this.setState({ dailyInfo: response.data});
      });
  }

  render () {
    let dContent = [
      {id: 1, field : "day", name: "Day", isKey: true, summary: "0"},
      {id: 2, field : "dayAmount", name: "Day amount", summary: "Total"},
      {id: 3, field : "cumulativeAmount", name: "Cumulative amount", summary: "Total"},
    ];

    return (
      <Summary data={this.state.dailyInfo} content={dContent}/>
    );
  }
}


export default SipDailyAmount
