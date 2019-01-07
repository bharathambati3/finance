import React, { Component } from 'react'
import axios from 'axios'
import Summary from './summary'
import Aux from '../../../hoc/aux'
import MFFilter from '../filters/MFFilter'
import * as Config from '../../../../config/Config'
import DropDown from "../selectors/DropDown";

const fundTypes = [
    {
      id: 0,
      name: 'All',
      value: null,
    },
    {
      id: 1,
      name: 'Tax Saver',
      value: 'ELSS'
    },
    {
        id: 2,
        name: 'Large cap',
        value: 'LARGE_CAP'
    },
    {
        id: 3,
        name: 'Mid cap',
        value: 'MID_CAP'
    },
    {
        id: 4,
        name: 'Small cap',
        value: 'SMALL_CAP'
    },
    {
        id: 5,
        name: 'Multi cap',
        value: 'MULTI_CAP'
    },
    {
        id: 6,
        name: 'Hybrid',
        value: 'HYBRID'
    }
]
class Investment extends Component {
  constructor (props) {

    console.log('Investment constructor is called again :)')
    super(props)
    this.state = {
      fundType : 0
    }
  }

  componentDidMount () {
    this.updateData(this.props.data.request);
  }

  dContent = [
    {id: 1, field: 'id', name: 'Id', isKey: true, summary: '0'},
    {id: 2, field: 'name', name: 'name', summary: 'Total'},
    {id: 3, field: 'amount', name: 'amount', summary: {operation: 'sum', params: {fixed: 0}}},
    {id: 4, field: 'currentValue', name: 'currentValue', summary: {operation: 'sum', params: {fixed: 0}}},
    // {id: 5, field: 'investmentType', name: 'investmentType', summary: '-'},
    {id: 6, field: 'investedOn', name: 'investedOn', summary: '-'},
    {id: 7, field: 'investmentPeriod', name: 'investmentPeriod', summary: '-'},
    {id: 8, field: 'purchaseNav', name: 'purchaseNav', summary: '-'},
    {id: 9, field: 'currentNav', name: 'currentNav', summary: '-'},
    {id: 10, field: 'earnings', name: 'earnings', summary: {operation: 'sum'}, formatter: 'priceFormatter'},
  ]

  updateData = (data) => {
    data["fundType"] = fundTypes[this.state.fundType].value;
    axios.post(`${Config.ROOT_URL}${Config.GET_INVESTMENTS}`, data)
      .then(response => {
        this.props.updateInv(response.data, data)
      })
  }

  handleSubmit = () => {
    this.updateData(this.props.data.request)
  }

  updateFundType = (id) => {
    this.setState({fundType: id});
    const cb = () => {
        this.updateData(this.props.data.request);
    };
    setTimeout(cb, 0);
  }

  render () {

    return (
      <Aux>
        <MFFilter handleSubmit={this.handleSubmit} companies={this.props.companies} request={this.props.data.request}
                  updateData={this.updateData}/>
        <DropDown label="FundType" data={fundTypes} selected={this.state.fundType} updateSelected={this.updateFundType}/>
        {(this.props.data.investments.length > 0) ?
          <Summary data={this.props.data.investments} content={this.dContent}/> : null}
      </Aux>
    )
  }
}

export default Investment